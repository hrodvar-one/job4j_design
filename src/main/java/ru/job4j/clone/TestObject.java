package ru.job4j.clone;

public class TestObject implements Cloneable {
    int num;
    InnerObject innerObj;

    @Override
    protected TestObject clone() throws CloneNotSupportedException {
        TestObject testObj = (TestObject) super.clone();
        testObj.innerObj = innerObj.clone();
        return testObj;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        TestObject testObj1 = new TestObject();
        testObj1.num = 5;
        InnerObject innerObj = new InnerObject();
        innerObj.num = 15;
        testObj1.innerObj = innerObj;
        TestObject testObj2 = testObj1.clone();
        testObj2.num = 25;
        testObj2.innerObj.num = 35;
        System.out.println("Исходный класс: " + testObj1.num);
        System.out.println("Исходный класс: " + testObj1.innerObj.num);
        System.out.println("Скопированный класс: " + testObj2.num);
        System.out.println("Скопированный класс: " + testObj2.innerObj.num);
    }
}
