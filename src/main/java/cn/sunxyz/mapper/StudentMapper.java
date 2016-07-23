package cn.sunxyz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.sunxyz.entity.Student;

public interface StudentMapper {

	//一对一查询
	@Select("select * from student where id = #{id}")
	@Results(id = "studentMap", value = {
			@Result(column = "id", property = "id", id = true),
			@Result(column= "name", property="name"),
			@Result(column = "clazz_id", property="clazz",one=@One(select = "cn.sunxyz.mapper.ClazzMapper.findById"))
	})
	Student findById(@Param("id")String id);
	
	@Select("select * from student where clazz_id = #{id}")
	List<Student> findByColumId(@Param("id")String id);

}
