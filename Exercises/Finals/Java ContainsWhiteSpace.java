import java.util.Scanner;

// JUNE 28, 2024
// Check if the user input any space within the text

public class ContainsWhiteSpace  {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    	
    	String sUserName = "";
    	do {
    		System.out.print("Enter your name: ");
            sUserName = scanner.nextLine();
            methodWhiteSpaceChecker(sUserName);
    	} while(methodWhiteSpaceChecker(sUserName));
        
    	System.out.println("Hello, "+sUserName);

    }

    static boolean methodWhiteSpaceChecker(String sTobeCheck) {

        for (char a : sTobeCheck.toCharArray()) {
            if(Character.isSpace(a)) {
               return true;
            }
        }
        return false;
    }

}
