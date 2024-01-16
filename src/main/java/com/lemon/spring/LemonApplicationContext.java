package com.lemon.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by lemoon on 2024/1/13 10:15
 */
public class LemonApplicationContext {

    private Class configClass;

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();  //单例池
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public LemonApplicationContext(Class configClass) {
        this.configClass = configClass;

        // 解析配置类
        // ComponentScan注解 ---> 扫描路径 ---> 扫描 --->Beandefinition -->BeanDefinitionMap
        scan(configClass);

        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getScope().equals("singleton")) {
                Object bean = createBean(beanDefinition);//创建bean
                singletonObjects.put(beanName, bean);
            }
        }
    }

    public Object createBean(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();

            //依赖注入
            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }


            return instance;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private URL scan(Class configClass) {
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
                            // ---> BeanDefinition
                            Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);

                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            } else {
                                beanDefinition.setScope("singleton");
                            }

                            beanDefinitionMap.put(beanName, beanDefinition);

                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

                }


            }
        }
        return resource;
    }

    public Object getBean(String beanName) {
        if (beanDefinitionMap.containsKey(beanName)) {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (beanDefinition.getScope().equals("singleton")) {
                Object o = singletonObjects.get(beanName);
                return o;
            } else {
                //创建Bean对象
                Object bean = createBean(beanDefinition);
                return bean;
            }
        } else {
            //不存在对应的bean
            throw new NullPointerException();
        }
    }
}
