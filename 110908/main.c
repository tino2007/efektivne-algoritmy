#include <stdio.h>
#include <math.h>

// kolko sa zmesti lopticiek na pocet kolikov
// pocet kolikov je index
// toto sa pocita od 1
// teda index 1 je pocet kolikov 1
int riesenia[55];
// aka je posledna / vrchna lopticka na koliku
// kolik je index
// toto sa pocita od nula
// index 0 je kolik 1
int koliky[55];

// funkcia, ktora zistuje, ci hodnota lopticky na vrchole koliku + hodnota lopticky,
// ktoru chceme umiestnit davaju druhu mocninu nejakeho celeho cisla
// a teda podla zadania je mozne lopticku umiestnit na tento kolik
int je_mozne_umiestnit(int lopticka_plus_lopticka_na_vrchole)
{
  // pre 26 vyjde vysledok 5
  int x = (int) sqrt(lopticka_plus_lopticka_na_vrchole);
  // a 5 * 5 nie je 26
  return x*x == lopticka_plus_lopticka_na_vrchole;
}

int main()
{
  // hodnota na lopticke
  int lopticka = 1;
  // pocet kolikov, ktore bolo doteraz treba (na ktore sa snazime umiestnit lopticku)
  // 0 znamena 0 kolikov na zaciatok
  int pocet_kolikov = 0;

  // postupne najdeme riesenie pre vsetky pocty kolikov
  // potrebujeme riesenia iba pre maximalne 50 kolikov
  // toto sa rata od 1 v poli riesenia[]
  while(pocet_kolikov <= 50)
  {
    // pokusime sa umiestnit lopticku na vrchol nejakeho
    // z doterajsieho poctu kolikov

    // ci sa podarilo umiestnit
    int uz_umiestnene = 0;
    int i;
    // ked sa hlada riesenie pre pocet_kolikov 2 skusa sa umiestnovat na koliky 0 a 1
    for(i = 0; i < pocet_kolikov; i++)
    {
      // ak je mozne umiestnit, umiestnime
      if(je_mozne_umiestnit(lopticka + koliky[i]))
      {
        // na vrchol tohto kolika ide terajsia lopticka
        koliky[i] = lopticka;
        // tato uz bola umiestnena, ide dalsia
        lopticka++;
        // ze sa podarilo umiestnit
        uz_umiestnene = 1;
        break;
      }
    }

    // ak by sa nepodarilo umiestnit lopticku na doterajsi pocet kolikov
    // treba lopticku umiestnit na dalsi kolik a zaroven mame riesenie
    // pre doterajsi pocet kolikov
    if(!uz_umiestnene)
    {
      // na kolik 0 a 1 sa nepodarilo umiestnit, tak sa da lopticka na kolik 2
      // a mame riesenie pre pocet kolikov 2
      koliky[i] = lopticka;
      // pocet lopticiek, ktore je mozne umiestnit na predchadzajuci pocet (napr. 2)
      // kolikov je rovny predchadzajucej hodnote lopticky
      riesenia[i] = lopticka - 1;

      // dalsia lopticka
      lopticka++;

      // hladame riesenie pre o jeden kolik viac
      pocet_kolikov++;
    }
  }

  // teraz sa nacita pocet test cases a vypise sa odpoved pre
  // zadane pocty kolikov
  int pocet_test_cases;
  scanf("%d", &pocet_test_cases);
  int i;
  for(i = 0; i < pocet_test_cases; i++)
  {
    int j;
    scanf("%d", &j);
    printf("%d\n", riesenia[j]);
  }

    return 0;
}
