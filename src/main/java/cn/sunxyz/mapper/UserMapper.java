package cn.sunxyz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import cn.sunxyz.entity.User;
import cn.sunxyz.mapper.provider.UserMapperProvider;

@Mapper
public interface UserMapper {

	// 字段与数据建立对应关系
	@Select("SELECT * FROM user where id = #{id}")
	@Results(id = "userMap", value = { @Result(column = "id", property = "id", id = true),
			@Result(column = "name", property = "name"), @Result(column = "age", property = "age") })
	User findById(@Param("id") String id);

	@Select("SELECT * FROM user")
	@ResultMap("userMap")
	List<User> fingAll();

	// 动态生成sql
	@SelectProvider(type = UserMapperProvider.class, method = "findByName1")
	List<User> findByName(String name);

	//多参使用map
	@SelectProvider(type = UserMapperProvider.class, method = "findByNameAndAge")
	List<User> findByNameAndAge(String name, Integer age);
	
	@UpdateProvider(type = UserMapperProvider.class, method = "update")
	int update(User user);

}
