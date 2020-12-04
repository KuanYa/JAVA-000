package com.read.write.separate.mapper;

import com.read.write.separate.entity.T1;
import org.apache.ibatis.annotations.*;

@Mapper
public interface T1Mapper {
    @Insert("insert into t1 (id) values (#{id})")
    @Options(useGeneratedKeys = true)
    int save(T1 t1);

    @Select("select * from t1 where id = #{id}")
    T1 findById(@Param("id") Integer id);
}
