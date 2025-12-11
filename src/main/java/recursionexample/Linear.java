package recursionexample;

public class Linear {
	//Factorial linear recursion.
	public static double factorial(double d) {
	   // Check base case.
	   if (d <= 1) {
	      return 1;
	   } 
	   else {
	      return d * factorial(d - 1);
	   }//endif
	}//end method factorial

   /*
    * Static main method tests the class instance methods.
    */
	public static void main(String[] args) {
		// Declare local variable.
		double d;
			 
		// Check first input argument and assign it.
		if (args.length > 0) {
		   d = new Integer(args[0]).doubleValue();
		}
		else {
		   d = 5;
		}//endif

		// Print the result from the factorial method.     
	     System.out.println("Factorial [" + Linear.factorial(d) + "] of   [" + d + "]");
	}//end method main
}//end class Linear

