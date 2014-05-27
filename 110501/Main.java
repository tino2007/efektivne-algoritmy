//110501 Primary Arithmetic
import java.util.Scanner;

/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;
import java.util.*;

class Main implements Runnable{
    static String ReadLn(int maxLength){  // utility function to read from stdin,
                                          // Provided by Programming-challenges, edit for style only
        byte line[] = new byte [maxLength];
        int length = 0;
        int input = -1;
        try{
            while (length < maxLength){//Read untill maxlength
                input = System.in.read();
                if ((input < 0) || (input == '\n')) break; //or untill end of line ninput
                line [length++] += input;
            }

            if ((input < 0) && (length == 0)) return null;  // eof
            return new String(line, 0, length);
        }catch (IOException e){
            return null;
        }
    }

    public static void main(String args[])  // entry point from OS
    {
        Main myWork = new Main();  // Construct the bootloader
        myWork.run();            // execute
    }

    public void run() {
        new myStuff().run();
    }
}
class myStuff implements Runnable{
    public void run(){
            Citacka();
        }
    /**
     * @param args
     */
    public static void Citacka(){//nacitanie udajov
        //Scanner sc = new Scanner(System.in);
        Integer c1;
        Integer c2;
        String[] temp;

        String riadok = Main.ReadLn(255);
        temp = riadok.trim().split(" ");
         while (!temp[0].equals("0") && !temp[1].equals("0")){
            //c1 = Integer.valueOf(temp[0]);
            //c2 = Integer.valueOf(temp[1]);
            int carry = 0;
            int prenesenaHodnota = 0;
            int i = 0;
            int minLength = Math.max(temp[0].length(), temp[1].length());
            do {
                c1 = Integer.parseInt("" + temp[0].charAt(temp[0].length() - i - 1));
                c2 = Integer.parseInt("" + temp[1].charAt(temp[1].length() - i - 1));
                if ((c1 + c2 + prenesenaHodnota) >= 10)
                {
                    carry++;
                    prenesenaHodnota = 1;
                }
                else
                {
                    prenesenaHodnota = 0;
                }
                i++;
            } while (i < minLength);

            if (carry == 0)
                System.out.println("No carry operation.");
            else if (carry == 1)
                System.out.println("1 carry operation.");
            else
                System.out.println(carry + " carry operations.");


            riadok = Main.ReadLn(255);
            temp = riadok.trim().split(" ");
        }
         return;
    }
    // You can insert more classes here if you want.
}

// Pogram hlada pocet "carry" operacii. Nacita si riadok, rozdeli podla medzery a potom postupne
// odzadu scitava cisla. Ak je sucet viac ako 9 tak pripocita carry a prechadza na dalsie cisla.