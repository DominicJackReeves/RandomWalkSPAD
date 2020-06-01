
public class Trials {
	
	public static void trials() {

		for(int j=0; j<=Param.voltage_step; j++) {
						
			double nNoise = 0, pNoise = 0, nMultiplication=0, pMultiplication=0;
			
			Update.setSeed(Param.seedNo);
			
			double appliedVoltage = Param.voltage_start+j*(Param.voltage_end-Param.voltage_start)/Param.voltage_step;
			Param.voltage = Param.builtInVoltage + appliedVoltage;
			Param.electric = Param.voltage/Param.depletionRegion;
			
			Param.alpha=Param.a_A*Math.exp(-Math.pow((Param.a_B/Param.electric), Param.a_C));
			Param.beta=Param.b_A*Math.exp(-Math.pow((Param.b_B/Param.electric), Param.b_C));

			//take in file for electric field profile
			//loop over varying electric field amplitude

			for(int i=0; i<Param.trials;i++) {
				double[] M = Program.program();
				nMultiplication += M[0];
				pMultiplication += M[1];
				nNoise += M[0]*M[0];
				pNoise += M[1]*M[1];
				
				//System.out.println(i+1 + ": " + M[i]);
			}

			nMultiplication/=Param.trials;
			pMultiplication/=Param.trials;
			
			if(nNoise!=0) nNoise /= (Param.trials*nMultiplication*nMultiplication);
			if(pNoise!=0) pNoise /= (Param.trials*pMultiplication*pMultiplication);

			System.out.println(Param.trials + "	" + appliedVoltage + "	" + nMultiplication + "	" + nNoise + "	" + pMultiplication + "	" + pNoise);
		}
	}
}

