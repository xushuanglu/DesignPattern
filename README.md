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
		
		
