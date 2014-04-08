package minesweeper;

/*
 * Main.java
 *  java program model for www.programming-challenges.com
 */

import java.io.*;

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
        String line = Main.ReadLn(512);         // Nacita prvy riadok
        String[] strSize = line.split(" ");
        int n = Integer.parseInt(strSize[0]);   // lines
        int m = Integer.parseInt(strSize[1]);   // columns
        int count = 1;
        while (n != 0 || m != 0) {              // Opakuje pokial nenatrafi na riadok s hodnotou "0 0"
            System.out.println("Field #" + count + ":");
            mineSweeper(n, m);
            line = Main.ReadLn(512);
            strSize = line.split(" ");
            n = Integer.parseInt(strSize[0]);   // lines
            m = Integer.parseInt(strSize[1]);   // columns
            count++;
        }
    }

    /**
     * Solve one.
     * @param n - lines
     * @param m - colums
     */
    public void mineSweeper(int n, int m) {
        char[][] table = new char[n][m];
        // Vytvory 2D pole char-ov do ktorych vpise hodnoty nacitane zo vstupu
        for (int row = 0; row < n; row++) {
            String line = Main.ReadLn(512);
            for (int col = 0; col < m; col++) {
                table[row][col] = line.charAt(col);
            }
        }

        // Prechadza kazdu jednu polozku a ak natrafi na miesto kde nieje mina skontroluje kolko min je dookola nej
        // a namiesto bodky vlozi pocit min v okoli.
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (table[row][col] == '.') {    // nieje to mina
                    Integer nMines = 0;
                    if (isThereABomb(table, row-1, col-1) ) nMines++;   // hore v lavo
                    if (isThereABomb(table, row-1, col) ) nMines++;     // hore v strede
                    if (isThereABomb(table, row-1, col+1) ) nMines++;   // hore v pravo
                    if (isThereABomb(table, row, col-1) ) nMines++;     // v lavo
                    if (isThereABomb(table, row, col+1) ) nMines++;     // v pravo
                    if (isThereABomb(table, row+1, col-1) ) nMines++;   // dole v lavo
                    if (isThereABomb(table, row+1, col) ) nMines++;     // dole v strede
                    if (isThereABomb(table, row+1, col+1) ) nMines++;   // dole v pravo

                    table[row][col] = nMines.toString().charAt(0);
                }
            }
        }

        // Iba vypise tabulku (minove pole :D)
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                System.out.print(table[row][col]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Skontroluje ci je na danom mieste bomba, Try catch kvoli tomu aby sme nemuseli osetrovat ci
    // nahodou nazadame zapornu suradnicu alebo taku ktora neexistuje vtedy je jasne ze tam mina nieje.
    public boolean isThereABomb(char[][] table, int n, int m)
    {
        try {
            if (table[n][m] == '*') return true;
        }
        catch (Exception ex)
        {

        }
        return false;
    }
    // You can insert more classes here if you want.
}