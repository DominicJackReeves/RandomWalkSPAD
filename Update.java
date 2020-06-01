import java.util.Random;

public class Update {
	private static Random rnd = new Random();
	private static Electron newElectron;
	private static Hole newHole;

	public static void setSeed(double seed) {
		rnd.setSeed((long) seed);
	}

	public static Particle[] update(Particle initialParticle) {
		double holeDS, electronDS;

		newElectron = new Electron(initialParticle.position,initialParticle.time);		
		newHole = new Hole(initialParticle.position,initialParticle.time);

		electronDS = DeadSpace.length(Param.electric, Param.thresh_A);
		holeDS = DeadSpace.length(Param.electric, Param.thresh_B);

		if(initialParticle.getType()) { //Is electron
			initialParticle.increasePos(electronDS - (Math.log(rnd.nextDouble())) * (1/Param.alpha-electronDS) );
			initialParticle.setTime(initialParticle.startTime + Math.abs(initialParticle.distance())/Param.driftV); //final time
		}
		else {
			initialParticle.decreasePos(holeDS - (Math.log(rnd.nextDouble()))*(1/Param.beta-holeDS) );
			initialParticle.setTime(initialParticle.startTime + Math.abs(initialParticle.distance())/Param.driftV); //final time
		}

		newElectron.increasePos(electronDS - (Math.log(rnd.nextDouble()))*(1/Param.alpha-electronDS) );
		newElectron.setTime(newElectron.startTime + Math.abs(newElectron.distance())/Param.driftV);

		newHole.decreasePos(holeDS - (Math.log(rnd.nextDouble()))*(1/Param.beta-holeDS) );
		newHole.setTime(newHole.startTime + Math.abs(newHole.distance())/Param.driftV); //final time
		
		return new Particle[] {newElectron, newHole};
	}

	public static void updateNoSplit(Particle initialParticle) {
		double holeDS, electronDS;

		if(initialParticle.getType()) {
			electronDS = DeadSpace.length(Param.electric, Param.thresh_A);
			initialParticle.increasePos(electronDS - (Math.log(rnd.nextDouble()))*(1/Param.alpha-electronDS) );
			initialParticle.setTime(initialParticle.startTime + Math.abs(initialParticle.distance())/Param.driftV); //final time
		}
		else {
			holeDS = DeadSpace.length(Param.electric, Param.thresh_B);
			initialParticle.decreasePos(holeDS - (Math.log(rnd.nextDouble()))*(1/Param.beta-holeDS) );
			initialParticle.setTime(initialParticle.startTime + Math.abs(initialParticle.distance())/Param.driftV); //final time
		}
	}

}
