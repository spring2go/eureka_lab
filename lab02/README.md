实验二、Spring Cloud Eureka/Ribbon高级实验
======

### 实验步骤

#### 1. 导入项目到IDE

* Eureka服务器[discovery-server](discovery-server)
* Time微服务服务端[time-service](time-service)
* Time微服务客户端[time-client](time-client)

#### 2. 更新hosts配置

```
{Windows操作系统目录}\System32\drivers\etc\hosts
```

添加映射：

```
127.0.0.1 discovery1
127.0.0.1 discovery2
```

#### 3. 运行Eureka服务器(集群模式)


在Eclipse IDE中，以定制配置方式运行discovery-server微服务，

```
Run As -> Run Configurations -> Spring Boot App
```

第一次运行启用`Profile=discovery1`，服务运行在`8761`端口

第二次运行启用`Profile=discovery2`，服务运行在`8762`端口


校验:

```
http://discovery1:8761/
或者
http://discovery2:8762/
```

#### 4. 运行Time微服务服务端


在Eclipse IDE中，以定制配置方式运行time-service微服务，

```
Run As -> Run Configurations -> Spring Boot App
```

配置服务启动端口`Override Properties`

第一次运行使用`4444`服务器端口

```
server.port=4444
```
第二次运行使用`5555`服务器端口
```
server.port=5555
```

UI校验：

```
http://discovery1:8761/
```

API校验：

```
http://discovery1:8761/eureka/apps
```

#### 5. 运行Time微服务客户端(Ribbon负载均衡模式)

直接以`Spring Boot App`方式运行time-client微服务

校验Ribbon负载均衡生效，浏览器访问：

```
http://localhost:8080
```

#### 6. 运行Time微服务客户端(DiscoveryClient模式)

如果运行了第5步，请先停止`time-client`微服务。

调整源代码：

* 将restTemplate方法上的`@LoadBalanced`标注注释掉

直接以`Spring Boot App`方式运行time-client微服务

校验Ribbon负载均衡已经失效，浏览器访问：

```
http://localhost:8080
```

校验DiscoveryClient直接访问已经生效，浏览器访问：

```
http://localhost:8080/getTimeViaEurekaClient
```



