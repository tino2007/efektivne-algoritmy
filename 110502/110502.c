/*
POPIS
The reverse and add function starts with a number, reverses its digits, and adds the
reverse to the original. If the sum is not a palindrome (meaning it does not give the
same number read from left to right and right to left), we repeat this procedure until
it does.
For example, if we start with 195 as the initial number, we get 9,339 as the resulting
palindrome after the fourth addition:
    195    786   1,473   5,214
    591    687   3,741   4,125
  + —–    + ——    + ——    + ——
    786  1,473   5,214   9,339
This method leads to palindromes in a few steps for almost all of the integers. But
there are interesting exceptions. 196 is the ﬁrst number for which no palindrome has
been found. It has never been proven, however, that no such palindrome exists.
You must write a program that takes a given number and gives the resulting
palindrome (if one exists) and the number of iterations/additions it took to ﬁnd it.
You may assume that all the numbers used as test data will terminate in an answer
with less than 1,000 iterations (additions), and yield a palindrome that is not greater
than 4,294,967,295.
-------------
INPUT
The ﬁrst line will contain an integer N (0 < N ≤ 100), giving the number of test cases,
while the next N lines each contain a single integer P whose palindrome you are to
compute.

SAMPLE INPUT
3
195
265
750
-------------
OUTPUT
For each of the N integers, print a line giving the minimum number of iterations to ﬁnd
the palindrome, a single space, and then the resulting palindrome itself.

SAMPLE OUTPUT
4 9339
5 45254
3 6666
-------------
VYSVETLENIE
Nacita pocet vstupov. Potom po riadkoch cita samotny vstup. Vezmeme nacitane cislo na vstupe 195. Z tohto cisla sa spravi reverznute cislo. Tieto dve cisla sa scitaju. Inkrementuje sa pocitadlo o 1, aby sme vedeli po kolko scitani sme nasli palindrom. Ak vysledne cislo je palindrom (citatelne rovnako zlava-doprava aj zprava-dolava), skoncili sme. V opacnom pripade vysledne cislo po sucte vezmeme, spravime jeho reverz a scitame s reverznutym cislom. Skontorlujeme ci palindrom, inak opakujeme postup. V pripade cisla 195 sa nasiel po 4 scitani a vypise sa pocet scitani a vysledny palindrom: 4 9339*
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Princip reverzu
//na vstupe cislo 195
//r = 0 * 10 + 195 % 10 = 5
//num = 195 / 10 = 19
//------repeat--------
//na vstupe cislo 19
//r = 5 * 10 + 19 % 10 = 59
//num = 19 / 10 = 1
//------repeat--------
//na vstupe cislo 1
//r = 59 * 10 + 1 % 10 = 591
//num = 1 / 10 = 0
//Koniec cyklu kvoli podmienke while(num=0)

unsigned long reverse(unsigned long num) {
    unsigned long r=0;
    while(num) { 
        r = r * 10 + num % 10; 
        num = num/10;
    }
    return r;
}

int main() {
    unsigned long cisel, i,c,c2,j;

    scanf("%lu", &cisel);
    for (i=0; i<cisel; i++) {
        scanf("%lu",&c); //Skenujem konkretne cislo napr. 195

        for(j=1; j<=1000; j++) {//Cyklus bude obmedzeny cislom 1000

            c2=reverse(c); //reverzia cisla 195 cize. 591
            c+=c2; //scitanie do dalsej premennej aj s reverzom cize v bude c= 195 + 591

            c2=reverse(c); //reverz scitaneho aby sa vedelo, ci vysledok v premennej c je naozaj palindrom, cize v c=786 a v c2=687

            if (c==c2) { //porovnanie v pripade zhody cize cislo bude palindrom
                printf("%lu %lu\n", j, c); //vypis a nasledovny break
                break;
            }
        }
    }
    return 0;
}