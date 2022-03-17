package com.plancer.mapper;

import com.plancer.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("select * from t_user")
    List<User> list();

    @SelectProvider(type = UserSqlProvider.class, method = "listByUsername")
    List<User> listByUsername(String username);


    @Results({
            @Result(property = "userId", column = "USER_ID"),
            @Result(property = "username", column = "USERNAME"),
            @Result(property = "password", column = "PASSWORD"),
            @Result(property = "mobileNum", column = "MOBILE_NUM"),
    })

    @Select("select * from t_user")
    List<User> listSample();

    @Select("select * from t_user where username like #{username} and password like #{password}")
    User get(@Param("username") String username, @Param("password") String password);


    @SelectProvider(type = UserSqlProvider.class, method = "getBadUser")
    User getBadUser(@Param("username") String username, @Param("password") String password);

}
