package com.wp.emp.service;


import com.wp.emp.dto.EmployeeDTO;
import com.wp.emp.dto.EmployeeUpdateDTO;
import com.wp.emp.dto.SearchCriteriaDTO;
import com.wp.emp.dto.UserDTO;

import java.util.LinkedHashMap;

public interface EmployeeService {

LinkedHashMap<String, Object> saveEmployee(EmployeeDTO emp);

LinkedHashMap<String,Object> deleteEmployee(Long id);

LinkedHashMap<String,Object> getEmployee(Long id);

LinkedHashMap<String, Object> updateEmployee(Long empId,EmployeeUpdateDTO emp);

public LinkedHashMap<String, Object> exceptionMap(String msg);

LinkedHashMap<String,Object> getAllEmployee(SearchCriteriaDTO data);

}
