package top.xrk.fn436.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import org.apache.ibatis.annotations.Select;
import top.xrk.fn436.community.model.User;

@Mapper
public interface  UserMapper {

    @Insert("insert into user (name,accountId,token,gmtCreate,gmtModified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(String token);
}
