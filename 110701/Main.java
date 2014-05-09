import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
    	Scanner in = new Scanner(System.in);
        long N;
        while ((N = in.nextLong()) != 0) {
            
        	long sq = (long) Math.floor(Math.sqrt(N)); // vypoèítame odmocninu naèítaného èísla a zaokruhlime 
            
            if(sq * sq == N){ // ak sa vynásobená odmocnica rovná pôvodnému èíslu tak svetlo svieti
            	System.out.println("yes");
            }
            else {
            	System.out.println("no");
            }
        }   
    }
}