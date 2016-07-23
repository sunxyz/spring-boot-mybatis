package cn.sunxyz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import cn.sunxyz.entity.User;
import cn.sunxyz.mapper.UserMapper;

@Service
@Transactional
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public User findById(String id){
		return userMapper.findById(id);
	}
	
	//使用分页插件
	public List<User> findAll(){
		PageHelper.startPage(1, 2);
		return userMapper.fingAll();
	}
	
	public List<User> findByName(String name){
		return userMapper.findByName(name);
	}

	
	public List<User> findByNameAndAge(String name, Integer age){
		return userMapper.findByNameAndAge(name, age);
	}
	
	public boolean update(User user){
		int num = userMapper.update(user);
		if(num>0){
			return true;
		}
		return false;
	}
}
