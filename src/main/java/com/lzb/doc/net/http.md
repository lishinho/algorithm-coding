# http与html
**1. html与jsp的关系**
html 静态网页
jsp 动态网页

jsp(-servlet) - 控制层(Controller) - 业务层(Service) - 持久层(Dao)
jsp淘汰原因：前后解偶，代理

**2. get与post**

2.1 定义
- 浏览器发请求的get与post

get读取资源，反复读取不应该对访问的数据有副作用，幂等性
post提交元素在页面form标签中生成一个表单，不幂等

get请求不加body，只有url，请求数据放在url里
post请求数据放在body中

- 接口中的get和post(如curl, postman, java httpclient, ajax api)

REST规范
GET（SELECT）：从服务器取出资源（一项或多项）。
POST（CREATE）：在服务器新建一个资源。
PUT（UPDATE）：在服务器更新资源（客户端提供改变后的完整资源）。
PATCH（UPDATE）：在服务器更新资源（客户端提供改变的属性）。
DELETE（DELETE）：从服务器删除资源。

非REST规范
SOAP， json-rpc等

2.2 安全性

get使用url传输，更容易被看到
但做到http的端对端加密，需要https
私密数据传输用POST + body

2.3 url编码

网址路径的编码，用的是utf-8编码
查询字符串的编码，用的是操作系统的默认编码
GET和POST方法的编码，用的是网页的编码


2.4 浏览器post请求需要发两个请求

到底是发一次还是发N次，客户端可以很灵活的决定。
因为不管怎么发都是符合HTTP协议的，因此我们应该视为这种优化是一种实现细节，而不用扯到GET和POST本身的区别上。

2.5 url长度

资源/api的URL长度有可能达到2000个bytes以上，就必须使用body来传输数据，除非有特殊情况。
至于到底是GET + body还是POST + body可以看情况决定。

1个汉字字符经过UTF8编码 + percent encoding后会变成9个字节。


**3. http基本知识**
- 状态码
1xx 提示信息
2xx 成功处理
3xx 重定向
4xx 客户端报文有误
5xx 服务器内部错误

- http版本
http1.1 支持txp长连接，管道网络传输 
报文格式为：报文首部+空行+报文主体
http2.0 头部压缩/二进制格式/数据流/多路复用/服务器推送
报文格式为：头信息帧+数据帧



