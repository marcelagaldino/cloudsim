/*
 * Title: CloudSim Toolkit Description: CloudSim (Cloud Simulation) Toolkit for Modeling and
 * Simulation of Clouds Licence: GPL - http://www.gnu.org/copyleft/gpl.html
 * 
 * Copyright (c) 2009-2012, The University of Melbourne, Australia
 */

package org.cloudbus.cloudsim;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.examples.power.planetlab.PlanetLabConstants;

/**
 * CloudletSchedulerDynamicWorkload implements a policy of scheduling performed by a virtual machine
 * assuming that there is just one cloudlet which is working as an online service.
 * 
 * @author Anton Beloglazov
 * @since CloudSim Toolkit 2.0
 */
public class CloudletSchedulerDynamicWorkload extends CloudletSchedulerTimeShared {

	/** The mips. */
	private double mips;

	/** The number of PEs. */
	private int numberOfPes;

	/** The total mips. */
	private double totalMips;

	/** The under allocated mips. */
	private Map<String, Double> underAllocatedMips;

	/** The cache previous time. */
	private double cachePreviousTime;

	/** The cache current requested mips. */
	private List<Double> cacheCurrentRequestedMips;
	
	/** Overcommitment Factor. */
	private double overcommitment;

	/**
	 * Instantiates a new vM scheduler time shared.
	 * 
	 * @param mips the mips
	 * @param numberOfPes the pes number
	 */
	public CloudletSchedulerDynamicWorkload(double mips, int numberOfPes, double overcommitment) {
		super();
		setOvercommitment(overcommitment);
		setMips(mips);
		setNumberOfPes(numberOfPes);
		setTotalMips(getNumberOfPes() * getMips());
		setUnderAllocatedMips(new HashMap<String, Double>());
		setCachePreviousTime(-1);
	}

	public void setOvercommitment(double overcommitment) {
		this.overcommitment = overcommitment;
		
	}
	
	public double getOvercommitment() {
		return overcommitment;
		
	}

	/**
	 * Updates the processing of cloudlets running under management of this scheduler.
	 * 
	 * @param currentTime current simulation time
	 * @param mipsShare array with MIPS share of each Pe available to the scheduler
	 * @return time predicted completion time of the earliest finishing cloudlet, or 0 if there is
	 *         no next events
	 * @pre currentTime >= 0
	 * @post $none
	 */
	@Override
	public double updateVmProcessing(double currentTime, List<Double> mipsShare) {
		setCurrentMipsShare(mipsShare);

		double timeSpan = currentTime - getPreviousTime();
		double nextEvent = Double.MAX_VALUE;
		List<ResCloudlet> cloudletsToFinish = new ArrayList<ResCloudlet>();

		for (ResCloudlet rcl : getCloudletExecList()) {
			rcl.updateCloudletFinishedSoFar((long) (timeSpan
					* getTotalCurrentAllocatedMipsForCloudlet(rcl, getPreviousTime()) * Consts.MILLION));
			
			double x = timeSpan;
			double y = getTotalCurrentAllocatedMipsForCloudlet(rcl, getPreviousTime());
			double z = Consts.MILLION;
			double v = x*y*z;
			//fileTamanhoCloudlet(this.getOvercommitment(),rcl.getRemainingCloudletLength(),currentTime,rcl.getCloudletId());
			
			if (rcl.getRemainingCloudletLength() == 0) { // finished: remove from the list
				cloudletsToFinish.add(rcl);
				continue;
			} else { // not finish: estimate the finish time
				int cid = rcl.getCloudletId();
				double estimatedFinishTime = getEstimatedFinishTime(rcl, currentTime);
				if (estimatedFinishTime - currentTime < CloudSim.getMinTimeBetweenEvents()) {
					estimatedFinishTime = currentTime + CloudSim.getMinTimeBetweenEvents();
				}
				if (estimatedFinishTime < nextEvent) {
					nextEvent = estimatedFinishTime;
				}
			}
		}

		for (ResCloudlet rgl : cloudletsToFinish) {
			getCloudletExecList().remove(rgl);
			cloudletFinish(rgl);
		}

		setPreviousTime(currentTime);

		if (getCloudletExecList().isEmpty()) {
			return 0;
		}

		return nextEvent;
	}

	
	 public void fileTamanhoCloudlet(double ov, long remaining, double currentTime, int cloudletID ){
			
			FileWriter arq = null;
			
			try {
			
				arq = new FileWriter("remaining_experimento_preliminar100_teste.csv", true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(ov+","+cloudletID+","+remaining+","+currentTime+","+"estatico"+"%n");
			
			
			try {
				arq.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	/**
	 * Receives an cloudlet to be executed in the VM managed by this scheduler.
	 * 
	 * @param cl the cl
	 * @return predicted completion time
	 * @pre _gl != null
	 * @post $none
	 */
	@Override
	public double cloudletSubmit(Cloudlet cl) {
		return cloudletSubmit(cl, 0);
	}

	/**
	 * Receives an cloudlet to be executed in the VM managed by this scheduler.
	 * 
	 * @param cl the cl
	 * @param fileTransferTime the file transfer time
	 * @return predicted completion time
	 * @pre _gl != null
	 * @post $none
	 */
	@Override
	public double cloudletSubmit(Cloudlet cl, double fileTransferTime) {
	
		ResCloudlet rcl = new ResCloudlet(cl);
		rcl.setCloudletStatus(Cloudlet.INEXEC);

		for (int i = 0; i < cl.getNumberOfPes(); i++) {
			rcl.setMachineAndPeId(0, i);
		}

		getCloudletExecList().add(rcl);
		return getEstimatedFinishTime(rcl, getPreviousTime());
	}

	/**
	 * Processes a finished cloudlet.
	 * 
	 * @param rcl finished cloudlet
	 * @pre rgl != $null
	 * @post $none
	 */
	@Override
	public void cloudletFinish(ResCloudlet rcl) {
		rcl.setCloudletStatus(Cloudlet.SUCCESS);
		rcl.finalizeCloudlet();
		getCloudletFinishedList().add(rcl);
	}

	/**
	 * Get utilization created by all cloudlets.
	 * 
	 * @param time the time
	 * @return total utilization
	 */
	@Override
	public double getTotalUtilizationOfCpu(double time) {
		double totalUtilization = 0;
		for (ResCloudlet rcl : getCloudletExecList()) {
			totalUtilization += rcl.getCloudlet().getUtilizationOfCpu(time);
		}
		return totalUtilization;
	}

	/**
	 * Gets the current mips.
	 * 
	 * @return the current mips
	 */
	@Override
	public List<Double> getCurrentRequestedMips() {
		if (getCachePreviousTime() == getPreviousTime()) {
			return getCacheCurrentRequestedMips();
		}
		List<Double> currentMips = new ArrayList<Double>();
		double totalMips = getTotalUtilizationOfCpu(getPreviousTime()) * getTotalMips();
		double mipsForPe = totalMips / getNumberOfPes();

		for (int i = 0; i < getNumberOfPes(); i++) {
			currentMips.add(mipsForPe);
		}

		setCachePreviousTime(getPreviousTime());
		setCacheCurrentRequestedMips(currentMips);

		return currentMips;
	}

	/**
	 * Gets the current mips.
	 * 
	 * @param rcl the rcl
	 * @param time the time
	 * @return the current mips
	 */
	@Override
	public double getTotalCurrentRequestedMipsForCloudlet(ResCloudlet rcl, double time) {
		
		return rcl.getCloudlet().getUtilizationOfCpu(time) * getTotalMips();
	}
	
	
	
	
	
	
	public double getTotalCurrentRequestedMipsForCloudlet2(ResCloudlet rcl, double time) {
		double utili =  rcl.getCloudlet().getUtilizationOfCpu2(time);
		double mipstotal = getTotalMips();
		return rcl.getCloudlet().getUtilizationOfCpu2(time) * getTotalMips();
	}

	/**
	 * Gets the total current mips for the clouddlet.
	 * 
	 * @param rcl the rcl
	 * @param mipsShare the mips share
	 * @return the total current mips
	 */
	@Override
	public double getTotalCurrentAvailableMipsForCloudlet(ResCloudlet rcl, List<Double> mipsShare) {
		double totalCurrentMips = 0.0;
		int y = rcl.getNumberOfPes();
		if (mipsShare != null) {
			int neededPEs = rcl.getNumberOfPes();
			for (double mips : mipsShare) {
				totalCurrentMips += mips;
				neededPEs--;
				if (neededPEs <= 0) {
					break;
				}
			}
		}
		
		return totalCurrentMips;
	}

	/**
	 * Gets the current mips.
	 * 
	 * @param rcl the rcl
	 * @param time the time
	 * @return the current mips
	 */
	@Override
	public double getTotalCurrentAllocatedMipsForCloudlet(ResCloudlet rcl, double time) {
		double totalCurrentRequestedMips = getTotalCurrentRequestedMipsForCloudlet(rcl, time);
		double totalCurrentAvailableMips = getTotalCurrentAvailableMipsForCloudlet(rcl, getCurrentMipsShare());
		if (totalCurrentRequestedMips > totalCurrentAvailableMips) {
			return totalCurrentAvailableMips;
		}
		return totalCurrentRequestedMips;
	}
	
	public double getTotalCurrentAllocatedMipsForCloudlet2(ResCloudlet rcl, double time) {
		double totalCurrentRequestedMips = getTotalCurrentRequestedMipsForCloudlet2(rcl, time);
		double totalCurrentAvailableMips = getTotalCurrentAvailableMipsForCloudlet(rcl, getCurrentMipsShare());
		if (totalCurrentRequestedMips > totalCurrentAvailableMips) {
			return totalCurrentAvailableMips;
		}
		return totalCurrentRequestedMips;
	}

	/**
	 * Update under allocated mips for cloudlet.
	 * 
	 * @param rcl the rgl
	 * @param mips the mips
	 */
	public void updateUnderAllocatedMipsForCloudlet(ResCloudlet rcl, double mips) {
		if (getUnderAllocatedMips().containsKey(rcl.getUid())) {
			mips += getUnderAllocatedMips().get(rcl.getUid());
		}
		getUnderAllocatedMips().put(rcl.getUid(), mips);
	}

	/**
	 * Get estimated cloudlet completion time.
	 * 
	 * @param rcl the rcl
	 * @param time the time
	 * @return the estimated finish time
	 */
	public double getEstimatedFinishTime(ResCloudlet rcl, double time) {
		return time
				+ ((rcl.getRemainingCloudletLength()) / getTotalCurrentAllocatedMipsForCloudlet(rcl, time));
	}

	/**
	 * Gets the total current mips.
	 * 
	 * @return the total current mips
	 */
	public int getTotalCurrentMips() {
		int totalCurrentMips = 0;
		for (double mips : getCurrentMipsShare()) {
			totalCurrentMips += mips;
		}
		return totalCurrentMips;
	}

	/**
	 * Sets the total mips.
	 * 
	 * @param mips the new total mips
	 */
	public void setTotalMips(double mips) {
		totalMips = mips;
	}

	/**
	 * Gets the total mips.
	 * 
	 * @return the total mips
	 */
	public double getTotalMips() {
		return totalMips/this.getOvercommitment();
	}

	/**
	 * Sets the pes number.
	 * 
	 * @param pesNumber the new pes number
	 */
	public void setNumberOfPes(int pesNumber) {
		numberOfPes = pesNumber;
	}

	/**
	 * Gets the pes number.
	 * 
	 * @return the pes number
	 */
	public int getNumberOfPes() {
		return numberOfPes;
	}

	/**
	 * Sets the mips.
	 * 
	 * @param mips the new mips
	 */
	public void setMips(double mips) {
		this.mips = mips;
	}

	/**
	 * Gets the mips.
	 * 
	 * @return the mips
	 */
	public double getMips() {
		return mips/getOvercommitment();
	}

	/**
	 * Sets the under allocated mips.
	 * 
	 * @param underAllocatedMips the under allocated mips
	 */
	public void setUnderAllocatedMips(Map<String, Double> underAllocatedMips) {
		this.underAllocatedMips = underAllocatedMips;
	}

	/**
	 * Gets the under allocated mips.
	 * 
	 * @return the under allocated mips
	 */
	public Map<String, Double> getUnderAllocatedMips() {
		return underAllocatedMips;
	}

	/**
	 * Gets the cache previous time.
	 * 
	 * @return the cache previous time
	 */
	protected double getCachePreviousTime() {
		return cachePreviousTime;
	}

	/**
	 * Sets the cache previous time.
	 * 
	 * @param cachePreviousTime the new cache previous time
	 */
	protected void setCachePreviousTime(double cachePreviousTime) {
		this.cachePreviousTime = cachePreviousTime;
	}

	/**
	 * Gets the cache current requested mips.
	 * 
	 * @return the cache current requested mips
	 */
	protected List<Double> getCacheCurrentRequestedMips() {
		return cacheCurrentRequestedMips;
	}

	/**
	 * Sets the cache current requested mips.
	 * 
	 * @param cacheCurrentRequestedMips the new cache current requested mips
	 */
	protected void setCacheCurrentRequestedMips(List<Double> cacheCurrentRequestedMips) {
		this.cacheCurrentRequestedMips = cacheCurrentRequestedMips;
	}

}
