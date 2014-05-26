#include <stdio.h>
#include <stdlib.h>

int main()
{
    long x, y, z;
    int pocet, pom;
    scanf("%d\n", &pocet);
    for(pom=0; pom<pocet; pom++){
        scanf("%ld %ld\n", &x,&y);
        if(0<=x<=y){
            z=y-x;
            int odmocnina = sqrt(z);
            int *pole = malloc(sizeof(odmocnina*2-1));
            int i, j;
            for(i=0; i<odmocnina; i++){
                pole[i]=i+1;
            }

            for(j=odmocnina; j<odmocnina*2-1; j++){
                i--;
                pole[j]=i;
            }




            int sucet=0;
            for(i=0; i<odmocnina*2-1; i++)
                sucet+=pole[i];

            int cislo= z-sucet;
            while(cislo>=odmocnina){
                cislo=cislo-odmocnina;
                j++;
            }
            if(cislo!=0)
                j++;

            printf("%d\n", j);


            free(pole);
        }
    }



    return 0;
}
