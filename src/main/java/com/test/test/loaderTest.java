package com.test.test;

class loaderTest{
    public static void main(String[] args) throws Exception {


//创建一个loader1类加载器，设置他的加载路径为d:\\serverlib\\，设置默认父加载器为系统类加载器
        MyClassLoader loader1 = new MyClassLoader("loader1");
        loader1.setPath("d:\\data\\");

//创建一个loader2类加载器，设置他的加载路径为d:\\clientlib\\，并设置父加载器为loader1
        MyClassLoader loader2 = new MyClassLoader(loader1, "loader2");
        loader2.setPath("d:\\myapp\\clientlib\\");
//创建一个loader3类加载器，设置他的加载路径为d:\\otherlib\\，并设置父加载器为根类加载器
        MyClassLoader loader3 = new MyClassLoader(null, "loader3");
        loader3.setPath("d:\\myapp\\otherlib\\");
        test(loader2);
        System.out.println("----------");
        test(loader3);
    }

    public static void test(ClassLoader loader) throws Exception {
        Class clazz = loader.loadClass("com.test.test.Singleton");
        Object object = clazz.newInstance();


    }
}