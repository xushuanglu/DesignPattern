                                                                        ���ģʽѧϰ
һ��Proxy����ģʽ
	1��ΪʲôҪ�ô���ģʽ��
	(1)�н�������ã���ĳЩ����£�һ���ͻ��಻����߲���ֱ������һ��ί�ж��󣬶��������������ڿͻ����ί�ж���֮�����н�����ã��������Ǵ������ί����ʵ����ͬ�Ľӿڡ�
	(2)����ԭ�����ӹ��ܣ�����������ǿͻ����ί������н�֮�⣬���ǻ�����ͨ�������������Ӷ���Ĺ�������չί����Ĺ��ܣ�����������ֻ��Ҫ�޸Ĵ����������Ҫ���޸�ί���࣬���ϴ�����ƵĿ���ԭ�򡣴�������Ҫ����Ϊί����Ԥ������Ϣ��������Ϣ������Ϣת����ί���࣬�Լ��º�Է��ؽ���Ĵ���ȡ������౾��������ʵ�ַ��񣬶���ͨ������ί�������ط��������ṩ�ض��ķ���������ҵ���ܻ�����ί������ʵ�֣����ǿ�����ҵ����ִ�е�ǰ�����һЩ�����ķ����������������Ŀ���뻺�桢��־��Щ���ܣ����ǾͿ���ʹ�ô���������ɣ���û��Ҫ���Ѿ���װ�õ�ί���ࡣ
	2�����ļ��ִ���ģʽ��	
		��̬������̬�������ɳ���Ա�������ض������Զ�����Դ���룬�ڶ�����롣�ڳ���Ա����֮ǰ��������.class�ļ����Ѿ��������ˡ�
			�ŵ㣺���������ڷ��Ͽ���ԭ�������¶�Ŀ�������й�����չ��
			ȱ�㣺���ǵ�Ϊÿһ�����񶼵ô��������࣬������̫�󣬲��׹���ͬʱ�ӿ�һ�������ı䣬������Ҳ����Ӧ�޸ġ� 
		��̬������̬�������ڳ�������ʱͨ��������ƶ�̬�����ġ�
			ע�⣺Proxy.newProxyInstance()������������������
				ClassLoader loader:ָ����ǰĿ�����ʹ�õ��������,��ȡ�������ķ����ǹ̶���
				Class<?>[] interfaces:ָ��Ŀ�����ʵ�ֵĽӿڵ�����,ʹ�÷��ͷ�ʽȷ������
				InvocationHandler:ָ����̬��������ִ��Ŀ�����ķ���ʱ,�ᴥ���¼��������ķ���
		Cglib����ʹ��cglib[Code Generation Library]ʵ�ֶ�̬��������Ҫ��ί�������ʵ�ֽӿڣ��ײ����asm�ֽ������ɿ�����ɴ�������ֽ���
		ע�⣺ʵ��CGLIB��̬�������ʵ��MethodInterceptor(����������)�ӿ�
			1������ҵ���߼�
			public class UserServiceImpl {  
			    public void add() {  
			        System.out.println("This is add service");  
			    }  
			    public void delete(int id) {  
			        System.out.println("This is delete service��delete " + id );  
			    }  
			}
			2��ʵ��MethodInterceptor�ӿڣ����巽����������
			public class MyMethodInterceptor implements MethodInterceptor {
			    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
			        System.out.println("Before:" + method);  
			        Object object = proxy.invokeSuper(obj, arg);
			        System.out.println("After:" + method); 
			        return object;
			    }
			}
			3������Enhancer�����ɴ����ࣻ
			Enhancer enhancer = new Enhancer();  
			enhancer.setSuperclass(UserServiceImpl.class);  
			enhancer.setCallback(new MyMethodInterceptor());  
			UserServiceImpl userService = (UserServiceImpl)enhancer.create();
			4��userService.add()��ִ�н����
			Before: add
			This is add service
			After: add
			
			�����������ɹ�����Enhancer��ʵ�֣���Ų������£�
			1�����ɴ�����Class�Ķ������ֽ��룻
			2��ͨ��Class.forName���ض������ֽ��룬����Class����
			3��ͨ��������ƻ�ȡʵ�����죬����ʼ�����������
		
			cglib�ֽ�������
			Enhancer��CGLib���ֽ�����ǿ�������Է���Ķ��������չ���ڲ�����GeneratorStrategy.generate�������ɴ�������ֽ��룬ͨ�����·�ʽ��������class�ļ���
		
		
		jdk��cglib��̬����ʵ�ֵ�����
		
		1��jdk��̬�������ɵĴ������ί����ʵ������ͬ�Ľӿڣ�
		2��cglib��̬���������ɵ��ֽ�����Ӹ��ӣ����ɵĴ�������ί��������࣬�Ҳ��ܴ���final�ؼ������εķ�����
		3��jdk���÷�����Ƶ���ί����ķ�����cglib�������������ķ�ʽֱ�ӵ���ί���෽����
		
		
