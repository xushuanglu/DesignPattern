                                                                        设计模式学习
一、Proxy代理模式
	1、为什么要用代理模式？
	(1)中介隔离作用：在某些情况下，一个客户类不想或者不能直接引用一个委托对象，而代理类对象可以在客户类和委托对象之间起到中介的作用，其特征是代理类和委托类实现相同的接口。
	(2)开闭原则，增加功能：代理类除了是客户类和委托类的中介之外，我们还可以通过给代理类增加额外的功能来扩展委托类的功能，这样做我们只需要修改代理类而不需要再修改委托类，符合代码设计的开闭原则。代理类主要负责为委托类预处理消息、过滤消息、把消息转发给委托类，以及事后对返回结果的处理等。代理类本身并不真正实现服务，而是通过调用委托类的相关方法，来提供特定的服务。真正的业务功能还是由委托类来实现，但是可以在业务功能执行的前后加入一些公共的服务。例如我们想给项目加入缓存、日志这些功能，我们就可以使用代理类来完成，而没必要打开已经封装好的委托类。
	2、有哪几种代理模式？	
		静态代理：静态代理是由程序员创建或特定工具自动生成源代码，在对其编译。在程序员运行之前，代理类.class文件就已经被创建了。
			优点：可以做到在符合开闭原则的情况下对目标对象进行功能扩展。
			缺点：我们得为每一个服务都得创建代理类，工作量太大，不易管理。同时接口一旦发生改变，代理类也得相应修改。 
		动态代理：动态代理是在程序运行时通过反射机制动态创建的。
			注意：Proxy.newProxyInstance()方法接受三个参数：
				ClassLoader loader:指定当前目标对象使用的类加载器,获取加载器的方法是固定的
				Class<?>[] interfaces:指定目标对象实现的接口的类型,使用泛型方式确认类型
				InvocationHandler:指定动态处理器，执行目标对象的方法时,会触发事件处理器的方法
		Cglib代理：使用cglib[Code Generation Library]实现动态代理，并不要求委托类必须实现接口，底层采用asm字节码生成框架生成代理类的字节码
		注意：实现CGLIB动态代理必须实现MethodInterceptor(方法拦截器)接口
			1、定义业务逻辑
			public class UserServiceImpl {  
			    public void add() {  
			        System.out.println("This is add service");  
			    }  
			    public void delete(int id) {  
			        System.out.println("This is delete service：delete " + id );  
			    }  
			}
			2、实现MethodInterceptor接口，定义方法的拦截器
			public class MyMethodInterceptor implements MethodInterceptor {
			    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
			        System.out.println("Before:" + method);  
			        Object object = proxy.invokeSuper(obj, arg);
			        System.out.println("After:" + method); 
			        return object;
			    }
			}
			3、利用Enhancer类生成代理类；
			Enhancer enhancer = new Enhancer();  
			enhancer.setSuperclass(UserServiceImpl.class);  
			enhancer.setCallback(new MyMethodInterceptor());  
			UserServiceImpl userService = (UserServiceImpl)enhancer.create();
			4、userService.add()的执行结果：
			Before: add
			This is add service
			After: add
			
			代理对象的生成过程由Enhancer类实现，大概步骤如下：
			1、生成代理类Class的二进制字节码；
			2、通过Class.forName加载二进制字节码，生成Class对象；
			3、通过反射机制获取实例构造，并初始化代理类对象。
		
			cglib字节码生成
			Enhancer是CGLib的字节码增强器，可以方便的对类进行扩展，内部调用GeneratorStrategy.generate方法生成代理类的字节码，通过以下方式可以生成class文件。
		
		
		jdk和cglib动态代理实现的区别
		
		1、jdk动态代理生成的代理类和委托类实现了相同的接口；
		2、cglib动态代理中生成的字节码更加复杂，生成的代理类是委托类的子类，且不能处理被final关键字修饰的方法；
		3、jdk采用反射机制调用委托类的方法，cglib采用类似索引的方式直接调用委托类方法；
		
		
		总结 JDK动态代理和 CGLib 动态代理的特点:
		JDK动态代理:  
		(1)代理类继承 Proxy 类, 并且实现委托类接口, 主要通过代理类调用 InvocationHandler 实现类的重写方法 invoke() 来实现动态代理. 
		(2)只能对接口进行代理. (只能对实现接口的委托类进行代理)
		(3)底层使用反射机制进行方法掉调用.
		
		CGLib动态代理:  
		(1)代理类继承了委托类, 在代理方法中, 会判断是否存在实现了 MethodInterceptor 接口的对象, 若存在则调用对象的 invoke() 方法, 对委托方法进行代理. 
		(2)不能对 final 类以及 final , private方法进行代理.
		(3)底层讲方法全部放入一个数组中, 通过索引直接进行方法调用.

		
二、工厂模式

	1、工厂模式的作用，为什么要用工厂模式？
	
	工厂模式是为了解耦：把对象的创建和使用的过程分开。就是Class A 想调用Class B,那么只是调用B的方法，而至于B的实例化，就交给工厂类。
	工厂模式可以降低代码重复。如果创建B过程都很复杂，需要一定的代码量，而且很多地方都要用到，那么就会有很多的重复代码。可以把这些创建对象B的代码放到工厂里统一管理。既减少了重复代码，也方便以后对B的维护。
	工厂模式可以减少错误，因为工厂管理了对象的创建逻辑，使用者不需要知道具体的创建过程，只管使用即可，减少了使用者因为创建逻辑导致的错误。
	
	应用实例： 
			1、您需要一辆汽车，可以直接从工厂里面提货，而不用去管这辆汽车是怎么做出来的，以及这个汽车里面的具体实现。 
			2、Hibernate 换数据库只需换方言和驱动就可以。
	优点： 
		1、一个调用者想创建一个对象，只要知道其名称就可以了。 
		2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。 
		3、屏蔽产品的具体实现，调用者只关心产品的接口。 
	缺点：每次增加一个产品时，都需要增加一个具体类和对象实现工厂，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统具体类的依赖。这并不是什么好事。
	
	使用场景： 
		1、日志记录器：记录可能记录到本地硬盘、系统事件、远程服务器等，用户可以选择记录日志到什么地方。 
		2、数据库访问，当用户不知道最后系统采用哪一类数据库，以及数据库可能有变化时。 
		3、设计一个连接服务器的框架，需要三个协议，"POP3"、"IMAP"、"HTTP"，可以把这三个作为产品类，共同实现一个接口。 
	
	二、简介工厂模式主要是为创建对象提供了接口。工厂模式按照《Java与模式》中的提法分为三类：
	
	1. 简单工厂模式(Simple Factory)
	2. 工厂方法模式(Factory Method)
	3. 抽象工厂模式(Abstract Factory)	
	
4、简单工厂模式的优缺点（看代码）
优点

（1）简单工厂包含必要的判断逻辑，简单工厂实现了对象的创建和使用的分离。
（2）客户端无需知道所创建的具体产品类的类名，只需要具体产品类对应的参数即可！
（3）在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性
缺点

（1）工厂类的职责过重，从类图中可以看出简单工厂中包含加减乘除的逻辑判断语句，它一旦有问题，整个系统都要出问题
（2）在添加新的类的时候，例如我添加了开根号运算，那么系统中的简单工厂类就要修改，违反了开放——封闭原则！这样及其不利于系统的扩展和维护！
（3）简单工厂的静态方法，使得工厂角色无法形成基于继承的等级结构！


简单工厂 VS 工厂方法模式 比较：

工厂模式中，要增加产品类时也要相应地增加工厂类，客户端的代码也增加了不少。工厂方法把简单工厂的内部逻辑判断转移到了客户端代码来进行。

你想要加功能，本来是改工厂类的，而现在是修改客户端。而且各个不同功能的实例对象的创建代码，也没有耦合在同一个工厂类里，这也是工厂方法模式对简单工厂模式解耦的一个体现。工厂方法模式克服了简单工厂会违背开-闭原则的缺点，又保持了封装对象创建过程的优点。


	抽象工厂模式的优缺点：

优点：

1. 抽象工厂模式最大的好处是易于交换产品系列，由于具体工厂类，例如 IFactory factory=new OracleFactory(); 在一个应用中只需要在初始化的时候出现一次，这就使得改变一个应用的具体工厂变得非常容易，它只需要改变具体工厂即可使用不同的产品配置。不管是任何人的设计都无法去完全防止需求的更改，或者项目的维护，那么我们的理想便是让改动变得最小、最容易，例如我现在要更改以上代码的数据库访问时，只需要更改具体的工厂即可。

2. 抽象工厂模式的另一个好处就是它让具体的创建实例过程与客户端分离，客户端是通过它们的抽象接口操作实例，产品实现类的具体类名也被具体的工厂实现类分离，不会出现在客户端代码中。就像我们上面的例子，客户端只认识IUser和ILogin，至于它是MySQl里的表还是Oracle里的表就不知道了。

缺点：

1. 如果你的需求来自增加功能，比如增加Login表，就有点太烦了。首先需要增加 ILogin，mysqlLogin,oracleLogin。 然后我们还要去修改工厂类： sqlFactory， mysqlFactory， oracleFactory 才可以实现，需要修改三个类，实在是有点麻烦。

2. 还有就是，客户端程序肯定不止一个，每次都需要声明sqlFactory factory=new MysqlFactory()， 如果有100个调用数据库的类，就需要更改100次sqlFactory factory=new oracleFactory()。



三、单例模式学习

在应用这个模式时，单例对象的类必须保证只有一个实例存在。

三要素：

    1、私有的构造方法；
    2、指向自己实例的私有静态引用；
    3、以自己实例为返回值的静态的公有方法。


保证一个类仅有一个实例，并提供一个访问它的全局访问点。spring中的单例模式完成了后半句话，即提供了全局的访问点BeanFactory。
但没有从构造器级别去控制单例，这是因为spring管理的是是任意的java对象。

核心提示点：Spring下默认的bean均为singleton，可以通过singleton=“true|false” 或者 scope="?"来指定。

2、单线程环境下的两种经典实现

　　在介绍单线程环境中单例模式的两种经典实现之前，我们有必要先解释一下 立即加载 和延迟加载 两个概念。

    立即加载 ： 在类加载初始化的时候就主动创建实例；(饿汉)
    延迟加载 ： 等到真正使用的时候才去创建实例，不用时不去主动创建。（懒汉）
    
    总之，从速度和反应时间角度来讲，饿汉式（又称立即加载）要好一些；从资源利用效率上说，懒汉式（又称延迟加载）要好一些。


单例有其独有的使用场景，一般是对于那些业务逻辑上限定不能多例只能单例的情况，
例如：类似于计数器之类的存在，一般都需要使用一个实例来进行记录，若多例计数则会不准确。

1、常见的单例模式有两种创建方式：所谓饿懒汉式与饿汉式
（1）懒汉式
　　  何为懒？顾名思义，就是不做事，这里也是同义，懒汉式就是不在系统加载时就创建类的单例，而是在第一次使用实例的时候再创建。
2）饿汉式
　　又何为饿？饿者，饥不择食；但凡有食，必急食之。此处同义：在加载类的时候就会创建类的单例，并保存在类中。	


3、单例模式的优点

　　我们从单例模式的定义和实现，可以知道单例模式具有以下几个优点：

    （1）在内存中只有一个对象，节省内存空间；
    （2）避免频繁的创建销毁对象，可以提高性能；
    （3）避免对共享资源的多重占用，简化访问；
    （4） 为整个系统提供一个全局访问点。
    
4、单例模式的使用场景

　　由于单例模式具有以上优点，并且形式上比较简单，所以是日常开发中用的比较多的一种设计模式，其核心在于为整个系统提供一个唯一的实例，其应用场景包括但不仅限于以下几种：

    有状态的工具类对象；
    频繁访问数据库或文件的对象；
    
5、单例模式的注意事项

　　在使用单例模式时，我们必须使用单例类提供的公有工厂方法得到单例对象，而不应该使用反射来创建，否则将会实例化一个新对象。此外，在多线程环境下使用单例模式时，应特别注意线程安全问题，我在下文会重点讲到这一点。


三. 多线程环境下单例模式的实现

　　在单线程环境下，无论是饿汉式单例还是懒汉式单例，它们都能够正常工作。但是，在多线程环境下，情形就发生了变化：由于饿汉式单例天生就是线程安全的，可以直接用于多线程而不会出现问题；但懒汉式单例本身是非线程安全的，因此就会出现多个实例的情况，与单例模式的初衷是相背离的。下面我重点阐述以下几个问题：

    1、为什么说饿汉式单例天生就是线程安全的？
    	我们已经在上面提到，类加载的方式是按需加载，且只加载一次。因此，在上述单例类被加载时，就会实例化一个对象并交给自己的引用，供系统使用。换句话说，在线程访问单例对象之前就已经创建好了。再加上，由于一个类在整个生命周期中只会被加载一次，因此该单例类只会创建一个实例，也就是说，线程每次都只能也必定只可以拿到这个唯一的对象。因此就说，饿汉式单例天生就是线程安全的。

    2、传统的懒汉式单例为什么是非线程安全的？
    	上面发生非线程安全的一个显著原因是，会有多个线程同时进入 if (singleton2 == null) {…} 语句块的情形发生。当这种这种情形发生后，该单例类就会创建出多个实例，违背单例模式的初衷。因此，传统的懒汉式单例是非线程安全的。
    

    3、怎么修改传统的懒汉式单例，使其线程变得安全？
    
   	 （1）该实现与上面传统懒汉式单例的实现唯一的差别就在于：是否使用 synchronized 修饰 getSingleton2()方法。若使用，就保证了对临界资源的同步互斥访问，也就保证了单例。
　　 从执行结果上来看，问题已经解决了，但是这种实现方式的运行效率会很低，因为同步块的作用域有点大，而且锁的粒度有点粗。同步方法效率低，那我们考虑使用同步代码块来实现。

	（2）该实现与上面synchronized方法版本实现类似，此不赘述。从执行结果上来看，问题已经解决了，但是这种实现方式的运行效率仍然比较低，事实上，和使用synchronized方法的版本相比，基本没有任何效率上的提高。
	
	（3）如上述代码所示，我们可以使用内部类实现线程安全的懒汉式单例，这种方式也是一种效率比较高的做法。至于其为什么是线程安全的，其与问题 “为什么说饿汉式单例天生就是线程安全的？” 相类似，此不赘述。

    线程安全的单例的实现还有哪些，怎么实现？

    (4)双重检查模式、Volatile关键字 在单例模式中的应用
    
           使用双重检测同步延迟加载去创建单例的做法是一个非常优秀的做法，其不但保证了单例，而且切实提高了程序运行效率。对应的代码清单如下：

    // 线程安全的懒汉式单例
    public class Singleton3 {
     
        //使用volatile关键字防止重排序，因为 new Instance()是一个非原子操作，可能创建一个不完整的实例
        private static volatile Singleton3 singleton3;
     
        private Singleton3() {
        }
     
        public static Singleton3 getSingleton3() {
            // Double-Check idiom
            if (singleton3 == null) {
                synchronized (Singleton3.class) {       // 1
                    // 只需在第一次创建实例时才同步
                    if (singleton3 == null) {       // 2
                        singleton3 = new Singleton3();      // 3
                    }
                }
            }
            return singleton3;
        }
    }/* Output(完全一致): 
            1104499981
            1104499981
            1104499981
            1104499981
            1104499981
            1104499981
            1104499981
            1104499981
            1104499981
            1104499981
     *///:~
     
    	 如上述代码所示，为了在保证单例的前提下提高运行效率，我们需要对 singleton3 进行第二次检查，目的是避开过多的同步（因为这里的同步只需在第一次创建实例时才同步，一旦创建成功，以后获取实例时就不需要同步获取锁了）。这种做法无疑是优秀的，但是我们必须注意一点：
　　必须使用volatile关键字修饰单例引用。

    （5）ThreadLocal 在单例模式中的应用
    
           借助于 ThreadLocal，我们可以实现双重检查模式的变体。我们将临界资源线程局部化，具体到本例就是将双重检测的第一层检测条件 if (instance == null) 转换为 线程局部范围内的操作 。这里的 ThreadLocal 也只是用作标识而已，用来标识每个线程是否已访问过：如果访问过，则不再需要走同步块，这样就提高了一定的效率。对应的代码清单如下：

    // 线程安全的懒汉式单例
    public class Singleton4 {
     
        // ThreadLocal 线程局部变量
        private static ThreadLocal<Singleton4> threadLocal = new ThreadLocal<Singleton4>();
        private static Singleton4 singleton4 = null;    // 不需要是
     
        private Singleton4(){}
     
        public static Singleton4 getSingleton4(){
            if (threadLocal.get() == null) {        // 第一次检查：该线程是否第一次访问
                createSingleton4();
            }
            return singleton4;
        }
     
        public static void createSingleton4(){
            synchronized (Singleton4.class) {
                if (singleton4 == null) {          // 第二次检查：该单例是否被创建
                    singleton4 = new Singleton4();   // 只执行一次
                }
            }
            threadLocal.set(singleton4);      // 将单例放入当前线程的局部变量中 
        }
    }
    
    
    六. 小结

　　本文首先介绍了单例模式的定义和结构，并给出了其在单线程和多线程环境下的几种经典实现。特别地，我们知道，传统的饿汉式单例无论在单线程还是多线程环境下都是线程安全的，但是传统的懒汉式单例在多线程环境下是非线程安全的。为此，我们特别介绍了五种方式来在多线程环境下创建线程安全的单例，包括：

    使用synchronized方法实现懒汉式单例；

    使用synchronized块实现懒汉式单例；

    使用静态内部类实现懒汉式单例；

    使用双重检查模式实现懒汉式单例；

    使用ThreadLocal实现懒汉式单例；

　　当然，实现懒汉式单例还有其他方式。但是，这五种是比较经典的实现，也是我们应该掌握的几种实现方式。从这五种实现中，我们可以总结出，要想实现效率高的线程安全的单例，我们必须注意以下两点：

    1、尽量减少同步块的作用域；
    2、尽量使用细粒度的锁。
    
    
四、适配器模式
	定义：将一个系统的接口转换成另外一种形式，从而使原来不能直接调用的接口变得可以调用。 
	优点 
	适配器模式也是一种包装模式，它与装饰模式同样具有包装的功能，此外，对象适配器模式还具有委托的意思。总的来说，适配器模式属于补偿模式，专用来在系统后期扩展、修改时使用。
	
	缺点 
	过多的使用适配器，会让系统非常零乱，不易整体进行把握。比如，明明看到调用的是 A 接口，其实内部被适配成了 B 接口的实现，一个系统如果太多出现这种情况，无异于一场灾难。因此如果不是很有必要，可以不使用适配器，而是直接对系统进行重构。
	
	适配器模式应用场景 
	在软件开发中，也就是系统的数据和行为都正确，但接口不相符时，我们应该考虑用适配器，目的是使控制范围之外的一个原有对象与某个接口匹配。适配器模式主要应用于希望复用一些现存的类，但是接口又与复用环境要求不一致的情况。比如在需要对早期代码复用一些功能等应用上很有实际价值。适用场景大致包含三类： 
	1、已经存在的类的接口不符合我们的需求； 
	2、创建一个可以复用的类，使得该类可以与其他不相关的类或不可预见的类（即那些接口可能不一定兼容的类）协同工作； 
	3、在不对每一个都进行子类化以匹配它们的接口的情况下，使用一些已经存在的子类。


一 概述

	定义：适配器模式将某个类的接口转换成客户端期望的另一个接口表示，主的目的是兼容性，让原本因接口不匹配不能一起工作的两个类可以协同工作。其别名为包装器(Wrapper)。
	
	属于结构型模式
	
	主要分为三类：类适配器模式、对象的适配器模式、接口的适配器模式。
	
	本文定义：
	
	需要被适配的类、接口、对象（我们有的），简称 src（source） 
	最终需要的输出（我们想要的），简称 dst (destination，即Target) 
	适配器称之为 Adapter 。
	
	一句话描述适配器模式的感觉： src->Adapter->dst,即src以某种形式（三种形式分别对应三种适配器模式）给到Adapter里，最终转化成了dst。
	
	拿我们Android开发最熟悉的展示列表数据的三大控件：ListView，GridView，RecyclerView的Adapter来说，它们三个控件需要的是View(dst),而我们有的一般是datas(src),所以适配器Adapter就是完成了数据源datas 转化成 ItemView的工作。 
	带入src->Adapter->dst中，即datas->Adapter->View.
	
	
	
	使用场景：
	
	1 系统需要使用现有的类，而这些类的接口不符合系统的需要。 
	2 想要建立一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作。 
	3 需要一个统一的输出接口，而输入端的类型不可预知。

二 类适配器模式：
	一句话描述：Adapter类，通过继承 src类，实现 dst 类接口，完成src->dst的适配。
	别的文章都用生活中充电器的例子来讲解适配器，的确，这是个极佳的举例，本文也不能免俗：
	
小结:

	Java这种单继承的机制，所有需要继承的我个人都不太喜欢。 
	所以类适配器需要继承src类这一点算是一个缺点， 
	因为这要求dst必须是接口，有一定局限性; 
	且src类的方法在Adapter中都会暴露出来，也增加了使用的成本。

三 对象适配器模式（常用）:

	基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承src类，而是持有src类的实例，以解决兼容性的问题。 
即：持有 src类，实现 dst 类接口，完成src->dst的适配。 

小结:

	对象适配器和类适配器其实算是同一种思想，只不过实现方式不同。 
	根据合成复用原则，组合大于继承， 
	所以它解决了类适配器必须继承src的局限性问题，也不再强求dst必须是接口。 
	同样的它使用成本更低，更灵活。
	
	（和装饰者模式初学时可能会弄混，这里要搞清，装饰者是对src的装饰，使用者毫无察觉到src已经被装饰了（使用者用法不变）。 这里对象适配以后，使用者的用法还是变的。 
	即，装饰者用法： setSrc->setSrc，对象适配器用法：setSrc->setAdapter.)

	
四 接口适配器模式

	也有文献称之为认适配器模式(Default Adapter Pattern)或缺省适配器模式。 
	定义： 
	当不需要全部实现接口提供的方法时，可先设计一个抽象类实现接口，并为该接口中每个方法提供一个默认实现（空方法），那么该抽象类的子类可有选择地覆盖父类的某些方法来实现需求，它适用于一个接口不想使用其所有的方法的情况。
	
	
五 总结

	我个人理解，三种命名方式，是根据 src是以怎样的形式给到Adapter（在Adapter里的形式）来命名的。 
	类适配器，以类给到，在Adapter里，就是将src当做类，继承， 
	对象适配器，以对象给到，在Adapter里，将src作为一个对象，持有。 
	接口适配器，以接口给到，在Adapter里，将src作为一个接口，实现。


适配器模式总结
	主要优点：
	
	将目标类和适配者类解耦，通过引入一个适配器类来重用现有的适配者类，无须修改原有结构。
	增加了类的透明性和复用性，将具体的业务实现过程封装在适配者类中，对于客户端类而言是透明的，而且提高了适配者的复用性，同一个适配者类可以在多个不同的系统中复用。
	灵活性和扩展性都非常好，通过使用配置文件，可以很方便地更换适配器，也可以在不修改原有代码的基础上增加新的适配器类，完全符合“开闭原则”。
	
	具体来说，类适配器模式还有如下优点：
	
	由于适配器类是适配者类的子类，因此可以在适配器类中置换一些适配者的方法，使得适配器的灵活性更强。
	
	对象适配器模式还有如下优点：
	
	一个对象适配器可以把多个不同的适配者适配到同一个目标；
	可以适配一个适配者的子类，由于适配器和适配者之间是关联关系，根据“里氏代换原则”，适配者的子类也可通过该适配器进行适配。
	
	类适配器模式的缺点如下：
	
	对于Java、C#等不支持多重类继承的语言，一次最多只能适配一个适配者类，不能同时适配多个适配者；
	适配者类不能为最终类，如在Java中不能为final类，C#中不能为sealed类；
	在Java、C#等语言中，类适配器模式中的目标抽象类只能为接口，不能为类，其使用有一定的局限性。
	
	对象适配器模式的缺点如下：
	
	与类适配器模式相比，要在适配器中置换适配者类的某些方法比较麻烦。如果一定要置换掉适配者类的一个或多个方法，可以先做一个适配者类的子类，将适配者类的方法置换掉，然后再把适配者类的子类当做真正的适配者进行适配，实现过程较为复杂。
	
	适用场景：
	
	系统需要使用一些现有的类，而这些类的接口（如方法名）不符合系统的需要，甚至没有这些类的源代码。
	想创建一个可以重复使用的类，用于与一些彼此之间没有太大关联的一些类，包括一些可能在将来引进的类一起工作。

源码分析适配器模式的典型应用
	spring AOP中的适配器模式
	
	在Spring的Aop中，使用的 Advice（通知） 来增强被代理类的功能。
	Advice的类型有：MethodBeforeAdvice、AfterReturningAdvice、ThrowsAdvice
	在每个类型 Advice 都有对应的拦截器，MethodBeforeAdviceInterceptor、AfterReturningAdviceInterceptor、ThrowsAdviceInterceptor
	Spring需要将每个 Advice 都封装成对应的拦截器类型，返回给容器，所以需要使用适配器模式对 Advice 进行转换

	
spring JPA中的适配器模式
	
	在Spring的ORM包中，对于JPA的支持也是采用了适配器模式，首先定义了一个接口的 JpaVendorAdapter，然后不同的持久层框架都实现此接口。
	jpaVendorAdapter：用于设置实现厂商JPA实现的特定属性，如设置Hibernate的是否自动生成DDL的属性generateDdl；这些属性是厂商特定的，因此最好在这里设置；目前Spring提供 HibernateJpaVendorAdapter、OpenJpaVendorAdapter、EclipseLinkJpaVendorAdapter、TopLinkJpaVendorAdapter 四个实现。其中最重要的属性是 database，用来指定使用的数据库类型，从而能根据数据库类型来决定比如如何将数据库特定异常转换为Spring的一致性异常，目前支持如下数据库（DB2、DERBY、H2、HSQL、INFORMIX、MYSQL、ORACLE、POSTGRESQL、SQL_SERVER、SYBASE）
	

spring MVC中的适配器模式

	Spring MVC中的适配器模式主要用于执行目标 Controller 中的请求处理方法。
	在Spring MVC中，DispatcherServlet 作为用户，HandlerAdapter 作为期望接口，具体的适配器实现类用于对目标类进行适配，Controller 作为需要适配的类。
	为什么要在 Spring MVC 中使用适配器模式？
	Spring MVC 中的 Controller 种类众多，不同类型的 Controller 通过不同的方法来对请求进行处理。如果不利用适配器模式的话，DispatcherServlet 直接获取对应类型的 Controller，需要的自行来判断

