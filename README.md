# springboot-review

## mybatis使用

1、编写application.yml配置文件，model层

2、/mapper中写对db存取的代码，有几种写法
- 方法上注解写法
- SqlProvider
    - return "sql"
    - return new SQL() //动态sql
    
3、/controller中控制url映射到mapper
- controller以@Pathvarible解析参数
- mapper以@Param把参数赋值给@Param("x")

## 多模块整合
一个maven为基础的根工程下整个多个子模块，注意：
- 根项目的pom中，module标签整合多个子模块，注意根项目的版本号，所有子模块都要将根项目作为parent
- 每个子模块都引用根项目，同时子模块之间的dependence进行自上而下的连接，如web->service->repo->entity
- 多个子模块的包名要一致
- 在项目启动模块，即web模块的依赖中添加打包插件，然后父类工程clean和package，生成jar包