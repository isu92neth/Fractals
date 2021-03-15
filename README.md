# Fractals

Designed and implemented a software that would plot two of the most popular fractals: **Mandelbrot set** and **Julia set**.

The programme accepts user input arguments.
The user should at least specify which set to plot.

If the set selected is **Mandelbrot** the user should give either 0, 4 or 5 arguments
  - If 0 arguments given then the default values are used as specified
  - 4 arguments will be the region of interest in the complex plane and the 5th one is the number of iterations to do for a point.

For the **Julia** set the user should give 0 or 2 arguments. 
  - If there are no arguments one should use the default arguments as specified
  - 2 arguments will be the real and complex part for C

|Item        |Default value   |Note   |
|:---:       |:---:           |:---:  |
|Region of interest|-1<real<1   -1<complex<1 |Always use default for Julia|
|Number of iterations|1000|           |
|C           |-0.4+0.6i       |Only for Julia set|

How to run(Examples):
  - **java Fractal Mandelbrot -0.5 0.5 -0.1 1 1000**
    - The region of interest for the image should be from -0.5<real<0.5 and -0.1<complex<1 and for each point you need to do 1000 iterations before deciding that it is in the set
  - **java Fractal Julia -0.5 0.156 1000**
    - Plot the Julia set for C = -0.5 + 0.156i with 1000 iterations for each point. The region of interest in the complex plane is 1 < real part < 1 and -1< complex part<1 which cannot be modified
