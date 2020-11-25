package com.baifukaun.spring.week05.spring.bean.initialization;

import com.baifukaun.spring.week05.spring.bean.factory.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.baifukaun.spring.week05.spring.bean.factory.domain") // 注解方式扫包
public class BeanAndComponentAnnotation {

    public static void main(String[] args) {
        // 创建BeanFactory 容器
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(BeanAndComponentAnnotation.class);
        //启动spring 上下文
        annotationConfigApplicationContext.refresh();
        // 方式一
        byBeanAnnotation(annotationConfigApplicationContext);

        // 方式二
        byComponent(annotationConfigApplicationContext);

        // 方式三
        byBeanDefinitionBuilder(annotationConfigApplicationContext);

        // 方式四
        bySingletonBeanRegistry(annotationConfigApplicationContext);

        // 关闭spring上下文
        annotationConfigApplicationContext.close();
    }

    /**
     * 单体实例注册bean
     *
     * @param annotationConfigApplicationContext
     */
    private static void bySingletonBeanRegistry(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        // 使用单体装配bean
        SingletonBeanRegistry singletonBeanRegistry = annotationConfigApplicationContext.getBeanFactory();
        // 初始化
        User temp = User.createUser("user-by-singleton", 18);
        // 注册
        singletonBeanRegistry.registerSingleton("user-by-singleton", temp);
        User singletonUser = annotationConfigApplicationContext.getBean("user-by-singleton", User.class);
        System.out.println(singletonUser);
    }

    /**
     *
     *
     * @param annotationConfigApplicationContext
     */
    private static void byBeanDefinitionBuilder(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        // 方式四 : 使用 BeanDefinitionBuilder 装配
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder
                .addPropertyValue("username", "user-by-beanDefinitionBuilder")
                .addPropertyValue("age", 18);
        // 获取 BeanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 设置className
        annotationConfigApplicationContext.registerBeanDefinition("user-by-beanDefinitionBuilder",beanDefinition);
        User beanDefinitionBuilderUser = annotationConfigApplicationContext.getBean("user-by-beanDefinitionBuilder",User.class);
        System.out.println(beanDefinitionBuilderUser);
    }

    /**
     * 通过@Component 派生方式
     *
     * @param annotationConfigApplicationContext
     */
    private static void byComponent(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        // 方式二 ：通过@Component 派生的方式装配bean
        User componentUser = annotationConfigApplicationContext.getBean("user-by-component",User.class);
        System.out.println(componentUser.createUser("user-by-component",18));
    }

    /**
     * 通过注解
     *
     * @param annotationConfigApplicationContext
     */
    private static void byBeanAnnotation(AnnotationConfigApplicationContext annotationConfigApplicationContext) {
        // 方式一 ：使用@bean 注解装配bean
        User user = annotationConfigApplicationContext.getBean("user-by-bean-annotation", User.class);
        System.out.println(user);
    }


    @Bean(value = "user-by-bean-annotation")
    public User getUserByBeanAnnotation(){
        User user = new User();
        user.setUsername("我是通过bean annotation装配的bean ------------>baifukuan");
        user.setAge(18);
        return user;
    }

}
