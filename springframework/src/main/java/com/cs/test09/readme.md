### @Transactional注解
```java
// service
public class UserServiceImpl {
    private UserDaoImpl userDao;
    @Transactional
    public int insert(UserEntity userEntity) {
        return userDao.insertDB(userEntity);
    }
}

// dao
public class UserDaoImpl{
    @Transactional
    public int insertDB(UserEntity userEntity){
        // doInsert
    }
}
```
#### propagation属性取值
- Propagation.REQUIRED
> 如果有事务在运行，当前的方法就在这个事务内运行，否则就启动一个新的事务，并在自己的事务中运行。
> 当设置为此传播级别时，上面案例中的dao.insert就不会开启一个新的事务而是共用service中开启的事务

- Propagation.SUPPORTS
> 如果有事务在运行，当前方法就在这个事务内运行否则他可以不在事务内运行。
> 如Test09.test02()中，在dao层insert方法上添加事务注解并将传播级别设置为SUPPORTS，
> 此时如果在service层上没有开启事务，那么dao中的事务也不会生效，即使出现异常也不会回滚
> 而当我们开启service上的事务注解时，dao中的事务会在出现异常时回滚

- Propagation.MANDATORY
> 当前方法必须在事务内部，如果不在则抛出异常
> 如Test09.test03()中，dao层事务传播级别设置为MANDATORY，此时如果在service上没有开启事务，
> 则代码执行会抛出IllegalTransactionStateException异常，当开启service上的事务时正常执行

- Propagation.REQUIRES_NEW
> 当前事务必须启动一个新的事务并在新的事务中执行，如果有事务正在执行，则应该将它挂起
> 如Test09.test04()中，dao层事务传播级别设置为REQUIRES_NEW，
> 但是这里没有设置回滚条件，即在这个事务中出现异常不会回滚，而在service中，只要是捕获到异常就会回滚
> 这里执行后删除sql正常执行，随后抛出异常。通过查询数据库发现数据被删除了
> 由此说明在dao层，删除的事务已经被提交了，后面异常抛出到service层的时候即使出发回滚条件也没法回滚数据

- Propagation.NOT_SUPPORTED
> 当前方法不应该运行在事务中，如果有事务，则将其挂起
> 如Test09.test05()中，dao层事务传播级别设置为NOT_SUPPORTED，
> 此时dao中执行完insert之后抛出异常，而数据已经被写入数据库中，说明service和dao中的事务都没有生效

- Propagation.NEVER
> 当前方法不应该运行在事务中，如果有事务则抛出异常

- Propagation.NESTED
> 如果有事务在运行，当前方法应该在这个事务的嵌套事务内运行，否则就启动一个新的事务并在新的事务中运行

#### Isolation属性取值
- Isolation.DEFAULT
> 
