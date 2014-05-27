#include <iostream>
#include <cmath>

using namespace std;

typedef unsigned int uint;
/*
Carmichaelovo číslo je take zlozene cislo ktore je podobne prvocilu
ale ich zlozenost nieje mozne zistit podla fermatovho testu
*/


// Funkcia ktorá zisti ci dane cislo je prvocislo odosle false ak je a true ak nieje prvocislo
bool prime(uint n) {
  if (n % 2 == 0)
    return false;
  uint i = 3;
  uint h = sqrt(n) + 1;
  while (i <= h) {
    if (n % i == 0)
      return false;
    i += 2;
  }
  return true;
}

/* Fermatov test aˇn mod n = a
nech a je cislo medzi 2 a n-1
kde n je cislo ktore testujeme
n je PRAVDEPODOBNE prvocislo ak plati podmienka aˇn mod n = a
*/
uint mod(uint a, uint n, uint m) {
  if (n == 1)
    return a;
  uint x = mod(a, n / 2, m);
  x = (x * x) % m;
  if (n % 2)
    x = (x * a) % m;
  return x;
}
// zisti ci je to Carmichael number--> n = testovane cislo
bool carm(uint n) {
  bool r = true;
  for (int i = 2; i < n; i++) {
    if (mod(i, n, n) != i) {
      r = false;
      break;
    }
  }
  if (r && prime(n))
    r = false;
  return r;
}

int main() {
  uint n;
  while (cin >> n && n) {
    if (carm(n))
      cout << "The number " << n << " is a Carmichael number." << endl;
    else
      cout << n << " is normal." << endl;
  }
  return 0;
}
