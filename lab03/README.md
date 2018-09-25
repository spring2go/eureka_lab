实验三、Spring Cloud Zuul/Eureka/Ribbon集成实验
======

### 实验步骤

#### 1. 导入项目到IDE

* Eureka服务器[eureka-service](eureka-service)
* Hello微服务服务端[hello-server](hello-server)
* Hello微服务客户端[hello-client](hello-client)
* Zuul服务器[zuul-service](zuul-service)

#### 2. 运行Eureka服务器(standalone模式)

校验:

```
http://localhost:8070/
```

#### 3. 运行Hello微服务服务端


在Eclipse IDE中，以定制配置方式运行hello-server微服务，

```
Run As -> Run Configurations -> Spring Boot App
```

配置服务启动端口`Override Properties`

第一次运行使用`8071`服务器端口

```
server.port=8071
```
第二次运行使用`8072`服务器端口
```
server.port=8072
```

UI校验：

```
http://localhost:8070/
```

API校验：

```
http://localhost:8070/eureka/apps
```

#### 4. 运行Hello微服务客户端


在Eclipse IDE中，以定制配置方式运行hello-client微服务，

```
Run As -> Run Configurations -> Spring Boot App
```

配置服务启动端口`Override Properties`

第一次运行使用`8073`服务器端口

```
server.port=8073
```
第二次运行使用`8074`服务器端口
```
server.port=8074
```

UI校验：

```
http://localhost:8070/
```

API校验：

```
http://localhost:8070/eureka/apps
```

#### 5. 运行Zuul服务端

通过浏览器或者Postman校验：

1. 不启用Ribbon负载均衡 (zuul -> hello server)
```
http://localhost:8079/api/server/rest/hello/server
```

2. 启用Ribbon负载均衡，(zuul/ribbon -> hello client/ribbon -> hello server)
```
http://localhost:8079/api/client/rest/hello/client
```
