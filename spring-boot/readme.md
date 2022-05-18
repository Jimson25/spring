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