package org.cloudbus.cloudsim;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;
public class Monitoring {
	
	public Vm vm;
	
	public void setVmMonitoring(Vm vm) {
		this.vm = vm;
	}
	
	public Vm getVmMonitoring(){
		return vm;
	}
	
	public double predictCPU(ArrayList<Double> values,int currentCPU, double[] data) {
	    return values.get(0)*data[currentCPU] + values.get(1);
	}
	
	public double predictCPUtoR2(ArrayList<Double> values,double observado) {
	    return values.get(0)*observado + values.get(1);
	}
	
	
	public double R2(ArrayList<Double> expectedCPU, ArrayList<Double> observedCPU) {
		//ArrayList<Double> previstos = new ArrayList<Double>();
		
		/*for(double observado:utilHistory) {
			previstos.add(predictCPUtoR2(values,observado));
		}*/
		
		//convertendo list para array
		//int previstos_size = previstos.size();
    	//int util_size = utilHistory.size();
		
		double[] x = expectedCPU.stream().mapToDouble(i -> i).toArray();
		
		double[] y = observedCPU.stream().mapToDouble(i -> i).toArray();
		
	    double corr = new PearsonsCorrelation().correlation(y, x);
	    
	    
	  /*  for(int i=0;i<x.length;i++) {
	    	int tam_x = x.length;
	    	int tam_y = y.length;
	    	previstos_size = previstos.size();
	    	util_size = utilHistory.size();
	    	double z = x[i];
	    	double g = y[i];
	    	int h=0;
	    }*/
		
		return corr*corr;
	}
	
	
	public ArrayList<Double>  cpuModel(ArrayList<Double> utilHistory) {
		
		ArrayList<Double> utilHistoryX = new ArrayList(utilHistory);
		ArrayList<Double> utilHistoryY = new ArrayList(utilHistory);
		int size = utilHistoryX.size();
		utilHistoryX.remove(size-1);
		utilHistoryY.remove(0);

		
		ArrayList<Double> values = LeastSquaresFitLinear(utilHistoryY,utilHistoryX);	
		
		return values;
	}
	
	 public  ArrayList<Double> LeastSquaresFitLinear(ArrayList<Double> known_y, ArrayList<Double> known_x)
	    {
	        double M, B;
	        if (known_y.size() != known_x.size())
	        {
	        	
	            return null;
	        }

	        int numPoints = known_y.size();

	        double x1, y1, xy, x2, J;

	        x1 = y1 = xy = x2 = 0.0;
	        for (int i = 0; i < numPoints; i++)
	        {
	            x1 = x1 + known_x.get(i);
	            y1 = y1 + known_y.get(i);
	            xy = xy + known_x.get(i) * known_y.get(i);
	            x2 = x2 + known_x.get(i) * known_x.get(i);
	        }

	        M = B = 0;
	        J = ((double)numPoints * x2) - (x1 * x1);

	        if (J != 0.0) {
	            M = (((double)numPoints * xy) - (x1 * y1)) / J;
	            B = ((y1 * x2) - (x1 * xy)) / J;
	        }
	        ArrayList<Double> coefficients = new ArrayList<Double>();
	        coefficients.add(M);
	        coefficients.add(B);
	        
	        return coefficients;
	    }

	 
	 public void ovAdjustment(double expectedCPU, double time) {
		Vm vm = this.getVmMonitoring();
		int priority = vm.getPriority();
		double limite=0;
		if(priority == 1) {
			limite = 999999999;
		}else if (priority == 2){
			limite = 999999999;
		}else if (priority == 3){
			limite = 999999999;
		}else if (priority == 4){
			limite = 999999999;
		}
		
		//expectedMips sem ov
		double expectedMips = vm.getMips()*vm.getOvercommitment()*expectedCPU; //multiplico pelo ov para anular o ov implementado no getMips
		//Mips alocadas para esta VM
		double allocatedMips = vm.getMips()*vm.getOvercommitment();
		
		
		ResCloudlet rcl = vm.getCloudletScheduler().getCloudletExecList().get(0);	
		//realMips com ov
		double realMips = vm.getCloudletScheduler().getTotalCurrentAllocatedMipsForCloudlet2(rcl, time);
		
		if (vm.getHost().getAvailableMips()>=expectedMips) {
			if((expectedMips != realMips) && expectedMips <= allocatedMips) {
				
				double ov = (vm.getMips()*vm.getOvercommitment())/expectedMips;
				
					
				
				if (ov>limite || ov <=0) {
					
					vm.setOvercommitment(limite);
					vm.getCloudletScheduler().setOvercommitment(limite);
				
				}else {
					
					vm.setOvercommitment(Math.floor(ov));
					vm.getCloudletScheduler().setOvercommitment(Math.floor(ov));
				
				}
			}else if(expectedMips > allocatedMips ) {
				vm.setOvercommitment(1);
				vm.getCloudletScheduler().setOvercommitment(1);
				
			}
		}
		
		
		
	 }
	 
	 
	 
	 

}
