package com.baifukaun.spring.week05.spring.bean.initialization;

import com.baifukaun.spring.week05.spring.bean.factory.domain.User;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanAndComponentAnnotation {

    public static void main(String[] args) {
        // 创建BeanFactory 容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanAndComponentAnnotation.class);
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(annotationConfigApplicationContext);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/user-bean.xml");
        //启动spring 上下文
        annotationConfigApplicationContext.refresh();

        // 使用@bean 注解装配bean
        User user = annotationConfigApplicationContext.getBean("getUserByBeanAnnotation", User.class);
        System.out.println(user);

        // 通过@Component 派生的方式装配bean
        User componentUser = annotationConfigApplicationContext.getBean("user-by-component",User.class);
        System.out.println(componentUser.createUser("通过@Component 派生的方式装配bean------->baifukuan",18));
        // 关闭spring上下文
        annotationConfigApplicationContext.close();
    }


    @Bean(value = "getUserByBeanAnnotation")
    public User getUserByBeanAnnotation(){
        User user = new User();
        user.setUsername("我是通过bean annotation装配的bean ------------>baifukuan");
        user.setAge(18);
        return user;
    }

}
