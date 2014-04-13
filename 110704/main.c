#include <stdio.h>
#include <math.h>
#include <stdlib.h>
#define N 47000
static char* primes;
static int* dd;
void cachePrimes(int n)
{
    int i,j,k;
    primes = (char*) malloc((n+1)*sizeof(char));
    for(i = 0;i<=n;i++)
        primes[i] = 0;
    primes[0]=1;
    primes[1] = 1;
    k = sqrt(n);
    for(i = 2;i<=k;i++)
        if(!primes[i])
            for(j=i*i;j<=n;j+=i)
                primes[j] = 1;
}
int* divisors(int n)
{
    int i,c;
    int* a = (int*) malloc(32*sizeof(int));
    dd = (int*) malloc(32*sizeof(int));

    int top = 0;
    for(i = 0;i<32;i++)
    {
        a[i] = 0;
        dd[i] = 0;
    }
    for(i = 2;i<N&&n!=1;i++)
    {
        if(!primes[i])
        {
            c = 0;
            while(n%i==0)
            {

                c++;
                n = n/i;
            }
            if(c)
            {
                a[top] = i;
                dd[top]=c;
                top++;
            }
        }
    }
    if(n!=1)
    {
        a[top] = n;
        dd[top] = 1;
        top++;
    }
    return a;
}
int main()
{
    int n,m,i,ok,l,s,j;
    int *a;
    cachePrimes(N);
    while(scanf("%d %d",&n,&m)==2)
    {
        if(n==0&&m!=1)
                printf("%d does not divide %d!\n",m,n);
    else if(m>n)
        {
                a = divisors(m);
                ok = 1;
                for(i=0;a[i];i++)
                {
                    if(a[i]*dd[i]>n)
                    {

                        l = log(n)/log(a[i]);
                        s = l*(l+1)/2 - l;
                        for(j = a[i];s<dd[i]&&j<=n;j+=a[i])
                            s++;
                            if(s<dd[i])
                            {
                                ok = 0;
                            }
                    }
                }
                if(ok)
                    printf("%d divides %d!\n",m,n);
                else
                    printf("%d does not divide %d!\n",m,n);

        }
        else if(m!=0)
            printf("%d divides %d!\n",m,n);
        else
            printf("%d does not divide %d!\n",m,n);
    }

    return 0;
}
