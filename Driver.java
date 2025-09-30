import java.io.*;

public class Driver 
{
    public static void main(String [] args) throws IOException, FileNotFoundException
    {
        Polynomial p = new Polynomial();
        System.out.println(p.evaluate(3));

        double [] c1 = {6,1,1,5};
        int [] e1 = {0,1,2,3};
        Polynomial p1 = new Polynomial(c1, e1);
        p1.saveToFile("./output1.txt");

        double [] c2 = {1,-2,4,7,-9};
        int [] e2 = {1,3,5,7,9};
        Polynomial p2 = new Polynomial(c2, e2);
        p2.saveToFile("./output2.txt");

        double [] c3 = {1,2};
        int [] e3 = {0,1};
        Polynomial p3 = new Polynomial(c3, e3);
        
        double [] c4 = {1,1};
        int [] e4 = {1,2};
        Polynomial p4 = new Polynomial(c4, e4);
        
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
        System.out.println("1 is a root of s");
        else
        System.out.println("1 is not a root of s");

        Polynomial s2 = p3.multiply(p4);
        s2.saveToFile("./output3.txt");

        Polynomial s3 = new Polynomial("./output3.txt");
        s3.saveToFile("./output4.txt");

    }
}