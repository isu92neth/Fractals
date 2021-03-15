/* CO225 Project 1*/
/* Name : Adikari A.M.I.N. */
/* Reg No : E/16/012 */

/* Class to declare attributes and methods needed to create the Julia set */

import java.awt.Color;

class Julia extends Complex implements MyMain {
	
	/* Array to store values of region of interest */
	private double[] reg= {-1,1,-1,1};
	
	/* iter stores value for iterations */
	private int iter=1000;
	
	/* Array to store real and complex parts of a complex number */
	private double[] complex = {-0.4,0.6};
	
	/* Overidden method to set the user inputs for Julia set */
	public void setIn(String input[],int length) {
		try {
			length = length - 1;
			if(length == 3) {
				/* For 3 input arguments */
				for(int i = 0;i < length-1;i++) {
					this.complex[i] = Double.parseDouble(input[i+1]);
				}
				this.iter = Integer.parseInt(input[length]);
			}
			/* For 2 input arguments */
			else if(length == 2) {
				for(int i = 0;i < length;i++) {
					this.complex[i] = Double.parseDouble(input[i+1]);
				}
			}
			/* For 0 input arguments */
			else if(length == 0) {}
			/* For wrong number of input arguments */
			else {
				System.out.println("Wrong number of inputs");
				System.out.println("Usage :");
				System.out.println("java Fractal Julia complex_real complex_imaginary No_of_iterations");
				System.out.println("java Fractal Julia complex_real complex_imaginary");
				System.out.println("java Fractal Julia");
				System.exit(0);
				
			}			
			
		} catch (NumberFormatException e) { /*Check if the input arguments are of correct format */
			System.out.println("Formats of inputs are wrong");
			System.out.println("Usage :");
			System.out.println("java Fractal Julia complex_real complex_imaginary No_of_iterations");
			System.out.println("java Fractal Julia complex_real complex_imaginary");
			System.out.println("java Fractal Julia");
			System.exit(0);
		}
	}
	
	/* Method to return values for region of interest */
	public double[] getReg() {
		return reg;
	}
	
	/* Method to return value for iterations */
	public int getIter() {
		return iter;
	}
	
	/* Method to return values for real and imaginary parts */
	public double[] getCom() {
		return complex;
	}
	
	/* Method to check if a given point is in Julia set and return colors accordingly*/
	public int calJulia(double x, double y, int iteration, double comX, double comY) {
		
		Complex com = new Julia();
		
		/* Get the real and imaginary parts of the complex number */
		com.setComplex(x, y);
		
		/* zX : Real part */
		/* zY : Imaginary part */
		double zX, zY;
		zX = com.getReal();
		zY = com.getImg();
		
		/* newX : Real part of z */
		/* newY : Imaginary part of z */
		double newX, newY;
		
		/* i : To count and increment iterations */
		int i;	
		for(i = 0;i < iteration;i++) {
			newX = zX;
			newY = zY;
			com.setComplex(newX, newY);
			zX = com.getSqrDif() + comX;
			zY = com.getMul() + comY;
			com.setComplex(zX, zY);
			
			/* Check if abs(Z^2) > 4 */
			if(com.getAbs() > 4)break;
		}
		/* Return black colour if the given point is in the Julia set */
		if(i==iteration)return 0x00000000;
		
		/* Return a different colour if not in the Julia set */
		return Color.HSBtoRGB((float)i/100.0F,1F,1F);
	}

}
