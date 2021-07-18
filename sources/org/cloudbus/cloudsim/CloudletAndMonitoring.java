package org.cloudbus.cloudsim;

import java.util.ArrayList;
import java.util.List;

public class CloudletAndMonitoring {
	private List<Cloudlet> listCloudlet = new ArrayList<Cloudlet>();
	private List<Monitoring> listMonitoring = new ArrayList<Monitoring>();
	
	public CloudletAndMonitoring(List<Cloudlet> listCloudlet,List<Monitoring> listMonitoring) {
		this.listCloudlet = listCloudlet;
		this.listMonitoring = listMonitoring;
	}

	public List<Cloudlet> getListCloudlet() {
		return listCloudlet;
	}

	public List<Monitoring> getListMonitoring() {
		return listMonitoring;
	}

	
	
}
