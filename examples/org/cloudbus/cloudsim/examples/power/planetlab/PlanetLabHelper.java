package org.cloudbus.cloudsim.examples.power.planetlab;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.CloudletAndMonitoring;
import org.cloudbus.cloudsim.Monitoring;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelNull;
import org.cloudbus.cloudsim.UtilizationModelPlanetLabInMemory;
import org.cloudbus.cloudsim.examples.power.Constants;

/**
 * A helper class for the running examples for the PlanetLab workload.
 * 
 * If you are using any algorithms, policies or workload included in the power package please cite
 * the following paper:
 * 
 * Anton Beloglazov, and Rajkumar Buyya, "Optimal Online Deterministic Algorithms and Adaptive
 * Heuristics for Energy and Performance Efficient Dynamic Consolidation of Virtual Machines in
 * Cloud Data Centers", Concurrency and Computation: Practice and Experience (CCPE), Volume 24,
 * Issue 13, Pages: 1397-1420, John Wiley & Sons, Ltd, New York, USA, 2012
 * 
 * @author Anton Beloglazov
 * @since Jan 5, 2012
 */
public class PlanetLabHelper {

	/**
	 * Creates the cloudlet list planet lab.
	 * 
	 * @param brokerId the broker id
	 * @param inputFolderName the input folder name
	 * @return the list
	 * @throws FileNotFoundException the file not found exception
	 */
	public static CloudletAndMonitoring createCloudletListPlanetLab(int brokerId, String inputFolderName,String expName)
			throws FileNotFoundException {
		List<Cloudlet> listCloudlet = new ArrayList<Cloudlet>();
		List<Monitoring> listMonitoring = new ArrayList<Monitoring>();
		
		

		long fileSize = 300;
		long outputSize = 300;
		UtilizationModel utilizationModelNull = new UtilizationModelNull();
		

		File inputFolder = new File(inputFolderName);
		File[] files = inputFolder.listFiles();

		/*BufferedReader input = null;
		try {
			input = new BufferedReader(new FileReader("C:\\Users\\marce\\OneDrive\\Documentos\\Marcela\\Mestrado_Pesquisa\\python_codes\\VMs.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		for (int i = 0; i < files.length; i++) {
		//for (int i = 0; i < 5; i++) {
			Monitoring monitoring = new Monitoring();
			Cloudlet cloudlet = null;
			
			String [] resources = null;
			/*try {
				resources =  input.readLine().split(", ");
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			try {
				cloudlet = new Cloudlet(
						i,
						cloudletLength(i) *Constants.CLOUDLET_LENGTH,//Constants.CLOUDLET_LENGTH,
						1,//Integer.parseInt(resources[0]),//Constants.CLOUDLET_PES,
						fileSize,
						outputSize,
						files[i].getName(),
						new UtilizationModelPlanetLabInMemory(
								files[i].getAbsolutePath(),
								Constants.SCHEDULING_INTERVAL, (double) i, monitoring,expName), utilizationModelNull, utilizationModelNull,expName);
				
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			listMonitoring.add(monitoring);
			monitoring = null;
			cloudlet.setUserId(brokerId);
			cloudlet.setVmId(i);
			listCloudlet.add(cloudlet);
		}
		CloudletAndMonitoring list= new CloudletAndMonitoring(listCloudlet,listMonitoring);
	
		return list;
	}

	
	public static int cloudletLength(int id) {
		//int [] iobound = {300,400,500,600,700};
		//int [] hibrido = {800,900,1000,1100,1200,1300,1400};
		//int [] cpubound = {1500,1600,1700,1800,1900,2000,2100,2200,2300,2400,2500};
		
		
		//int [] iobound = {250};
		//int [] hibrido = {500};
		//int [] cpubound = {1000};
		
		int [] iobound = {1000};
		int [] hibrido = {2000};
		int [] cpubound = {4000};
		
		//int [] iobound = {150,200,250};
		//int [] hibrido = {400,550,500};
		//int [] cpubound = {900,950,1000};
		
		Random rand = new Random();
		
		//para teste do SLO
		/*
		if (id == 0 || id == 1) {
		//if (id == 0) {
			int size = cpubound.length;
			int index = rand.nextInt(size);
			int cloudletLength = cpubound[index];
			return cloudletLength;
		}else if (id < 5) {
			int size = hibrido.length;
			int index = rand.nextInt(size);
			int cloudletLength = hibrido[index];
			return cloudletLength;
		}else {
			int size = iobound.length;
			int index = rand.nextInt(size);
			int cloudletLength = iobound[index];
			return cloudletLength;
		}//*/
		
		///*
		//para base1
		/*if (id < 55) {
			int size = cpubound.length;
			int index = rand.nextInt(size);
			int cloudletLength = cpubound[index];
			return cloudletLength;
		}else if (id < 160) {
			int size = hibrido.length;
			int index = rand.nextInt(size);
			int cloudletLength = hibrido[index];
			return cloudletLength;
		}else if (id < 438) {
			int size = iobound.length;
			int index = rand.nextInt(size);
			int cloudletLength = iobound[index];
			return cloudletLength;
		}*/
		
		if (id < 55) {
			int size = cpubound.length;
			int index = rand.nextInt(size);
			int cloudletLength = cpubound[index];
			return cloudletLength;
		}else if (id < 244) {
			int size = hibrido.length;
			int index = rand.nextInt(size);
			int cloudletLength = hibrido[index];
			return cloudletLength;
		}else if (id < 795) {
			int size = iobound.length;
			int index = rand.nextInt(size);
			int cloudletLength = iobound[index];
			return cloudletLength;
		}
		
		
		return 0;
		//*/
	}
}
