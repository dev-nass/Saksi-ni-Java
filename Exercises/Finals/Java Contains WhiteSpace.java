import java.util.Scanner;

// JUNE 28, 2024
// Check if the user input any space within the text

public class containsWhiteSpace  {

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

        // converts the STRING to CHAR ARRAY
        for (char a : sTobeCheck.toCharArray()) {
            // check each and every index of the array if it contains any space
            if(Character.isSpace(a)) {
                // return TRUE if it does
               return true;
            }
        }
        // if the loop ends and doens't find any, it will return false
        return false;
    }

}
