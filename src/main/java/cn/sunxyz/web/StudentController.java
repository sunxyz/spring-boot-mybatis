package cn.sunxyz.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.sunxyz.entity.Student;
import cn.sunxyz.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping(value= "/{id}", method = RequestMethod.GET)
	public Student findById(@PathVariable String id){
		return studentService.findById(id); 
	}

}
