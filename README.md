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