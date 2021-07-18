package org.cloudbus.cloudsim.power;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;

public class NodeResourcesFitFilter implements Filter {
	
	
	
	public double calculateAvailableMipsInstantiation(Vm vmCandidate, Host host) {
		double totalAllocatedMipsVMs = 0;
		
		for (Vm vm : host.getVmList()) {
			
			totalAllocatedMipsVMs += vm.getMips();	
			
		}
		
		double hostTotalMips =  host.getVmScheduler().getHostTotalMips();
		return (hostTotalMips - totalAllocatedMipsVMs);
	}
	
	/**
	 * Checks if is suitable for vm.
	 * 
	 * @param vm the vm
	 * @return true, if is suitable for vm
	 */
	
	
	
	
	@Override
	public boolean filtering (Vm vm, Host host) {
		
		double availableMipsInstantiation = calculateAvailableMipsInstantiation(vm,host);
		
		return (host.getVmScheduler().getPeCapacity() >= vm.getCurrentRequestedMaxMips()
				&& availableMipsInstantiation >= vm.getCurrentRequestedTotalMips()
				&& host.getRamProvisioner().isSuitableForVm(vm, vm.getCurrentRequestedRam()) && host.getBwProvisioner()
				.isSuitableForVm(vm, vm.getCurrentRequestedBw()));
	
		/*return (host.getVmScheduler().getPeCapacity() >= vm.getCurrentRequestedMaxMips()
				&& host.getVmScheduler().getAvailableMipsInstantiation() >= vm.getCurrentRequestedTotalMips()
				&& host.getRamProvisioner().isSuitableForVm(vm, vm.getCurrentRequestedRam()) && host.getBwProvisioner()
				.isSuitableForVm(vm, vm.getCurrentRequestedBw()));*/
	}

}
