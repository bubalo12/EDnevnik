package com.iktpreobuka.EDnevnik.controllers;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;*/
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.EDnevnik.entities.StudentEntity;
import com.iktpreobuka.EDnevnik.entities.TeacherEntity;
import com.iktpreobuka.EDnevnik.entities.UserEntity;
import com.iktpreobuka.EDnevnik.entities.dto.TeacherRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.UserRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.UserTokenDTO;
import com.iktpreobuka.EDnevnik.repositories.UserRepository;
//import com.iktpreobuka.EDnevnik.utils.Encryption;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@RestController
@RequestMapping("")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Value("${spring.security.secret-key}")
	private String secretKey;

	@Value("${spring.security.token-duration}")
	private Integer timetoEx;
	
	
	
	@PostMapping("/")
	public ResponseEntity<?> createNewUser(@RequestBody UserRegisterDTO userDTO) {

		

		UserEntity newUser = new UserEntity();
		newUser.setUsername(userDTO.getUsername());
		newUser.setPassword(userDTO.getPassword());
		

		userRepository.save(newUser);

		return new ResponseEntity<UserEntity>(newUser, HttpStatus.CREATED);

	}
	
	
/*
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
		UserEntity user = userRepository.findByUsername(username);
		if (user != null && Encryption.validatePassword(password, user.getPassword())) {
			String token = getJWTToken(user);
			UserTokenDTO userDTO = new UserTokenDTO(username, token);
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}
		return new ResponseEntity<>("Wrong username/password", HttpStatus.UNAUTHORIZED);
	}
	
	private String getJWTToken(UserEntity user) {
		String secretKey = "mySecretKey";
		
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole().getName());
		String token = Jwts.builder().setId("softtekJWT").setSubject(user.getUsername())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

	
	
	
	
	
	@Secured("ROLE_ADMIN")
	@GetMapping("/api/v1/users")
	public ResponseEntity<?> getAllUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}

	
	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
		
		List<UserEntity> users = (List<UserEntity>) userRepository.findAll();
		
		for (UserEntity userEntity : users) {
			if (userEntity.getUsername().equals(username) && Encryption.validatePassword(password, userEntity.getPassword())) {
				
				String token = getJWTToken(userEntity);
				
				UserTokenDTO userTokenDTO = new UserTokenDTO();
				userTokenDTO.setUsername(username);
				userTokenDTO.setToken(token);
				
			//	logger.info(userTokenDTO.getUsername() + " : logged in.");
				
				return new ResponseEntity<>(userTokenDTO, HttpStatus.OK);
			}
		}
		
		return new ResponseEntity<>("Username and password do not match", HttpStatus.UNAUTHORIZED);
	}
	private String getJWTToken(UserEntity user) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder().setId("softtoJWT").setSubject(user.getUsername())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();
		return "Bearer " + token;
	}

	//@Secured("ROLE_ADMIN")
	@GetMapping("/users")
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
	}
*/
}
