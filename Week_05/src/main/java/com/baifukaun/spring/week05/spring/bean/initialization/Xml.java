package com.baifukaun.spring.week05.spring.bean.initialization;

import com.baifukaun.spring.week05.spring.bean.factory.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring bean装配方式一
 * 定义是bean的xml 文件，通过ClassPathXmlApplicationContext 创建上下文后，
 * 通过xml 中定义的bean id 获取装配的bean
 *
 *
 */
public class Xml {
    public static void main(String[] args) {
        // 创建spring上下文
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/user-bean.xml");
        User user = classPathXmlApplicationContext.getBean("user-by-xml",User.class);
        System.out.println(user);
    }
}
