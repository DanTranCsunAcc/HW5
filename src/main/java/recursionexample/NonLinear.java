package recursionexample;

public class NonLinear {
	//Static fibonacci method.
	public static double fibonacci(double d) {
		if (d < 2) {
			return d;
		}
	    else {
	    	return fibonacci(d - 1) + fibonacci(d - 2);  
		}//endif
	}//end method fibonacci

	/*
	 * Static main method tests the fibonacci method.
	 */
	public static void main(String[] args) {
		// Declar local variable.
		double d;
		// Check first input argument and assign it.
		if (args.length > 0) {
		   d = new Integer(args[0]).doubleValue();
		} 
	    else {
		   d = 5;
		}//endif

	    // Print the results from a series of numbers.
	    for (double i = 0; i < d; i++) {
	       System.out.println("Fibonacci index [" + i + "] value [" +
	    		   NonLinear.fibonacci(i) + "]");
	    }//endfor
	}//end method main
}//end class NonLinear

