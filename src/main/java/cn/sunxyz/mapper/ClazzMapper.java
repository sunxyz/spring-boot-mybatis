package cn.sunxyz.mapper;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.sunxyz.entity.Clazz;

public interface ClazzMapper {
	
	//一对多
	@Select("select * from clazz where id = #{id}")
	@Results({
		@Result(column="id" ,property = "id", id = true),
		@Result(column = "name", property = "name"),
		@Result(column = "student_id", property = "students",many = @Many(select="cn.sunxyz.mapper.StudentMapper.findByColumId"))
	})
	Clazz findById(@Param("id") String id);

}
