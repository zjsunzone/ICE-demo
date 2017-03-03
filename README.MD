ICE RPC 调用详解
---

**结构解析**
1、_OnlineBookOperations 接口和_OnlineBookOperationsNC 是两个姊妹接口，它们是OnlineBook的
具体业务方法定义的接口,不通点：

- _OnlineBookOperations 在方法的签名中增加了ICE的内部对象 Ice.Current，此对象包括了当前调用的
网络连接等上下文信息.

2、OnlineBook 接口组合继承：_OnlineBookOperations _OnlineBookOperationsNC Ice.Object

备注：通过组合继承Ice.Object根接口与上述业务方法接口的独特设计思路，OnlineBook接口具备了实现多重业务接口的独特
能力，即：Ice中的Facet的概念---- 一个IceObject有一个主接口及多个Facet.


**IceBox**
   IceBox 就好像是一个Tomcat，我们只要写N个Ice服务的代码，用一个装配文件定义需要加载的服务列表、服务的
启动参数、启动次序等必要信息，然后启动IceBox;

- 一个IceBox 内部会创建一个service manager对象（Ice.Admin），这个对象专门
负责loading和initializing那些配置好的服务，可以有选择性将这个对象暴露给远程客户端；
(注：Ice.Admin默认关闭，而在IceGrid中部署的IceBox则会自动被激活)


2、Registry
```conf
# Registry 一个以二进制文件形式存储运行时期Ice服务注册信息的独立进程，支持主从同步,从节点可以分担查询请求

# 设置主节点实例名称，其它配置都基于该名称进行配置
IceGrid.InstanceName=DemoGrid

# 客户端通过此Endpint访问注册表
IceGrid.Registry.Client.Endpoints=tcp -p 4061
IceGrid.Registry.Server.Endpoints=tcp -p 11111
IceGrid.Registry.Internal.Endpoints=tcp
IceGrid.Registry.AdminPermissionsVerifier=DemoGrid/NullPermissionsVerifier

# Registry的持久化文件存放目录，需要手工创建目录
IceGrid.Registry.Data=./registry

# 是否允许动态注册，一般在开发阶段允许，而生产部署则关闭.
IceGrid.Registry.DynamicRegistration=1

```

3、IceGrid 管理工具

- icegridadmin ： 用来管理IceGrid的命令行工具，部署或升级IceGrid，查看grid状态，启停Node中的Server实例等。
- iceGridGUI:
- icepatch ： 命令行工具，替代rsyn或FTP

管理命令
```shell
# 启动Registry
icegridregistry --Ice.Config=registry.cfg

# 启动Node
icegridnode --Ice.Config=node1.cfg

# 启动icegridadmin
icegridadmin -u test -p test --Ice.Default.Locator="IceGrid/Locator:tcp -h locallhost -p 4061"












```





