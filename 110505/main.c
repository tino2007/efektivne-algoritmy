#include <stdio.h>
#include <stdlib.h>

int main()
{
    // hranica, ktoru treba pri hre dosiahnut
    int hranica;
    while(scanf("%d/n", &hranica) != -1)
    {
      // optimalna hra pre Stana
      int stan = 9;
      while(stan < hranica) {
        stan = stan * 2 * 9;
      }

      // optimalna hra pre Ollieho
      int ollie = 2*9;
      while(ollie < hranica)
      {
        ollie = ollie * 2 *9;
      }

      // ako vidno z optimalnych hier, hodnota pre Stana musi by mensia
      // ak nie, vyhral Ollie
      if(stan < ollie)  printf("Stan wins.\n");
      else printf("Ollie wins.\n");
    }
    return 0;
}
