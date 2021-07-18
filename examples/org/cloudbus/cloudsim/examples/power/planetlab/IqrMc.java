package org.cloudbus.cloudsim.examples.power.planetlab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



/**
 * A simulation of a heterogeneous power aware data center that applies the Inter Quartile Range
 * (IQR) VM allocation policy and Maximum Correlation (MC) VM selection policy.
 * 
 * This example uses a real PlanetLab workload: 20110303.
 * 
 * The remaining configuration parameters are in the Constants and PlanetLabConstants classes.
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
public class IqrMc {

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		
		List<String> expNamelist = new ArrayList<>();
		
		expNamelist.add("exp1");
		expNamelist.add("exp2");
		expNamelist.add("exp3");
		expNamelist.add("exp4");
		expNamelist.add("exp5");
		expNamelist.add("exp6");
		expNamelist.add("exp7");
		expNamelist.add("exp8");
		expNamelist.add("exp9");
		expNamelist.add("exp10");
		expNamelist.add("exp11");
		expNamelist.add("exp12");
		expNamelist.add("exp13");
		expNamelist.add("exp14");
		expNamelist.add("exp15");
		expNamelist.add("exp16");
		expNamelist.add("exp17");
		expNamelist.add("exp18");
		expNamelist.add("exp19");
		expNamelist.add("exp20");
		expNamelist.add("exp21");
		expNamelist.add("exp22");
		expNamelist.add("exp23");
		expNamelist.add("exp24");
		expNamelist.add("exp25");
		expNamelist.add("exp26");
		expNamelist.add("exp27");
		expNamelist.add("exp28");
		expNamelist.add("exp29");
		expNamelist.add("exp30");
		expNamelist.add("exp31");
		expNamelist.add("exp32");
		expNamelist.add("exp33");
		expNamelist.add("exp34");
		expNamelist.add("exp35");
		expNamelist.add("exp36");
		expNamelist.add("exp37");
		expNamelist.add("exp38");
		expNamelist.add("exp39");
		expNamelist.add("exp40");
		expNamelist.add("exp41");
		expNamelist.add("exp42");
		expNamelist.add("exp43");
		expNamelist.add("exp44");
		expNamelist.add("exp45");
		expNamelist.add("exp46");
		expNamelist.add("exp47");
		expNamelist.add("exp48");
		expNamelist.add("exp49");
		expNamelist.add("exp50");
		expNamelist.add("exp51");
		expNamelist.add("exp52");
		expNamelist.add("exp53");
		expNamelist.add("exp54");
		expNamelist.add("exp55");
		expNamelist.add("exp56");
		expNamelist.add("exp57");
		expNamelist.add("exp58");
		expNamelist.add("exp59");
		expNamelist.add("exp60");
		expNamelist.add("exp61");
		expNamelist.add("exp62");
		expNamelist.add("exp63");
		expNamelist.add("exp64");
		expNamelist.add("exp65");
		expNamelist.add("exp66");
		expNamelist.add("exp67");
		expNamelist.add("exp68");
		expNamelist.add("exp69");
		expNamelist.add("exp70");
		expNamelist.add("exp71");
		expNamelist.add("exp72");
		expNamelist.add("exp73");
		expNamelist.add("exp74");
		expNamelist.add("exp75");
		expNamelist.add("exp76");
		expNamelist.add("exp77");
		expNamelist.add("exp78");
		expNamelist.add("exp79");
		expNamelist.add("exp80");
		for (String exp : expNamelist) {
			
			boolean enableOutput = false;//true;
			boolean outputToFile = false;
			String inputFolder = IqrMc.class.getClassLoader().getResource("workload/planetlab").getPath();
			String outputFolder = "output";
			String workload = "basefinaldefesa2";//"total_with_low";//"test_consolidation";//"teste_SLO2";//"prioridades_teste";//"first_subset";//"prioridades_teste";//"first_subset_1";//"teste_v3_5_tasks";//"outputpart6_2";//"traces02_final_100";//"100_100";//"traces02_final";//"20110303"; //"100_4"; // PlanetLab workload
			String vmAllocationPolicy = "iqr"; // Inter Quartile Range (IQR) VM allocation policy
			String vmSelectionPolicy = "mc"; // Maximum Correlation (MC) VM selection policy
			String parameter = "1.5"; // the safety parameter of the IQR policy
			String fileInitVm="/home/marcela/cloudsim-v2/cloudsim-v2/timeOfInitiationVMsdefesa"+exp+".csv";
			//String fileInitVm=exp;
			String filePriority="/home/marcela/cloudsim-v2/cloudsim-v2/priority_SLO"+exp+".csv";
			String fileError="/home/marcela/cloudsim-v2/cloudsim-v2/erro10choice"+exp+".csv";
			//String fileError=exp;
			String expName=exp;
			new PlanetLabRunner(
					enableOutput,
					outputToFile,
					inputFolder,
					outputFolder,
					workload,
					vmAllocationPolicy,
					vmSelectionPolicy,
					parameter,
					fileInitVm,
					filePriority,
					fileError,
					expName);
			}
		
		
		
		
	}
	
	

}
