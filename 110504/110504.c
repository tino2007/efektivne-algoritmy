/*
ONES
Given any integer 0 ≤ n ≤ 10,000 not divisible by 2 or 5, some multiple of n is a
number which in decimal notation is a sequence of 1’s. How many digits are in the
smallest such multiple of n?
-----------------
INPUT
A ﬁle of integers at one integer per line.

SAMPLE INPUT
3
7
9901
-----------------
OUTPUT
Each output line gives the smallest integer x > 0 such that p = x−1 i=0 1×10i, where a
is the corresponding input integer, p = axb, and b is an integer greater than zero.

SAMPLE OUTPUT
3
6
12
-----------------
RIESENIE
Dane je cislo na vstupe, ktore nie je delitelne ani 2 a ani cislo 5. 
*/

import java.util.Scanner;

public class Ones {
    public static void main(String[] args) {
        Ones.begin();
    }

    private static void begin() {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        while (sc.hasNextInt()) { //nacitanie cisla
            int a = sc.nextInt();

            int x = findSmallestMultipleAsOne(a); //zavolanie metody
            sb.append(x).append("\n");
        }

        System.out.print(sb);
    }

    private static int findSmallestMultipleAsOne(int a) {
        /*
         * e.g., 111 = (1 * 10 + 1) * 10 + 1
         * 111 mod 3 = ((((1 mod 3) * 10 + 1) mod 3) * 10 + 1) mod 3
         * 1 mod 3 = 1;
         * 1 * 10 + 1 = 11;
         * 11 mod 3 = 2;
         * 2 * 10 + 1 = 21;
         * 21 mod 3 = 0;
         * is similar to:
         * 1 = 1;
         * 11 = 1 * 10 + 1 = 3 * 3 + 2;
         * 111 = 11 * 10 + 1 = (3 * 3 + 2) * 10 + 1 = 30 * 3 + 20 + 1 = 30 * 3 + 21
         * 111 mod 3 = (30 * 3 + 21) mod 3 = ((30 * 3 mod 3) + (21 mod 3)) mod 3 = 0
         * As one can see, when we mod m in each step, we only keep the remainder.
         * There is no need to take over the value when mod m gives 0.
         * e.g., (3 * 3) mod 3 = 0, (30 * 3) mod 3 = 0 as well.
         */

        //PRINCIP ULOHY
        //Je potrebne najst taky nasobok daneho cisla nacitaneho na vstupe, kde jeho nasobok je jeho najmensim nasobkom cisiel 1
        //Snazime sa najst jeho najmensi nasobok, pre ktory plati ze sa sklada len zo samych jednotiek. Vypise sa potom dlzka tohto nasobkoveho cisla.
        //Napr. pre cislo 3 je jeho nasobkom, ktory hladame cislo 111, lebo 111/3 = 37, nemoze to byst cislo 11/3, kedze vrati vysledok so zvyskom. A teda
        //kedze 111/3= 37, tak vysledkom je pocet cislic v cisle 111, cize 3. Pre cislo 7 je to cislo 111111 a teda vysledok je 6.
        //Najjednoduchsie vysvetlene na tomto bloku kodu

        int current = 1; //prve cislo sa skontroluje jednotka
        int digitCount = 1; //prvotny pocet jednotiek
    
        while (current % n != 0) { //cyklus kym jednotkove cislo / zakladne cislo !=0
            current = current * 10 + 1; // pridame o jednotku navyse, cize z 11 sa stane 111, z 1 111 sa stane 11 111
            digitCount++; //inkrementujem pocitadlo
        }
        return digitCount;

        //Optimalne riesenie je toto nasledovne, kedze vo vrchnom je v ramci datoveho typu obmedzena moznost matematickych operacii, avsak princip obsahuje aj popis vyssie

        int s = 1;
        int n = 1;
        while (s % a != 0) {
            s = (s % a) * 10 + 1;
            n++;
        }
        return n;
    }
}