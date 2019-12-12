package top.xrk.fn436.community.mapper;

import org.apache.ibatis.annotations.*;

import top.xrk.fn436.community.model.User;

@Mapper
public interface  UserMapper {

    @Insert("insert into user (name,accountId,token,gmtCreate,gmtModified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);

    @Select("select * from user where accountId = #{accountId}")
    User findByid(String accountId);

    @Update("update user set token=#{param1} where accountId = #{param2}")
    void updateToken(String token,String accountId);

}
