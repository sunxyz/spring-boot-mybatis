package cn.sunxyz.mapper.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import cn.sunxyz.entity.User;

public class UserMapperProvider {

	// 动态生成sql
	public String findByName(String name) {
		String sql = "SELECT * FROM user";
		if (StringUtils.isEmpty(name)) {
			return sql;
		}
		sql += " WHERE name LIKE '%" + name + "%'";
		return sql;
	}

	// 使用工具类来准备相同的 SQL 语句
	public String findByName1(String name) {

		return new SQL() {
			{
				SELECT("id, name, age");
				FROM("user");
				WHERE("name LIKE '%" + name + "%'");
			}
		}.toString();
	}

	public String findByNameAndAge(Map<String, Object> map) {

		String name = (String) map.get("param1");
		Integer age = (Integer) map.get("param2");

		return new SQL() {
			{
				SELECT("id, name, age");
				FROM("user");
				WHERE("name LIKE '%" + name + "%'");
				AND();
				WHERE("age = " + age);
			}
		}.toString();

	}

	public String update(User user) {
		return new SQL() {
			{

				if (!StringUtils.isEmpty(user.getId())) {
					UPDATE("user");
					if (!StringUtils.isEmpty(user.getName())) {
						SET("name = #{name}");
					}
					if (user.getAge() != 0) {
						SET("age = #{age}");
					}
					WHERE("id = #{id}");
				}
			}
		}.toString();
	}

}
