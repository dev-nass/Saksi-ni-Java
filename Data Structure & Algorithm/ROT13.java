import java.util.Scanner;

/*
 	FOR FUTURE SELF HERE'S WHAT U DID:
 		1. Identify the pattern required which is 3, 1, 7, 4, 2, 5.
 		2. Used another variable that will increment through the array pattern.
 		3. Shift the current letter based on its alphabet index + the current SHIFT.
*/
public class Main {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		
	System.out.print("Enter the word to cipher: ");
	String yourWord = scanner.nextLine().toUpperCase();
	
	System.out.println(cipher(yourWord));
	
	}
	
	static String cipher(String word) {
		String result = "";
		
		String alphabetv1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] alphabetv2  = alphabetv1.toCharArray();
		
		
        int[] shifts = {3, 1, 7, 4, 2, 5}; // Define the shift pattern
        int shiftIndex = 0; // used for iterating through the shift pattern
        int search = 0; // Track the current shift from the array

		String wordToTransfer = word;
		char[] wordArray = wordToTransfer.toCharArray();

		for(char letter : wordArray) {

				search = alphabetv1.indexOf(letter) + shifts[shiftIndex];
				
				if(Character.isWhitespace(letter)) {
					result += letter;
					continue;
				}
				
//				NOTE: .length method return base 1 value, not starting to 0
				if(search > alphabetv1.length() - 1) {
					search = search % alphabetv1.length();
				}

				result += alphabetv2[search];

				
/*				NOTE: Responsible for incrementing shiftIndex by one everytime the loop runs.
 				If 6 % 6 is reached, however, it will return to 0 index hence repeating the process
 				To understand this fully future self, use simulation screen
*/
	            shiftIndex = (shiftIndex + 1) % shifts.length;
			}
	
			
		
		return result;
	}
}
