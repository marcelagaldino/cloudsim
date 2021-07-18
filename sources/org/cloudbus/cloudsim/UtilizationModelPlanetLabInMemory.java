package org.cloudbus.cloudsim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.cloudbus.cloudsim.enums.SLO;
import org.cloudbus.cloudsim.enums.ServiceType;
import org.cloudbus.cloudsim.examples.power.planetlab.PlanetLabConstants;

//import com.sun.tools.javac.util.ArrayUtils;

/**
 * The Class UtilizationModelPlanetLab.
 */
public class UtilizationModelPlanetLabInMemory implements UtilizationModel {
	
	/** The scheduling interval. */
	private double schedulingInterval;

	/** The data (5 min * 288 = 24 hours). */
	private double[] data; 
	private double[] data3; 
	
	
	private double indice;
	
	private ArrayList<Double> utilHistory = new ArrayList<Double>();
	private ArrayList<Double> expectedCPU = new ArrayList<Double>();
	private ArrayList<Double> observedCPU = new ArrayList<Double>();
	
	private double timeMonitoring = 0;
	private double timeSubmission = -1;
	
	private Monitoring monitoring;

	private String expName;
	
	/**
	 * Instantiates a new utilization model PlanetLab.
	 * 
	 * @param inputPath the input path
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public UtilizationModelPlanetLabInMemory(String inputPath, double schedulingInterval, double indice, Monitoring monitoring, String expName)
			throws NumberFormatException,
			IOException {
		
		setExpName(expName);
		LineNumberReader lnr = new LineNumberReader(new FileReader(inputPath));
		lnr.skip(Long.MAX_VALUE);
		int len = lnr.getLineNumber();
		data = new double[len*75];//5761
		lnr.close();
		this.indice = indice;
		this.monitoring = monitoring;
		setSchedulingInterval(schedulingInterval);
		BufferedReader input = new BufferedReader(new FileReader(inputPath));
		BufferedReader input2 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input3 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input4 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input5 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input6 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input7 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input8 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input9 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input10 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input11 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input12 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input13 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input14 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input15 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input16 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input17 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input18 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input19 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input20 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input21 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input22 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input23 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input24 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input25 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input26 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input27 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input28 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input29 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input30 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input31 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input32 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input33 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input34 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input35 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input36 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input37 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input38 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input39 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input40 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input41 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input42 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input43 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input44 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input45 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input46 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input47 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input48 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input49 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input50 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input51 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input52 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input53 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input54 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input55 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input56 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input57 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input58 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input59 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input60 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input61 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input62 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input63 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input64 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input65 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input66 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input67 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input68 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input69 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input70 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input71 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input72 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input73 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input74 = new BufferedReader(new FileReader(inputPath));
		BufferedReader input75 = new BufferedReader(new FileReader(inputPath));
		
		int n = data.length;
		
		
		 
		 double x = 0;
		//for (int i = 0; i < 288; i++) {
		int j = 0;
		String line = input.readLine();
		while ((line) != null) {	
			data[j] = Double.valueOf(line) / 100.0;
			x = x + (data[j]*100);
			line = input.readLine();
			j++;
		}
		
		
		int denominador = j;
		
		double media = x/denominador; 
		

		
		double somatorio = 0;
		String line2 = input2.readLine();
		while ((line2) != null) {
			data[j] = Double.valueOf(line2) / 100.0;
			somatorio += Math.pow(((data[j]*100) - media),2);
			line2 = input2.readLine();
			j++;
		}
		
		double desvio = Math.sqrt(somatorio/denominador);
		

		
		String line3 = input3.readLine();
		while ((line3) != null) {
			data[j] = Double.valueOf(line3) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line3 = input3.readLine();
			j++;
		}
		
		String line4 = input4.readLine();
		while ((line4) != null) {
			data[j] = Double.valueOf(line4) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line4 = input4.readLine();
			j++;
		}
		
		String line5 = input5.readLine();
		while ((line5) != null) {
			data[j] = Double.valueOf(line5) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line5 = input5.readLine();
			j++;
		}
		
		
		
		String line6 = input6.readLine();
		while ((line6) != null) {
			data[j] = Double.valueOf(line6) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line6 = input6.readLine();
			j++;
		}
		
		String line7 = input7.readLine();
		while ((line7) != null) {
			data[j] = Double.valueOf(line7) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line7 = input7.readLine();
			j++;
		}
		
		String line8 = input8.readLine();
		while ((line8) != null) {
			data[j] = Double.valueOf(line8) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line8 = input8.readLine();
			j++;
		}
		
		
		String line9 = input9.readLine();
		while ((line9) != null) {
			data[j] = Double.valueOf(line9) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line9 = input9.readLine();
			j++;
		}
		

		String line10 = input10.readLine();
		while ((line10) != null) {
			data[j] = Double.valueOf(line10) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line10 = input10.readLine();
			j++;
		}
		
		String line11 = input11.readLine();
		while ((line11) != null) {
			data[j] = Double.valueOf(line11) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line11 = input11.readLine();
			j++;
		}
		
		String line12 = input12.readLine();
		while ((line12) != null) {
			data[j] = Double.valueOf(line12) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line12 = input12.readLine();
			j++;
		}
		
		String line13 = input13.readLine();
		while ((line13) != null) {
			data[j] = Double.valueOf(line13) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line13 = input13.readLine();
			j++;
		}
		
		
		String line14 = input14.readLine();
		while ((line14) != null) {
			data[j] = Double.valueOf(line14) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line14 = input14.readLine();
			j++;
		}
		
		String line15 = input15.readLine();
		while ((line15) != null) {
			data[j] = Double.valueOf(line15) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line15 = input15.readLine();
			j++;
		}
		
		String line16 = input16.readLine();
		while ((line16) != null) {
			data[j] = Double.valueOf(line16) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line16 = input16.readLine();
			j++;
		}
		
		String line17 = input17.readLine();
		while ((line17) != null) {
			data[j] = Double.valueOf(line17) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line17 = input17.readLine();
			j++;
		}
		
		String line18 = input18.readLine();
		while ((line18) != null) {
			data[j] = Double.valueOf(line18) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line18 = input18.readLine();
			j++;
		}
		
		String line19 = input19.readLine();
		while ((line19) != null) {
			data[j] = Double.valueOf(line19) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line19 = input19.readLine();
			j++;
		}
		
		String line20 = input20.readLine();
		while ((line20) != null) {
			data[j] = Double.valueOf(line20) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line20 = input20.readLine();
			j++;
		}
		
		String line21 = input21.readLine();
		while ((line21) != null) {
			data[j] = Double.valueOf(line21) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line21 = input21.readLine();
			j++;
		}
		
		String line22 = input22.readLine();
		while ((line22) != null) {
			data[j] = Double.valueOf(line22) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line22 = input22.readLine();
			j++;
		}
		
		
		String line23 = input23.readLine();
		while ((line23) != null) {
			data[j] = Double.valueOf(line23) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line23 = input23.readLine();
			j++;
		}
		
		
		String line24 = input24.readLine();
		while ((line24) != null) {
			data[j] = Double.valueOf(line24) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line24 = input24.readLine();
			j++;
		}
		
		
		String line25 = input25.readLine();
		while ((line25) != null) {
			data[j] = Double.valueOf(line25) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line25 = input25.readLine();
			j++;
		}
		
		String line26 = input26.readLine();
		while ((line26) != null) {
			data[j] = Double.valueOf(line26) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line26 = input26.readLine();
			j++;
		}
		
		String line27 = input27.readLine();
		while ((line27) != null) {
			data[j] = Double.valueOf(line27) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line27 = input27.readLine();
			j++;
		}
		
		String line28 = input28.readLine();
		while ((line28) != null) {
			data[j] = Double.valueOf(line28) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line28 = input28.readLine();
			j++;
		}
		
		String line29 = input29.readLine();
		while ((line29) != null) {
			data[j] = Double.valueOf(line29) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line29 = input29.readLine();
			j++;
		}
		
		String line30 = input30.readLine();
		while ((line30) != null) {
			data[j] = Double.valueOf(line30) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line30 = input30.readLine();
			j++;
		}
		
		String line31 = input31.readLine();
		while ((line31) != null) {
			data[j] = Double.valueOf(line31) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line31 = input31.readLine();
			j++;
		}
		
		String line32 = input32.readLine();
		while ((line32) != null) {
			data[j] = Double.valueOf(line32) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line32 = input32.readLine();
			j++;
		}
		
		String line33 = input33.readLine();
		while ((line33) != null) {
			data[j] = Double.valueOf(line33) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line33 = input33.readLine();
			j++;
		}
		
		
		String line34 = input34.readLine();
		while ((line34) != null) {
			data[j] = Double.valueOf(line34) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line34 = input34.readLine();
			j++;
		}
		
		String line35 = input35.readLine();
		while ((line35) != null) {
			data[j] = Double.valueOf(line35) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line35 = input35.readLine();
			j++;
		}
		
		
		String line36 = input36.readLine();
		while ((line36) != null) {
			data[j] = Double.valueOf(line36) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line36 = input36.readLine();
			j++;
		}
		
		String line37 = input37.readLine();
		while ((line37) != null) {
			data[j] = Double.valueOf(line37) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line37 = input37.readLine();
			j++;
		}
		
		
		String line38 = input38.readLine();
		while ((line38) != null) {
			data[j] = Double.valueOf(line38) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line38 = input38.readLine();
			j++;
		}
		
		String line39 = input39.readLine();
		while ((line39) != null) {
			data[j] = Double.valueOf(line39) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line39 = input39.readLine();
			j++;
		}
		
		String line40 = input40.readLine();
		while ((line40) != null) {
			data[j] = Double.valueOf(line40) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line40 = input40.readLine();
			j++;
		}
		
		String line41 = input41.readLine();
		while ((line41) != null) {
			data[j] = Double.valueOf(line41) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line41 = input41.readLine();
			j++;
		}
		
		String line42 = input42.readLine();
		while ((line42) != null) {
			data[j] = Double.valueOf(line42) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line42 = input42.readLine();
			j++;
		}
		
		String line43 = input43.readLine();
		while ((line43) != null) {
			data[j] = Double.valueOf(line43) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line43 = input43.readLine();
			j++;
		}
		
		
		String line44 = input44.readLine();
		while ((line44) != null) {
			data[j] = Double.valueOf(line44) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line44 = input44.readLine();
			j++;
		}
		
		String line45 = input45.readLine();
		while ((line45) != null) {
			data[j] = Double.valueOf(line45) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line45 = input45.readLine();
			j++;
		}
		
		String line46 = input46.readLine();
		while ((line46) != null) {
			data[j] = Double.valueOf(line46) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line46 = input46.readLine();
			j++;
		}
		
		String line47 = input47.readLine();
		while ((line47) != null) {
			data[j] = Double.valueOf(line47) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line47 = input47.readLine();
			j++;
		}
		
		String line48 = input48.readLine();
		while ((line48) != null) {
			data[j] = Double.valueOf(line48) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line48 = input48.readLine();
			j++;
		}
		
		String line49 = input49.readLine();
		while ((line49) != null) {
			data[j] = Double.valueOf(line49) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line49 = input49.readLine();
			j++;
		}
		
		String line50 = input50.readLine();
		while ((line50) != null) {
			data[j] = Double.valueOf(line50) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line50 = input50.readLine();
			j++;
		}
		

		String line51 = input51.readLine();
		while ((line51) != null) {
			data[j] = Double.valueOf(line51) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line51 = input51.readLine();
			j++;
		}
		

		String line52 = input52.readLine();
		while ((line52) != null) {
			data[j] = Double.valueOf(line52) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line52 = input52.readLine();
			j++;
		}
		

		String line53 = input53.readLine();
		while ((line53) != null) {
			data[j] = Double.valueOf(line53) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line53 = input53.readLine();
			j++;
		}
		

		String line54 = input54.readLine();
		while ((line54) != null) {
			data[j] = Double.valueOf(line54) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line54 = input54.readLine();
			j++;
		}
		

		String line55 = input55.readLine();
		while ((line55) != null) {
			data[j] = Double.valueOf(line55) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line55 = input55.readLine();
			j++;
		}
		

		String line56 = input56.readLine();
		while ((line56) != null) {
			data[j] = Double.valueOf(line56) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line56 = input56.readLine();
			j++;
		}
		

		String line57 = input57.readLine();
		while ((line57) != null) {
			data[j] = Double.valueOf(line57) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line57 = input57.readLine();
			j++;
		}
		
		String line58 = input58.readLine();
		while ((line58) != null) {
			data[j] = Double.valueOf(line58) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line58 = input58.readLine();
			j++;
		}
		
		String line59 = input59.readLine();
		while ((line59) != null) {
			data[j] = Double.valueOf(line59) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line59 = input59.readLine();
			j++;
		}
		
		String line60 = input60.readLine();
		while ((line60) != null) {
			data[j] = Double.valueOf(line60) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line60 = input60.readLine();
			j++;
		}
		
		String line61 = input61.readLine();
		while ((line61) != null) {
			data[j] = Double.valueOf(line61) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line61 = input61.readLine();
			j++;
		}
		
		String line62 = input62.readLine();
		while ((line62) != null) {
			data[j] = Double.valueOf(line62) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line62 = input62.readLine();
			j++;
		}
		
		String line63 = input63.readLine();
		while ((line63) != null) {
			data[j] = Double.valueOf(line63) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line63 = input63.readLine();
			j++;
		}
		
		String line64 = input64.readLine();
		while ((line64) != null) {
			data[j] = Double.valueOf(line64) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line64 = input64.readLine();
			j++;
		}
		
		String line65 = input65.readLine();
		while ((line65) != null) {
			data[j] = Double.valueOf(line65) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line65 = input65.readLine();
			j++;
		}
		
		String line66 = input66.readLine();
		while ((line66) != null) {
			data[j] = Double.valueOf(line66) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line66 = input66.readLine();
			j++;
		}
		
		String line67 = input67.readLine();
		while ((line67) != null) {
			data[j] = Double.valueOf(line67) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line67 = input67.readLine();
			j++;
		}
		
		String line68 = input68.readLine();
		while ((line68) != null) {
			data[j] = Double.valueOf(line68) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line68 = input68.readLine();
			j++;
		}
		
		String line69 = input69.readLine();
		while ((line69) != null) {
			data[j] = Double.valueOf(line69) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line69 = input69.readLine();
			j++;
		}
		
		String line70 = input70.readLine();
		while ((line70) != null) {
			data[j] = Double.valueOf(line70) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line70 = input70.readLine();
			j++;
		}
		
		String line71 = input71.readLine();
		while ((line71) != null) {
			data[j] = Double.valueOf(line71) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line71 = input71.readLine();
			j++;
		}
		
		String line72 = input72.readLine();
		while ((line72) != null) {
			data[j] = Double.valueOf(line72) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line72 = input72.readLine();
			j++;
		}
		
		String line73 = input73.readLine();
		while ((line73) != null) {
			data[j] = Double.valueOf(line73) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line73 = input73.readLine();
			j++;
		}
		
		String line74 = input74.readLine();
		while ((line74) != null) {
			data[j] = Double.valueOf(line74) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line74 = input74.readLine();
			j++;
		}
		
		String line75 = input75.readLine();
		while ((line75) != null) {
			data[j] = Double.valueOf(line75) / 100.0;
			//somatorio += Math.pow(((data[i]*100) - media),2);
			line75 = input75.readLine();
			j++;
		}
		
		

	    double[] data2 = new double[len*75*3];
	    int h =0;
		for (int u=0;u<data2.length-1;u++) {
			
			data2[u] = data[h];
			h++;
			if(h == (data.length-1)) {
				h=0;
			}
		}
		
		data3 = data2;
		
		//data = data2;
		
		
		data[n - 1] = data[n - 2];
		data3[n - 1] = data3[n - 2];
		input.close();
		input2.close();
		input3.close();
		input4.close();
		input5.close();
		input6.close();
		input7.close();
		input8.close();
		input9.close();
		input10.close();
		input11.close();
		input12.close();
		input13.close();
		input14.close();
		input15.close();
		input16.close();
		input17.close();
		input18.close();
		input19.close();
		input20.close();
		input21.close();
		input22.close();
		input23.close();
		input24.close();
		input25.close();
		input26.close();
		input27.close();
		input28.close();
		input29.close();
		input30.close();
		input31.close();
		input32.close();
		input33.close();
		input34.close();
		input35.close();
		input36.close();
		input37.close();
		input38.close();
		input39.close();
		input40.close();
		input41.close();
		input42.close();
		input43.close();
		input44.close();
		input45.close();
		input46.close();
		input47.close();
		input48.close();
		input49.close();
		input50.close();
		input51.close();
		input52.close();
		input53.close();
		input54.close();
		input55.close();
		input56.close();
		input57.close();
		input58.close();
		input59.close();
		input60.close();
		input61.close();
		input62.close();
		input63.close();
		input64.close();
		input65.close();
		input66.close();
		input67.close();
		input68.close();
		input69.close();
		input70.close();
		input71.close();
		input72.close();
		input73.close();
		input74.close();
		input75.close();
		
	}
	//metodo original
	/*public UtilizationModelPlanetLabInMemory(String inputPath, double schedulingInterval)
			throws NumberFormatException,
			IOException {
		data = new double[289];
		setSchedulingInterval(schedulingInterval);
		BufferedReader input = new BufferedReader(new FileReader(inputPath));
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			data[i] = Integer.valueOf(input.readLine()) / 100.0;
		}
		data[n - 1] = data[n - 2];
		input.close();
	}*/

	
	private void setExpName (String expName) {
		this.expName = expName;
	}
	
	public String  getExpName () {
		return expName;
	}
	
  public void carga(double time, double cpu, double expectedCPU, double id, double coefRegression1, double coefRegression2){
		
		FileWriter arq = null;
		
		try {
		
			arq = new FileWriter("dados_observados.csv", true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int indice = (int) id;
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.printf(indice+","+time+","+cpu+","+expectedCPU+","+coefRegression1+","+coefRegression2+"%n");
		
		
		try {
			arq.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Instantiates a new utilization model PlanetLab with variable data samples.
	 * 
	 * @param inputPath the input path
	 * @param dataSamples number of samples in the file
	 * @throws NumberFormatException the number format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public UtilizationModelPlanetLabInMemory(String inputPath, double schedulingInterval, int dataSamples)
			throws NumberFormatException,
			IOException {
		setSchedulingInterval(schedulingInterval);
		data = new double[dataSamples];
		BufferedReader input = new BufferedReader(new FileReader(inputPath));
		int n = data.length;
		for (int i = 0; i < n - 1; i++) {
			data[i] = Integer.valueOf(input.readLine()) / 100.0;
		}
		data[n - 1] = data[n - 2];
		input.close();
	}

	
	/*
	 * (non-Javadoc)
	 * @see cloudsim.power.UtilizationModel#getUtilization(double)
	 */
	@Override
	public double getUtilization(double time) {
		
		/*if(timeSubmission ==-1 ) {
			timeSubmission = time;
		}
		
		double timeCalculated = time - timeSubmission;*/
		if (time% getSchedulingInterval() == 0) {
			return Math.min((data3[(int) time / (int) getSchedulingInterval()]*monitoring.getVmMonitoring().getCloudletScheduler().getOvercommitment()),1);
		}
		
		Vm vm  = monitoring.getVmMonitoring();
		
		
		double cloudletID = indice;
		int time1 = (int) Math.floor(time / getSchedulingInterval());
		int time2 = (int) Math.ceil(time / getSchedulingInterval());
		
		
		
		
		double utilization1 = Math.min((data3[time1]*vm.getCloudletScheduler().getOvercommitment()),1);
		double utilization2 = Math.min((data3[time2]*vm.getCloudletScheduler().getOvercommitment()),1);
		double utilization3 = data3[time1];
		//double utilization4 = data[time2];
		
        
		
		if(timeMonitoring != time) {
			violation(time, utilization1, utilization3, cloudletID,vm);
		}
			
		if(utilHistory.size()>=18 && timeMonitoring != time) {
			
			if (!verifyAllEqualUsingFrequency(utilHistory)) {
				ArrayList<Double> values = monitoring.cpuModel(utilHistory);
				//ArrayList<Double> expectedCPU = new ArrayList<Double>();
				//ArrayList<Double> observedCPU = new ArrayList<Double>();
				
				double coefRegression1 = values.get(0);
				double coefRegression2 = values.get(1);
				
				double currentExpectedCPU = monitoring.predictCPU(values, time1, data3);
				//o ultimo valor de cpu eh passado para poder prever o valor futuro a partir do valor atual.
			    expectedCPU.add(currentExpectedCPU);
			    observedCPU.add(utilization3);
				//int tam = utilHistory.size();
			    //carga(time, utilization3, currentExpectedCPU, cloudletID, coefRegression1, coefRegression2);
				int priority = vm.getPriority();
				if (priority ==1) {
					if(expectedCPU.size()>10) {
						double R2 = monitoring.R2(expectedCPU,observedCPU);
						if(R2 > 0.99) {
							monitoring.ovAdjustment(expectedCPU.get(expectedCPU.size()-1),time);
						}
					}
				}else if(priority == 2){
					if(expectedCPU.size()>10) {
						double R2 = monitoring.R2(expectedCPU,observedCPU);
						if(R2 > 0.99) {
							monitoring.ovAdjustment(expectedCPU.get(expectedCPU.size()-1),time);
						}
						
					}
				}else if(priority == 3) {
					if(expectedCPU.size()>10) {
						double R2 = monitoring.R2(expectedCPU,observedCPU);
						
						if(R2 > 0.95) {
							monitoring.ovAdjustment(expectedCPU.get(expectedCPU.size()-1),time);
						}
						
					}
				}else if(priority == 4) {
					if(expectedCPU.size()>10) {
						double R2 = monitoring.R2(expectedCPU,observedCPU);
						if(R2 > 0.85) {
							monitoring.ovAdjustment(expectedCPU.get(expectedCPU.size()-1),time);
						}
						
					}
				}
				
				
				
				
			}
			
			
		} 
		
		if(timeMonitoring != time) {
			timeMonitoring = time;
			utilHistory.add(data3[time1]);
		}
		
		
		
		//fileDynamicOV(monitoring.getVmMonitoring().getId(),time,monitoring.getVmMonitoring().getOvercommitment(), vm.getHost().getAvailableMips(),vm.getHost().getId());
		
		double delta = (utilization2 - utilization1) / ((time2 - time1) * getSchedulingInterval());
		double utilization = utilization1 + delta * (time - time1 * getSchedulingInterval());
		return utilization;

	}
	
	
	
	
	public void fileDynamicOV(int vmid, double time, double ov, double avalableMipsHost, int idhost ){
		
		FileWriter arq = null;
		
		try {
		
			arq = new FileWriter("dynamic_ov.csv", true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PrintWriter gravarArq = new PrintWriter(arq);
		gravarArq.printf(time+","+vmid+","+ov+","+avalableMipsHost+","+idhost+"%n");
		
		
		try {
			arq.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean verifyAllEqualUsingFrequency(ArrayList<Double> list) {
	    return list.isEmpty() || Collections.frequency(list, list.get(0)) == list.size();
	}
	
public double getUtilization2(double time) {
		
		
	
		if (time % getSchedulingInterval() == 0) {
			return Math.min((data3[(int) time / (int) getSchedulingInterval()]*monitoring.getVmMonitoring().getCloudletScheduler().getOvercommitment()),1);
		}
		
		double cloudletID = indice;
		
		int time1 = (int) Math.floor(time / getSchedulingInterval());
		int time2 = (int) Math.ceil(time / getSchedulingInterval());
		
		
		double utilization1 = Math.min((data3[time1]*monitoring.getVmMonitoring().getCloudletScheduler().getOvercommitment()),1);
		double utilization2 = Math.min((data3[time2]*monitoring.getVmMonitoring().getCloudletScheduler().getOvercommitment()),1);
		
	
		
		double delta = (utilization2 - utilization1) / ((time2 - time1) * getSchedulingInterval());
		double utilization = utilization1 + delta * (time - time1 * getSchedulingInterval());
		return utilization;

	}

public void violation(double time, double utilizationWithOv, double utilizationWithoutOv, double id, Vm vm){
	

	
	int indice = (int) id;
	double mips = monitoring.getVmMonitoring().getMips();
	double ov = monitoring.getVmMonitoring().getCloudletScheduler().getOvercommitment();
	
	double withOv = mips*utilizationWithOv;
	double withoutOv = (mips*ov)*utilizationWithoutOv;
	

	SLO slo = vm.getSLO();
	ServiceType servicetype=vm.getServiceType();
	
	double difference = Math.round(withoutOv) - Math.round(withOv);
	if(difference > 0) {
		double percentage=(difference/withoutOv)*100;
		
		if(servicetype == ServiceType.CPU_BOUND && (slo == SLO.HIGH_SLO && percentage>1 || slo == SLO.MEDIUM_SLO && percentage>5 || slo == SLO.LOW_SLO && percentage>10)) {
			FileWriter arq = null;
			
			try {
			
				arq = new FileWriter("violation"+this.getExpName()+".csv", true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(this.getExpName()+","+indice+","+time+","+difference+","+servicetype+","+slo+","+ov+"%n");
			
			try {
				arq.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(servicetype == ServiceType.HYBRID && (slo == SLO.HIGH_SLO && percentage>10 || slo == SLO.MEDIUM_SLO && percentage>15 || slo == SLO.LOW_SLO && percentage>20)) {
			FileWriter arq = null;
			
			try {
			
				arq = new FileWriter("violation"+this.getExpName()+".csv", true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(this.getExpName()+","+indice+","+time+","+difference+","+servicetype+","+slo+","+ov+"%n");
			
			try {
				arq.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(servicetype == ServiceType.IO_BOUND && (slo == SLO.HIGH_SLO && percentage>20 || slo == SLO.MEDIUM_SLO && percentage>25 || slo == SLO.LOW_SLO && percentage>30)) {
			FileWriter arq = null;
			
			try {
			
				arq = new FileWriter("violation"+this.getExpName()+".csv", true);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf(this.getExpName()+","+indice+","+time+","+difference+","+servicetype+","+slo+","+ov+"%n");
			
			try {
				arq.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
		
	
	
	
	
	
	/* try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
}

	/*
	public double predictCPU(ArrayList<Double> values,int currentCPU) {
	    return values.get(0)*data[currentCPU] + values.get(1);
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

	        if (J != 0.0)
	        {
	            M = (((double)numPoints * xy) - (x1 * y1)) / J;
	            B = ((y1 * x2) - (x1 * xy)) / J;
	        }
	        
	        ArrayList<Double> coefficients = new ArrayList<Double>();
	        coefficients.add(M);
	        coefficients.add(B);
	        
	        return coefficients;
	    }
*/
	/**
	 * Sets the scheduling interval.
	 * 
	 * @param schedulingInterval the new scheduling interval
	 */
	public void setSchedulingInterval(double schedulingInterval) {
		this.schedulingInterval = schedulingInterval;
	}

	/**
	 * Gets the scheduling interval.
	 * 
	 * @return the scheduling interval
	 */
	public double getSchedulingInterval() {
		return schedulingInterval;
	}
}
