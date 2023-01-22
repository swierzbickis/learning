package oop.generics;

public class BoxOnSteroids<T> {

    public T fruit;

    public BoxOnSteroids(T fruit) {
        this.fruit = fruit;
    }

    public T getFruit() {
        return fruit;
    }

}



