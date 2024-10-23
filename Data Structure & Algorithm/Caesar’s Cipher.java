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
	
	System.out.print("How many jump per letter you want: ");
	int yourJump = scanner.nextInt();
	
	System.out.println(cipher(yourWord, yourJump));
	
	}
	
	static String cipher(String word, int jump) {
		String result = "";
		
		String alphabetv1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] alphabetv2  = alphabetv1.toCharArray();
		
		int shift = jump;
		int search = 0;
		String wordToTransfer = word;
		char[] wordArray = wordToTransfer.toCharArray();

		for(char letter : wordArray) {
			search = alphabetv1.indexOf(letter) + shift;
//			System.out.println(search) used for debugging;
			
			if(Character.isWhitespace(letter)) {
				result += letter;
				continue;
			}
			
//			This condition if used if the search value exceed the alphabet length
//			may -1 kasi array ito 0 to 25, not 1 to 26 ang alphabet
			if(search > alphabetv1.length() - 1) {
/*			
 			Sample if yung value ng SEARCH ay 27, sobra dahil sa + shift.
			Modulo natin yun sa length ng alphabet which is 26.
			
			So: Ilang 26 meron sa 27, isa, ilan natira isa din so
			SEARCH = 1
*/
				search = search % alphabetv1.length();
			}
//			then pag hinanap "A" na yung makikita
			result += alphabetv2[search];
		}
		
		
		return result;
	}
}
