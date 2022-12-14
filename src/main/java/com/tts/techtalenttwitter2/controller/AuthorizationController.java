package com.tts.techtalenttwitter2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalenttwitter2.model.User;
import com.tts.techtalenttwitter2.service.UserService;

//listens to login page and handles signup form

//@Controller
//public class AuthorizationController {
//	@GetMapping(value= "/login")
//	public String login() {
//		return "login";
//	}
//	
//	@GetMapping(path="/signup")
//	public String registration(Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		return "registration";
//		}
//}


@Controller
public class AuthorizationController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(path="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(path="/signup")
	public String registration(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "registration";
	}
	
	@PostMapping(path="/signup")
	public String createNewUser(@Valid User user, BindingResult bindingResult, Model model) {
		User userExists = userService.findByUsername(user.getUsername());
		if(userExists != null) {
			bindingResult.rejectValue("username","error.user", "Username is already taken");
			//handle error
		}
		
		if(!bindingResult .hasErrors()) {
			userService.saveNewUser(user);
			model.addAttribute("success", "Signup Successful");
			model.addAttribute("user", new User());
		}
		return "registration";
	}
	
}


