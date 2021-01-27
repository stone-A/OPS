package com.test.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @author 曹胜欢
 * @version 1.0
 */
public class MyClassLoader extends ClassLoader {
    private String name; // 类加载器的名字
    private String path = "d:\\"; // 加载类的路径
    private final String fileType = ".class"; // class文件的扩展名

    public MyClassLoader(String name) {
        super(); // 让系统类加载器成为该类加载器的父加载器
        this.name = name;
    }

    public MyClassLoader(ClassLoader parent, String name) {
        super(parent); // 显式指定该类加载器的父加载器
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @param 类文件的名字
     * @return 类文件中类的class对象
     * <p/>
     * 在这里我们并不需要去显示的调用这里的findclass方法，在上篇文章中，我们通过查看
     * loadclass的源码可以发现，她是在loadclass中被调用的，所以这里我们只需重写这个方法，
     * 让它根据我们的想法去查找类文件就ok，他会自动被调用
     * <p/>
     * <p/>
     * defineClass()将一个 byte 数组转换为 Class 类的实例。必须分析 Class，然后才能使用它
     * 参数：
     * name - 所需要的类的二进制名称，如果不知道此名称，则该参数为 null
     * b - 组成类数据的字节。off 与 off+len-1 之间的字节应该具有《Java Virtual Machine Specification》定义的有效类文件的格式。
     * off - 类数据的 b 中的起始偏移量
     * len - 类数据的长度
     */
    @Override
    public Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = this.loadClassData(name);//获得类文件的字节数组
        return this.defineClass(name, data, 0, data.length);//
    }

    /**
     * @param 类文件的名字
     * @return 类文件的 字节数组
     * 通过类文件的名字获得类文件的字节数组，其实主要就是用
     * 输入输出流实现。
     */
    private byte[] loadClassData(String name) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        try {
            this.name = this.name.replace(".", "\\");
            is = new FileInputStream(new File(path + name + fileType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return data;
    }

}

