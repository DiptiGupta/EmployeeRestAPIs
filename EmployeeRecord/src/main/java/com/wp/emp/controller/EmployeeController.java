package com.wp.emp.controller;

import com.wp.emp.dto.EmployeeDTO;
import com.wp.emp.dto.EmployeeUpdateDTO;
import com.wp.emp.dto.SearchCriteriaDTO;
import com.wp.emp.dto.UserDTO;
import com.wp.emp.service.EmployeeService;
import com.wp.emp.util.CommonResponseEntity;
import com.wp.emp.util.ConstantVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.LinkedHashMap;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CommonResponseEntity commonResponseEntity;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    // create employee
    @RequestMapping(value = "employee", method = RequestMethod.POST)
    public LinkedHashMap<String,Object> createEmployee(@Valid @RequestBody EmployeeDTO emp, BindingResult result, Principal principal) {

        logger.debug("creating new employee");
        if (result.hasErrors()) {
            return commonResponseEntity.responseEntity(HttpStatus.UNPROCESSABLE_ENTITY, result.getFieldError().getDefaultMessage(), ConstantVariables.PARAM_MISSING_CODE);
        }
        return employeeService.saveEmployee(emp);
    }

    // delete employee
    @RequestMapping(value="employee/{empId}",method=RequestMethod.DELETE)
    public LinkedHashMap<String,Object> deleteEmployee(@PathVariable("empId") Long id, Principal principal){
        logger.debug(" request to delete employee with id"+ id);
        return employeeService.deleteEmployee(id);

    }

    // update employee
    @RequestMapping(value = "employee/{empId}", method = RequestMethod.PUT)
    public LinkedHashMap<String,Object> updateEmployee(@Valid @RequestBody EmployeeUpdateDTO emp, @PathVariable("empId") Long empId, BindingResult result, Principal principal) {
        logger.debug("request to updated employee with emp id"+empId);
        return employeeService.updateEmployee(empId,emp);

    }

    // get employee end point
    @RequestMapping(value = "employee/getAll", method = RequestMethod.POST)
    public LinkedHashMap<String,Object> getEmployee(@Valid @RequestBody SearchCriteriaDTO data, BindingResult result, Principal principal) {

        if (result.hasErrors()) {
            return commonResponseEntity.responseEntity(HttpStatus.UNPROCESSABLE_ENTITY, result.getFieldError().getDefaultMessage(), ConstantVariables.PARAM_MISSING_CODE);
        }

        return employeeService.getAllEmployee(data);


    }

    // get only one employee : additional endpoint
    @RequestMapping(value="employee/{empId}",method = RequestMethod.GET)
    public LinkedHashMap<String,Object> getEmployee(@PathVariable("empId") Long id, Principal principal) {
        logger.debug(" get employee data of " + id);
        return employeeService.getEmployee(id);

    }

}
