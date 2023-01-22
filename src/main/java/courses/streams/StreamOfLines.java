package courses.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOfLines {

    public static void main(String[] args) {

        //Regex that matches one or more consecutive whitespace characters
        Pattern pattern = Pattern.compile("\\s+");

        // Files.lines(Path path) --zwraca stream z liniami z pliku
        // Paths.get(String path) --zwraca obiekt URI z podanej sciezki
        //("?!') --ignore aposthropies in the rest of the string
        //\\p{P} --find all of the punctuation characters
        // ("?!')\\p{P} - will be replaced by "" character - it means that all of the punctuation characters will be replaced by empty strings

        //.flatMap(line -> pattern.splitAsStream(line)) -- this method will convert the Stream of Lines to the Stream of Words by using the given lambda function

        //wordList -> list of the Map Entry objects that represent a key value pairs

        /*
        !!!!!!!!!!!WAZNA UWAGA!!!!
        there is no way to go directly from a map object to a stream; however,
        there is a way to go from a map to a set of the key value pairs within the map,
         and then go from a set to a stream containing those map entries if you will.


         */


        try {
            //count occurences of each word in a Stream<String> sorted by word
            Map<String, Long> wordsCounts =
                    Files.lines(Paths.get("Chapter2Paragraph.txt"))
                            .map(line -> line.replaceAll("(?!')\\p{P}}", ""))
                            .flatMap(line -> pattern.splitAsStream(line))
                            .collect(Collectors.groupingBy(String::toLowerCase, TreeMap::new, Collectors.counting()));

            wordsCounts.entrySet() //getting map  entry set
                    .stream() // getting stream from entry objects
                    .collect(
                            Collectors.groupingBy(entry -> entry.getKey().charAt(0),
                                    TreeMap::new, Collectors.toList()))
                    .forEach((letter, wordList) ->
                    {
                        System.out.printf("%n%C%n", letter);
                        wordList.stream().forEach(word -> System.out.printf("" +
                                "%13s: %d%n", word.getKey(), word.getValue()));
                    });


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
