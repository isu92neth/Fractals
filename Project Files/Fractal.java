/* CO225 Project 1*/
/* Name : Adikari A.M.I.N. */
/* Reg No : E/16/012 */


/* Class to draw Mandelbrot and Julia sets */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

class Fractal extends JPanel implements Runnable {
	
	private final int WIDTH = 800;   /* Width of the area */
	private final int HEIGHT = 800;  /* Height of the area */
	private int startX;  /* Starting point of the real axis */
	private int endX;    /* Ending point of the real axis */
	private int startY;  /* Starting point of the imaginary axis */
	private int endY;    /* Ending point of the imaginary axis */
	private static Graphics2D g2D;  /* Attribute to save Graphics2D object */
	private static int iter; /* No of iterations */
	private static double[] reg; /* To store values for region of interest */
	private static double[] complex; /* To store values for real and imaginary parts */
	private static String set = ""; /* To store fractal name */
	private static int [][]color = new int[800][800]; /* To store colours for pixels */
	
	Fractal() {}
	
    /* Constructor to get values for region of interest */
	Fractal(int startX, int endX, int startY, int endY){
		this.startX = startX;
		this.endX = endX;
		this.startY = startY;
		this.endY = endY;
		
	}
	
	/* Constructor to create the jframe and plot */
	Fractal(String title) {
	    JFrame frame = new JFrame(title);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(this);
	    frame.setSize(WIDTH, HEIGHT);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
	
	/* Method called on creating JPanel */
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    g2D = (Graphics2D) g;
	    try {
	    	
	    	/* Threads to run parallel to calculate colours for each pixel */
	    	Thread t1 = new Thread(new Fractal(0, 400,0,400),"one");
		    Thread t2 = new Thread(new Fractal(0, 400,400,800),"two");
		    Thread t3 = new Thread(new Fractal(400, 800,0,400),"three");
		    Thread t4 = new Thread(new Fractal(400, 800,400,800),"four");
		    t1.start();
		    t2.start();		  
		    t3.start();		   
		    t4.start();
		    t1.join();
		    t2.join();
		    t3.join();
		    t4.join();
		    
		    /* Draw the respective fractal */
			for(int x = 0;x < WIDTH;x++) {
					for(int y = 0;y < HEIGHT;y++) {
						Color myColor = new Color(color[x][y]);
						g2D.setColor(myColor);
						g2D.drawLine(x, y, x, y);
					}
			 }
   
			
		} catch (Exception e) {
			System.out.println("Exception is handled");
		}
 
	  }
	
	/* Overidden method called by thread to calculate colours for each pixels */
	public void run() {
	
		Mandelbrot objM = new Mandelbrot();
		Julia objJ = new Julia();
		for(int x = startX;x < endX;x++) {
			for(int y = startY;y < endY;y++) {
				
			    /* Calculation for colours for Mandelbrot set */
				if(set.equals("Mandelbrot")) {
					double xM, yM;
					xM = ((x * (reg[1] - reg[0])) / 800) - Math.abs(reg[0]);
					yM = ((y * (reg[3] - reg[2])) / 800) - Math.abs(reg[3]);
					color[x][y] = objM.calMandelbrot(xM, yM, iter);
				}
				/* Calculation for colours for Julia set */
				else if(set.equals("Julia")) {
					double xJ, yJ;
					xJ = (x-WIDTH/2f)/400;
					yJ = (y-HEIGHT/2f)/400;
					color[x][y] = objJ.calJulia(-xJ, yJ, iter, complex[0], complex[1]);
				}
				
			}
		}
		
	}

	public static void main(String[] args) {
		
		/* Get the total no of arguments length */
		int argLen = args.length;
		/* Check if no arguments */
		if(argLen == 0) {
			System.out.println("No arguments");
			System.out.println("Usage :");
			System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end No_of_iterations");
			System.out.println("java Fractal Mandelbrot x_start x_end y_start");
			System.out.println("java Fractal Mandelbrot");
			System.out.println("Usage :");
			System.out.println("java Fractal Julia complex_real complex_imaginary No_of_iterations");
			System.out.println("java Fractal Julia complex_real complex_imaginary");
			System.out.println("java Fractal Julia");
			System.exit(0);
		}
		
		/* Array to the given input arguments */
		String argIn[] = new String[argLen];
		for(int i = 0;i < argLen;i++) {
			argIn [i] = args[i];
		}
		
		/* To store the fractal name */
		set = argIn[0];
		
		/* To draw the Mandelbrot set if choosen */
		if(set.equals("Mandelbrot")) {
			MyMain objMan = new Mandelbrot();
			
			/* Set input arguments for Mandelbrot set */
			objMan.setIn(argIn, argLen);
			
			/* Get the given no of iterations */
			iter = objMan.getIter();
			
			/* Get the values for given region of interest */
			reg = objMan.getReg();
			
			/*Check if no of iterations is negative */
			if(iter < 0) {
				System.out.println("Number of iterations should be a positive value");
				System.exit(0);
			}
			
			/* Check if a valid region of interest is given */
			else if(reg[0] > reg[1] || reg[2] > reg[3]) {
				System.out.println("Wrong format for region of interest");
				System.out.println("Usage : x_start x_end y_start y_end");
				System.exit(0);
			}
			
			/* Draw the Mandelbrot set */
			new Fractal("Mandelbrot Set");
		}
		
		/* To draw the Julia set if choosen */
		else if(set.equals("Julia")) {
			Julia objJul = new Julia();
			
			/* Set input arguments for Julia set */
			objJul.setIn(argIn, argLen);
			
			/* Get the given no of iterations */
			iter = objJul.getIter();
			
			/* Get the values for real and imaginary parts */
			complex = objJul.getCom();
			
			/*Check if no of iterations is negative */
			if(iter < 0) {
				System.out.println("Number of iterations should be a positive value");
				System.exit(0);
			}
			
			/* Draw the Julia set */
			new Fractal("Julia Set");
		}
		else {
			System.out.println("Invalid set");
			System.out.println("Usage :");
			System.out.println("java Fractal Mandelbrot x_start x_end y_start y_end No_of_iterations");
			System.out.println("java Fractal Mandelbrot x_start x_end y_start");
			System.out.println("java Fractal Mandelbrot");
			System.out.println("Usage :");
			System.out.println("java Fractal Julia complex_real complex_imaginary No_of_iterations");
			System.out.println("java Fractal Julia complex_real complex_imaginary");
			System.out.println("java Fractal Julia");
		}
	}

}
