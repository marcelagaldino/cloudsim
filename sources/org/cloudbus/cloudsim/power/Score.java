package org.cloudbus.cloudsim.power;


import java.util.Map;

import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Vm;


 
public interface Score {
	
	Map<PowerHost, Integer> scoring (Vm vm, PowerHost host);
}
