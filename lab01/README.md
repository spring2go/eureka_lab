实验一、Spring Cloud Eureka/Ribbon基础实验
======

### 实验步骤

#### 1. 导入项目到IDE

* Eureka服务器[discovery-server](discovery-server)
* Time微服务服务端[time-service](time-service)
* Time微服务客户端[time-client](time-client)

#### 2. 运行Eureka服务器(standalone模式)

校验:

```
http://localhost:8761/
```

#### 3. 运行Time微服务服务端


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
http://localhost:8761/
```

API校验：

```
http://localhost:8761/eureka/apps
```

#### 4. 运行Time微服务客户端(Ribbon负载均衡模式)

直接以`Spring Boot App`方式运行time-client微服务

校验Ribbon负载均衡生效，浏览器访问：

```
http://localhost:8080
```

