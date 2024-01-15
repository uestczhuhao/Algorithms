
### 简述下Spring boot
- Spring Boot是一种基于Spring框架的开源框架，它旨在简化Spring应用程序的初始化和开发过程。
- Spring Boot通过自动配置和约定优于配置的原则，可以快速创建独立的、生产级别的Spring应用程序。
- Spring Boot提供了许多特性，如内嵌Tomcat、自动配置、健康检查、外部化配置等，可以帮助开发人员更轻松地构建出企业级应用。
    - 内嵌Tomcat（一个Web），可以直接运行
        - 否则要先起一个Tomcat服务（容器），再把Java应用打包成war包，放入容器中执行
        - 可以通过配置把内置的Tomcat关掉，使用外部的容器
- Spring Boot简单易用、快速开发，是目前最流行的Java框架之一
- 其他优点
    - 微服务支持：Spring Boot 自带微服务开发核心组件（如 Spring Cloud），提供了一套完整的微服务开发和部署方案，使开发人员可以轻松构建和部署大型、复杂的分布式应用程序。
    - 轻量级：Spring Boot 的特性是精简、灵活、模块化的，应用程序的运行时开销较小，能够快速响应用户请求，同时减少了开发和部署的成本。
    - 多数据源支持：Spring Boot 提供了多种数据库的支持，包括关系型数据库和非关系型数据库，开发人员可以根据自己的需要方便地进行配置和使用
    - 安全性：Spring Boot 提供了各种安全功能和特性，如 SSL/TLS、OAuth2、JWT 等，可以保障应用程序的安全性。
    - 提供starter依赖项简化构建配置

#### Spring boot、Spring cloud和dubbo的设计原理和应用场景
- boot基于spring，实现了自动装配
- cloud可以看做是boot的集合，单机和分布式项目的区别


### spring的注解
- @Component, @Service, @Controller, @Repository是spring注解，注解后可以被spring框架所扫描并注入到spring容器来进行管理
- @Component是通用注解，其他三个注解是这个注解的拓展，并且具有了特定的功能
- @Repository注解在持久层中，具有将数据库操作抛出的原生异常翻译转化为spring的持久层异常的功能。
- @Controller层是spring-mvc的注解，具有将请求进行转发，重定向的功能。
- @Service层是业务逻辑层注解，这个注解只是标注该类处于业务逻辑层。
- 用这些注解对应用进行分层之后，就能将请求处理，业务逻辑处理，数据库操作处理分离出来，为代码解耦，也方便了以后项目的维护和开发。



### Springboot处理HTTP请求的过程
1. 请求打到DispatcherServlet（HTTP 请求处理程序/控制器的中央调度程序）
2. 根据URL解析，判断请求URI对应的映射
3. 调用HandlerMapping获得该Handler配置的所有相关的对象（包括Handler对象以及 Handler对象对应的拦截器），最后以HandlerExecutionChain执行链对象的形式返回。
    - HandlerMapping：处理器映射器根据请求的url、method等信息查找Handler，即控制器方法
        - 所有标注了@RequestMapping的方法都会以map的形式储存在这里，保存了所有@RequestMapping 和handler的映射规则。
4. DispatcherServlet 根据获得的Handler，选择一个合适的HandlerAdapter做参数解析
    - Handler：处理器，即在Controller中写的方法
    - HandlerAdapter：处理器适配器，解析参数用的，常用的参数：@PathVariable  @RequestParam  @RequestBody
5. 如果成功获得HandlerAdapter，此时将开始执行拦截器的preHandler(…)方法
    - 这一步执行拦截器
6. 提取Request中的模型数据，填充Handler入参，开始执行Handler（Controller)方法，处理请求。
7. 如果不采用前后端分离，下面的过程是
    1. Handler执行完成后，向DispatcherServlet 返回一个ModelAndView对象。
        - 默认情况下我们返回的对象都是采用Jackson来帮我们序列化到response中返回
    2. 此时将开始执行拦截器的postHandle(...)方法
    3. 根据返回的ModelAndView，选择一个适合的ViewResolver进行视图解析，根据Model 和View，来渲染视图。
        - 此时会判断是否存在异常：如果存在异常，则执行 HandlerExceptionResolver进行异常处理
    4. 将渲染结果返回给客户端。
8. 否则，直接返回Jackson反序列化后的json数组
    - 主要区别在于，前后端分离的注解为@RestController（等于@Controller + @ResponseBody）
    - 在这种情况下，DispatcherServlet就不会将处理结果转发给视图，而是直接将处理结果写入到response的body区域
    - @Controller和@RestController是两个注解，它们都可以用于定义控制器类。
        - @Controller注解用于创建传统的Spring MVC控制器，它可以返回视图。
        - @RestController注解用于创建RESTful Web服务，它可以返回JSON或XML数据。
        - @RestController注解相当于@Controller和@ResponseBody注解的组合，它可以省略每个请求处理方法的@ResponseBody注解。
            - 因此，如果您的控制器需要返回JSON或XML数据，那么您应该使用@RestController注解。
            - 如果您的控制器需要返回视图，那么您应该使用@Controller注解

#### Springboot 最多处理多少请求（并发数）
- 200个，理由是Tomcat的线程池的配置中，核心线程数为10，最大线程数为200，队列长度为Int最大值
- 但是Tomcat 的线程池，就是先使用核心线程数配置，再使用最大线程配置，最后才使用队列长度。
    - 这一点和java线程池不一样
    - 原因是在Tomcat使用了自定义的阻塞队列，其offer时会判断线程池中的线程数是否达到最大，达不到就返回false
    - 此时效果是新线程（第11个~第200个）不入队列，而是直接起线程执行


### Spring Boot Starter是什么？怎么实现？如何自定义Starter
- 是一种包含了一组特定功能和依赖关系的依赖项，旨在为特定的应用程序场景提供快速集成和开箱即用的功能（否则你要手动配置这些依赖）
    - 有效地降低了项目开发过程的复杂程度，对于简化开发操作有着很好的效果
    - 还会根据 Starter 的依赖关系，自动加载依赖的 Starter 中的 AutoConfiguration Class
- 常见的starter
    - spring-boot-starter-web - 用于构建Web应用程序,使用Tomcat作为默认嵌入式容器
    - spring-boot-starter-logging - 用于使用Spring 日志框架
    - spring-boot-starter-security - 用于使用Spring Security添加安全性功能
    - spring-boot-starter-test - 用于使用Junit、Hamcrest和Mockito进行测试
    - spring-boot-starter-cache - 集成Spring Cache
- Starter 的核心原理是**自动配置和条件化装配**。
    - Spring Boot 根据 classpath 上的资源和配置来自动配置应用程序的特定功能模块，而 Starter 中的 `spring.factories` 文件则是自动配置的入口。
    - 在 `spring.factories` 文件中，通过声明 `org.springframework.boot.autoconfigure.EnableAutoConfiguration` 类型的类来启用自动配置。
    - 自动配置类通过 @Conditional 注解（和@Configuration配合使用）来判断是否满足特定条件，如果满足条件，则会自动配置相应的功能模块。
        - @ConditionalOnClass：用于判断当前classpath下是否存在指定的类。如果存在，则该注解修饰的配置类才会生效
        - @ConditionalOnBean：用于判断当前容器中是否存在指定的Bean。如果存在，则该注解修饰的配置类才会生效
        - @EnableConfigurationProperties：把引用类的属性注入
    - 通过 Maven 或 Gradle 等构建工具引入 Starter 时，它们会自动将 Starter 中的依赖项引入到项目的 classpath 中
- 自定义自己的starter
    - 要自定义Spring Boot的自动配置，可以通过编写自定义的配置类，使用@Configuration注解进行标识，并在其中定义需要的配置和Bean。
    - 通过使用条件化的注解（如@ConditionalOnClass、@ConditionalOnProperty等），可以根据一定的条件决定是否生效。
    - 另外，还可以通过在META-INF/spring.factories文件中定义自定义的org.springframework.boot.autoconfigure.EnableAutoConfiguration配置，将自定义的配置类引入到自动配置中。
    - 这样，在项目中引入相应的自定义Starter时，自定义的自动配置就会生效。
    - 和日常开发项目的不同
        - 日常的Spring Boot项目开发，所有在主类相同层级及其子层级下标注了Bean相关注解（@Service、@Component等等）都位于扫描范围内，都会被初始化为Bean
        - 而starter的开发中，只有声明在自动配置候选类文件中的类，才会被初始化为Bean
            - 所以在自己开发的starter中，要注意使用该类
- 如果没有starter
    - 先要找到必要的程序包（如mybatis jar包）
    - 再找到对应的spring整合的jar包，选版本（mybatis-spring）
    - 在spring的applicationContext.xml文件中配置dataSource和mybatis相关信息。
    - 每一个组件（如redis等）都要重复这个过程

### Springboot 的自动配置是什么？
- Spring Boot自动配置是Spring Boot框架提供的一种机制，旨在根据项目的依赖关系和应用场景，自动配置Spring应用程序的各种组件和功能。
    - 这种自动配置能力大大简化了项目的配置过程，减少了开发者的配置工作，提高了开发效率。
    - Spring Boot通过条件化的配置和约定大于配置的原则，实现了对常见应用场景的自动配置。
- 通过@EnableAutoConfiguration开启（SpringBootApplication注解之一）
- Spring Boot自动配置的原理是通过条件化配置和Spring AOP（面向切面编程）技术实现的
- Spring Boot 的自动配置机制通过扫描 classpath 上的 jar 包，自动配置 Spring 应用程序的运行环境。
    - 在Spring Boot启动（2.x）时，会扫描所有的外部库的classpath下所有的META-INF/spring.factories文件（Spring Boot 2.x版本），
        - 在这个文件中读取哪些类需要被读取以进行自动配置（自动配置候选类配置文件）
        - 3.x版本则扫描 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports文件
    - 具体来说，自动配置机制会根据 classpath 上存在的 jar 包和配置文件，自动创建 Spring Bean（否则这些工作在Spring中需要程序员手动完成），完成对应的配置。
    - 在这些配置类中所定义的Bean会根据条件注解所指定的条件来决定是否需要将其导入到Spring容器中。

### SpringBootApplication注解，分为哪三个？详细介绍
- @SpringBootConfiguration // 继承了Configuration，表示当前是注解类
- @EnableAutoConfiguration // 开启springboot的注解功能，springboot的四大神器（auto-configuration、starters、cli、actuator）之一，其借助@import的帮助，从各个spring.factories配置文件中加载需要的bean到IOC容器
    - 通过@Import(AutoConfigurationImportSelector.class)，从classpath中搜寻所有的META-INF/spring.factories配置文件，
    - 并将其中org.springframework.boot.autoconfigure.EnableAutoConfiguration对应的配置项
        - 通过反射（Java Refletion）实例化为对应的标注了@Configuration的JavaConfig形式的IoC容器配置类，然后汇总为一个并加载到IoC容器。
- @ComponentScan // 扫描路径设置

### Spring Boot 的security是什么？
- 它基于Spring Security，提供了许多默认配置，可以轻松地保护Web应用程序。Spring Boot Security提供了以下功能：
    - 身份验证和授权
    - 支持多种身份验证方式，如表单、HTTP Basic、OAuth2等
    - 防止跨站点请求伪造（CSRF）
- 使用了@PreAuthorize注解，在方法执行前做鉴权
    - @PreAuthorize注解的原理是使用Spring AOP（面向切面编程）技术，在方法执行之前进行权限检查。
    - Spring AOP是Spring框架的一个重要组成部分，它可以在运行时动态地将代码织入到方法调用中，从而实现一些横切关注点的功能，例如事务管理、安全性、日志记录等。

### 循环依赖有哪些情况？怎么解决？
- 构造函数循环依赖
    - 这种依赖spring是处理不了的，直接抛出BeanCurrentlyInCreationException异常。
    - 可以通过@Lazy懒加载，什么时候需要对象再进行bean对象的创建
- 属性循环依赖
    - 通过“三级缓存”处理循环依赖
- 方法循环依赖。
- 避免手段
    - 构造函数注入： 在构造函数中注入依赖项，而不是在属性中注入。
    - Setter注入： 使用setter方法注入依赖项，而不是在构造函数中注入。
    - 延迟注入： 使用@Lazy注解延迟加载依赖项。
    - @Autowired注解的required属性： 将required属性设置为false，以避免出现循环依赖问题。
    - @DependsOn注解： 使用@DependsOn注解指定依赖项的加载顺序，以避免出现循环依赖问题

### Spring循环依赖
1. 构造器循环依赖
    - 通过构造器注入构成的循环依赖，此依赖是无法解决的，只能抛出BeanCurrentlyInCreationException异常表示循环依赖
    - 原理：Spring容器将每一个正在创建的bean标识符放在一个“当前创建bean池”中，bean标识符创建过程中将一直保持在这个池中，
        - 因为如果在创建bean过程中发现自己已经在“当前创建bean池”中时，将会抛出BeanCurrentlyInCreationException异常表示循环依赖；而对于创建完毕的bean将从“当前创建bean池”中清除掉。
2. prototype范围的依赖处理
- 对于scope为prototype范围的bean，Spring容器无法完成依赖注入，因为Spring容器不进行缓存“prototype”作用域的bean，因此无法提前暴露一个创建中的bean，所以检测到循环依赖会直接抛出BeanCurrentlyInCreationException异常
3. Setter方法注入
    - 一级缓存（singletonObjects）：用于存放完全初始化好的 bean
    - 二级缓存（earlySingletonObjects）：存放原始的 bean 对象（尚未填充属性），用于解决循环依赖
    - 三级级缓存（singletonFactories）：存放 bean 工厂对象
    - 过程
        - A 创建过程中需要 B， 于是 A 将自己放到三级缓存里面（此时是A的ObjectFactory，因为三级缓存中存放的都是ObjectFactory对象），去实例化 B
        - B 实例化的时候发现需要 A，于是 B 先查一级缓存，没有，再查二级缓存，还是没有，再查三级缓存找到了A，然后把三级缓存中的 A 放到二级缓存，并删除三级缓存中的 A
        - B 顺利初始化完毕，将自己放到一级缓存中(此时 B 中的 A 还是创建中状态，并没有完全初始化)，删除三级缓存中的 B然后接着回来创建 A，
            - 此时 B 已经完成创建，直接从一级缓存中拿到 B，完成 A 的创建，并将 A 添加到单例池，删除二级缓存中的 A
    - 过程（详细版）
        - 实例化 A，此时 A 还未完成属性填充和初始化方法（@PostConstruct）的执行，A 只是一个半成品。
        - 为 A 创建一个 Bean工厂，并放入到 singletonFactories 中。
        - 发现 A 需要注入 B 对象，但是一级、二级、三级缓存均为发现对象 B。
        - 实例化 B，此时 B 还未完成属性填充和初始化方法（@PostConstruct）的执行，B 只是一个半成品。
        - 为 B 创建一个 Bean工厂，并放入到 singletonFactories 中。
        - 发现 B 需要注入 A 对象，此时在一级、二级未发现对象A，但是在三级缓存中发现了对象 A，从三级缓存中得到对象 A，并将对象 A 放入二级缓存中，同时删除三级缓存中的对象 A。（注意，此时的 A 还是一个半成品，并没有完成属性填充和执行初始化方法）
        - 将对象 A 注入到对象 B 中
        - 对象 B 完成属性填充，执行初始化方法，并放入到一级缓存中，同时删除二级缓存中的对象 B。（此时对象 B 已经是一个成品）
        - 对象 A 得到对象B，将对象 B 注入到对象 A 中。（对象 A 得到的是一个完整的对象 B）
        - 对象 A完成属性填充，执行初始化方法，并放入到一级缓存中，同时删除二级缓存中的对象 A。

4. 为什么要用三级缓存（本质是三个Map）？二级行不行？
    - 二级解决不了动态代理生成的bean的循环依赖，需要三级缓存
        - 二级缓存只是存储了Bean的原始对象，三级缓存存储了Bean的ObjectFactory，
        - 在AOP场景下，Bean可能是一个代理对象，而不是原始对象。因此，三级缓存存储了Bean的ObjectFactory，而不是原始对象，以便在循环依赖过程中返回正确的Bean实例
    - 三级也能解决弱依赖中有代理的情况

### FactoryBean和BeanFactory区别
- BeanFactory，以Factory结尾，表示它是一个工厂类(接口)， 它负责生产和管理bean的一个工厂。
- 在Spring中，BeanFactory是IOC容器的核心接口，它的职责包括：实例化、定位、配置应用程序中的对象及建立这些对象间的依赖。（如XMLBeanFactory）
- BeanFactory以Bean结尾，表示它是一个工厂对象，用于创建和管理其他Bean的实例，它允许开发人员在 Bean 的创建过程中进行更多的自定义操作
    - 一般情况下，Spring通过反射机制利用<bean>的class属性指定实现类实例化Bean，在某些情况下，实例化Bean过程比较复杂，如果按照传统的方式，则需要在<bean>中提供大量的配置信息。
        - 配置方式的灵活性是受限的，这时采用编码的方式可能会得到一个简单的方案。
    - 对于复杂的Bean对象初始化创建使用其可封装对象的创建细节
    - 第三方提供的类，但是不能直接直接注入，可以提供一个FactoryBean类，让使用更简单
    - 常用的：SqlSessionFactoryBean：创建MyBatis的SqlSessionFactory实例，可以通过该实例获取SqlSession实例，从而操作数据库


### Bean的生命周期
- 通过Spring下的beanFactory工厂利用反射机制创建bean对象
- 根据set方法或有参构造方法给对象的属性进行依赖注入（@Autowire注解在这一步完成）
- 判断当前的bean对象是否实现相关的aware接口，如beanNameAware，有的话执行相关的方法
- 执行bean的前置处理器postProcessBeforeInitialization
- 执行初始化方法initMethod
- 执行bean对象的后置处理器postProcessAfterInitialization，主要是对bean进行增强，有可能在这里产生代理对象
- 判断当前bean对象是否为单例，是则放到Spring对象容器中，多例则直接返回bean对象
- 使用bean对象
- 关闭容器，调用destroy方法销毁对象

### springboot 的启动
1. SpringBootApplication注解
    - @SpringBootConfiguration // 继承了Configuration，表示当前是注解类
    - @EnableAutoConfiguration // 开启springboot的注解功能，springboot的四大神器（auto-configuration、starters、cli、actuator）之一，其借助@import的帮助，从各个spring.factories配置文件中加载需要的bean到IOC容器
        - 通过@Import(AutoConfigurationImportSelector.class)，从classpath中搜寻所有的META-INF/spring.factories配置文件，
        - 并将其中org.springframework.boot.autoconfigure.EnableAutoConfiguration对应的配置项
        - 通过反射实例化为对应的标注了@Configuration的JavaConfig形式的IoC容器配置类，然后汇总为一个并加载到IoC容器。
    - @ComponentScan // 扫描路径设置
2. 启动过程
    - 从spring.factories配置文件中加载EventPublishingRunListener对象，该对象拥有SimpleApplicationEventMulticaster属性，即在SpringBoot启动过程的不同阶段用来发射内置的生命周期事件;
    - 准备环境变量，包括系统变量，环境变量，命令行参数，默认变量，servlet相关配置变量，随机值以及配置文件（比如application.properties）等;
    - 控制台打印SpringBoot的bannner标志；
    - 根据不同类型环境创建不同类型的applicationcontext容器，因为这里是servlet环境，所以创建的是AnnotationConfigServletWebServerApplicationContext容器对象；
    - 从spring.factories配置文件中加载FailureAnalyzers对象,用来报告SpringBoot启动过程中的异常；
    - 为刚创建的容器对象做一些初始化工作，准备一些容器属性值等，对ApplicationContext应用一些相关的后置处理和调用各个ApplicationContextInitializer的初始化方法来执行一些初始化逻辑等，
        - 其中最核心的一步，将之前通过@EnableAutoConfiguration获取的所有配置以及其他形式的IoC容器配置加载到已经准备完毕的ApplicationContext。
    - 刷新容器，这一步至关重要。比如调用bean factory的后置处理器，注册BeanPostProcessor后置处理器，初始化事件广播器且广播事件，初始化剩下的单例bean和SpringBoot创建内嵌的Tomcat服务器等等重要且复杂的逻辑都在这里实现，主要步骤可见代码的注释，关于这里的逻辑会在以后的spring源码分析专题详细分析；
    - 执行刷新容器后的后置处理逻辑，注意这里为空方法；
    - 调用ApplicationRunner和CommandLineRunner的run方法，我们实现这两个接口可以在spring容器启动后需要的一些东西比如加载一些业务数据等;
    - 报告启动异常，即若启动过程中抛出异常，此时用FailureAnalyzers来报告异常;
    - 最终返回容器对象，这里调用方法没有声明对象来接收。


#### beanfactory和ApplicationContext是什么关系？使用有什么区别？
- BeanFactory和ApplicationContext都是接口，并且ApplicationContext（即为IOC容器）间接继承了BeanFactory。
    - BeanFactory是Spring中最底层的接口，提供了最简单的容器的功能，只提供了实例化对象和获取对象的功能，负责bean的整个生命周期
    - ApplicationContext是Spring的一个更高级的容器，提供了更多的有用的功能。
        - 例如：获取Bean的详细信息(如定义、类型)、国际化的功能、统一加载资源的功能、强大的事件机制、对Web应用的支持
        - 在容器启动时，一次性创建了所有的Bean。这样，在容器启动时，我们就可以发现Spring中存在的配置错误，这样有利于检查所依赖属性是否注入。
- 一般称BeanFactory为IoC容器，而称ApplicationContext为应用上下文
- Springboot采用ApplicationContext来扫描和生成Bean
- 加载方式不同
    - BeanFactory ：延迟注入(使用到某个 bean 的时候才会注入)，相比于BeanFactory来说会占用更少的内存，程序启动速度更快
    - ApplicationContext ：容器启动的时候，不管你用没用到，一次性创建所有bean


### springboot 四大神器
- actuator：Spring Boot Actuator的关键特性是在应用程序里提供众多Web端点，通过它们了解应用程序 运行时的内部状况。
- starters：构建Spring Boot启动器是为了解决复杂的依赖管理。Starter POM是一组方便的依赖描述符，您可以在应用程序中包含这些描述符。
    - 创建一个ConfigurationProperties用于保存你的配置信息（如果你的项目不使用配置信息则可以跳过这一步，不过这种情况非常少见）
    - 配置spring.factories配置文件；在spring boot启动时会加载各个starter的配置文件，汇总成一个配置类，加载到IOC容器
- CLI：可以检测到代码中使用的类，知道需要给Classpath中添加的哪些起步依赖才能让程序运行起来。
- auto-configuration：Spring Boot自动配置代表了一种基于类路径上存在的依赖关系自动配置Spring应用程序的方法。还可以通过定义消除自动配置类中包含的某些bean。这些可以使开发更快更容易。


### Autowired 变量都是单例吗？
- 默认情况下是，但是不绝对
- 因为使用Autowired注解是进行Spring中Bean的注入的，而Spring中Bean的默认作用域是Singleton，在Singleton作用域下，Spring中的Bean都是单例的。
- 那为什么又说不是绝对的呢：因为Spring中Bean的作用域可以通过@scope注解或是在xml文件添加scope属性修改
    - 还可以修改为Prototype（每次getBean时创建一个新的Bean实例）
    - Requset（每次请求创建Bean）
    - Session（每个会话Bean）
    - Application（在Web应用程序的整个生命周期内，只创建一个Bean实例）

### Spring中事务失效的场景有哪些
- 事务方法内部捕获了异常，但是没有将异常重新抛出，导致事务无法回滚。
    - 或者是没有使用rollbackFor属性指定需要回滚的异常类型，导致事务无法回滚
- 从类中的一个没有注解的方法A，调用同一类中的有注解的方法B
    - 这是由于使用Spring AOP代理造成的，因为只有当事务方法被当前类以外的代码调用时，才会由Spring生成的代理对象来管理。
    - 解决：把A上面也加上注解（即最外层的调用处加注解）
- 方法的事务传播类型不支持事务
    - NOT_SUPPORTED：以非事务方式运行，如果当前存在事务，则把当前事务挂起。
    - NEVER：以非事务方式运行，如果当前存在事务，则抛出异常。
- 方法使用final（或static）修饰。
    - 那么Spring AOP就无法在代理类中重写该方法，事务就不会生效
- 在一个private方法上用@Transactional注解
    - 这是因为Spring AOP（面向切面编程）的工作原理是通过代理对象来实现的。
    - 当您在一个public方法上使用@Transactional注解时，Spring会生成一个代理对象，该代理对象负责管理事务
    - 但是，对于private方法，由于其访问权限的限制，Spring无法生成代理对象，因此事务也无法正常生效
- 在新的线程中调用
    - 线程不同，数据库连接不同，所以是两个不同的事务，不会回滚
- 类没有被spring管理
    - 如把类上面的@Service去掉


### spring事物传播行为（事物传播级别）7种
- PROPAGATION_REQUIRED	如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。
- PROPAGATION_SUPPORTS	支持当前事务，如果当前没有事务，就以非事务方式执行。
- PROPAGATION_MANDATORY	使用当前的事务，如果当前没有事务，就抛出异常。
- PROPAGATION_REQUIRES_NEW	新建事务，如果当前存在事务，把当前事务挂起。
- PROPAGATION_NOT_SUPPORTED	以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
- PROPAGATION_NEVER	以非事务方式执行，如果当前存在事务，则抛出异常。
- PROPAGATION_NESTED	如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类似的操作。

### Spring的IOC 和 DI
- 是一种设计模式，它将对象的创建和对象之间的调用过程交给了Spring容器来管理。这样做的好处是，我们可以将程序中的对象解耦，使得程序更加灵活，易于维护和扩展
- 本质是个全局Map，管理进程中的各种对象，然后处理对象之间的依赖关系（比如解决循环依赖）
- 谁控制谁，控制什么，为何是反转（有反转就应该有正转了），哪些方面反转了
    - IoC 容器控制了对象，主要控制了外部资源获取（不只是对象包括比如文件等）
    - 传统中，自己在对象中控制区直接获取对象（正转）；现在由容器来帮忙创建及注入依赖对象（依赖对象的获取被反转了）
    - 因为由容器帮我们查找及注入依赖对象，对象只是被动的接受依赖对象，所以是反转
- DI—Dependency Injection，即“依赖注入”，通过依赖注入，实现IOC，是一种实现方式（IOC是一种设计思想）
    - 组件之间依赖关系由容器在运行期决定，形象的说，即由容器动态的将某个依赖关系注入到组件之中。
    - 依赖注入的目的并非为软件系统带来更多功能，而是为了提升组件重用的频率，并为系统搭建一个灵活、可扩展的平台。

### 动态代理
1. JDK动态代理：利用反射机制生成一个实现代理接口的匿名类（是Proxy的子类），在调用具体方法前调用InvokeHandler来处理。
    - 优点
        - JDK 动态代理是 JDK 原生的，不需要任何依赖即可使用；
        - 通过反射机制生成代理类的速度要比 CGLib 操作字节码生成代理类的速度更快；
    - 缺点
        - 如果要使用 JDK 动态代理，被代理的类必须实现了接口，否则无法代理；（此时必须要采用CGlib动态代理）
        - JDK 动态代理无法为没有在接口中定义的方法实现代理，假设我们有一个实现了接口的类，我们为它的一个不属于接口中的方法配置了切面，
            - Spring 仍然会使用 JDK 的动态代理，但是由于配置了切面的方法不属于接口，为这个方法配置的切面将不会被织入。
            - 比如接口A中有方法a，继承的类B中不仅有方法a，又有方法b
        - JDK 动态代理执行代理方法时，需要通过反射机制进行回调，此时方法执行的效率比较低；
2. CGlib动态代理（Enhancer增强类，把代理类设置为其父类）：利用ASM（开源的Java字节码编辑库，操作字节码）开源包，将代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。
    - 优点
        - 使用 CGLib 代理的类，不需要实现接口，因为 CGLib 生成的代理类是直接继承自需要被代理的类；
        - CGLib 生成的代理类是原来那个类的子类，这就意味着这个代理类可以为原来那个类中，所有能够被子类重写的方法进行代理；
        - CGLib 生成的代理类，和我们自己编写并编译的类没有太大区别，对方法的调用和直接调用普通类的方式一致，所以 CGLib 执行代理方法的效率要高于 JDK 的动态代理；
    - 缺点
        - 由于 CGLib 的代理类使用的是继承，这也就意味着如果需要被代理的类是一个 final 类，则无法使用 CGLib 代理；
        - 由于 CGLib 实现代理方法的方式是重写父类的方法，所以无法对 final 方法，或者 private 方法进行代理，因为子类无法重写这些方法；
        - CGLib 生成代理类的方式是通过操作字节码，这种方式生成代理类的速度要比 JDK 通过反射生成代理类的速度更慢；
3. 区别
    - JDK代理使用的是反射机制实现aop的动态代理，CGLIB代理使用字节码处理框架asm，通过修改字节码生成子类。所以jdk动态代理的方式创建代理对象效率较高，执行效率较低，cglib创建效率较低，执行效率高；
    - JDK动态代理机制是委托机制，具体说动态实现接口类，在动态生成的实现类里面委托handler去调用原始实现类方法，
    - CGLIB则使用的继承机制，具体说被代理类和代理类是继承关系，所以代理类是可以赋值给被代理类的，如果被代理类有接口，那么代理类也可以赋值给接口。

### Spring BOOT 的AOP
- 面向切面编程，是面向对象编程的补充
- aop有三种，可以运行时织入，编译时织入，类加载时织入，（一般是第一种，基于JVM的动态代理）
- 本质是代理，Spring默认采用JDK动态代理（2.0之前，之后默认CGLib 代理），
    - 当我们为Spring Boot的某个bean配置了切面时，Spring Boot在创建这个bean时，实际上创建的是这个bean的一个代理对象。
    - 我们后续对bean中方法的调用，实际上调用的是代理类重写的代理方法

#### spring的设计模式，如模板、策略、适配、责任链、观察者、单例等（了解下）
- AOP的底层是责任链？？？
- 工厂模式：通过BeanFactory或ApplicationContext创建bean对象
- 单例：bean的默认作用域
    - 手写单例，并说明为什么这么写？（涉及到volatile的原理）
        - 饿汉式，缺点：如果单例对象的创建过程比较耗时，那么将会导致应用程序的启动比较慢。
            - private static Singleton uniqueInstance = new Singleton();
            - 需要时返回即可
        - 简单懒汉式（在方法声明时加锁），缺点是方法上加锁严重影响性能
            - 需要时加synchronized锁创建
            - private static synchronized Singleton getUinqueInstance() {不存在则创建}
        - DCL双重检验加锁（进阶懒汉式）
            - 实现
                - private static Singleton instance;
                - if (instance == null) 时，加锁new Singleton()，返回
            - 因为JVM本质重排序的原因，可能会初始化多次，不推荐使用
                - instance = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
                    1. 给 instance 分配堆内存(Singleton 对象)
                    2. 调用 Singleton 的构造函数来初始化成员变量，形成实例
                    3. 将instance 指针指向分配的内存空间（执行完这步singleton才是非 null了）。
                - 正常执行顺序：1->2->3，由于操作2和操作3没有依赖性（操作1和操作3有依赖性），可能发生指令重排，可能的执行顺序为：1->3->2。
                - 回到代码，当操作1,3执行后，instance 指针是不为null了，
                    - 此时，另一个线程执行发现instance不为null了，就会直接使用该对象，然后发现对象还没被初始化完成，带来数据错误
            - 加上volatile，即private volatile static Singleton instance，可以解决重排序问题，变成线程安全的
        - 静态内部类（优雅懒汉式，需要时才会被加载）
            - 内部一个private static class Holder类，然后类里面有一个final变量
            - 再getInstance()时，return Holder.singleton; 通过类加载完成单例
        - 枚举
            - 把单例封装为一个enum，需要时获取
- 代理设计模式：Spring AOP就是基于动态代理的
- 模板模式：父类定义了骨架（调用哪些方法及顺序），某些特定方法由子类实现。
    - Spring中jdbcTemplate、hibernateTemplate等以Template结尾的对数据库操作的类，它们就使用到模板模式
- 观察者设计模式：观察者设计模式是一种对象行为模式。
    - 它表示的是一种对象与对象之间具有依赖关系，当一个对象发生改变时，这个对象锁依赖的对象也会做出反应。
    - Spring事件驱动模型就是观察者模式很经典的应用。
        - ApplicationEventPublisher充当了事件的发布者，它也是个接口。
        - ApplicationListener充当了事件监听者的角色
- 适配器设计模式：适配器设计模式将一个接口转换成客户希望的另一个接口，适配器模式使得接口不兼容的那些类可以一起工作，其别名为包装器。
    - 在Spring MVC中，DispatcherServlet根据请求信息调用HandlerMapping，解析请求对应的Handler，
    - 解析到对应的Handler（也就是我们常说的Controller控制器）后，开始由HandlerAdapter适配器处理。
- 策略模式：Spring 框架的资源访问接口就是基于策略设计模式实现的。
    - UrlResource：访问网络资源的实现类。
    - ClassPathResource：访问类加载路径里资源的实现类。
    - FileSystemResource：访问文件系统里资源的实现类。
    - ServletContextResource：访问相对于 ServletContext 路径里的资源的实现类.
    - InputStreamResource：访问输入流资源的实现类。
- 责任链模式：它将请求的发送者和接收者解耦，让多个对象都有机会处理这个请求。当请求发生时，它会沿着这条链依次传递，直到有一个处理者处理它为止。
    - 责任链模式可以避免请求发送者和接收者之间的耦合，提高系统的灵活性和可扩展性。
    - 责任链模式的主要优点是降低了对象之间的耦合度，增强了系统的可扩展性，增强了给对象指派职责的灵活性，简化了对象之间的连接，以及责任分担。
    - 责任链模式的主要缺点是不能保证每个请求一定被处理，可能会导致系统性能受到影响，以及职责链建立的合理性要靠客户端来保证，增加了客户端的复杂性。
    - Spring的HandlerInterceptor（如鉴权、登录验证登）在责任链中充当处理者的角色，通过HandlerExecutionChain进行责任链调用。


    















