package com.spring.serverJWT.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("logintwo.html");	
		mv.setViewName("logintwo.html");
		return mv;
}

@RequestMapping(value = "/index", method = RequestMethod.GET)
public ModelAndView index() {
	ModelAndView mv = new ModelAndView("index.html");	
	mv.setViewName("index.html");
	return mv;
}

	
@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
@ResponseBody
public ModelAndView cadastro() {
	ModelAndView mv = new ModelAndView("cadastro.html");	
	mv.setViewName("cadastro.html");
	return mv;
}

@GetMapping("/registros/{email}")
@ResponseBody
public UserModel getContaById(@PathVariable String email){
	return repository.findByEmail(email).get();
}

	
	
@PostMapping("/salvar")
@ResponseBody
public ResponseEntity<UserModel> salvar(@RequestBody UserModel user){
	user.setPassword(encoder.encode(user.getPassword()));
	return ResponseEntity.ok(repository.save(user));
}




}
