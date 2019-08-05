package com.wp.emp.controller;

import com.wp.emp.dto.UserDTO;
import com.wp.emp.service.EmployeeService;
import com.wp.emp.service.UserService;
import com.wp.emp.util.CommonResponseEntity;
import com.wp.emp.util.ConstantVariables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;

/* this is created to create user which has access to perform any operation on employee data */
@RestController
public class UserController {


    @Autowired
    private UserService userDetailsService;

    @Autowired
    private CommonResponseEntity commonResponseEntity;

    // this API is created just to add some user. It did not consider all the scenarios.
    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public LinkedHashMap<String, Object> registration(@Valid @RequestBody UserDTO user, BindingResult result){
        if (result.hasErrors()) {
            return commonResponseEntity.responseEntity(HttpStatus.UNPROCESSABLE_ENTITY, result.getFieldError().getDefaultMessage(), ConstantVariables.PARAM_MISSING_CODE);
        }
        return userDetailsService.createUser(user);
    }
}
