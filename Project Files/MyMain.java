/* CO225 Project 1*/
/* Name : Adikari A.M.I.N. */
/* Reg No : E/16/012 */

/* Interface to declare common methods to both Mandelbrot and Julia classes*/

interface MyMain {
	
	/* Methods to be overidden by Mandelbrot and Julia classes*/
	
	/* Method to set the user inputs */
    public void setIn(String input[],int length);
	
    /* Method to return default values for region of interest */
	public double[] getReg();
	
	/* Method to return default value for iterations */
	public int getIter();

}
