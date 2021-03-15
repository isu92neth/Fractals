/* CO225 Project 1*/
/* Name : Adikari A.M.I.N. */
/* Reg No : E/16/012 */

/* Class to declare attributes and methods needed to create the Mandelbrot set */

import java.awt.Color;

class Mandelbrot extends Complex implements MyMain{

	/* Array to store values of region of interest */
	private double[] reg= {-1,1,-1,1};
	
	/* iter stores value for iterations */
	private int iter=1000;

	/* Overidden method to set the user inputs for Mandelbrot set */
	public void setIn(String input[],int length) {
		try {
			length = length - 1;
			/* For 5 input arguments */
			if(length == 5) {
				for(int i = 0;i < length-1;i++) {
					this.reg[i] = Double.parseDouble(input[i+1]);
				}
				this.iter = Integer.parseInt(input[length]);
			}
			/* For 4 input arguments */
			else if(length == 4) {
				for(int i = 0;i < length;i++) {
					this.reg[i] = Double.parseDouble(input[i+1]);
				}
			}
			/* For 0 input arguments */
			else if(length == 0) {}
			/* For wrong number of input arguments */
			else {
				System.out.println("Wrong number of inputs");
				System.out.println("Usage :");
				System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end No_of_iterations");
				System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end");
				System.out.println("java Fractal Mandelbrot");
				System.exit(0);
			}			
		} catch (NumberFormatException e) { /*Check if the input arguments are of correct format */
			System.out.println("Formats of inputs are wrong");
			System.out.println("Usage :");
			System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end No_of_iterations");
			System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end");
			System.out.println("java Fractal Mandelbrot");
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
	
	/* Method to check if a given point is in Mandelbrot set and return colors accordingly*/
	public int calMandelbrot(double x,double y,int iteration) {

		Complex com = new Mandelbrot();
		
		/* Get the real and imaginary parts of the complex number */
		com.setComplex(x, y);
		
		/* comX : Real part */
		/* comY : Imaginary part */
		double comX, comY;
		comX = com.getReal();
		comY = com.getImg();
		
		/* zX : Real part of z */
		/* zY : Imaginary part of z */
		double zX, zY;
		
		/* i : To count and increment iterations */
		int i;
		for(i = 0;i < iteration;i++) {
			zX = com.getSqrDif() + comX;
			zY = com.getMul() + comY;
			x = zX;
			y = zY;
			com.setComplex(x, y);
			
			/* Check if abs(Z^2) > 4 */
			if(com.getAbs() > 4)break;
		}		
		/* Return black colour if the given point is in the Mandelbrot set */
		if(i==iteration)return 0x00000000;
		
		/* Return a different colour if not in the Mandelbrot set */
		double r, g, b;
		r = i | (i<<2);
		g = i | (i<<4);
		b = i | (i<<8);
		while(r>255) r-=255;
		while(g>255) g-=255;
		while(b>255) b-=255;
		
		Color color = new Color((int)r, (int)g, (int)b);
		return color.getRGB();
	}

}
