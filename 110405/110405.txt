#include <stdio.h>
#include <stdlib.h>

typedef struct
{
    int index;
    double priorita;

} PRIORITA;

int cmp (const void * a, const void * b)
{
    PRIORITA *x = (PRIORITA *)a;
    PRIORITA *y = (PRIORITA *)b;
    if (x->priorita > y->priorita) return -1;
    else if (x->priorita == y->priorita)
    {
        if (x->index < y->index) return -1;
        else return 1;
    }
    else return 1;
}

int main()
{

    int pripadov, pprac;
    PRIORITA pr[1000];

    scanf("%d", &pripadov);
//    printf("%d\n\n", pripadov);

    char str[100];
    gets(str);


    int i,j, dni, penalizacia;

    for (i=0; i<pripadov; i++)
    {
        scanf("%d", &pprac);
        //printf("%d\n", pprac);
        for (j=0; j<pprac; j++)
        {
            scanf("%d %d",&dni, &penalizacia);
//            printf("%d %d; ",dni, penalizacia);
            pr[j].index=j+1;
            pr[j].priorita = 1.0*penalizacia/dni;
//            printf("%lf\n", pr[j].priorita);
        }
        //if (i<pripadov-1) gets(str);

        qsort(pr, pprac, sizeof(PRIORITA), cmp);

        for (j=0; j<pprac; j++)
        {
            printf("%d", pr[j].index);
            if (j<pprac-1) putchar(' ');

        }
        putchar('\n');
        if (i<pripadov-1)
            putchar('\n');
    }

    return 0;
}
