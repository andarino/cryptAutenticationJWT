package com.spring.serverJWT.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.serverJWT.data.DetalheUserData;
import com.spring.serverJWT.model.UserModel;

public class JWTAutenticarFilter extends UsernamePasswordAuthenticationFilter{

	public static final int TOKEN_EXPIRACAO = 600_000;
	public static final String TOKEN_SENHA = "29f0605b-2cf8-45fd-8139-968b2df8d943"; //nao poderia esta aqui 
	
	private final AuthenticationManager authenticationManager;

	public JWTAutenticarFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	 @Override
public Authentication attemptAuthentication(HttpServletRequest request, 
											HttpServletResponse response)
															throws AuthenticationException {
try {	
		//receber o json no nosso formato de model
		UserModel usuario = new ObjectMapper()
				.readValue(request.getInputStream(), UserModel.class); //convertendo o json de usuario em classe
		
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				usuario.getEmail(),
				usuario.getPassword(),
				new ArrayList<>()	//lista de permissoes do usuario 
				));
} catch (IOException e){
		throw new RuntimeException("Falha ao autenticar usuario", e);
}	

}
	 //caso aconteca um sucesso na autenticao, o que devo fazer?
	 //R: GERAR o TOKEN JWT!!!!
@Override
protected void successfulAuthentication(HttpServletRequest request,
										HttpServletResponse response, 
										FilterChain chain, 
										Authentication authResult) throws IOException, ServletException {
	
	DetalheUserData usuarioData = (DetalheUserData)authResult.getPrincipal();	
	
	String token = JWT.create().
			withSubject(usuarioData.getUsername())
			.withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
			.sign(Algorithm.HMAC512(TOKEN_SENHA));	//token uni
	
	response.getWriter().write(token); //registrar o token pro corpo da pagina
	response.getWriter().flush();
	}	 

	 
	
}
