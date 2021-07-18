package org.cloudbus.cloudsim.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;

public class InterVmAffinityFilter implements Filter {

	
	public boolean affinity(Map<String, String> mapVmCandidate, Map<String, String> mapVmsExisting) {
		
		for(HashMap.Entry<String, String> entry: mapVmCandidate.entrySet()) {
			

			if (!((mapVmsExisting.containsKey(entry.getKey())) && (mapVmsExisting.get(entry.getKey()).equalsIgnoreCase(entry.getValue())))) {
				
				return false;
			}
			
		}
		
		return true;
		
	}
	
	
	public boolean antiAffinity(Map<String, String> mapVmCandidate, Map<String, String> mapVmsExisting) {
		
		for(HashMap.Entry<String, String> entry: mapVmCandidate.entrySet()) {
			
			if ((mapVmsExisting.containsKey(entry.getKey())) && (mapVmsExisting.get(entry.getKey()).equalsIgnoreCase(entry.getValue()))) {
				
				return false;
			}
			
		}
		
		return true;
		
	}
	@Override
	public boolean filtering(Vm vm, Host host) {
		if(host.getVmList().size() != 0){
			Map<String, String> vmAllMap = new HashMap<>();
			for(Vm vmOfHost : host.getVmList()) {
				
				vmAllMap.putAll(vmOfHost.getVmAffinityMap());
				//vmAllMap.putAll(vmOfHost.getVmAntiAffinityMap());

			}
			
			//if (affinity(vm.getVmAffinityMap(),vmAllMap) && antiAffinity(vm.getVmAntiAffinityMap(),vmAllMap)) {	
			if (affinity(vm.getVmAffinityMap(),vmAllMap)) {	
			//if (antiAffinity(vm.getVmAntiAffinityMap(),vmAllMap)) {	
				
				return true;
				
			}else {
				return false;
			}
			
			
			
		}else {
			return false; 
		}
			
		
		
		
	}

}
