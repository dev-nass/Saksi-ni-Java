import java.util.Scanner;

// JUNE 28 2024
// Factorial Calculator
// I did not add the factorial of 0 == 1 on v2, bcz I can easily copy it from v1

public class FactorialCalcu {
	
	static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.print("Enter the number to be factorial: ");
		int iUserFactorialNum = scanner.nextInt();
		
		// holds the original value given by the user
		int iUserFactorialNumv2 = iUserFactorialNum;
		// holds the answer each iteration and the final answer
		long answer = 0;
		

		// v1 = process 5*1 ... 5*2 ... 10*3 ... 30*4 
		// 120*5 wont happen because of the condition (i != iUserFactorialNum)
		// Basically, the loop will iterate (i) as long as it wont be equivalent to the value given (iUserFactorialNumv2)
		
		for (int i = 1; i != iUserFactorialNumv2; i++) {
			// ensures that if the value given is greater than 0 it will do the explanation above v1....
			if (iUserFactorialNumv2 > 0) {
				if (i == 1) {
					answer = iUserFactorialNum * i;
				}
				// ensures, if the value given is 0 the factorial is always == 1
			} else if (iUserFactorialNumv2 == 0) {
				answer = 1;
				break;
			}
			
			// the main cause, and reason that factorial was able to be calculated
			answer = answer * i;
			
		}
	

		
/*
		// V2 = process 5*4 ... 20*3 ... 60*2 ... 120*1
		// 120*5 wont happen because of the condition (i < iUserFactorialNumv2)
		for (int i = 1; i < iUserFactorialNumv2; i++) {
			// given value is passed 
			// the condition ensure that it will only happen on first iteration
			if (iUserFactorialNum == iUserFactorialNumv2) {
				answer = iUserFactorialNum;
			}
			
			
			iUserFactorialNum--;
			answer = answer * iUserFactorialNum;
			
		}
*/	
		System.out.println(answer);
	}
}