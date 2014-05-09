import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
 
public class Main {

 public static void main(String[] args) throws NumberFormatException, IOException{
	  final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	  //final BufferedReader in = new BufferedReader(new FileReader("in.txt"));
	  
	  // nacita sa cislo ktore urcuje pocet testovacich pripadov (test cases)
	  int numTestCases = Integer.parseInt(in.readLine());
	  // pre kazdy test case
	  for(int testCase = 1; testCase <= numTestCases; testCase++){
		   // precitaj prazdny riadok
		   in.readLine();
		   
		   // precitaj dimenzie a podla toho vytvor 2d pole charov
		   String[] dimensions = in.readLine().split(" ");
		   int numRows = Integer.parseInt(dimensions[0]);
		   int numCols = Integer.parseInt(dimensions[1]);
		   char[][] grid = new char[numRows][numCols];
		   
		   // napln 2d pole charov
		   for(int row = 0; row < numRows; row++){
				char[] chars = in.readLine().trim().toCharArray();
				for(int col = 0; col < chars.length; col++){
					grid[row][col] = Character.toLowerCase(chars[col]);
				}
		   }
		
		   // nacita sa cislo ktore urcuje pocet slov ktore sa maju hladat
		   int numWords = Integer.parseInt(in.readLine().trim());
		   // kazde slovo sa postupne nacita a zaroven sa hlada jeho vyskyt v 2d poli 
		   for(int w = 0; w < numWords; w++){
				String word = in.readLine().trim();
				// spusta sa hladanie v 2d poli
				int[] coords = findString(grid, word.toLowerCase());
				// vypis koordinatov
				System.out.println(coords[0] + " " + coords[1]);
		   }
			
		   // po spracovani testu sa musi vypisat prazdny riadok (oddelenie vypisov test case-ov)
		   if(testCase < numTestCases)
			System.out.println();
	  }
 }
  
 static int[] findString(char[][] grid, String string){
	  // prechadza sa cele 2d pole po policku 
	  for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[row].length; col++){
				if(grid[row][col] == string.charAt(0)){ // ak sa znak v 2d poli rovna prvemu znaku hladaneho slova
					 // skusaju sa najst slovo vo vsetkych smeroch (ako 8 smerovka)
					 if(
					  // vychodny smer posle sa 2d pole, hladane slovo, riadok a stlpec kde sa naslo prve pismeno slova
					  checkEast(grid, string, row, col) || 
					  // sever
					  checkNorth(grid, string, row, col) ||
					  // severo-vychod
					  checkNorthEast(grid, string, row, col) ||
					  // severo-zapad
					  checkNorthWest(grid, string, row, col) || 
					  // juh
					  checkSouth(grid, string, row, col) ||
					  // juho-vychod
					  checkSouthEast(grid, string, row, col) ||
					  // juho-zapad
					  checkSouthWest(grid, string, row, col) ||
					  // zapad
					  checkWest(grid, string, row, col)
					  )
					  return new int[] {row + 1, col + 1};
				}
			}
	  }
	  
      // nemalo by sa sem nikdy dostat (v zadani bolo ze kazde slovo sa v mriezke charov nachadza aspon raz)
	  return new int[] {0, 0}; 
 }
  
 /* tu okomentujem 2 smery hladania (ostatne su analogicke) */
 
 /*sever*/
 static boolean checkNorth(char[][] grid, String string, int row, int col){
	  int i = 0;
	  /*  
	  dokym nie sme mimo pola(row >= 0)
	  a zaroven nie sme mimo stringu(i < string.length())
	  a zaroven znak pola == znaku hladaneho slova (grid[row][col] == string.charAt(i))
	  tak chod o riadok vyssie (stlpec ostava), a chod na dalsi znak slova (row--, i++)
	  */
	  for(; row >= 0 && i < string.length() && 
		grid[row][col] == string.charAt(i); row--, i++);
	  //vysli sme z cyklu ak sa i == poctu znakov slova, tak sme nasli cele slovo
	  return (i == string.length());
 }
  
 static boolean checkSouth(char[][] grid, String string, int row, int col){
	  int i = 0;
	  for(; row < grid.length && i < string.length() && 
		grid[row][col] == string.charAt(i); row++, i++);
	  return (i == string.length());
 }
  
 static boolean checkWest(char[][] grid, String string, int row, int col){
	  int i = 0;
	  for(; col >= 0 && i < string.length() && 
		grid[row][col] == string.charAt(i); col--, i++);
	  return (i == string.length());
 }
  
 static boolean checkEast(char[][] grid, String string, int row, int col){
	  int i = 0;
	  for(; col < grid[row].length && i < string.length() && 
		grid[row][col] == string.charAt(i); col++, i++);
	  return (i == string.length());
 }
 
 /*severo-vychod*/
 static boolean checkNorthEast(char[][] grid, String string, int row, int col){
	  int i = 0;
	  /*  
	  dokym nie sme mimo pola,(row >= 0) && (col < grid[row].length)
	  a zaroven nie sme mimo stringu(i < string.length())
	  a zaroven znak pola == znaku hladaneho slova (grid[row][col] == string.charAt(i))
	  tak chod o riadok vyssie, o stlpec dalej do lava, a chod na dalsi znak slova (row--, col++, i++)
	  */
	  for(; row >= 0 && col < grid[row].length && 
	   i < string.length() && grid[row][col] == string.charAt(i); row--, col++, i++);
	  //vysli sme z cyklu ak sa i == poctu znakov slova, tak sme nasli cele slovo
	  return (i == string.length());
 }
  
 static boolean checkNorthWest(char[][] grid, String string, int row, int col){
	  int i = 0;
	  for(; row >= 0 && col >= 0 && i < string.length() && 
		grid[row][col] == string.charAt(i); row--, col--, i++);
	  return (i == string.length());
 }
  
 static boolean checkSouthWest(char[][] grid, String string, int row, int col){
	  int i = 0;
	  for(; row < grid.length && col >= 0 && i < string.length() && 
		grid[row][col] == string.charAt(i); row++, col--, i++);
	  return (i == string.length());
 }
  
 static boolean checkSouthEast(char[][] grid, String string, int row, int col){
	  int i = 0;
	  for(; row < grid.length && col < grid[row].length && i < string.length() && 
		grid[row][col] == string.charAt(i); row++, col++, i++);
	  return (i == string.length());
 }
}