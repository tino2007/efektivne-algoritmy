//110203 Hartals
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
 
public class Hartals {
 
 public static int simulate(int numDays, int[] hartalParams){
 
  BitSet hartals = new BitSet(numDays);
 
  for(int hartalParam : hartalParams){
   for(int day = hartalParam; day <= numDays; day = day + hartalParam){
    if(!isFriday(day) && !isSaturday(day)){
     hartals.set(day - 1, true);
    }
   }
  }
 
  return hartals.cardinality();
 
 }
 
 public static boolean isFriday(int day){
  int week = getCurrentWeek(day);
  return (day == (6 + 7 * (week - 1)));
 }
 
 public static boolean isSaturday(int day){
  int week = getCurrentWeek(day);
  return (day == (7 + 7 * (week - 1)));
 }
 
 public static int getCurrentWeek(int day){
  return (int)Math.ceil((double) day / 7);
 }
 
 public static void main(String[] args) throws IOException{
  final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
  String line = in.readLine();
 
  if(line != null){
    int numTestCases = Integer.parseInt(line.trim());
    for(int testCase = 0; testCase < numTestCases; testCase++){
        int numDays = Integer.parseInt(in.readLine().trim());
        int numParties = Integer.parseInt(in.readLine().trim());
 
        int[] hartalParams = new int[numParties ];
        for(int party = 0; party < numParties; party++){
          hartalParams[party] = Integer.parseInt(in.readLine().trim());
        }
        System.out.println(simulate(numDays, hartalParams));
       }
      }
    }
}