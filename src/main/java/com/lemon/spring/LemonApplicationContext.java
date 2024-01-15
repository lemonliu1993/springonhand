package com.lemon.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;

/**
 * Created by lemoon on 2024/1/13 10:15
 */
public class LemonApplicationContext {

    private Class configClass;

    public LemonApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 解析配置类
        // ComponentScan注解 ---> 扫描路径 ---> 扫描
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScanAnnotation.value();  //扫描路径
        System.out.println(path);

        //扫描
        //Bootstrap --->jre/lib
        //Ext       --->jre/ext/lib
        //App       --->classpath
        ClassLoader classLoader = LemonApplicationContext.class.getClassLoader();   //app 应用类加载器
        URL resource = classLoader.getResource("com/lemon/service");
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                System.out.println(f);
//                Class<?> aClass = classLoader.loadClass("com.lemon.service.UserService");
                String fileName = f.getAbsolutePath();
                if (fileName.endsWith(".class")) {
                    //是类文件才进行处理
                    String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                    className = className.replace("/", ".");
                    System.out.println(className);
                    Class<?> clazz = null;
                    try {
                        clazz = classLoader.loadClass(className);
                        if (clazz.isAnnotationPresent(Component.class)) {
                            // 表示当前这个类是一个Bean
                            //解析类，判断当前bean是单例bean，还是prototype的bean
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }


            }
        }
        System.out.println(resource);
    }

    public Object getBean(String beanName) {
        return null;
    }
}
