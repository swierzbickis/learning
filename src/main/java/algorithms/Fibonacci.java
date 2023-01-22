package algorithms;

public class Fibonacci {

    public static void main(String[] args) {


//        String value ->System.out.printf("%d", value);
//
//        () -> System.out.println("Welcome to Island");


        System.out.println("Return number with recursive version: " + recursiveFibonnacci(6));
        System.out.println("Return number with recursive version with operand: " + recursiveFibonnacciWithOperand(6));
    }


    public static long recursiveFibonnacci(long number) {

        if (number == 0) {
            return 0;
        } else if (number == 1) {
            return 1;
        } else {
            return recursiveFibonnacci(number - 2) + recursiveFibonnacci(number - 1);
        }

    }

    public static long recursiveFibonnacciWithOperand(long number) {

        return (number == 0) ? 0 :
                (number == 1) ? 1 :
                        recursiveFibonnacciWithOperand(number - 2) + recursiveFibonnacci(number - 1);


    }


}
