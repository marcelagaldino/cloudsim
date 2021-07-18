package org.cloudbus.cloudsim.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;

public class InterVmAffinityScore implements Score {

	
	@Override
	public Map<PowerHost, Integer> scoring(Vm vm, PowerHost host) {
			
		
		Map<PowerHost, Integer> score = new HashMap<>();
		
		
		if (host.getVmList().size() == 0) {
			score.put(host, 1);
			return (score);
		}
		
		
		//int sumAffinityAntiAffinity = scoreAffinity(vm, host.getVmList()) + scoreAntiAffinity(vm,host.getVmList());
		//int sumAffinityAntiAffinity = scoreAffinity(vm, host.getVmList()) 
		int sumAffinityAntiAffinity = scoreAntiAffinity(vm,host.getVmList());
		if(sumAffinityAntiAffinity > 0 ) {
			
			score.put(host, sumAffinityAntiAffinity);
			return (score);
		}else {
			score.put(host, 1);
			return (score);
		}
		
	}
	
	
	
	
	
	public int scoreAffinity(Vm vm, List<Vm> vmList){
		int weightTotalAffinity = 0;
		int weightAffinity = 0;
		
		for(Vm vmOfHost : vmList) {
			
			int count = 0;
			for(HashMap.Entry<String, ArrayList<String>> entry: vm.getPreferVmAffinityMap().entrySet()) {
				
				count += affinity(entry,vmOfHost.getPreferVmAffinityMap());
				
				if (count >= 1) {
					
					weightAffinity += Integer.parseInt(entry.getValue().get(1));
				}
			}
			
			if (count == vm.getPreferVmAffinityMap().size()) {
				weightTotalAffinity = weightAffinity;
				return weightTotalAffinity;
			
			}
		}
		
		return weightTotalAffinity;
	}
	
	
	public int scoreAntiAffinity(Vm vm, List<Vm> vmList){
		
		int weightAntiAffinity = 0;
	
		int weightTotalAntiAffinity = 0;
		for(Vm vmOfHost : vmList) {
			int count = 0;
			for(HashMap.Entry<String, ArrayList<String>> entry: vm.getPreferVmAffinityMap().entrySet()) {
				
				count += antiAffinity(entry,vmOfHost.getPreferVmAffinityMap());
				
				if (count >= 1) {
					
					weightAntiAffinity += Integer.parseInt(entry.getValue().get(1));
				}
			}
			
			if (count != vm.getPreferVmAffinityMap().size()) {
				weightTotalAntiAffinity = 0;
				return weightTotalAntiAffinity;
			}else {
				weightTotalAntiAffinity = weightAntiAffinity;
			}
				
					
			}
		
		return weightTotalAntiAffinity;
	}
	
	
	//É atribuída pontuação ao nó se no mapa de afinidade da vm já alocada no nó, há o mesmo par de chave-valor que o mapa de afinidade da VM candidata à alocação
	public  int affinity(Map.Entry<String, ArrayList<String>> mapVmCandidate, Map<String, ArrayList<String>> mapVmsExisting) {
		int count = 0;
		//for(HashMap.Entry<String, ArrayList<String>> entry: mapVmCandidate.entrySet()) {
			
			if ((mapVmsExisting.containsKey(mapVmCandidate.getKey()) && (mapVmsExisting.get(mapVmCandidate.getKey()).get(0) == mapVmCandidate.getValue().get(0)))) {
				//weight += Integer.parseInt(mapVmCandidate.getValue().get(1));
				count = 1;
			}
			
		//}
		
		return count;
		
	}
	
	//É atribuída pontuação ao nó se no mapa de *afinidade* da vm já alocada no nó não tem o mesmo par de chave-valor que o mapa de *antiafinidade* da VM candidata à alocação
	public static int antiAffinity(Map.Entry<String, ArrayList<String>> mapVmCandidate, Map<String, ArrayList<String>> mapVmsExisting) {
		int count = 0;
		
		//for(HashMap.Entry<String, ArrayList<String>> entry: mapVmCandidate.entrySet()) {
			
			if (!(mapVmsExisting.containsKey(mapVmCandidate.getKey()) && (mapVmsExisting.get(mapVmCandidate.getKey()).get(0) == mapVmCandidate.getValue().get(0)))) {
				
				//weight += Integer.parseInt(mapVmCandidate.getValue().get(1));
				count = 1;
			}
			
		//}
		
		return count;
		
	}


}
