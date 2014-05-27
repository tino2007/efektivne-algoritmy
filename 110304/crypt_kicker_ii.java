import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
/*

POPIS
------------------
A popular but insecure method of encrypting text is to permute the letters of the alphabet. That is, in the text, each letter of the alphabet is consistently replaced by some other letter. To ensure that the encryption is reversible, no two letters are replaced by the same letter.

A powerful method of cryptanalysis is the known plain text attack. In a known plain text attack, the cryptanalyst manages to have a known phrase or sentence encrypted by the enemy, and by observing the encrypted text then deduces the method of encoding.

Your task is to decrypt several encrypted lines of text, assuming that each line uses the same set of replacements, and that one of the lines of input is the encrypted form of the plain text the quick brown fox jumps over the lazy dog.

VSTUP
-----------------------
The input begins with a single positive integer on a line by itself indicating the number of test cases, followed by a blank line. There will also be a blank line between each two consecutive cases.

Each case consists of several lines of input, encrypted as described above. The encrypted lines contain only lowercase letters and spaces and do not exceed 80 characters in length. There are at most 100 input lines.

VYSTUP
------------------------

For each test case, decrypt each line and print it to standard output. If there is more than one possible decryption, any one will do. If decryption is impossible, output

No solution.
The output of each two consecutive cases must be separated by a blank line.

Sample Input
------------------

1

vtz ud xnm xugm itr pyy jttk gmv xt otgm xt xnm puk ti xnm fprxq
xnm ceuob lrtzv ita hegfd tsmr xnm ypwq ktj
frtjrpgguvj otvxmdxd prm iev prmvx xnmq

Sample Output
------------------
now is the time for all good men to come to the aid of the party
the quick brown fox jumps over the lazy dog
programming contests are fun arent they

 
*/
public class Main {
	static String check = "the quick brown fox jumps over the lazy dog";
	static String[] checkVars = check.split(" ");

	public static void main(String[] args) throws FileNotFoundException {
		//System.setIn(new FileInputStream("in.in"));
		Scanner sc = new Scanner(System.in);
		int trials = sc.nextInt();
		sc.nextLine();
		sc.nextLine();
		for (int tri = 0; tri < trials; tri++) {
			if(tri != 0) {
				System.out.println();
			}
			ArrayList<String> message = new ArrayList<String>();
			String s;
			while (sc.hasNext() && !(s = sc.nextLine()).equals("")) {
				//System.out.println(s);
				message.add(s);
			}
			HashMap<Character, Character> map = findLine(message);
			if (map.isEmpty()) {
				System.out.println("No solution.");
			} else {
				for (String str : message) {
					for (int i = 0; i < str.length(); i++) {
						if (map.containsKey(str.charAt(i))) {
							System.out.print(map.get(str.charAt(i)));
						} else {
							System.out.print(str.charAt(i));
						}
					}
					System.out.println();
				}
			}
		}
	}

	private static HashMap<Character, Character> findLine(
			ArrayList<String> message) {

		HashMap<Character, Character> cipher = new HashMap<Character, Character>();
		for (String s : message) {
			if (s.length() == check.length()) {
				String[] vars = s.split(" ");
				boolean sol = true;
				for (int i = 0; i < vars.length; i++) {
					if (vars[i].length() != checkVars[i].length()) {
						sol = false;
					}
				}
				if (sol) {
					cipher.clear();
					for (int i = 0; i < s.length(); i++) {
						if (!cipher.containsKey(s.charAt(i))) {
							cipher.put(s.charAt(i), check.charAt(i));
						} else {
							if (!cipher.get(s.charAt(i))
									.equals(check.charAt(i))) {
								sol = false;
							}
						}
					}
				}
				if (sol) {
					return cipher;
				} else {
					cipher.clear();
				}
			}
		}
		return cipher;
	}
}
