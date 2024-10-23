import java.util.Scanner;

/*
 	FOR FUTURE SELF HERE'S WHAT U DID:
 		1. For each muna para isa-isahin yung LETTERS ng buong WORD na gustong itago
 		2. Hinanap yung INDEX ng kada LETTER + SHIFT
 		3. Kung ano ngayon yung mahahanap na INDEX yun yung gagamitin para i-search o
 		hanapin yung new value nito.
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
		
		// Define the shift pattern
//		THIS TWO ARE ADDED
        int[] shifts = {3, 1, 7, 4, 2, 5};
        int shiftIndex = 0;
        
        int search = 0; // Track the current shift from the array

		String wordToTransfer = word;
		char[] wordArray = wordToTransfer.toCharArray();

		for(char letter : wordArray) {

				search = alphabetv1.indexOf(letter) + shifts[shiftIndex];
//				System.out.println(search) used for debugging;
				
				if(Character.isWhitespace(letter)) {
					result += letter;
					continue;
				}
				
//				This condition if used if the search value exceed the alphabet length
//				may -1 kasi array ito 0 to 25, not 1 to 26 ang alphabet
				if(search > alphabetv1.length() - 1) {
	/*			
	 			Sample if yung value ng SEARCH ay 27, sobra dahil sa + shift.
				Modulo natin yun sa length ng alphabet which is 26.
				
				So: Ilang 26 meron sa 27, isa, ilan natira isa din so
				SEARCH = 1
	*/
					search = search % alphabetv1.length();
				}
//				then pag hinanap "A" na yung makikita
				result += alphabetv2[search];
				// Move to the next shift in the pattern, reset after the last one
				
//				UNDERSTAND THIS ONE
	            shiftIndex = (shiftIndex + 1) % shifts.length;
			}
	
			
		
		return result;
	}
}
