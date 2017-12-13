package com.flowergarden.run;
import java.io.*;


public class RunFileExample {
    public static void main(String[] args)  {
        try (FileInputStream in = new FileInputStream("file.txt");
            FileOutputStream out = new FileOutputStream("output.txt")) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.println((char) c);
                out.write(c);
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
