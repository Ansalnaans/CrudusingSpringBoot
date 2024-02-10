package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	
	@Autowired
	StudentDAO studentdao;
	@RequestMapping("/studentDetials")
	public String show(){
		return "home.jsp";
	}
	@RequestMapping("/addStudent")
	public ModelAndView addUser(Student stud) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("studentDetials",stud);
		mv.setViewName("add.jsp");
		studentdao.save(stud);
		return mv;
	}
	
	@RequestMapping("updateStudent")
	public ModelAndView updateUser(Student stud) {
		studentdao.updateStudent(stud.getStudentName(), stud.getRollNo());
		ModelAndView mv = new ModelAndView();
		mv.addObject("studentDetails",stud);
		mv.setViewName("update.jsp");
		return mv;	
	}
	
	@RequestMapping("viewStudent")
	public ModelAndView display(Student stud) {
		Optional<Student> op = studentdao.findById(stud.getRollNo());
		Student student = op.get();
		ModelAndView mv = new ModelAndView();
		mv.addObject("studentDetails",student);
		mv.setViewName("display.jsp");
		return mv;
	}

}
