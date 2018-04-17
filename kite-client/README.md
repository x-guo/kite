# project name is kite-寓意风筝
此项目为了解spring家族各项插件以及模块所创建
目前项目仅仅加入了Eureka,方便接口定义联调对接</p>
1.启动类Application增加注解:@EnableEurekaServer
  1.1对应jar包:
     <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-eureka-server</artifactId>
     </dependency>

2.增加本地缓存,引入对应jar包,启动Application增加注解@EnableCache,两种使用方法
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-cache</artifactId>
  </dependency>
  <dependency>
      <groupId>com.github.ben-manes.caffeine</groupId>
      <artifactId>caffeine</artifactId>
  </dependency>

- application.yml配置文件中配置：
  - 优点：简单
  - 缺点：无法针对每个cache配置不同的参数，比如过期时长、最大容量
- 配置类中配置
  - 优点：可以针对每个cache配置不同的参数，比如过期时长、最大容量
  - 缺点：要写一点代码,请查看CacheConfig类
3.增加数据源Hikari配置,更新环境打包发布分离管理
  Hikari:来自日文,是“光”（阳光的光）的意思,该连接池口号是:快速,简单,可靠,据称是目前最好的连接池
1.	详情配置请查看HikariCPConfig类
2.	分为三个环境,local,dev,production
3.	打包命令mvn install package -P 环境名称

工程:
    eureka-server2: 是两个eureka服务注册中心集群,用于kite工程发现
    ribbon-consumer:用于消费eureka-server2中的服务
    kite:           服务提供者

4.actuator这个包用途可以监控系统各项数据包括jvm详情,使用时记得将spring-security先禁用掉,否则会被拦截</p>

5.ribbon服务消费,包在是在ribbon-consumer中使用,用于服务消费,结合eureka使用他自身的RibbonServerList会被DiscoveryEnabledNIWServerList所覆盖,扩展成
从eureka获取服务列表,同时也会用NIWDiscoveryPing取代IPing,将职责委托给eureka来确定服务是否启动</p>
    --5.1:启动类创建RestTemplate的spring bean,通过@LoadBalanced注解开启客户端负载均衡
          </p>访问对应的consumer链接会发现,负载均衡起到相应作用

eureka.client.register-with-eureka=true;表示当前服务启动注册操作,false表示当前服务不会启动注册操作
eureka.client.fetch-registry=true 表示获取服务列表,若是消费端必须为true
eureka.client.registry-fetch-interval-seconds=30 表示服务列表更新时间
eureka.instance.lease-renewal-interval-in-seconds=30 默认向心跳时间
eureka.instance.lease-expiration-duration-in-seconds=90 默认服务失效时间

eureka支持CAP中的AP放弃一致性,加强可用性和分区容错性
zookeeper支持CAP中的CP,放弃可用性,保证数据最终一致性和分区容错性

● 一致性（C）：在分布式系统中的所有数据备份，在同一时刻是否同样的值。（等同于所有节点访问同一份最新的数据副本）
● 可用性（A）：在集群中一部分节点故障后，集群整体是否还能响应客户端的读写请求。（对数据更新具备高可用性）
● 分区容错性（P）：以实际效果而言，分区相当于对通信的时限要求。系统如果不能在时限内达成数据一致性，就意味着发生了分区的情况，必须就当前操作在C和A之间做出选择。


spring-cloud负载均衡原理:
        通过LoadBalancerInterceptor拦截器对RestTemplate 请求进行拦截,利用spring-cloud负载均衡器LoadBalancerClient将以逻辑服务名host的URI转换成具体的服务实例地址
        通过LoadBalancerClient的ribbon实现RibbonBalancerClient,实现负载均衡器时实际使用的是ribbon的ILoadBalancer接口实现,自动化配置采用ZoneAwareLoadBalancer

6.spring-cloud 多服务熔断机制hystrix
    引入对应的依赖:
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
        </dependency>
    对应的service服务增加注解
    @HystrixCommand(fallbackMethod = "helloFallback",commandKey = "helloKey")
    意思是:当前方法如果出现服务间访问时间超时,服务不可用但可访问时,返回自定义内容,调用方法helloFallback.
    根据注解可以看出来,该熔断机制是“命令模式”,单个操作是HystrixCommand返回单个结果,HystrixObservableCommand返回多个结果

    熔断机制:进入fallback回退处理,称之为“服务降级”,一共三种情况:
    1,当命令处于熔断/短路状态,断路器是打开的时候
    2,当命令的线程池,请求队列或者信号量被占满的时候
    3,HystrixObservableCommand.construct()和HystrixCommand.run()抛出异常的时候

    最终的服务降级肯定不是一个依赖网络请求的处理,而是一个能够稳定返回结果的处理逻辑
    HystrixCommand.getFallback();
    HystrixObservableCommand.resumeWithFallback();

    命令模式:实现“行为请求者”与“行为实现者”之间的解耦,请求者不需要知道行为的具体实现






