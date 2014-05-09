import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {

/* funkcia na vypocet minimalnej sumy ktora sa musi prerozdelit aby sa vyrovnali naklady s presnostou na 1 cent */
 public static int calculateAmountToExchange(int[] expenses) {
	  int amountToExchange = 0; // suma ktora sa musi prerozdelit
	  int numberOfStudents = expenses.length;
	 
	  // vypocet priemerneho nakladu na studenta
	  long averageExpense = 0; // v centoch
	  long totalExpenses = 0; // v centoch
	  for (double expense : expenses)
	   totalExpenses += expense;
	  // priemerny naklad sa zaokruhli
	  averageExpense = Math.round((double) totalExpenses / numberOfStudents);
	  
	  int totalReceived = 0, totalGiven = 0;
	  for (int i = 0; i < numberOfStudents; i++) {
		   // rozdiel medzi terajsim nakladom studenta a priemernym
		   long diff = expenses[i] - averageExpense;
		   if(diff > 0)
				// ak je kladny tak student potrebuje prijat peniaze
				totalReceived += diff;
		   else
				// ak je zaporny tak student potrebuje odovzdat peniaze
				totalGiven -= diff;
	  }
	  
	  // vysledkom je to mensie cislo z nich
	  amountToExchange = totalReceived < totalGiven ? totalReceived : totalGiven;
	 
	  return amountToExchange;
 }
 
 public static void main(String[] args) throws IOException {
	  final BufferedReader in = new BufferedReader(new InputStreamReader(
		System.in));
	  String line;
	  int numberOfStudents = 0; // pocet studentov
	  int[] expenses = null; // naklady v centoch
	 
	  while ((line = in.readLine()) != null) {
		   try {
				// nacita sa cislo ktore urcuje pocet studentov
				numberOfStudents = Integer.parseInt(line);
		   } catch (NumberFormatException e) {
				// ak dojde k chybe pri parsovani cisla tak nastavime pocet studentov na 0 (tam dalej sa kvoli tomu ukonci program)
				numberOfStudents = 0;
		   }
		   if (numberOfStudents == 0)
			return; // podla zadania ked sa objavi 0 tak sa ma ukoncit program
			
		   // nacitaju sa jednotlive naklady studentov
		   expenses = new int[numberOfStudents];
		   for (int i = 0; i < numberOfStudents; i++) {
				line = in.readLine();
				// vykrati sa 100-vkou lebo naklady chceme mat v centoch 
				float value = Float.parseFloat(line) * 100;
				// zaokruhli sa cislo pre pripad ze tam bolo daco napisane na viac ako na 2 desatinne miesta (nemalo by sa stat)  
				expenses[i] = Math.round(value); 
		   }
			
		   // ma sa vypocitat minimalna suma ktora sa musi prerozdelit aby sa vyrovnali naklady s presnostou na 1 cent
		   int amount = calculateAmountToExchange(expenses);
		   // vypis vysledku
		   System.out.print("$"+amount/100+".");
		   // ak je zvysok po deleni cislom 100 < 10 (menej ako 10 centov musi sa vypisat predto este 0 -> napriklad .08)
		   if(amount%100 < 10)
				System.out.print("0");
		   System.out.println(amount%100);
	  }
 }
}