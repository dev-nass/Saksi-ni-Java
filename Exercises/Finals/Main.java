import java.util.Scanner;

// JUNE 21, 2024
// INTEGER TO BINARY CONVERSION

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        binaryConverterMethod();
    }

    // METHODD: responsble for the conversion
    static void binaryConverterMethod() {
        System.out.print("Number to convert to binary: ");
        int iToBinary = scanner.nextInt();

        String answer = "";
        int iQuotinet = 0; // takes the answer for dividing user's number to 2
        int iRemainder = 0; // takes the answer for the remainder(%) of user's number to dividing it to 2
        int iLoopEnder = 0;

        do {
            iQuotinet = iToBinary / 2; // halves the number to ber converted
            iRemainder = iToBinary % 2; // (ilang 2 meron sa 12)

            answer = iRemainder + answer;

            // replaces the orignal number to be converted to its half
            iToBinary = iQuotinet;

        } while (iToBinary > 0);

        System.out.print(answer);

        /*

        ORIGNAL: TESTING FOR CONVERTING NUM 12
        //6
        int firstHalfQuotient = iToBinary / 2;
        int firstHalfRemainder = iToBinary % 2;
        
        // 3
        int secondHalfQuotient = firstHalfQuotient / 2;
        int secondHalfRemainder = firstHalfQuotient % 2;

        // 1
        int thirdHalfQuotient = secondHalfQuotient / 2;
        int thirdHalfRemainder = secondHalfQuotient % 2;

        // 0
        int fourthHalfQuotient = thirdHalfQuotient / 2;
        int fourthHalfRemainder = thirdHalfQuotient % 2;

        String answer = fourthHalfRemainder +" "+ thirdHalfRemainder +" "+ secondHalfRemainder +" "+ firstHalfRemainder;

        System.out.print(answer); 
        */
    }
}