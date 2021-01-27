package com.test.test;
class Singleton
{
    private static Singleton singleton = new Singleton();
    public static int counter1;
    public static int counter2 =3;
    public  int counter3=1;
    private Singleton()
    {
        System.out.println("1counter1 = " + counter1);
        System.out.println("1counter2 = " + counter2);
        System.out.println("1counter3= " + counter3);
        counter1++;
        counter2++;
        counter3=11;
    }

    public static Singleton getInstance()
    {
    return singleton;
    }
}
public class Test extends  java.lang.Object
{

    public static void main(String[] args)
    {
        Integer A=12>>2;
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1 = " + singleton.counter1);
        System.out.println("counter2 = " + singleton.counter2);
        System.out.println("counter3 = " + singleton.counter3);
    }
}