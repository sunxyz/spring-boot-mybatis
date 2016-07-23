package cn.sunxyz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.sunxyz.entity.Student;
import cn.sunxyz.mapper.StudentMapper;

@Service
public class StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	public Student findById(String id){
		return studentMapper.findById(id); 
	}
}
