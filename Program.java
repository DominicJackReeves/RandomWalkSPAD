import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Program {

	public static double[] program() {

		double current = 0;
		double currentDensity = Param.e * Param.driftV/(Param.depletionRegion*Param.depletionRegion);  //I/w
		double cutoff;


		String outFileName= "filenameHere" + ".csv";

		// Open a file to save the bunch positions in
		final PrintWriter outFile;
		PrintWriter tryFile=null;
		try{
			tryFile = new PrintWriter(outFileName);
		}
		catch (IOException e){
			System.out.println("Exception opening file: " + e.getMessage());
			System.exit(4);	
		}
		finally{
			outFile=tryFile;
		}

		//outFile.println("Time,Current");

		ArrayList<Particle> activeParticles = new ArrayList<Particle>();
		ArrayList<Particle> doneParticles = new ArrayList<Particle>();

		for(int i=0; i<Param.injection_n; i++) {
			activeParticles.add(new Electron(Param.injection_pos,0));
		}


		for(int i=0; i<Param.injection_p; i++) {
			activeParticles.add(new Hole(Param.injection_pos,0));
		}

		int particleNumber= activeParticles.size();
		for(int i=0; i<particleNumber; i++) {

			//System.out.println(activeParticles.get(i).toString());

			Update.updateNoSplit(activeParticles.get(i));

			//System.out.println(activeParticles.get(i).toString());

			if(activeParticles.get(i).position<0) {
				//System.out.println("initial removed 0");
				//System.out.println(activeParticles.get(i).toString());

				activeParticles.get(i).setTime(activeParticles.get(i).time - activeParticles.get(i).position/(activeParticles.get(i).position- activeParticles.get(i).startPos) * (activeParticles.get(i).time - activeParticles.get(i).startTime)) ;
				activeParticles.get(i).setPosition(0);
				doneParticles.add(activeParticles.get(i));
				activeParticles.remove(i);
				i--;
				particleNumber--;
			} else if(activeParticles.get(i).position>Param.depletionRegion) {
				//System.out.println("initial removed w");
				//System.out.println(activeParticles.get(i).toString());

				activeParticles.get(i).setTime(activeParticles.get(i).time - (activeParticles.get(i).position-Param.depletionRegion)/(activeParticles.get(i).position - activeParticles.get(i).startPos) * (activeParticles.get(i).time - activeParticles.get(i).startTime)) ;
				activeParticles.get(i).setPosition(Param.depletionRegion);
				doneParticles.add(activeParticles.get(i));
				activeParticles.remove(i);
				i--;
				particleNumber--;
			}
		}

		double min, max;
		double timeCheck, timeMeasure;
		double[] multiplicationCheck={1,1};

		while(activeParticles.size()!=0 && current<Param.currentLim && multiplicationCheck[0]<Param.multiMax && multiplicationCheck[1]<Param.multiMax) {  //

			min=activeParticles.get(0).time;
			max=activeParticles.get(0).time;

			for(Particle p:activeParticles) {
				timeCheck=p.time;
				if(timeCheck<min) {
					min=timeCheck;
				}
				if(timeCheck>max) {
					max=timeCheck;
				}
			}

			current=0;
			timeMeasure=min;

			for(Particle p: activeParticles) {
				if(p.startTime<=timeMeasure) {
					current+=(timeMeasure-p.startTime)/(p.time-p.startTime) * p.distance();
				}
			}
			for(Particle p: doneParticles) {
				if(p.startTime<=timeMeasure && timeMeasure<=p.time) {
					current+=(timeMeasure-p.startTime)/(p.time-p.startTime) * p.distance();
				}
			}
			current *= currentDensity;

			//outFile.println(timeMeasure + "," + current);
			//outFile.flush();



			//outFile.println(timeMeasure + "," + current);
			//outFile.flush();

			cutoff = min + (max-min)/3;
			particleNumber= activeParticles.size();

			for(int i=0; i<particleNumber; i++) {

				if(activeParticles.get(i).time<=cutoff) {

					Particle[] newParticles=Update.update(activeParticles.get(i));

					if(activeParticles.get(i).position<0) {
						//System.out.println("removed 0");
						//System.out.println(activeParticles.get(i).toString());

						activeParticles.get(i).setTime(activeParticles.get(i).time + (activeParticles.get(i).position)/Param.driftV) ;
						activeParticles.get(i).setPosition(0);
						doneParticles.add(activeParticles.get(i));
						activeParticles.remove(i);
						i--;
						particleNumber--;
					} else if( activeParticles.get(i).position>Param.depletionRegion) {
						//System.out.println("removed w");
						//System.out.println(activeParticles.get(i).toString());

						activeParticles.get(i).setTime(activeParticles.get(i).time - (activeParticles.get(i).position-Param.depletionRegion)/Param.driftV) ;
						activeParticles.get(i).setPosition(Param.depletionRegion);
						doneParticles.add(activeParticles.get(i));
						activeParticles.remove(i);
						i--;
						particleNumber--;
					}
					for(int j = 0; j<2; j++) {
						if(newParticles[j].position<0) {
							//System.out.println("not added 0");
							//System.out.println(newParticles[j].toString());

							newParticles[j].setTime(newParticles[j].time + (newParticles[j].position)/Param.driftV);
							newParticles[j].setPosition(0);
							doneParticles.add(newParticles[j]);
						}else if( newParticles[j].position>Param.depletionRegion){
							//System.out.println("not added w");
							//	System.out.println(newParticles[j].toString());

							newParticles[j].setTime(newParticles[j].time - (newParticles[j].position-Param.depletionRegion)/Param.driftV);
							newParticles[j].setPosition(Param.depletionRegion);
							doneParticles.add(newParticles[j]);
						}else {
							activeParticles.add(newParticles[j]);
						}
					}

				}
			}

			multiplicationCheck = multi(activeParticles,doneParticles);



			//System.out.println();
			//	for(Particle p:activeParticles) {
			//		System.out.println(p.toString());
			//}
		}
		//System.out.println();
		//for(Particle p:doneParticles) {
		//	System.out.println(p.toString());
		//}

		//double multiplication=(activeParticles.size()+doneParticles.size())/(holeInjection+electronInjection);

		outFile.close();
		return multiplicationCheck;

	}

	private static double[] multi(ArrayList<Particle> activeParticles, ArrayList<Particle> doneParticles) {
		int electronNumber=0, holeNumber=0;
		double nMultiplication=1, pMultiplication=1;

		for(Particle p:activeParticles) {
			if(p.getType()) {
				electronNumber++;
			}else {
				holeNumber++;
			}
		}
		for(Particle p:doneParticles) {
			if(p.getType()) {
				electronNumber++;
			}
			else {
				holeNumber++;
			}
		}

		if(Param.injection_n!=0) nMultiplication = (electronNumber/Param.injection_n);
		if(Param.injection_p!=0) pMultiplication = (holeNumber/Param.injection_p);
		return new double[] {nMultiplication,pMultiplication};
	}
}