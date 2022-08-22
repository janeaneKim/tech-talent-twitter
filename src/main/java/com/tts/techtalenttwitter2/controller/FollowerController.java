package com.tts.techtalenttwitter2.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tts.techtalenttwitter2.model.User;
import com.tts.techtalenttwitter2.service.UserService;

@Controller
public class FollowerController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/follow/{username}")
	public String follow(@PathVariable(value = "username") String username, 
							HttpServletRequest request) {
		User loggedInUser = userService.getLoggedInUser();
		User userToFollow = userService.findByUsername(username);
		if(userToFollow != null) {
			List<User>followers = userToFollow.getFollowers();
			followers.add(loggedInUser);
			userToFollow.setFollowers(followers);
			userService.save(userToFollow);
		}
		//want method to reload with page last referenced
		return "redirect:" + request.getHeader("Referer");
	}
	
	@PostMapping(value = "/unfollow/{username}")
	public String unfollow(@PathVariable(value = "username") String username, 
							HttpServletRequest request) {
		User loggedInUser = userService.getLoggedInUser();
		User userToUnfollow = userService.findByUsername(username);
		if(userToUnfollow != null) {
			List<User>followers = userToUnfollow.getFollowers();
			followers.remove(loggedInUser);
			userToUnfollow.setFollowers(followers);
			userService.save(userToUnfollow);
		}
		//want method to reload with page last referenced
		return "redirect:" + request.getHeader("Referer");
	}
}
