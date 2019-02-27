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

		
	