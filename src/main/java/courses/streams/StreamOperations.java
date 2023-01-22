package courses.streams;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
Before processing each stream, we have to use Instream.of(dataset) command in order to produce Instream


 */

public class StreamOperations {

    public static void main(String[] args) {

//        String s = Objects.toString(null);
//        if (s != null) {
//            System.out.println("NOT NULL " + s);
//        } else {
//            System.out.println("NULL!!!!!!!");
//        }


        int[] values = {3, 10, 6, 1, 4, 8, 2, 5, 9, 7};
        //int[] values = {};


        //testSimpleLambda(values);

        //testForEachMethod(values);
        //testOOTBReduceMethods(values);
        //testCustomReduceMethods(values);
        //testFlterAndSortMethods(values);
        //testRangeConditions();
        //testIntegerOperations();
        // testStringOperations();
        //testEmployees();
        testEmployeesExtended();
    }

    public static void testSimpleLambda(int[] values) {
        Arrays.stream(values).forEach(value -> System.out.println("Value: " + value));

    }

    public static void testForEachMethod(int[] values) {

        //int value =10;
        //will make error on below statements
        IntStream.of(values)
                .forEach(value -> System.out.println("value: " + value));


        IntStream.of(values)
                .forEach((int value) -> System.out.println("value: " + value));

    }

    public static void testOOTBReduceMethods(int[] values) {

        //System.out.println("Count: " + IntStream.of(values).count());
        System.out.println("Max: " + IntStream.of(values).max().getAsInt());
        System.out.println("Min: " + IntStream.of(values).min().getAsInt());
        System.out.println("Sum: " + IntStream.of(values).sum());
        System.out.println("Average: " + IntStream.of(values).average().orElse(11));

        System.out.println("Sequential: " + IntStream.of(values).sequential().max());

    }

    public static void testCustomReduceMethods(int[] values) {

        Integer sum = IntStream.of(values)
                .reduce(0, (x, y) -> x + y);
        System.out.println("Sum with reduce method:" + sum);
        //0 to wartosc poczatkowa i od niej zaczynamy sumowac dalsze wartosci
        //Sumowanie dzial mniej wiecej tak:  ((((0 + 1) + 2) + 3) + -4) gdzie tablica jest taka values[] ={ 1,2,3,-4 }
        // - zaczynamy od 0 bo to jest podana wartosc poczatkowa
        //Operacja reduce nie jest threadSafe -- takze uzywac jej mozemy w opercjach w ktorych kolejnosc dzialan nie ma znacznia

        Integer sum2 = IntStream.of(values)
                .sum();
        System.out.println("Sum with ootb reduce method:" + sum2);

        Integer reduction = IntStream.of(values)
                .reduce(0, (x, y) -> x - y);
        System.out.println("Reduction with reduce method:" + reduction);

        Integer product = IntStream.of(values)
                .reduce(1, (x, y) -> x * y);
        System.out.println("Product with reduce method:" + product);
        //1 poniewaz jest to liczba neutralna dla operacji dzielenia

        int[] values2 = {2, 3, 2};

        Integer addition = IntStream.of(values2)
                .reduce(1, (x, y) -> x + y * y);


        System.out.println("Addition with with reduce method:" + addition);


    }

    public static void testFlterAndSortMethods(int[] values) {

        IntStream.of(values)
                .filter(value -> value % 2 == 0)
                .sorted()
                //.unordered()
                .forEach(value -> System.out.println("value: " + value));

        IntStream.of(values)
                .filter(value -> value % 2 == 0)
                .map(value -> value * 10) //took value, dose something on it and return the new value
                .sorted()
                //.unordered()
                .forEach(value -> System.out.println("value2: " + value));
    }

    public static void testRangeConditions() {

        System.out.println("Sum of integers from 1 to 9: " +
                IntStream.range(1, 10).sum()); //1 + 2 + 3 + ... + 9 = 45

        System.out.println("Sum of integers from 1 to 10: " +
                IntStream.rangeClosed(1, 10).sum());  //1 + 2 + 3 + ... + 10 = 55


    }

    public static void testIntegerOperations() {

        Integer[] values = {2, 9, 5, 0, 3, 7, 1, 4, 8, 6};

        System.out.println("Original values list: " + asList(values));

        System.out.println("Sorted values list : " + Arrays.stream(values)
                .sorted()
                .collect(Collectors.toList()));


        List<Integer> greaterThan4 = Arrays.stream(values)
                .filter(value -> value > 4)
                .collect(Collectors.toList());

        System.out.println("Values greater than 4: " + greaterThan4);


        System.out.println("Values greater than 4 in sorted order: " + Arrays.stream(values)
                .sorted()
                .filter(value -> value > 4)
                //.sorted() --does not matter wheter sorted method call is assigned after or before filter method
                .collect(Collectors.toList()));


        System.out.println("Values greater than 4 coming directly from greaterThan4 list: " + greaterThan4.stream()
                .sorted()
                .collect(Collectors.toList()));

    }


    public static void testStringOperations() {

        String[] strings = {"Red", "orange", "Yellow", "green", "Blue", "indigo", "Violet"};

        System.out.println("Original values: " + asList(strings));

        System.out.println("Strings in uppercase" +
                Arrays.stream(strings)
                        //.map(value -> value.toUpperCase())
                        .map(String::toUpperCase) //equivalent of the above line
                        .collect(Collectors.toList())
        );

        //:: - method references (for example String::toUpperCase
        // is the shorthand for lambda expression

        System.out.println("Strings greater than m sorted ascending: " +
                Arrays.stream(strings)
                        .filter(s -> s.compareToIgnoreCase("m") > 0) // wyfiltruje te wyrazy których pierwsza litera jest wieksza od m
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(Collectors.toList())
        );

        System.out.println("Strings greater than m sorted descending: " +
                Arrays.stream(strings)
                        .filter(s -> s.compareToIgnoreCase("m") > 0) // wyfiltruje te wyrazy których pierwsza litera jest wieksza od m
                        .sorted(String.CASE_INSENSITIVE_ORDER.reversed()) //String.CASE_INSENSITIVE_ORDER to jest comparator
                        .collect(Collectors.toList())
        );

        System.out.println("Strings greater than m sorted descending: " +
                Arrays.stream(strings)
                        .filter(s -> s.compareToIgnoreCase("m") > 0) // wyfiltruje te wyrazy których pierwsza litera jest wieksza od m
                        .sorted(String.CASE_INSENSITIVE_ORDER.reversed()) //String.CASE_INSENSITIVE_ORDER to jest comparator dla klasy String
                        .map(String::toUpperCase) //map podbije wyraz do uppercase
                        .collect(Collectors.toList())
        );


    }

    public static void testEmployees() {

        Employee [] employees = {
                new Employee("Jason", "Red", 5000, "IT"),
                new Employee("Ashley", "Green", 7600, "IT"),
                new Employee("Matthew", "Indigo", 3587.5, "Sales"),
                new Employee("James", "Indigo", 4700.77, "Marketing"),
                new Employee("Luke", "Indigo", 6200, "IT"),
                new Employee("Jason", "Blue", 3200, "Sales"),
                new Employee("Wendy", "Brown", 4236.4, "Marketing")
        };

        List<Employee> list = asList(employees);
        System.out.println("Complete Employee list:");

        list
                .stream() //creating stream from list
                .forEach(System.out::println); //invoking reference method on each object
        //.forEach(value -> System.out.println("Value: " + value)); //the same as above but without using reference method


        Predicate<Employee> fourToSixThousand =
                e -> (e.getSalary() >= 4000 && e.getSalary() <= 6000);
        //assigning lambda function to local variable -> treat lambda expresion as variable


        System.out.println("\nEmployees earning $4000 - $6000 per month sorted by salary: ");
        list.stream()
                .filter(fourToSixThousand)
                .sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);

//        System.out.println("\nFirst employee who earns $4000 - $6000 per month:\n" +
//                list.stream()
//                        .filter(fourToSixThousand)
//                        .sorted(Comparator.comparing(Employee::getFirstName))
//                        //.findAny()
//                        .findFirst()
//                        //.sorted(Comparator.comparing(Employee::test))
//                        .get());

        System.out.println("\nFirst employee who earns $4000 - $6000 per month:\n" +
                list.stream()
                        .filter(fourToSixThousand)
                        .sorted(Comparator.comparing(Employee::getFirstName))
                //.findAny()
                       .findFirst()
                //.sorted(Comparator.comparing(Employee::test))
                       // .get());
        );

    }

    public static void testEmployeesExtended() {

        Employee[] employees = {
                new Employee("Jason", "Red", 5000, "IT"),
                new Employee("Ashley", "Green", 7600, "IT"),
                new Employee("Matthew", "Indigo", 3587.5, "Sales"),
                new Employee("James", "Indigo", 4700.77, "Marketing"),
                new Employee("Luke", "Indigo", 6200, "IT"),
                new Employee("Jason", "Blue", 3200, "Sales"),
                new Employee("Wendy", "Brown", 4236.4, "Marketing")
        };

        List<Employee> list = asList(employees);
        System.out.println("Complete Employee list:");

        list
                .stream() //creating stream from list
                .forEach(System.out::println); //invoking reference method on each object
        //.forEach(value -> System.out.println("Value: " + value)); //the same as above but without using reference method

        //Functions for Getting First Name and last names from an employee object
        Function<Employee, String> byFirstName = Employee::getFirstName;
        Function<Employee, String> byLastName = Employee::getLastName;

        //Comparator for comparing Employees by first name then last name
        Comparator<Employee> lastThenFirst = Comparator.comparing(byLastName).thenComparing(byFirstName);

        System.out.printf("%nEmployees in ascending order by last name then first:%n");
        list.stream()
                .sorted(lastThenFirst)
                .forEach(System.out::println);

        System.out.printf("%nEmployees in descending order by last name then first:%n");
        list.stream()
                .sorted(lastThenFirst.reversed())
                .forEach(System.out::println);

        System.out.printf("%nEmployees in descending order by last name then first:%n");
        list.stream()
                .sorted(lastThenFirst.reversed())
                .forEach(System.out::println);

        System.out.printf("%nUnique employee last names:%n");
        list.stream()
                .map(Employee::getLastName) //creating stream of Strings that contain employees last names
                .distinct()
                .sorted()
                .forEach(System.out::println);

        System.out.printf("%nEmployee names in order by last name then first name:%n");
        list.stream()
                .sorted(lastThenFirst)
                .map(Employee::getName) //creating stream of Strings that contains full names of employees
                .forEach(System.out::println); //terminal operation


        System.out.printf("%nUnique Employee names in order by last name then first name:%n");
        list.stream()
                .sorted(lastThenFirst)
                .map(Employee::getName) //creating stream of Strings that contains full names of employees
                .distinct()
                .forEach(System.out::println); //terminal operation

        ///group Employees by department
        System.out.println("Grouping Employees by department");
        Map<String, List<Employee>> groupedByDepartment =
                list.stream()
                        .collect(Collectors.groupingBy(Employee::getDepartment)); //collecting result into mutable collection

        groupedByDepartment.forEach( //consumes BiConsumer which means that it will take two arguments as lambda parameters
                (department, employeesInDepartment) ->
                {
                    System.out.println("Department: " + department);
                    employeesInDepartment.forEach(employee ->
                            System.out.println(employee.getFirstName())); //displaying employees first names
                    // employeesInDepartment.forEach(System.out::println); //displaying whole employee info
                }
        );

        //count number of employees in each department:
        System.out.println("\nCount number of employees in each department: ");

        Map<String, Long> employeeCountByDepartment =
                list.stream()
                        .collect(Collectors.
                                groupingBy(Employee::getDepartment, TreeMap::new, Collectors.counting())
                        ); //grouping by Department name, accumulating Map into the TreeMap and uses Collectors.counting() as the function which will put the values to the map

        employeeCountByDepartment.forEach(
                (department, count) -> System.out.println("Department : " + department + " has : " + count + " Employees")
        );

        //count number of employees in each department:
        System.out.println("\nUnsorted number of employees in each department: ");

        Map<String, Long> employeeCountByDepartmentNotSorted =
                list.stream()
                        .collect(Collectors.
                                groupingBy(Employee::getDepartment, Collectors.counting())
                        ); //grouping by Department name, by DEFAULT accumulating Map into the HASHMAP (which is not sorted) and uses Collectors.counting() as the function which will put the values to the map

        employeeCountByDepartmentNotSorted.forEach(
                (department, count) -> System.out.println("Department : " + department + " has : " + count + " Employees")
        );


        System.out.println("\nSum of Employees salaries (via sum method): " + list.stream()
                .mapToDouble(Employee::getSalary) //converting stream to stream of Double values which contain salaries
                .sum());


        System.out.printf("\nSum of Employees salaries (via reduce method): %.2f%n",
                list.stream()
                        .mapToDouble(Employee::getSalary)
                        .reduce(0, (value1, value2) -> value1 + value2));

        /* Wyjasnienienie czemu uzywamy 0 jako argument w metodzie reduce
        You can do any type of reduction you want by using the reduce method.
        In this case, the reduce operation that I'm performing is doing the exact same thing that sum does.
         So remember, you have to provide an identity value that will be combined with the first element of the stream.
          Then, all subsequent streams, the first value passed into the lambda expression will be the previous result
          and the next value in the stream will be passed in as well.
           What you can see here is that we're just adding the values together using this lambda expression that's passed as the
            second argument to the reduced method and we start with the identity value 0.
             Remember the identity value is going to be combined with the first element in the stream and needs to produce the
              same exact value as the first element in the stream. So, when you're doing addition, you use 0.
               If you're doing multiplication, you would use 1 in that case


         */

        System.out.printf("\nAverage of Employees salaries (via reduce method): %.2f%n",
                list.stream()
                        .mapToDouble(Employee::getSalary)
                        .average()
                        .getAsDouble() //above line return Optional so it should be converted to double by getAsDouble() method
        );


    }


}