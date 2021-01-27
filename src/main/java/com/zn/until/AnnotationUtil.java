/**
 * Created by zhounan on 2015/12/5.
 */
package com.zn.until;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public  class AnnotationUtil {

    public static AnnotationUtil anno = null;

    public static AnnotationUtil getInstance() {
        if (anno == null) {
            anno = new AnnotationUtil();
        }
        return anno;
    }

    /**
     * 读取注解值
     *
     * @param annotationClasss 处理Annotation类名称
     * @param annotationField 处理Annotation类属性名称
     * @param className 处理Annotation的使用类名称
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static String loadVlaue(Class annotationClasss,
            String annotationField, String className,String strMethod){
        try {
            Map<String, String> map = new HashMap<String, String>();
            Method[] methods = Class.forName(className).getDeclaredMethods();
            for (Method method : methods) {
                if (!strMethod.equals(method.getName())) {
                    continue;
                }
                if (method.isAnnotationPresent(annotationClasss)) {
                    Annotation p = method.getAnnotation(annotationClasss);
                    Method m = p.getClass()
                            .getDeclaredMethod(annotationField, null);
                    String value = (String) m.invoke(p, null);

                    return value;

                }
            }
            return "";
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }


    /**
     * 根据类名读取注解值
     *
     */
    @SuppressWarnings("all")
    public static String loadValue(Class annotationClasss, String annotationField, String className){
        try{
               Class targetClass=Class.forName(className);
               if(targetClass.isAnnotationPresent(annotationClasss)){
                   Annotation p=targetClass.getAnnotation(annotationClasss);
                   String result= p.getClass().getDeclaredMethod(annotationField).invoke(p).toString();
                   return result;
               }
        }catch(Exception e){
            e.printStackTrace();
            return "";
        }
        return "";
    }


}