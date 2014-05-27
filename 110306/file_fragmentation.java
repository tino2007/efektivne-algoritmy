import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

/*
POPIS   
------------------
Your friend, a biochemistry major, tripped while carrying a tray of computer files through the lab. All of the files fell to the ground and broke. Your friend picked up all the file fragments and called you to ask for help putting them back together again.

Fortunately, all of the files on the tray were identical, all of them broke into exactly two fragments, and all of the file fragments were found. Unfortunately, the files didn't all break in the same place, and the fragments were completely mixed up by their fall to the floor.

The original binary fragments have been translated into strings of ASCII 1's and 0's. Your job is to write a program that determines the bit pattern the files contained.

VSTUP
------------------
The input begins with a single positive integer on its own line indicating the number of test cases, followed by a blank line. There will also be a blank line between each two consecutive cases.

Each case will consist of a sequence of ``file fragments,'' one per line, terminated by the end-of-file marker or a blank line. Each fragment consists of a string of ASCII 1's and 0's.

VYSTUP
------------------
For each test case, the output is a single line of ASCII 1's and 0's giving the bit pattern of the original files. If there are 2N fragments in the input, it should be possible to concatenate these fragments together in pairs to make N copies of the output string. If there is no unique solution, any of the possible solutions may be output.

Your friend is certain that there were no more than 144 files on the tray, and that the files were all less than 256 bytes in size.

The output from two consecutive test cases will be separated by a blank line.

Sample Input
------------------
1

011
0111
01110
111
0111
10111

Sample Output
------------------
01110111

*/

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int trials = sc.nextInt();
		sc.nextLine();
		sc.nextLine();
		for (int tri = 0; tri < trials; tri++) {
			if (tri != 0) {
				System.out.println();
			}
			String str;
			LinkedList<String> files = new LinkedList<String>();
			while (sc.hasNext() && !(str = sc.nextLine()).equals("")) {
				files.add(str);
			}
			Collections.sort(files, new StringComp());
			int size = files.peekFirst().length() + files.peekLast().length();
			int[] solutions = new int[files.size()];
			//System.out.println(files);
			solve(0,solutions,files, size, new boolean[files.size()]);
			System.out.println(files.get(solutions[0]) + files.get(solutions[1]));
			
			for(int i = 0; i < solutions.length; i++) {
				//System.out.print(solutions[i] + ",");
			}
			//System.out.println();
			
		}
	}
	public static boolean solve(int spot, int[] index, LinkedList<String> files, int size, boolean[] used) {
		if(spot == index.length) {
			return true;
		}
		if(spot >= 2 && spot % 2 == 0) {
			String currString = files.get(index[0]) + files.get(index[1]);
			if(currString.length() != size) {
				return false;
			}
			for(int i = 3; i < spot; i+=2) {
				String temp = files.get(index[i-1]) + files.get(index[i]);
				if(!currString.equals(temp)) {
					return false;
				}
			}
		}
		for(int i = 0; i < files.size(); i++) {
			if(!used[i]) {
				used[i] = true;
				index[spot] = i;
				boolean works = solve(spot+1, index, files, size, used);
				if(works) {
					return true;
				}
				used[i] = false;
				index[spot] = 0;
			}
		}
		return false;
	}

}
class StringComp implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.length() - o2.length();
	}

}
