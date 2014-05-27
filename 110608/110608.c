#include <stdio.h>
#include <stdlib.h>

/*
Consider the process of stepping from integer x to integer y along integer points of
the straight line. The length of each step must be non-negative and can be one bigger
than, equal to, or one smaller than the length of the previous step.
What is the minimum number of steps in order to get from x to y? The length of
both the ﬁrst and the last step must be 1.

Input
The input begins with a line containing n, the number of test cases. Each test case that
follows consists of a line with two integers: 0 ≤ x ≤ y < 2^31.

Output
For each test case, print a line giving the minimum number of steps to get from x to y.
Sample Input
3
45 48        1 1 1
45 49        1 2 1
45 50        1 2 1 1
Sample Output
3
3
4
*/

int main()
{
    long x, y, z;
    int pocet, pom;
    scanf("%d\n", &pocet);  //pocet=1
    for(pom=0; pom<pocet; pom++){   //cyklus ktorý načítava hodnoty v riadkoch
        scanf("%ld %ld\n", &x,&y);  //x=1 y=33
        if(0<=x<=y){
            z=y-x;  //z=32
            int odmocnina = sqrt(z);    /*odmocnina=5, vytvoriť ako keby pole: (1 2 3 4 5 4 3 2 1), to ale nie je potrebné
                                          pretože potrebujeme len údaje dlzka a sucet a to sa dá vypočítať
                                        */
            int dlzka=odmocnina*2-1;    //dlzka=9 (dĺžka poľa)
            int sucet=odmocnina*odmocnina;  //sucet=25  (súčet hodnôt v poli)

            int cislo= z-sucet; //cislo=7 (treba určiť či ešte premennú "dlzka" treba zväčšovať, ak je cislo=0 tak je to vysledok)
            while(cislo>=odmocnina){    /*ak je cislo>=odmocnina, je potrebne do pola pridať hodnotu odmocniny
                                          (1 2 3 4 5 5 4 3 2 1)
                                        */
                cislo=cislo-odmocnina;  //cislo=2, odčítať pridanú hodnotu do poľa
                dlzka++;    //dlzka=10, zväčšiť dlžku poľa o 1
            }
            if(cislo>=0)    //ak je cislo>=0 treba ešte pridať do pola hodnotu "cisla" (1 2 3 4 5 5 4 3 2 2 1)
                dlzka++;    //dlzka=11, zväčšiť dlžku poľa o 1

            printf("%d\n", dlzka);
        }
    }
    return 0;
}
