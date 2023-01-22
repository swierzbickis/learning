package courses.streams.rolldie;

import java.security.SecureRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RollDie {

    public static void main(String[] args) {

        SecureRandom randomNumbers = new SecureRandom();

        //roll a die 6 milion times ans summarize the results
        randomNumbers.ints(6_000_000, 1, 7) //produces 6 milion numbers from range 1-6 (7 is excluded)
                .boxed() //convert stream of int's -> to stream of Integer's. It is done because in java Collection cannot contains primitive types
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((face, frequency) ->
                        System.out.printf("%-6d%d%n", face, frequency));

//key are the faces of the die --klucz - sciana kostki --np 1,2,3
        //value - number of occurence of each faces - liczba wystapien danej scianki kostki np. sciana 4 wystapila 100 razy
        /*
            Function.identity - tworzy implementację interfejsu - https://stackoverflow.com/questions/28032827/java-8-lambdas-function-identity-or-t-t
            zwraca tez jako klucz sciane kostki
        */


    }

}
