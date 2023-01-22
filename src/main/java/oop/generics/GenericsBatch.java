package oop.generics;

//public class List<T> - T is type PARAMETER
//List<String> list = new ArrayList<>(); --<String> is Type ARGUMENT


import oop.generics.fruits.Apple;
import oop.generics.fruits.FruitBox;
import oop.generics.fruits.Orange;

public class GenericsBatch {

    public static void main(String[] args) {
//        testFruitBox();
//        testBoxOnSteroids();
        // testPair();
        castExceptionExample();

        // public static <T> List<T> asList(T... a) { -- co oznacza taki zapis :  <T> List<T>

    }


    public static void testFruitBox() {

        FruitBox fruitBox = new FruitBox(new Orange());
        Orange fruit1 = (Orange) fruitBox.getFruit();

    }

    public static void testBoxOnSteroids() {

        BoxOnSteroids<Apple> appleBox = new BoxOnSteroids<>(new Apple()); //przy tworzeniu obiektu podajemy jakiej klasy ma on byc
        BoxOnSteroids<Orange> orangeBox = new BoxOnSteroids<>(new Orange());
        // BoxOnSteroids<Orange> orangeBox2 = new BoxOnSteroids<Apple>(new Apple()); -- blad kompilacji, nie zadziala bo nie mozemy do typu kopertowego Orange przypisac typu apple

        Orange orange = orangeBox.getFruit(); //dzieki temu ze przy tworzeniu obiektu podalismy klase, nie musimy robic rzutowania
        Apple apple = appleBox.getFruit();

        //Apple apple2 = orangeBox.getFruit();-- blad kompilacji, nie zadziala bo nie mozemy poprzez orangeBox zwrocic typu Apple

    }

    public static void testPair() {
        Pair<BoxOnSteroids<Orange>, BoxOnSteroids<Apple>> pairOfBoxes = new Pair<>(
            new BoxOnSteroids<>(new Orange())
            , new BoxOnSteroids<>(new Apple())); //przyklad tworzenia obiektu klasy generycznej, ktora sklada sie z klas generycznych

        System.out.println("Pair of Boxes class: " + pairOfBoxes.getClass().getName());
        System.out.println("Pair of Boxes first object class: " + pairOfBoxes.getFirst().getClass().getName());
        System.out.println("Pair of Boxes second object class: " + pairOfBoxes.getSecond().getClass().getName());

        System.out.println("Pair of Boxes first object's content class: " + pairOfBoxes.getFirst().getFruit().getClass().getName());


        Pair<Orange, Apple> pairOfFruits = new Pair<>(new Orange(), new Apple());


        System.out.println("Pair Of Fruits class: " + pairOfFruits.getClass().getName());
        System.out.println("Pair Of Fruits first object class: " + pairOfFruits.getFirst().getClass().getName());
        System.out.println("Pair Of Fruits second object class: " + pairOfFruits.getSecond().getClass().getName());


    }

    public static void castExceptionExample() {

        //Przyklad1 bez uzycia <> w momencie tworzenia obiektu
        BoxOnSteroids boxWithoutType = new BoxOnSteroids(new Apple());
        BoxOnSteroids<Apple> boxWithApple = boxWithoutType;
        BoxOnSteroids<Orange> boxWithOrange = boxWithoutType;
        Apple apple = boxWithApple.getFruit();
        //Orange orange = boxWithOrange.getFruit(); // Tu pojawia sie automatyczne rzutowanie generowane przez kompilator --to sie wyali bo nie mozna rzutowac Apple na Orange

        //przyklad2 uzycie <> w momencie tworzenia obiektu
        BoxOnSteroids<Apple> boxWithoutType2 = new BoxOnSteroids(
            new Apple()); //jezeli zadeklarujemy typ <apple>  w nawiasach ostrych, to wtedy zabezpieczymy przed rzutowaniem niewlasciwych typow juz na etapie kompilacji
        BoxOnSteroids<Apple> boxWithApple2 = boxWithoutType;
        BoxOnSteroids<Orange> boxWithOrange2 = boxWithoutType;
        Apple apple2 = boxWithApple.getFruit();
        // Orange orange2 = boxWithApple.getFruit(); --to sie nawet nie bedzie chcialo skompilowac -- poleci ponizszy exception
        //Incompatible types.
        //Required:
        //com.learn.oop.generics.fruits.Orange
        //Found:
        //com.learn.oop.generics.fruits.Apple

//
        //przyklad3 legacy code:
        BoxOnSteroids boxWithoutType3 = new BoxOnSteroids(new Apple());
        BoxOnSteroids boxWithApple3 = boxWithoutType;
        BoxOnSteroids boxWithOrange3 = boxWithoutType;
        Apple apple3 = boxWithApple.getFruit();
        //Orange orange3 = boxWithApple.getFruit();


        BoxOnSteroids<?> boxWithWildcard = new BoxOnSteroids(new Apple());
        BoxOnSteroids<Apple> appleBoxWithWildcard = boxWithoutType;
        BoxOnSteroids<Orange> aorangeBoxWithWildcard = boxWithoutType;
        //Apple apple = appleBoxWithWildcard.getFruit(); //compilation error

    }

}
