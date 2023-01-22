package algorithms;

public class Factorial {

    public static void main(String[] args) {


        System.out.println("Recursive factorial value: " + recursiveFactorial(5));

    }


    private static long recursiveFactorial(long number) {

        if (number <= 1)
            return 1;
        else
            return number * recursiveFactorial(number - 1);

    }




}
