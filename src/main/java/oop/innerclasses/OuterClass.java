package oop.innerclasses;

public class OuterClass {

    public class InnerClass {

        public class InnerClass2 {

        }

    }

    public InnerClass instantiate() {
        return new InnerClass(); //nie trzeba podawac pelnej nazwy pakietowej jezeli tworzymy klase wewnetrzna bezposrednio z zewnetrznej.
        //return  new InnerClass(); //tez poprawnie
    }

}
