package com.xiu.fastWeb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xiu.fastWeb.model.User;
import com.xiu.fastWeb.service.UserService;



@RestController
@RequestMapping(value="/test")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping(value="test1")
	public String savePerson1() {
		
		User person = userService.saveUser();
		
		System.err.println(person.toString());
		
		return "";
	}
	
	@GetMapping(value = "test2")
	@ResponseBody
	public List<User> getUserList(@RequestParam String id){
		
		return userService.getUserList( id);
		
	}
	
	
}
