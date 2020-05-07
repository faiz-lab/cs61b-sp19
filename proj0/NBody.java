public class NBody {
    public static double readRadius(String fileName) {
        /**
         * Return the radius of the universe reading from the file
         */
        In in = new In(fileName);
        in.readInt();
        double Radius = in.readDouble();
        return Radius;
    }

    /**
     * Return an array of Bodys corresponding to the bodies in the file
     */
    // * Object arrays, hard!!
    public static Body[] readBodies(String fileName) {
        In in = new In(fileName);
        int num = in.readInt();
        in.readDouble();
        Body[] Planets = new Body[num];
        for (int i = 0; i < Planets.length; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planets[i] = new Body(xP, yP, xV, yV, m, img);
        }
        return Planets;
    }

    public static String imageToDraw = "images/starfield.jpg";

    public static void main(String[] args) {
        // * Command line parameters are used here
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Body[] Planets = NBody.readBodies(filename);
        double radius = NBody.readRadius(filename);

        // # Drawing the Background:
        // set the scale so that it matches the radius of the universe
        // Then draw the image starfield.jpg as the background.
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, imageToDraw);

        // # Drawing More than One Body
        for (Body b : Planets) {
            b.draw();
        }

        /**
         * Animation
         */
        StdDraw.enableDoubleBuffering();
        /**
         * Set up a loop to loop until time variable reaches T
         */
        for (double time = 0; time <= T; time += dt) {
            double[] xForces = new double[Planets.length];
            double[] yForces = new double[Planets.length];
            for (int i = 0; i < Planets.length; i++) {
                xForces[i] = Planets[i].calcNetForceExertedByX(Planets);
                yForces[i] = Planets[i].calcNetForceExertedByY(Planets);
            }
            /**
             * Update positions and velocities of each planet
             */
            for (int i = 0; i < Planets.length; i++) {
                Planets[i].update(dt, xForces[i], yForces[i]);
            }
            // Draw the background image.
            StdDraw.picture(0, 0, imageToDraw);
            // Draw all of the Bodys.
            for (Body b : Planets) {
                b.draw();
            }
            // Show the offscreen buffer
            StdDraw.show();
            StdDraw.pause(10);
        }
        /**
         * Print out the final state of the universe when time reaches T
         */
        StdOut.printf("%d\n", Planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < Planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    Planets[i].xxPos,
                    Planets[i].yyPos,
                    Planets[i].xxVel, Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);
        }
    }
}
