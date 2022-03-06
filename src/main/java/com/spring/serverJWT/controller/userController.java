package com.spring.serverJWT.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import com.spring.serverJWT.Repository.UserRepository;
import com.spring.serverJWT.model.UserModel;

@RestController
public class userController {
	
	@Autowired
	private final UserRepository repository;
	private final PasswordEncoder encoder;
	
	public userController(UserRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}

@RequestMapping(value = "/", method = RequestMethod.GET)
@ResponseBody
public ModelAndView cadastro() {
	ModelAndView mv = new ModelAndView("cadastro.html");	
	mv.setViewName("cadastro.html");
	return mv;
}

	
	
@PostMapping("/salvar")
@ResponseBody
public ResponseEntity<UserModel> salvar(@RequestBody UserModel user){
	user.setPassword(encoder.encode(user.getPassword()));
	return ResponseEntity.ok(repository.save(user));
}




}
