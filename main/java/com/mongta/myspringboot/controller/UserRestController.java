package com.mongta.myspringboot.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongta.myspringboot.entity.User;
import com.mongta.myspringboot.exception.ResourceNotFoundException;
import com.mongta.myspringboot.repository.UserRepository;

@RestController
public class UserRestController {
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/users")
	public User create(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable long id) {
		Optional <User> optional = userRepository.findById(id);
		User user =  optional.orElseThrow(() -> new ResourceNotFoundException("user","id", id));
		return user;
	}
	
	@RequestMapping(value="/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetail) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		user.setName(userDetail.getName());
		user.setEmail(userDetail.getEmail());
		User updatedUser = userRepository.save(user);
		return updatedUser;
	}
	
	//<?> -> 정해지지 않았음
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		Optional<User> optional = userRepository.findById(id);
		// id 외 매핑되는 User객체가 없으면  
		if(optional.isEmpty()) {
			return new ResponseEntity<>(id + "User not found", HttpStatus.NOT_FOUND);
		}
		
		userRepository.deleteById(id);
		return ResponseEntity.ok(id+"User 삭제되었음");		//return 200 + message
	}

	@GetMapping(value = "/usersxml", produces = {"application/xml"})
	public List<User> getUserXml() {
		return userRepository.findAll();
	}
}
