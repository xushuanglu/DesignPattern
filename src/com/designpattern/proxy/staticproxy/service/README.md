设计模式学习

2、静态代理总结：
	优点：可以做到在符合开闭原则的情况下对目标对象进行功能扩展。
	缺点：我们得为每一个服务都得创建代理类，工作量太大，不易管理。同时接口一旦发生改变，代理类也得相应修改。 
	
2、动态代理总结：

注意Proxy.newProxyInstance()方法接受三个参数：

    ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
    Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
    InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法

动态代理总结：
	虽然相对于静态代理，动态代理大大减少了我们的开发任务，同时减少了对业务接口的依赖，降低了耦合度。但是还是有一点点小小的遗憾之处，那就是它始终无法摆脱仅支持interface代理的桎梏，因为它的设计注定了这个遗憾。回想一下那些动态生成的代理类的继承关系图，它们已经注定有一个共同的父类叫Proxy。Java的继承机制注定了这些动态代理类们无法实现对class的动态代理，原因是多继承在Java中本质上就行不通。有很多条理由，人们可以否定对 class代理的必要性，但是同样有一些理由，相信支持class动态代理会更美好。接口和类的划分，本就不是很明显，只是到了Java中才变得如此的细化。如果只从方法的声明及是否被定义来考量，有一种两者的混合体，它的名字叫抽象类。实现对抽象类的动态代理，相信也有其内在的价值。此外，还有一些历史遗留的类，它们将因为没有实现任何接口而从此与动态代理永世无缘。如此种种，不得不说是一个小小的遗憾。但是，不完美并不等于不伟大，伟大是一种本质，Java动态代理就是佐例。

	
3.CGLIB代理

       JDK实现动态代理需要实现类通过接口定义业务方法，对于没有接口的类，如何实现动态代理呢，这就需要CGLib了。CGLib采用了非常底层的字节码技术，其原理是通过字节码技术为一个类创建子类，并在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。但因为采用的是继承，所以不能对final修饰的类进行代理。JDK动态代理与CGLib动态代理均是实现Spring AOP的基础。
       
CGLIB代理总结：
	 CGLIB创建的动态代理对象比JDK创建的动态代理对象的性能更高，但是CGLIB创建代理对象时所花费的时间却比JDK多得多。所以对于单例的对象，因为无需频繁创建对象，用CGLIB合适，反之使用JDK方式要更为合适一些。同时由于CGLib由于是采用动态创建子类的方法，对于final修饰的方法无法进行代理。
	 

代理模式的使用场景？	 
(1)代理模式的应用很多，比如Spring容器的延迟加载，AOP增强处理等。
(2)Spring AOP 编程的实现原理就是动态代理。使用的是JDK代理和cglib代理，比如Spring的事务使用的是aop技术，当目标类没有实现接口时候，会使用cglib代理，实现了接口默认使用JDK代理。

JDK的动态代理

JDK动态代理实现步骤：

    创建被代理的类以及实现的接口；
    创建一个实现接口InvocationHandler的类，它必须实现invoke方法；
    调用Proxy的newProxyInstance静态方法，创建一个代理类。
    通过代理对象调用目标方法。
-------------------------------------------------------------------------------------------------------------------------------------
实现InvocationHandler接口步骤：

    1、定义含参构造方法，该参数为要代理的实例对象，目的是用于执行method.invoke()方法（也就是执行目标方法）
    2、 实现接口的invoke()方法，该方法用于对目标方法的增强处理，比如记录日志等。该方法的返回值就是代理对象执行目标方法的返回值。具体参数：
	    proxy 动态生成的代理对象
	    method 目标方法的实例
	    args 目标方法的参数
	    
测试方法：

public static void main(String[] args) {
    Moveable move =  (Moveable) Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), new LogHandler(new Car()));
    
    System.out.println("代理对象:"+move.getClass().getName());
    System.out.println("执行方法:"+move.move());
}

通过调用Proxy.newProxyInstance方法生成代理对象，具体参数有：

    1、loader 目标类的类加载器
    2、interfaces 目标类实现的接口
    3、InvocationHandler 调用处理程序的实现对象

-------------------------------------------------------------------------------------------------------------------------------------
cglib的动态代理

引用cglib的依赖包

<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib-nodep</artifactId>
    <version>2.2</version>
</dependency>

为了方便思考它的原理，我把执行步骤按顺序写下

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //设置父类，被代理类（这里是Car.class）
        enhancer.setSuperclass(Car.class);
        //设置回调函数
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                //增强处理...
                Object o= proxy.invokeSuper(obj, args);//代理类调用父类的方法
                //增强处理...
                return o;
            }
        });
        //创建代理类并使用回调（用父类Car去引用）
        Car car = (Car) enhancer.create();
        //执行目标方法
        System.out.println(car.move());
    }

方法拦截器

实现MethodInterceptor接口的intercept方法后，所有生成的代理方法都调用这个方法。

intercept方法的具体参数有

    obj 目标类的实例
    method 目标方法实例（通过反射获取的目标方法实例）
    args 目标方法的参数
    proxy 代理类的实例

该方法的返回值就是目标方法的返回值。
特点

    1、cglib的动态代理是针对类来实现代理。
    2、对指定目标类产生一个子类，通过方法拦截技术拦截所有父类方法的调用。
    3、因为是通过继承实现，final类无法使用。

三：手写JDK动态代理源码

从上面可以看到JDK动态代理的核心代码在于Proxy.newProxyInstance方法。

Moveable m = (Moveable)Proxy.newProxyInstance(Car.class,new InvocationHandler());

实现原理

    1、声明一段源码，源码动态产生动态代理
    2、源码产生java文件，对java文件进行编译
    3、得到编译生成后的class文件
    4、把class文件load到内存之中，产生代理类对象返回即可。
    


Spring切面配置代理模式

想要使用CGLIB，<aop:config>下面的proxy-target-class属性为true，意思是说代理者的是类，不是接口。

    <aop:config proxy-target-class="true">
            <!-- other beans defined here... -->
    </aop:config>


要是使用@AspectJ注解切面的话，可以配置<aop:aspectj-autoproxy>下的proxy-target-class属性为true。

<aop:aspectj-autoproxy proxy-target-class="true"/>

当然现在流行使用SpringBoot，则在SpringBoot中配置主需要在yml中添加

    spring:
      aop:
        proxy-target-class: true

Spring事务配置代理模式

配置：springboot的启动类要加上@EnableTransactionManagement和@EnableAspectAutoProxy注解，且其中的proxyTargetClass属性默认的fasle，如果设置成了true就会开启cglib。

 
总结：

静态代理需要自己手动编写代理类和目标方法。
动态代理就不需要自己手动实现代理类和目标方法，但动态代理的目标类要必须实现接口！


