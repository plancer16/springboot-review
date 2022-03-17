package com.plancer.mapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.plancer.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

public class UserSqlProvider {
    public String listByUsername(String username) {
        return "select * from t_user where username =#{username}";
    }

    public String getBadUser(@Param("username") String username, @Param("password") String password) {
        return new SQL() {{
            SELECT("*");
            FROM("t_user");
            if (username != null && password != null) {
                WHERE("username like #{username} and password like #{password}");
            } else {
                WHERE("1=2");
            }
        }}.toString();
    }



    /**
     * 1.用于获取结果集的映射关系
     */
    public static String getResultsStr(Class origin) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("@Results({\n");
        for (Field field : origin.getDeclaredFields()) {
            String property = field.getName();
            //映射关系：对象属性(驼峰)->数据库字段(下划线)
            String column = new PropertyNamingStrategy.SnakeCaseStrategy().translate(field.getName()).toUpperCase();
            stringBuilder.append(String.format("@Result(property = \"%s\", column = \"%s\"),\n", property, column));
        }
        stringBuilder.append("})");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getResultsStr(User.class));
    }
}
