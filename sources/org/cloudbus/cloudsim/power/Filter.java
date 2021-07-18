package org.cloudbus.cloudsim.power;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;

public interface Filter {

	boolean filtering (Vm vm, Host host);
	
}
