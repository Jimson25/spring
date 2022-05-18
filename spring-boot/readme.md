### spring

#### Configuration使用示例

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

#### @Import注解

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

#### @Conditional注解
@Conditional注解为条件注解，表示当满足条件之后才执行某些操作  
当该注解修饰类时，只有满足注解中配置的条件时才会执行类中对应的方法   
当该注解修饰方法是，当满足注解条件时才会自动加载对应的方法，如果不满足则不执行
如@ConditionalOnBean表示当容器中存在指定的bean实例时才加载被该注解修饰的内容   
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

#### @ConfigurationProperties
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
---
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```
在按照上述方式配置完之后可以在配置文件中为属性赋值，但是此时在配置文件中配置相关属性时是没有提示的。
此时可以引入spring-boot-configuration-processor依赖，之后重新编译项目，在配置文件配置相关属性就会有提示信息。
添加上述依赖项之后查看编译输出目录文件可以看到，在META-INF目录下生成了spring-configuration-metadata.json文件，该文件中即配置文件相关属性

