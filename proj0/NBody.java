import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

import javafx.beans.binding.StringExpression;

public class NBody {
    public static double readRadius(String filename){
        In in = new In(filename);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int N = in.readInt();
        Body[] bs = new Body[N];
        in.readDouble();
        for(int i=0; i<N; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            
            bs[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return bs;
    }

    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Body[] bs = NBody.readBodies(filename);
        double t = 0;

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        // draw all the planets

        StdDraw.picture(0,0,"images/starfield.jpg");
       // for(int i=0; i<bs.length; i++){
       //     StdDraw.picture(bs[i].xxPos, bs[i].xxPos, "images/"+bs[i].imgFileName);    
       // }

        StdDraw.enableDoubleBuffering();
       /* 
        double[] xForces = new double[bs.length];
        double[] yForces = new double[bs.length];
        for(int i=0; i<1; i++){
               xForces[i] = bs[i].calcNetForceExertedByX(bs);
               yForces[i] = bs[i].calcNetForceExertedByY(bs);
               //System.out.printf("%f, %f\n", xForces[i],yForces[i]);
        }
        System.out.printf("\n");
        for(int i=0; i<bs.length; i++){
            //System.out.printf("%f,%f\t",bs[i].xxPos,bs[i].yyPos);
               bs[i].update(dt, xForces[i], yForces[i]);

            //System.out.printf("%f,%f\n",bs[i].xxPos,bs[i].yyPos);
        }
        for(int i=0; i<bs.length; i++){
                 StdDraw.picture(bs[i].xxPos, bs[i].xxPos, "images/"+bs[i].imgFileName);    
        }
        */
        
        for(t=0; t<T; t += dt){
           double[] xForces = new double[bs.length];
           double[] yForces = new double[bs.length];

           for(int i=0; i<bs.length; i++){
               xForces[i] = bs[i].calcNetForceExertedByX(bs);
               yForces[i] = bs[i].calcNetForceExertedByY(bs);
           }

           for(int i=0; i<bs.length; i++){
               bs[i].update(dt, xForces[i], yForces[i]);
           }
           StdDraw.picture(0,0,"images/starfield.jpg");
           for(int i=0; i<bs.length; i++){
                 StdDraw.picture(bs[i].xxPos, bs[i].xxPos, "images/"+bs[i].imgFileName);    
           }
           StdDraw.show();
           StdDraw.pause(10);
       }
       
        
       StdDraw.show();
       StdDraw.pause(2000);
        

    }
    /*
    StdOut.printf("%d\n", bs.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < bs.length; i++){
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                      bs[i].xxPos, bs[i].yyPos, bs[i].xxVel,
                      bs[i].yyVel, bs[i].mass, bs[i].imgFileName);   
    }
*/
}
