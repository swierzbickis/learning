package oop.innerclasses;

import java.util.Arrays;

/*
Glownym ograniczeniem/zaleta klas lokalnych jest ich zasieg.
Podobnie jak w przypadku zmiennych lokalnych, dostep do klas lokalnych jest wylacznie w bloku, w ktorym zostaly zdefiniowane.

 */

public class LocalInnerClass {

    public LocalInnerClass(int number) {

        class LocalClass {
            private int number; //mozna natomiast stosowac modyfikatory dostepu dla zmiennych wewnatrz klasy lokalnej

            LocalClass(int number) {
                this.number = number;
            }

        }

        LocalClass localClass = new LocalClass(number);
        System.out.println("Invoked from constuctor of LocalInnerClass. Number in local class: " + localClass.number);


    }

    public LocalInnerClass() {

    }


    public static void localClassInstantiation(String[] args) {
        class LocalClass {
            // class LocalClass { -- w klasach prywatnych lokalnych (wewnatrz metody, konstruktora etc. - nie mozna stosowac modyfikatorow dostepu
            @Override
            public String toString() {
                return "Argumenty metody: " + Arrays.toString(args);
            }
        }

        LocalClass localClassInstance = new LocalClass();
        System.out.println(localClassInstance);


    }

}
