package com.baifukaun.spring.week05.spring.bean.initialization;

import com.baifukaun.spring.week05.spring.bean.factory.DefaultUserFactory;
import com.baifukaun.spring.week05.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitializationSpringBean {

    public static void main(String[] args) {
        // 创建 BeanFactory 容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册
        applicationContext.register(InitializationSpringBean.class);
        // 启动
        applicationContext.refresh();
        System.out.println("启动");
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        // 关闭
        applicationContext.close();
    }

    /**
     * 基于 @PostConstruct 注解
     *
     * @return
     */
    @Bean(initMethod = "initUserMethod")
    public UserFactory userFactory() {
        return new DefaultUserFactory();
    }

}
