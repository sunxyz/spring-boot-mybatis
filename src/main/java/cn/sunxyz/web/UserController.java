package cn.sunxyz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.sunxyz.entity.User;
import cn.sunxyz.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value="查询所有用户", notes="整合分页插件pagehelper+Swagger2构建RESTful API文档")
	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}

	@ApiOperation(value="根据id查询用户", notes="查询单用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") String id) {
		return userService.findById(id);
	}

	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public List<User> findByname(@PathVariable("name") String name) {
		return userService.findByName(name);
	}

	@RequestMapping(value = "/q", method = RequestMethod.GET)
	public List<User> findByNameAndAge(String name, Integer age) {
		return userService.findByNameAndAge(name, age);
	}
	
	@RequestMapping(method = RequestMethod.PUT)	
	public String update(User user){
		if(userService.update(user)){
			return "SUCCESS";
		}
		return "ERROR";
		
	}

}
