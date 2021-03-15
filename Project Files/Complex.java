/* CO225 Project 1*/
/* Name : Adikari A.M.I.N. */
/* Reg No : E/16/012 */

/* Class to declare attributes and methods to do complex number operations */

class Complex {
	
	/* real : Real part of a complex number */
	/* img : Imaginary part of a complex number */
	private double real, img;
	
	/* Method to set the real and imaginary parts of a complex number */
	public void setComplex(double real, double img){
		this.real = real;
		this.img = img;
	}
	
	/* Method to return the real part of a complex number */
	public double getReal() {
		return this.real;
	}
	
	/* Method to return the imaginary part of a complex number */
	public double getImg() {
		return this.img;
	}
	
	/* Method to return the absolute value of a complex number */
	public double getAbs() {
		double abs = this.real*this.real + this.img*this.img;
		return abs;
	}
	
	/* Method to return the difference of squares of real and imaginary parts */
	public double getSqrDif() {
		double dif = this.real*this.real - this.img*this.img;
		return dif;
	}
	
	/* Method to return the value for 2 * (real_part) * (imaginary_part) */
	public double getMul() {
		double mul = 2*this.real*this.img;
		return mul;
	}

}
