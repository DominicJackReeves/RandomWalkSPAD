
public class DeadSpace {
	
	public static double length(double A, double C, double x, double E, double q) {
		double d = -(A*q*x + C*q - Math.sqrt(A*A*q*q*x*x + 2*A*C*q*q*x + C*C*q*q + 2*A*E*q))/(A*q);
		return d;
	}

	public static double length(double electric, double energy) {
		double d = energy/(electric*Param.e);
		return d;
	}

}

