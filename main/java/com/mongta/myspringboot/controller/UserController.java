package com.mongta.myspringboot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mongta.myspringboot.entity.User;
import com.mongta.myspringboot.repository.UserRepository;

@Controller
public class UserController {

private final UserRepository userRepository;

public UserController(UserRepository repository) {
	this.userRepository = repository;
	
}

@GetMapping("/index")
public ModelAndView index() {
	List<User> userList = userRepository.findAll();
	return new ModelAndView("index", "users", userList);
}

@GetMapping("/signup")
public String showSignUpForm(User user) {
return "add-user";                 // url이 아니라 page 명
}


@PostMapping("/adduser")
public String addUser(@Valid User user, BindingResult result) {
	// 임력항목 검증 오류가 있으면
	if(result.hasErrors()) {
		//동록페이지에 머물러있어야 한다.
		return "add-user";
	}	
		userRepository.save(user);
		//User 목록 요청하는 url로 redirect해라
		return "redirect:/index";           // page 명이 아니라 url
}

@GetMapping("/delete/{id}")
public String deleteUser(@PathVariable("id") long id, Model model) {
User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
userRepository.delete(user);
model.addAttribute("users", userRepository.findAll());
return "index";
}

@GetMapping("thymeleaf")
	public String leaf(Model model) {
		model.addAttribute("name", "몽타");
		return "leaf";
	}


@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}

}
