package courses.generics;

public class OverLoadedMethods {

    public static void testOverloadedMethods() {

        //Create arrays of Integer, Double and Character by autoboxing
        Integer[] integerArray = {1, 2, 3, 4, 5, 6};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4, 5.5, 6.6, 7.7};
        Character[] characterArray = {'H', 'E', 'L', 'L', 'O'};

        System.out.printf("Array integerArray contains:%n");
        printArray(integerArray);

        System.out.printf("%nArray doubleArray contains:%n");
        printArray(doubleArray);
        System.out.printf("%nArray characterArray contains:%n");
        printArray(characterArray);


        System.out.println("\n\nPrinting array in generic way");

        System.out.printf("Array integerArray contains:%n");
        printArrayGenericWay(integerArray);

        System.out.printf("%nArray doubleArray contains:%n");
        printArrayGenericWay(doubleArray);
        System.out.printf("%nArray characterArray contains:%n");
        printArrayGenericWay(characterArray);

        System.out.println("\n\nPrinting array in object way");

        System.out.printf("Array integerArray contains:%n");
        printArrayObjectWay(integerArray);

        System.out.printf("%nArray doubleArray contains:%n");
        printArrayObjectWay(doubleArray);
        System.out.printf("%nArray characterArray contains:%n");
        printArrayObjectWay(characterArray);

    }

    //non generic overloaded method
    private static void printArray(Integer[] inputArray) {

        for (Integer element : inputArray) {
            System.out.printf("%s ", element);
        }

    }

    //non generic method
    private static void printArray(Double[] inputArray) {

        for (Double element : inputArray) {
            System.out.printf("%s ", element);
        }

    }

    //non generic method
    private static void printArray(Character[] inputArray) {

        for (Character element : inputArray) {
            System.out.printf("%s ", element);
        }

    }

    //generic method
    private static <T> void printArrayGenericWay(T[] inputArray) { //<t> is the parameter of the type --need to be added before teturn type and in method

        for (T element : inputArray) {
            System.out.printf("%s ", element);
        }

    }

    //object method
    private static void printArrayObjectWay(Object[] inputArray) {

        for (Object element : inputArray) {
            System.out.printf("%s ", element);
        }

    }


}
