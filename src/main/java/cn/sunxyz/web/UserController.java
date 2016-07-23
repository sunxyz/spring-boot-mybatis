package cn.sunxyz.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.sunxyz.entity.User;
import cn.sunxyz.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public List<User> findAll() {
		return userService.findAll();
	}

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
