package top.xrk.fn436.community.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.xrk.fn436.community.model.Question;

@Mapper
public interface QuesstionMapper {

    @Insert("insert into question (title, description, gmtCreate, gmtModified, creator, tag) " +
            "values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);
}
