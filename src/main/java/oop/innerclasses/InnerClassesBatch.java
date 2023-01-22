package oop.innerclasses;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import oop.innerclasses.anonymousclasses.AnonymousCheating;
import oop.innerclasses.anonymousclasses.AnonymousClasses;
import oop.innerclasses.anonymousclasses.GreetingModuleTest;
import oop.innerclasses.anonymousclasses.LocalVariables;

//http://www.samouczekprogramisty.pl/klasy-wewnetrzne-i-anonimowe-w-jezyku-java/
public class InnerClassesBatch {

    public static void main(String[] args) {

        //testOuterClass();
        //testStaticOuterClass();
        // testLocalInnerClass();
        //testHashMapAsInnerClassTest();
        //testGreetingModule();
        //testAnonymousClasses();
        //testAnonymousCheating();
        testLocalVariables();
        //testComparator();

    }


    public static void testOuterClass() {

        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass instance1 = outerClass.instantiate();
        OuterClass.InnerClass instance2 = outerClass.new InnerClass();

        OuterClass.InnerClass.InnerClass2 instance3 = new OuterClass().new InnerClass().new InnerClass2();

        System.out.println(outerClass.getClass().getName());
        System.out.println(instance2.getClass().getName());
        System.out.println(instance3.getClass().getName());

    }

    public static void testOuterClassWithPrivateInnerClasses() {

        OuterClassWithPrivateInnerClasses outerClass1 = new OuterClassWithPrivateInnerClasses();
        //OuterClassWithPrivateInnerClasses.InnerClass innerClass = outerClass1.new InnerClass(); --nie skompiluje sie
        //jak klasa innerClass jest zdefiniowana jako protected to wtedy kompiluje sie

    }

    public static void testStaticOuterClass() {

        StaticOuterClass outerClass = new StaticOuterClass();
        StaticOuterClass.StaticInnerClass instance1 = outerClass.instantiate();
        StaticOuterClass.StaticInnerClass instance2 = new StaticOuterClass.StaticInnerClass();
        //roznica jest w linijce powyzej. Jak tworzymy statyczna klase wewnetrzna to trzeba to robic bezposrednio przez klase zewnetrzna w statyczny sposob a nie przez obiekt tej klasy zewnetrznej

        StaticOuterClass.StaticInnerClass instance3 = new StaticOuterClass.StaticInnerClass();
        instance3.test(); //metoda nie statyczna wywolana z obiektu
        instance3.test2(); //metoda statyczna wywolana z obiektu

        //StaticOuterClass.StaticInnerClass.test(); // TO NIE ZADZIALA - metoda niestatyczna wywolana z klasy

        StaticOuterClass.StaticInnerClass.test2(); //metoda statyczna wywolana z klasy


        System.out.println(outerClass.getClass().getName());
        System.out.println(instance1.getClass().getName());
        System.out.println(instance2.getClass().getName());

    }

    public static void testLocalInnerClass() {

        LocalInnerClass localInnerClass = new LocalInnerClass();
        localInnerClass.localClassInstantiation(new String[]{"Hey"}); //metoda wywolana przez obiekt
        LocalInnerClass.localClassInstantiation(new String[]{"Hey Joe"}); //metoda wywolana przez klase

        //test z klasa lokalna w konstruktorze

        LocalInnerClass localInnerClass2 = new LocalInnerClass(100);

    }

    public static void testHashMapAsInnerClassTest() {
        HashMapAsInnerClassTest.testHashMap();
    }

    public static void testGreetingModule() {
        GreetingModuleTest greetingModuleTest = new GreetingModuleTest();
        greetingModuleTest.doSomething();
    }

    public static void testAnonymousClasses() {

        AnonymousClasses.Robot jan = new AnonymousClasses.Robot(new AnonymousClasses.HelpingModule() {
            @Override
            public void sayHello() {
                System.out.println("Dzien dobry");
            }
        });

        AnonymousClasses.Robot john = new AnonymousClasses.Robot(() -> {
            System.out.println("Good morning");
        });

        AnonymousClasses.Robot hans = new AnonymousClasses.Robot(() -> {
            System.out.println("Guten morgen");
        });

        jan.saySomething();
        john.saySomething();
        hans.saySomething();

    }

    public static void testAnonymousCheating() {
        AnonymousCheating.SomeInterface someInterface = new AnonymousCheating.SomeInterface() {

            @Override
            public void doSomethingUseful() {
                System.out.println("I'm doing something useful!");
            }
        };

        AnonymousCheating.SomeInterface someInterface2 = new AnonymousCheating.SomeInterface() {

            @Override
            public void doSomethingUseful() {
                System.out.println("I'm doing something useful! 2");
            }
        };

        AnonymousCheating.SomeInterface someInterface3 = () -> System.out.println("I'm doing something useful! 3");

        someInterface.doSomethingUseful();
        System.out.println(someInterface.getClass());
        someInterface2.doSomethingUseful();
        System.out.println(someInterface2.getClass());

        someInterface3.doSomethingUseful();
        System.out.println(someInterface3.getClass()); //lamda ma inna nazwe
    }

    public static void testLocalVariables() {

        LocalVariables outerClass = new LocalVariables();
        LocalVariables.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.saySomething();
        outerClass.saySomething();

    }


    public static void testComparator() {

        String[] arrays = {"Trump", "Zordon", "Salah", "Peszko", "Ronaldo"};
        List<String> words = Arrays.asList(arrays);

        Collections.sort(words, (String word1, String word2) -> {
            return word1.compareToIgnoreCase(word2); //rosnaco
            //return word2.compareToIgnoreCase(word1); //malejaco
        });

        words.stream().forEach((word) -> System.out.println(word));

    }


}
