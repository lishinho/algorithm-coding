=========  
JAVA基础  
=========
### 图的算法
- 图的分类
带权图 
有向图/无向图  
- 存储
邻接矩阵  
邻接表
逆邻接表 逆邻接表每一个顶点作为链表的头节点，后继节点所存储的是能够直接达到该顶点的相邻顶点

- 遍历算法
visited数组记录访问过的节点
广度遍历算法 相当于树的按层遍历 用队列
深度遍历算法 相当于树的前序/中序/后序遍历 用栈  

- 图的最短路径问题
缔结斯特拉算法  
距离表  
从起点出发。遍历相邻节点，每次访问刷新距离表  

### 重载equals方法为什么要重载hashcode
你的类将违反 hashCode 的一般约定，这将阻止该类在 HashMap 和 HashSet 等集合中正常运行。  
hashmap中使用  
Hash的公式---> index = HashCode（Key） & （Length - 1）  
找到key对应的index，然后遍历链表equals找到key对应的value  
如果根据 equals(Object) 方法判断出两个对象是相等的，那么在两个对象上调用 hashCode 必须产生相同的整数结果。  

###自动装拆箱  
Integer i = 10 //拆箱  
int n = i; // 装箱  
在装箱的时候自动调用的是Integer的valueOf(int)方法。而在拆箱的时候自动调用的是Integer的intValue方法。  
注意拆箱时的integer.cache会缓存-128~127的值  


### 多态  
instance field（类成员属性） 跟 method （类成员方法）两者是有区别的  
如果父类、子类定义了相同名字的成员属性，那么这两者是同时存在的。  
如Fruit fr = new Apple();  
fr.getclass() // 调用apple的重载方法， 即实际类型的成员方法，也是动态绑定，方法存在堆中（方法区？）  
fr.price      // 调用fruit的成员变量， 即引用的成员变量，存在栈中，线程独享  


### 泛型
有无泛型的区别
```java
public class test {
public static void main(String[] args) {
        BoxT<Integer> b = new BoxT<Integer>();

        b.setObject(123);   // 变化一，由于实例化时，已经指定了 类型的实际参数是Integer，
                            // 因此这里就只能set一个Integer。 编译器帮助检查错误

        Integer integer = b.getObject();  // 变化二，这里不需要强转了，因为编译器能判断这里就是 Integer。

        System.out.println(integer);
    }
}

```



###自定义注解
元注解
- @Target  
定义注解能够应用于源码的哪些位置  
比如  
类或接口：ElementType.TYPE；  
字段：ElementType.FIELD；  
方法：ElementType.METHOD；  
构造方法：ElementType.CONSTRUCTOR；  
方法参数：ElementType.PARAMETER  


- @Retension  
定义注解生命周期  
仅编译期：RetentionPolicy.SOURCE；  
仅class文件：RetentionPolicy.CLASS；  
运行期：RetentionPolicy.RUNTIME  

- @Repeatable  
定义注解是否可重复  


- @Inherited  
用@Inherited定义子类是否可继承父类定义的Annotation  


###红黑树
- 红黑树原则

-变色/左旋/右旋




=========  
go  
=========  
go channel

- channel
goroutines之间的通信和同步  
channel有buffer构造器/非buffered  

-- making channels
hchan struct  
buf -- 环状队列  
sendx -- 发送指针  
recvx -- 接收指针  
lock -- 互斥锁  
sendq -- 发送队列  
recvq -- 接收队列  

程序内存 高地址为栈 低地址为堆  
hchan分配内存到堆中  

缓存满了-用户级线程 调度器  
并存在一个sudog的数据结构放在等待队列中  



=========
集合框架  
=========

### HashMap   
- 底层数据结构组成 数组加链表 k-v数据结构叫entry/node  

- /put过程/get过程  
输入 通过key的hashcode（高16位抑或低16位）进行hash（hash&数组长度）得到entry位置，添加节点，如果发生碰撞，通过equals方法去链表/树  


- 哈希碰撞 为什么需要链表  

- 头插尾插(jdk7前线程安全问题/jdk8后线程安全问题）  

- 初始容量 扩容机制 当前长度/扩容因子 扩容/rehash  
 
 初始值为什么是16 -- 利于rehash位运算  
 equals和hashcode  

 一致性哈希 -- 容错性/扩展性/虚拟节点  

### ConcurrentHashMap  

- 如何解决？有没有线程安全的并发容器？  
collections.synchronizedMap  
hashtable  

- ConcurrentHashMap 是如何实现的？  

segment (reentrantlock)+hashEntry volatile  
node+CAS+红黑树+synchronized  

- 谈谈你理解的 Hashtable，讲讲其中的 get put 过程。ConcurrentHashMap同问。  
定位segment put  

写入过程  
定位访问segment 自旋获取锁 遍历hashentry equals方法 尾插法插入   

### ArrayList  

ArrayList系列  
- ArrayList, LinkerList, Vector  
查询效率高，增删效率低，线程不安全。使用频率很高。  
arraylist扩容  

- 快速失败（fail—fast）是啥，应用场景有哪些？安全失败（fail—safe）同问。  
iterator在迭代器遍历元素的过程中，如果集合的结构被改变的话，就会抛出异常，防止继续遍历。（hashmap/hashtable）  














============  
JAVA并发  
============  
### CAS    
jvm层做的操作，unsafe类native方法  
自增操作及一些简单的操作，加锁会影响效率，但确实有并发问题  
cas是一种乐观锁，可以保持原子性，juc的原子类和并发框架使用  
jmm模型，线程从主内存读取当前值，与线程工作内存的值比较，如果符合预期，则改变值  
涉及到的问题 aba问题，可以加版本号去避免，Java 中提供了 AtomicStampedReference 这个类   
java8的优化，8之前是compareAndSet，8之后引入cell数组，
线程分类，汇总，避免大并发时大多请求不能及时处理的效率问题  

### 锁

- synchronized底层原理是啥？  
java对象头 monitor mutex  

- synchronized锁升级策略
偏向锁-自旋轻量级锁-重量级锁  
JVM 使用了锁升级的优化方式，就是先使用偏向锁优先同一线程然后再次获取锁，如果失败，就升级为 CAS 轻量级锁，如果失败就会短暂自旋，防止线程被系统挂起。  
最后如果以上都失败就升级为重量级锁。  

- threadlocal  

threadlocal数据隔离 数据结构模型 弱引用 哈希冲突解决 内存泄漏  
没有链表的hashmap  
线程中维护数组，每个数组元素维护链表，每个链表元素的entry，entry的key是一个弱引用的threadlocal  
- 软引用的key会导致内存泄漏  

- volatile

volatile jmm 缓存一致性 内存可见性 内存屏障防止指令重排 单例模式双重检验锁  


- 线程池
**用途** 降低资源消耗  提升系统相应速度 易于管理  
**流程**   
核心线程池是否已满 - 阻塞队列是否已满 - 线程池是否已满  
-- 新建线程 - 队列等待（队列满了会新建线程）核心线程数外的线程会按keepalivetime 策略 - 异常处理策略  

**线程池的创建** ThreadPoolExecutor类 executors的几个参数 如何设置线程池大小 1线程池中核心线程数 2最大 3用于缓存任务的阻塞队列   
4空闲线程的存活时间/5单位 6线程工厂 7拒绝策略处理  

**合理配置**   
任务的性质：CPU 密集型任务，IO 密集型任务和混合型任务  
CPU 密集型任务 - 尽可能少的线程数量，如配置Ncpu+1个线程的线程池。  
IO 密集型任务 - 尽可能多的线程，如2xNcpu。  
混合型的任务，如果可以拆分，则将其拆分成一个 CPU 密集型任务和一个 IO 密集型任务  


- forkjoin -mapreduce -分治法/工作窃取算法 WorkStealingPool  
// forkJoinPool is a special thread pool which the numbers of thread depends on CPU cores  
// each thread in the pool maintains a deque based on array  
// the free thread can steal task to execute from other running thread  

- blockingqueue   
-- arrayListBlockingQueue 固定大小 （有界）  
-- LinkedBlockingQueue顾名思义是用链表实现的队列，可以是有界的，也可以是无界的，但在Executors中默认使用无界的（无界）。
- newFixedThreadPool
-- SynchronousQueue 并不是队列 维护cache线程 任务直接交付 用于newCachedThreadPool （无）  

### J.U.C


- AQS   
CLH队列 先进先出 双向 自旋轮询前驱状态  

AQS主要是采用state，通过对state的CAS判断来获取锁和解锁，  
并且存在等待队列和条件等待队列来park相关线程之后入队等待，有公平和非公平两者模式来唤醒等待的线程。  
主要是为了封装和抽象，通过封装了公共的方法，减少重复代码。  


AQS可以说是一个给予实现同步锁、同步器的一个框架，很多实现类都在它的的基础上构建的  
在AQS中实现了对等待队列的默认实现，子类只要重写部分的代码即可实现  

-- JUC同步器框架原理  
--- 同步状态管理  
--- CLH队列变体  
--- 线程阻塞与唤醒 通过unsafe类  

-- 独占线程的保存  
--- AbstractOwnableSynchronizer是AQS的父类，提供基础的独占线程保存功能  

-- CLH队列变体的实现  
--- 同步队列 sync-queue  
--- 条件等待队列 condition-queue  

-- 独占模式/共享模式  


- reentrantlock（可重入独占锁） - lock - AQS队列阻塞队列  
ReentrantLock 就是基于 AQS 实现的，ReentrantLock 内部有公平锁和非公平锁两种实现，差别就在于新来的线程是否比已经在同步队列中的等待线程更早获得锁。
默认非公平，因为效率更高，更不容易被阻塞，在CLH队列中善用cpu的时间片  
公平锁 严格先进先出/非公平 由底层os调度 影响吞吐量  


- 读写锁/COWlist CopyOnWriteArrayList  
- cyclicbarrier, countdownlatch,   
-countDownLatch  
--可以实现计数器，需要等待其他任务结束后才能执行  
-- 调用await()方法的线程会被挂起，等到为0继续执行  
-- countdown() 将count减1  



















========  
JVM  
========  

### JVM 两种数据类型
基本类型：数值类型/boolean类型/returnAddress类型
和引用类型 类/数组/接口

### 运行时数据区: 
堆/方法区 pc寄存器（程序计数器）/jvm栈/native方法栈

- 永久代和方法区 jdk8区别
hotspot
1.6 方法区 -> 永久代
1.7 逐步移出永久代
1.8 元空间

- 四种引用类型 引用计数 可达性分析

- CMS收集器
serial/serailOld； parnew/CMS； G1
标记清除

- G1收集器
标记整理
标记整理/大对象直接进老年区/空间分配担保/可指定stop the world时间
分代收集原理 对象在新生代分配/触发minor gc 幸存区 存活年龄 空间分配担保 fullgc stop the world 

老年代占2/3
新生对象分配空间在edeneden-survivor（复制算法/年龄）



- 如何标记垃圾
可达性分析
gc root
初始标记- 并发标记- 重新标记 - 筛选回收


### 破坏双亲委派
线程上下文类加载器 osgi用网状的类加载结构

### 类加载过程
加载-链接-初始化/验证-运行时（JIT/编译/动态连接）

### gc调优
- minor gc频繁
新生代空间太小
- major gc频繁/回收比很大
新生代调整
- full gc频繁 且回收内存仅一点点
内存泄露

### tlab是堆的eden区为每个线程初始化一块独享区域


### 类文件放在哪里？
- 字节码文件结构
魔数+版本号+常量池+访问标志











=========
框架
=========
- Spirng AOP


静态AOP是指AspectJ实现的AOP，他是将切面代码直接编译到Java类文件中
Spring AOP采用的是动态代理，在运行期间对业务方法进行增强，所以不会生成新类，

动态代理
spring aop是运行时生成代理对象来织入
Spring AOP提供了对JDK动态代理的支持以及CGLib的支持 可以通过xml或注解的方式实现

如果目标对象实现了接口，默认情况下会采用JDK的动态代理实现AOP, 没有接口时只能通过cglib实现aop动态代理
动态代理 JDK（通过接口），cglib（通过继承）
动态代理类的源码是在程序运行期间由JVM根据反射等机制动态的生成，所以不存在代理类的字节码文件。代理类和委托类的关系是在程序运行时确定。

- jdk动态代理
- cglib动态代理

- 常见注解
@RestController
@RestController=@ResponseBody+@Controller，该注解类下的所有函数都返回的是json类型数据，不再返回页面。
@Api swagger用覆盖路径值打tag description一直可用
@Configuration
Spring的@Bean注解用于告诉方法，产生一个Bean对象，然后这个Bean对象交给Spring管理。产生这个Bean对象的方法Spring只会调用一次，
随后这个Spring将会将这个Bean对象放在自己的IOC容器中。
@Autowired
@Component
@NestedConfigurationProperty，该注解会为basicProperties生成单独的一个属性组。如果不添加该注解，则不会生成单独的属性组，而是形成如下的一个属性节点：
@JsonUnwrapped @JsonUnwrapped: 作用在属性字段或方法上，用来将子JSON对象的属性添加到封闭的JSON对象。
@ConfigurationProperties适用与注入配置文件整个对应bean的全部属性，而@Value正如其名称一样，适合注入配置文件单个值
@TestPropertySource spring 在单测中envirionment注入优先
　@RequestMapping 访问这个controller需要的方法/url/参数/header

- spring controller重定向网页的方式
使用ModelAndView
使用SpringMVC
使用HttpServletResponse 













=========
中间件/分布式
========
### 分布式锁/
- 数据库唯一索引
- redis的setnx指令
- 分布式redis用redlock算法 从n个相互独立的redis实例获取锁
- zk 树形命名空间+节点类型zkid+监听器

### 分布式事务/

2PC
CAP
BASE
分布式协议
PAXOS 投票 三阶段

大数据组件
### kafka
发消息默认不是一条一条发，是等一波再发
吞吐更高，并且适合大数据场景，不适合业务



### zookeeper
- zk能用来做什么？
Eureka的注册中心，实现分布式锁，kafka使用zk管理元数据配置
即统一配置管理、统一命名服务、分布式锁、集群管理。
通用中间件解决分布式节点管理


- zk的数据结构
ZooKeeper的数据结构，跟Unix文件系统非常类似，可以看做是一颗树，每个节点叫做ZNode。每一个节点可以通过路径来标识
znode分为两种
1. 短暂/临时节点： 当client和server断开连接时，创建的znode自动删除
2. 持久节点： 断开链接不会删除


- 监听器
常见的监听场景有以下两项：
监听Znode节点的数据变化
监听子节点的增减变化


- zk工作
znode+监听器
1. 统一配置管理
把统一配置文件放到znode节点中，各系统监听数据变化，变更及时响应
监听器监听数据变化-写数据库-客户端点击更新配置-从数据库读配置

2. 统一命名服务
统一命名服务的理解其实跟域名一样，是我们为这某一部分的资源给它取一个名字，别人通过这个名字就可以拿到对应的资源。
host-ip

3. 分布式锁
zkid是有序的  
各系统同时访问同一znode下资源，创建带顺序的临时节点  
每个临时节点判断自己是不是最小节点  

如果是，拿到锁，释放后删掉临时节点  

如果不是，在自己前一个临时节点放置监听器，监听节点状态  

- 分布式锁  
临时节点解决死锁  
过半数可用实现高可用  
顺序节点实现阻塞  
带线程信息可实现锁的可重入  
客户端重试防止网络抖动的羊群效应  


4. 集群状态
以三个系统A、B、C为例，在ZooKeeper中创建临时节点即可
只要系统A挂了，那/groupMember/A这个节点就会删除，通过监听groupMember下的子节点，系统B和C就能够感知到系统A已经挂了。

- zab协议
zk集群角色
Leader  
follower  
observer（不参加选举）  
每个角色有其对应的状态  
zk服务状态  
looking 没有leader时 准备选举  
following / leading /observing  

zxid  
全局有序id = epoch+counter  

zab状态
ELECTION  
DISCOVERY  
SYNCHRONIZATION  
BROADCAST  

选举过程
通过规则，接受一半投票


选举结束之后follower要连接leader，然后进行数据同步


广播
可靠性/有序性





==========
redis
==========
五种redis对象及其编码
- 字符串对象的编码可以是 int(利于数字） 、 raw 或者 embstr（利于短编码），底层数据结构是sds  
- 列表对象编码可以是 ziplist（压缩列表） 或者 linkedlist（双端链表）  
- 哈希对象的编码可以是 ziplist 或者 hashtable （字典）  
- 集合对象的编码可以是 intset（整数集合） 或者 hashtable   
- 有序集合的编码可以是 ziplist 或者 skiplist（跳跃表） 
 
编码及对应数据结构设计
- sds 常数获取字符串长度
- 跳跃表 层 跨度 节点中存储了“跳跃”的指向后续节点的指针 上一层的节点数为下一层的1/P
- 字典结构有两个哈希表 渐进式rehash
- ziplist 顺序型数据结构 连续内存 特殊编码

跳跃表
- 跳表层数上限为啥是32？
根据前面的随机算法当level[0]有2的64次方个节点时，才能达到32层，因此层数上限是32完全够用了。

- redis中为啥不用红黑树二用跳表？
https://news.ycombinator.com/item?id=1171423
1 内存占用方面跳表比红黑树多，但是多的内存很有限
2 实现比红黑树简单
3 跟红黑树比更方便的支持范围查询

布隆过滤器
- 用于海量数据判存
- k个哈希函数映射到位向量的k个位置
- 缺点删除元素 -- countingBloomFilter
- funnel类将具体对象分解成原生字段值



解决热点key访问
- 不同的热点key分配随机的过期时间
- redis分布式锁
- 通过hash分key
- 一致性hash
- 本地缓存

缓存种类
- ehcache
- guava cache
- memcached
- redis
- spring注解







==========
mysql
==========
### 隔离级别
-读未提交/允许脏读
-读已提交/允许不可重复读 -- 读操作 写时读操作加锁
-可重复读RR/允许幻读 -- 间隙锁
-可串行化

### innodb
- 支持事务
- 行锁
- 支持外键
- 支持非锁定读
innodb主键是聚簇索引，采用b+树结构，非叶节点存的是主键和指向子节点的指针，叶子节点存的就是整体行数据，整体都是有序的，通过主键扫描根据树查找，最终落到叶子节点，命中然后返回

- 实现mvcc乐观锁 提高并发 
MVCC会给每行元组加一些辅助字段，记录创建版本号和删除版本号。
而每一个事务在启动的时候，都有一个唯一的递增的版本号。每开启一个新事务，事务的版本号就会递增。

- 聚集索引
主键索引的叶子结点中保存的数据为整行数据，利于主键排序查找和范围查找。而非主键索引叶子节点保存的是主键的值。

- B+树
兄弟叶节点会维持索引

- redo log
物理日志，记录在数据页上的修改

### 共享锁排他锁
- 共享锁是可以加读锁，但不能加写锁
- 排他所不能再加所有锁

### innoDB是如何解决幻读的
在RR的隔离级别下，Innodb使用MVCC和next-key locks解决幻读，MVCC解决的是普通读（快照读）的幻读，next-key locks解决的是当前读情况下的幻读。
幻读和不可重复读的区别是，前者是一个范围，后者是本身

当前读指加锁的读，如所谓当前读，指的是加锁的select(S或者X), update, delete等语句。使用间隙锁
普通读指不会加锁的读，使用mvcc

### 索引
- 索引实现方式（hash 有序数组 搜索树 B+树）
在B+树中，我们将节点分为叶子结点和非叶子结点，非叶子结点上保存的是索引，而且一个节点可以保存多个索引；
数据全部存于叶子结点上,根据叶子结点的内容不同，innodb索引分为主键索引和非主键索引。
非主键索引也称为二级索引。主键索引的叶子结点中保存的数据为整行数据，而非主键索引叶子节点保存的是主键的值。（聚簇索引）

- 索引创建原则
1.选择唯一性索引
2.为经常需要排序、分组和联合操作的字段建立索引
3.为常作为查询条件的字段建立索引
4. 限制索引的数目
5. 尽量使用数据量少的索引
6. 最左前缀匹配原则，非常重要的原则。
mysql会一直向右匹配直到遇到范围查询(>、<、between、like)就停止匹配






=========
网络
=========
### HTTP
- http和https的区别
ssl/tls 密钥交换算法 - 签名算法 - 对称加密算法 - 摘要算法 

- http get和post区别

- 无状态协议与cookie

- 报文组成
请求行-头部字段-空行-请求数据

- 通用标头
Date Cache-control Connection等
- 实体标头
Content-Length Content-Language Content-Encoding
- 请求标头
Host Referer If-Modified-Since Accept
- 响应标头
Access-Control-Allow-Origin Keep-alive set-cookie X-Frame-Options(点击劫持）








