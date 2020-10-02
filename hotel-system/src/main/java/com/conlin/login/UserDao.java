package com.conlin.login;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    User login (@Param("acct") String acct, @Param("pwd") String pwd);
}
