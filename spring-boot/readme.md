# spring-boot

## 底层注解

### Configuration使用示例

使用@Configuration 注解修饰的类是一个spring配置类，该类同时也会被注册为spring的一个组件。
在类中定义一个使用@bean注解修饰的方法，则spring会以方法名为组件名，返回值类型为组件类型，返回值为组件值创建一个bean实例

在@Configuration注解中可以通过配置proxyBeanMethods为true或false来设置spring为全模式或精简模式
> proxyBeanMethods:是否代理bean中的方法，默认值为true。当设置为true时，从容器中获取SpringConfig的bean实例时获取的是代理对象
> true：full模式，在此模式下每次获取bean实例都需要从容器中查找是否存在，如果存在就返回，保证bean都是单实例的
> false：lite模式，每次获取bean实例都是返回一个全新的实例
> 这里主要是针对bean依赖，如下面代码中，user依赖pet，
> 这时候如果设置为true，则在创建初始化user实例时会从容器中获取pet实例，
> 而如果设置的是false，初始化时会创建一个新的pet实例

```java

@Configuration(proxyBeanMethods = true)
public class SpringConfig {

    @Bean
    public UserEntity user() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("zhangsan");
        userEntity.setPet(pet());
        return userEntity;
    }

    @Bean
    public Pet pet() {
        return new Pet();
    }
}
```

### @Import注解

@Import注解用于往容器中导入组件，传入一个Class数组，数组内的类会被初始化为bean实例，以类的全路径名为实例名

```java

@Configuration
@Import({UserEntity.class})
public class SpringConfig {
}
```

```java

@SpringBootApplication
public class Boot01HelloworldApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Boot01HelloworldApplication.class, args);
        // com.cs.entity.UserEntity  user
        Arrays.stream(context.getBeanNamesForType(UserEntity.class)).forEach(System.out::println);
    }
}
```

### @Conditional注解

@Conditional注解为条件注解，表示当满足条件之后才执行某些操作  
当该注解修饰类时，只有满足注解中配置的条件时才会执行类中对应的方法   
当该注解修饰方法是，当满足注解条件时才会自动加载对应的方法，如果不满足则不执行 如@ConditionalOnBean表示当容器中存在指定的bean实例时才加载被该注解修饰的内容   
在下面代码中使用@ConditionalOnBean(name = "pet")修饰user方法，表示只有容器中存在name为pet的bean的情况下才会执行user方法

```java

@Configuration
public class SpringConfig {

    @Bean
    @ConditionalOnBean(name = "pet")
    public UserEntity user() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("zhangsan");
        userEntity.setPet(pet());
        return userEntity;
    }

    //    @Bean
    public Pet pet() {
        return new Pet();
    }
}

@SpringBootApplication
public class Boot01HelloworldApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Boot01HelloworldApplication.class, args);
        // err : NoSuchBeanDefinitionException: No bean named 'user' available
        UserEntity user1 = context.getBean("user", UserEntity.class);
    }
}
```

### @ConfigurationProperties

@ConfigurationProperties用于将类中的属性与配置文件绑定，该注解修饰的类必须是spring的一个组件。
当一个组件被该注解修饰后，可以通过在配置文件中按{prefix}.{field}来设置类中的属性值。如在下面的代码中，可以在配置文件中使用car.name=xxx为其中的name属性赋值

```java

@Component
@ConfigurationProperties(prefix = "car")
public class Car {

    /**
     * name of car
     */
    private String name;
    /**
     * price of car
     */
    private String price;

    // Getter/Setter
}
```
在按照上述方式配置完之后可以在配置文件中为属性赋值，但是此时在配置文件中配置相关属性时是没有提示的。
此时可以引入spring-boot-configuration-processor依赖，之后重新编译项目，在配置文件配置相关属性就会有提示信息。
添加上述依赖项之后查看编译输出目录文件可以看到，在META-INF目录下生成了spring-configuration-metadata.json文件，该文件中即配置文件相关属性
```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
---
上面的功能也可以使用 @EnableConfigurationProperties(Car.class) 实现。@EnableConfigurationProperties注解需要添加在配置类上，
在参数中需要传入做配置绑定的类，要求传入的类必须被@ConfigurationProperties
注解修饰，但是可以没有被注册为spring组件。使用@EnableConfigurationProperties后会自动将该类加载到spring容器中

## 自动配置

### @SpringBootApplication自动导入组件

进入 @SpringBootApplication 注解后看到其中除了一些元注解之外主要生效的是以下3个注解，其中 @SpringBootConfiguration 主要用于标识该类为spring配置类 @ComponentScan(
······)配置了一些组件扫描信息，重点关注 @EnableAutoConfiguration 注解。

```java

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(/*······*/)
public @interface SpringBootApplication {
}
```

打开 @EnableAutoConfiguration 注解如下

```java

@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {
}
```

进入AutoConfigurationPackage注解代码如下，其中导入了AutoConfigurationPackages.Registrar组件。 
在该组件中有一个register方法，其中第二个参数即为启动类所在的包名。在该包下所有声明为组件的类会自动注册到容器中。

```java

@Import(AutoConfigurationPackages.Registrar.class)
public @interface AutoConfigurationPackage {
}
```

```java
static class Registrar implements ImportBeanDefinitionRegistrar, DeterminableImports {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        register(registry, new PackageImports(metadata).getPackageNames().toArray(new String[0]));
    }
}
```

### @SpringBootApplication初始加载自动配置类
在springboot项目启动时会调用SpringFactoriesLoader.loadSpringFactories()方法，在该方法中会加载如下spring.factories文件
> private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader)
```
org/springframework/boot/spring-boot/2.3.4.RELEASE/spring-boot-2.3.4.RELEASE.jar!/META-INF/spring.factories
org/springframework/boot/spring-boot-autoconfigure/2.3.4.RELEASE/spring-boot-autoconfigure-2.3.4.RELEASE.jar!/META-INF/spring.factories
org/springframework/spring-beans/5.2.9.RELEASE/spring-beans-5.2.9.RELEASE.jar!/META-INF/spring.factories
```

将配置文件中key=v1,v2,v3···等信息中的key作为key，v1|v2|···等数据添加到一个LinkedList中。这里重点关注spring-boot-autoconfigure依赖下spring.factories文件中的 **org.springframework.boot.autoconfigure.EnableAutoConfiguration=** 

在前面分析中，打开 @EnableAutoConfiguration 注解后导入了 AutoConfigurationImportSelector.class 类，
其中的getAutoConfigurationEntry()方法中会调用 getCandidateConfigurations(annotationMetadata, attributes) 方法，这里会返回上面
加载的org.springframework.boot.autoconfigure.EnableAutoConfiguration对应的LinkedList并初始化list中配置的spring配置类。
> 这里虽然加载了很多配置类，但是不是所有的配置类都会加载。需要根据配置类上的@Conditional注解来决定是否加载
