���ģʽѧϰ

2����̬�����ܽ᣺
	�ŵ㣺���������ڷ��Ͽ���ԭ�������¶�Ŀ�������й�����չ��
	ȱ�㣺���ǵ�Ϊÿһ�����񶼵ô��������࣬������̫�󣬲��׹���ͬʱ�ӿ�һ�������ı䣬������Ҳ����Ӧ�޸ġ� 
	
2����̬�����ܽ᣺

ע��Proxy.newProxyInstance()������������������

    ClassLoader loader:ָ����ǰĿ�����ʹ�õ��������,��ȡ�������ķ����ǹ̶���
    Class<?>[] interfaces:ָ��Ŀ�����ʵ�ֵĽӿڵ�����,ʹ�÷��ͷ�ʽȷ������
    InvocationHandler:ָ����̬��������ִ��Ŀ�����ķ���ʱ,�ᴥ���¼��������ķ���

��̬�����ܽ᣺
	��Ȼ����ھ�̬������̬��������������ǵĿ�������ͬʱ�����˶�ҵ��ӿڵ���������������϶ȡ����ǻ�����һ���СС���ź�֮�����Ǿ�����ʼ���޷����ѽ�֧��interface�������������Ϊ�������ע��������ź�������һ����Щ��̬���ɵĴ�����ļ̳й�ϵͼ�������Ѿ�ע����һ����ͬ�ĸ����Proxy��Java�ļ̳л���ע������Щ��̬���������޷�ʵ�ֶ�class�Ķ�̬����ԭ���Ƕ�̳���Java�б����Ͼ��в�ͨ���кܶ������ɣ����ǿ��Է񶨶� class����ı�Ҫ�ԣ�����ͬ����һЩ���ɣ�����֧��class��̬���������á��ӿں���Ļ��֣����Ͳ��Ǻ����ԣ�ֻ�ǵ���Java�вű����˵�ϸ�������ֻ�ӷ������������Ƿ񱻶�������������һ�����ߵĻ���壬�������ֽг����ࡣʵ�ֶԳ�����Ķ�̬��������Ҳ�������ڵļ�ֵ�����⣬����һЩ��ʷ�������࣬���ǽ���Ϊû��ʵ���κνӿڶ��Ӵ��붯̬����������Ե��������֣����ò�˵��һ��СС���ź������ǣ��������������ڲ�ΰ��ΰ����һ�ֱ��ʣ�Java��̬�������������

	
3.CGLIB����

       JDKʵ�ֶ�̬������Ҫʵ����ͨ���ӿڶ���ҵ�񷽷�������û�нӿڵ��࣬���ʵ�ֶ�̬�����أ������ҪCGLib�ˡ�CGLib�����˷ǳ��ײ���ֽ��뼼������ԭ����ͨ���ֽ��뼼��Ϊһ���ഴ�����࣬���������в��÷������صļ����������и��෽���ĵ��ã�˳��֯������߼�������Ϊ���õ��Ǽ̳У����Բ��ܶ�final���ε�����д���JDK��̬������CGLib��̬�������ʵ��Spring AOP�Ļ�����
       
CGLIB�����ܽ᣺
	 CGLIB�����Ķ�̬��������JDK�����Ķ�̬�����������ܸ��ߣ�����CGLIB�����������ʱ�����ѵ�ʱ��ȴ��JDK��öࡣ���Զ��ڵ����Ķ�����Ϊ����Ƶ������������CGLIB���ʣ���֮ʹ��JDK��ʽҪ��Ϊ����һЩ��ͬʱ����CGLib�����ǲ��ö�̬��������ķ���������final���εķ����޷����д���
	 

����ģʽ��ʹ�ó�����	 
(1)����ģʽ��Ӧ�úܶ࣬����Spring�������ӳټ��أ�AOP��ǿ����ȡ�
(2)Spring AOP ��̵�ʵ��ԭ����Ƕ�̬����ʹ�õ���JDK�����cglib��������Spring������ʹ�õ���aop��������Ŀ����û��ʵ�ֽӿ�ʱ�򣬻�ʹ��cglib����ʵ���˽ӿ�Ĭ��ʹ��JDK����

JDK�Ķ�̬����

JDK��̬����ʵ�ֲ��裺

    ��������������Լ�ʵ�ֵĽӿڣ�
    ����һ��ʵ�ֽӿ�InvocationHandler���࣬������ʵ��invoke������
    ����Proxy��newProxyInstance��̬����������һ�������ࡣ
    ͨ������������Ŀ�귽����
-------------------------------------------------------------------------------------------------------------------------------------
ʵ��InvocationHandler�ӿڲ��裺

    1�����庬�ι��췽�����ò���ΪҪ�����ʵ������Ŀ��������ִ��method.invoke()������Ҳ����ִ��Ŀ�귽����
    2�� ʵ�ֽӿڵ�invoke()�������÷������ڶ�Ŀ�귽������ǿ���������¼��־�ȡ��÷����ķ���ֵ���Ǵ������ִ��Ŀ�귽���ķ���ֵ�����������
	    proxy ��̬���ɵĴ������
	    method Ŀ�귽����ʵ��
	    args Ŀ�귽���Ĳ���
	    
���Է�����

public static void main(String[] args) {
    Moveable move =  (Moveable) Proxy.newProxyInstance(Car.class.getClassLoader(), Car.class.getInterfaces(), new LogHandler(new Car()));
    
    System.out.println("�������:"+move.getClass().getName());
    System.out.println("ִ�з���:"+move.move());
}

ͨ������Proxy.newProxyInstance�������ɴ�����󣬾�������У�

    1��loader Ŀ������������
    2��interfaces Ŀ����ʵ�ֵĽӿ�
    3��InvocationHandler ���ô�������ʵ�ֶ���

-------------------------------------------------------------------------------------------------------------------------------------
cglib�Ķ�̬����

����cglib��������

<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib-nodep</artifactId>
    <version>2.2</version>
</dependency>

Ϊ�˷���˼������ԭ���Ұ�ִ�в��谴˳��д��

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        //���ø��࣬�������ࣨ������Car.class��
        enhancer.setSuperclass(Car.class);
        //���ûص�����
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                //��ǿ����...
                Object o= proxy.invokeSuper(obj, args);//��������ø���ķ���
                //��ǿ����...
                return o;
            }
        });
        //���������ಢʹ�ûص����ø���Carȥ���ã�
        Car car = (Car) enhancer.create();
        //ִ��Ŀ�귽��
        System.out.println(car.move());
    }

����������

ʵ��MethodInterceptor�ӿڵ�intercept�������������ɵĴ��������������������

intercept�����ľ��������

    obj Ŀ�����ʵ��
    method Ŀ�귽��ʵ����ͨ�������ȡ��Ŀ�귽��ʵ����
    args Ŀ�귽���Ĳ���
    proxy �������ʵ��

�÷����ķ���ֵ����Ŀ�귽���ķ���ֵ��
�ص�

    1��cglib�Ķ�̬�������������ʵ�ִ���
    2����ָ��Ŀ�������һ�����࣬ͨ���������ؼ����������и��෽���ĵ��á�
    3����Ϊ��ͨ���̳�ʵ�֣�final���޷�ʹ�á�

������дJDK��̬����Դ��

��������Կ���JDK��̬����ĺ��Ĵ�������Proxy.newProxyInstance������

Moveable m = (Moveable)Proxy.newProxyInstance(Car.class,new InvocationHandler());

ʵ��ԭ��

    1������һ��Դ�룬Դ�붯̬������̬����
    2��Դ�����java�ļ�����java�ļ����б���
    3���õ��������ɺ��class�ļ�
    4����class�ļ�load���ڴ�֮�У�������������󷵻ؼ��ɡ�
    


Spring�������ô���ģʽ

��Ҫʹ��CGLIB��<aop:config>�����proxy-target-class����Ϊtrue����˼��˵�����ߵ����࣬���ǽӿڡ�

    <aop:config proxy-target-class="true">
            <!-- other beans defined here... -->
    </aop:config>


Ҫ��ʹ��@AspectJע������Ļ�����������<aop:aspectj-autoproxy>�µ�proxy-target-class����Ϊtrue��

<aop:aspectj-autoproxy proxy-target-class="true"/>

��Ȼ��������ʹ��SpringBoot������SpringBoot����������Ҫ��yml�����

    spring:
      aop:
        proxy-target-class: true

Spring�������ô���ģʽ

���ã�springboot��������Ҫ����@EnableTransactionManagement��@EnableAspectAutoProxyע�⣬�����е�proxyTargetClass����Ĭ�ϵ�fasle��������ó���true�ͻῪ��cglib��

 
�ܽ᣺

��̬������Ҫ�Լ��ֶ���д�������Ŀ�귽����
��̬����Ͳ���Ҫ�Լ��ֶ�ʵ�ִ������Ŀ�귽��������̬�����Ŀ����Ҫ����ʵ�ֽӿڣ�


