package oop.innerclasses.anonymousclasses;

/*

Wewnatrz definicji klas wewnetrznych (takze klas anonimowych)
mozemy uzywac zmiennych lokalnych z otaczającego je kontekstu.

 */

public class LocalVariables {

    final String finalVariable1 = "final variable invoked from inner class";
    String effectivelyFinalVariable1 = "effectively final variable invoked from inner class"; //klasa "wlasciwie finalna" poniewaz nie zmieniamy jej wartosci i kompilator wstawia przed nia slowko final za nas
    String nonFinalVariable1 = "non final variable invoked from inner class";

    public class InnerClass {
        public void saySomething() {
            System.out.println(finalVariable1);
            System.out.println(effectivelyFinalVariable1);
        }

    }


    public void saySomething() {

        final String finalVariable = "final variable";
        String effectivelyFinalVariable = "effectively final variable"; //klasa "wlasciwie finalna" poniewaz nie zmieniamy jej wartosci i kompilator wstawia przed nia slowko final za nas
        String nonFinalVariable = "non final variable";

        class InnerClass {
            public void saySomething() {
                System.out.println(finalVariable);
                System.out.println(effectivelyFinalVariable);
                //System.out.println(nonFinalVariable); //Variable 'nonFinalVariable' is accessed from within inner class, needs to be final or effectively final
            }
        }

        InnerClass instance = new InnerClass();
        instance.saySomething();

        nonFinalVariable = "new value";  //nie jest to juz zmienna finalna ani tez "wlasciwie finalna"

    }



}
