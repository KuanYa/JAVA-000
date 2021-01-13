学习笔记

### 一、搭建ActiveMQ服务，基于JMS

#### 1. win10搭建ActiveMQ

* ActiveMQ官网下载

  * http://activemq.apache.org/components/classic/download/

  ![image-20210110174258213](D:\Java-training-camp\JAVA-000\JAVA-000\Week_13\README.assets\image-20210110174258213.png)





* 注册ActiveMQ服务

  * 解压下载后的压缩文件 `apache-activemq-5.16.0-bin.zip`

  * 进入如下目录 `\apache-activemq-5.16.0\bin\win64` （当前电脑 win10 、64位），执行`activemq.bat` 文件，启动ActiveMQ 即可

    ![image-20210110174930035](D:\Java-training-camp\JAVA-000\JAVA-000\Week_13\README.assets\image-20210110174930035.png)

    ​			![image-20210110175011434](D:\Java-training-camp\JAVA-000\JAVA-000\Week_13\README.assets\image-20210110175011434.png)

    ````js
    用户名： admin
    密码：  admin
    ````

  * 综上，win10下，搭建ActiveMQ已经完成。

#### 2、spring-boot下搭建基于JMS协议的ActiveMQ

* Maven 项目引入需要的依赖

  ```xml
   <properties>
          <java.version>1.8</java.version>
      </properties>
  
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>1.5.1.RELEASE</version>
      </parent>
  	
      <dependencies>
  	<dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-activemq</artifactId>
          </dependency>
  		
  
  	<dependency>
              <groupId>org.apache.activemq</groupId>
              <artifactId>activemq-broker</artifactId>
          </dependency>
  
  	<dependency>
              <groupId>com.google.code.gson</groupId>
              <artifactId>gson</artifactId>
          </dependency>
  		
      </dependencies>
  	
  	<build>
              <plugins>
                  <plugin>
                      <groupId>org.springframework.boot</groupId>
                      <artifactId>spring-boot-maven-plugin</artifactId>
                  </plugin>
               </plugins>
           </build>
  
  </project>
  ```

* application.yml

  ```yaml
  server:
    port: 8080
  
  spring:
    activemq:
      broker-url: tcp://127.0.0.1:61616
      user: admin
      password: admin
      close-timeout: 15s   # 在考虑结束之前等待的时间
      in-memory: true      # 默认代理URL是否应该在内存中。如果指定了显式代理，则忽略此值。
      non-blocking-redelivery: false  # 是否在回滚回滚消息之前停止消息传递。这意味着当启用此命令时，消息顺序不会被保留。
      send-timeout: 0     # 等待消息发送响应的时间。设置为0等待永远。
      queue-name: active.queue
      topic-name: active.topic.name.model
      packages:
        trust-all: true #不配置此项，会报错
    pool:
      enabled: true
      max-connections: 10   #连接池最大连接数
      idle-timeout: 30000   #空闲的连接过期时间，默认为30秒
      
    profiles:
      active: true
  
  # jms:
  #   pub-sub-domain: true  #默认情况下activemq提供的是queue模式，若要使用topic模式需要配置下面配置
  
  # 是否信任所有包
  #spring.activemq.packages.trust-all=
  # 要信任的特定包的逗号分隔列表（当不信任所有包时）
  #spring.activemq.packages.trusted=
  # 当连接请求和池满时是否阻塞。设置false会抛“JMSException异常”。
  #spring.activemq.pool.block-if-full=true
  # 如果池仍然满，则在抛出异常前阻塞时间。
  #spring.activemq.pool.block-if-full-timeout=-1ms
  # 是否在启动时创建连接。可以在启动时用于加热池。
  #spring.activemq.pool.create-connection-on-startup=true
  # 是否用Pooledconnectionfactory代替普通的ConnectionFactory。
  #spring.activemq.pool.enabled=false
  # 连接过期超时。
  #spring.activemq.pool.expiry-timeout=0ms
  # 连接空闲超时
  #spring.activemq.pool.idle-timeout=30s
  # 连接池最大连接数
  #spring.activemq.pool.max-connections=1
  # 每个连接的有效会话的最大数目。
  #spring.activemq.pool.maximum-active-session-per-connection=500
  # 当有"JMSException"时尝试重新连接
  #spring.activemq.pool.reconnect-on-exception=true
  # 在空闲连接清除线程之间运行的时间。当为负数时，没有空闲连接驱逐线程运行。
  #spring.activemq.pool.time-between-expiration-check=-1ms
  # 是否只使用一个MessageProducer
  #spring.activemq.pool.use-anonymous-producers=true
  ```

* BeanConfig.java

  ```java
  @Configuration
  public class BeanConfig {
      
      @Value("${spring.activemq.broker-url}")
      private String brokerUrl;
      
      @Value("${spring.activemq.user}")
      private String username;
      
      @Value("${spring.activemq.topic-name}")
      private String password;
      
      @Value("${spring.activemq.queue-name}")
      private String queueName;
      
      @Value("${spring.activemq.topic-name}")
      private String topicName;
      
      @Bean(name = "queue")
      public Queue queue() {
          return new ActiveMQQueue(queueName);
      }
      
      @Bean(name = "topic")
      public Topic topic() {
          return new ActiveMQTopic(topicName);
      }
  
      /**
       * 定义ActiveMQ 连接工厂
       *
       * @return
       */
      @Bean
      public ConnectionFactory connectionFactory() {
          return new ActiveMQConnectionFactory(username, password, brokerUrl);
      }
  
      @Bean
      public JmsMessagingTemplate jmsMessageTemplate() {
          return new JmsMessagingTemplate(connectionFactory());
      }
  
      /**
       * Queue模式中，对消息的监听需要对containerFactory进行配置
       *
       * @param connectionFactory
       * @return
       */
      @Bean("queueListener")
      public JmsListenerContainerFactory<?> queueJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
          SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
          factory.setConnectionFactory(connectionFactory);
          // 此处禁用 topic 模式
          factory.setPubSubDomain(false);
          return factory;
      }
  
      /**
       * Topic模式中，对消息的监听需要对containerFactory进行配置
       *
       * @param connectionFactory
       * @return
       */
      @Bean("topicListener")
      public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
          SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
          factory.setConnectionFactory(connectionFactory);
          // 开启 topic 模式
          factory.setPubSubDomain(true);
          return factory;
      }
  }
  ```

* 生产者 

  ```java
  @RestController
  public class ProducerController {
      @Autowired
      private JmsMessagingTemplate jmsMessagingTemplate;
  
      @Autowired
      private Queue queue;
  
      @Autowired
      private Topic topic;
  
  
      // curl http://localhost:8080/queue/test -X POST -d "testququuquqq"
      @RequestMapping("/queue/test")
      public String sendQueue(@RequestBody String str) {
          this.sendMessage(this.queue, str);
          return "success";
      }
  
  
      //curl http://localhost:8080/topic/test -X POST -d "testtopiccccc"
      @RequestMapping("/topic/test")
      public String sendTopic(@RequestBody String str) {
          this.sendMessage(this.topic, str);
          return "success";
      }
  
      // 发送消息，destination是发送到的队列，message是待发送的消息
      private void sendMessage(Destination destination, final String message) {
          jmsMessagingTemplate.convertAndSend(destination, message);
      }
  }
  ```

* Queue 模式的消费者

  ```
  @Component
  public class QueueConsumerListener {
      //queue模式的消费者
      @JmsListener(destination = "${spring.activemq.queue-name}", containerFactory = "queueListener")
      public void readActiveQueue(String message) {
          System.out.println("queue接受到：" + message);
      }
  }
  ```

* Topic 模式的消费者

  ```java
  @Component
  public class TopicConsumerListener {
      // topic模式的消费者
      @JmsListener(destination = "${spring.activemq.topic-name}", containerFactory = "topicListener")
      public void readActiveQueue(String message) {
          System.out.println("topic接受到：" + message);
      }
  }
  ```

* ActivemqApplication.java

  ```java
  @SpringBootApplication
  @EnableJms // 启动JMS服务
  public class ActivemqApplication {
  
      public static void main(String[] args) {
          SpringApplication.run(ActivemqApplication.class, args);
      }
  }
  ```

* 启动后，在`http://127.0.0.1:8161/admin/topics.jsp` 查看是否已经创建成功

  ![image-20210110213403134](D:\Java-training-camp\JAVA-000\JAVA-000\Week_13\README.assets\image-20210110213403134.png)

  ![image-20210110195045778](D:\Java-training-camp\JAVA-000\JAVA-000\Week_13\README.assets\image-20210110195045778.png)

* 总结，基于上述配置，启动程序后，可以看到，`Queue `和`Topic ` 两种模式下，对列已经创建成功，后续进一步操作使用各种对列。

* **Queue模式的使用**

  * 

* 参考地址

* 

* 

  * https://www.devglan.com/spring-boot/spring-boot-jms-activemq-example





