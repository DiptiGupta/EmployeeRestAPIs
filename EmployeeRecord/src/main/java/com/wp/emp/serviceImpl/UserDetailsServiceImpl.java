package com.wp.emp.serviceImpl;

import com.wp.emp.dto.UserDTO;
import com.wp.emp.exception.DisabledException;
import com.wp.emp.model.User;
import com.wp.emp.repository.UserRepository;
import com.wp.emp.service.UserService;
import com.wp.emp.util.CommonResponseEntity;
import com.wp.emp.util.ConstantVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashMap;

@Service("userDetailsService")
@Component
public class UserDetailsServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommonResponseEntity commonResponseEntity;

    @Override
	public UserDetails loadUserByUsername(String username) throws DisabledException {
		com.wp.emp.model.User newUser = userRepository.findOneByUsername(username);
		if (newUser == null) {
			throw new DisabledException("user is not authorized for this application");
		}
		return newUser;
	}

	@Override
    public LinkedHashMap<String,Object> createUser(UserDTO user){

        User u = new User();
        u.setEnabled(true);
        u.setCreatedDate(new Date());
        u.setPassword(encodePassword(user.getPassword()) );

        u.setUsername(user.getUsername());

        userRepository.save(u);
       return commonResponseEntity.responseEntity(HttpStatus.CREATED,
                    "User created successfully", ConstantVariables.SUCCESS_CODE);

    }

     private String encodePassword(String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String newPassword = passwordEncoder.encode(password);
		passwordEncoder = null;
		return newPassword;
	}
}
