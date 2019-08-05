package com.wp.emp.service;


import com.wp.emp.dto.EmployeeDTO;
import com.wp.emp.dto.EmployeeUpdateDTO;
import com.wp.emp.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.LinkedHashMap;

public interface UserService extends UserDetailsService {

LinkedHashMap<String,Object> createUser(UserDTO user);

}
