package io.kimmking.bean.initialization;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 通过xml方式，然后通过静态方法初始化bean
 *
 * 麻烦助教老师批阅时，请指正此方法是否满足作业要求
 *
 */
public class XmLInitializationSpringBean {

    public static void main(String[] args) {
        // 创建spring上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/META-INF/bean-instantiation-context.xml");
        User user = beanFactory.getBean("user-static-method",User.class);
        System.out.println(user);
    }

}
