package com.wp.emp.serviceImpl;


import com.wp.emp.controller.EmployeeController;
import com.wp.emp.dto.EmployeeDTO;
import com.wp.emp.dto.EmployeeUpdateDTO;
import com.wp.emp.dto.SearchCriteriaDTO;
import com.wp.emp.dto.UserDTO;
import com.wp.emp.model.Employee;
import com.wp.emp.model.User;
import com.wp.emp.repository.EmployeeRepository;
import com.wp.emp.repository.UserRepository;
import com.wp.emp.util.CommonResponseEntity;
import com.wp.emp.util.ConstantVariables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.wp.emp.service.EmployeeService;
import sun.awt.image.ImageWatched;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Optional;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CommonResponseEntity commonResponseEntity;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Override
	public LinkedHashMap<String, Object> saveEmployee(EmployeeDTO emp) {
	    LinkedHashMap<String, Object> result =  new LinkedHashMap<String,Object>();
        try {
            Employee data = new Employee();
            data.setAddress(emp.getAddress());
            data.setBirthDate(emp.getDateOfBirth());
            data.setBoss(emp.getBoss());
            data.setFirstName(emp.getFirstname());
            data.setLastName(emp.getLastname());
            data.setSalary(emp.getSalary());
            data.setCreatedDate(new Date());
            data.setUpdatedDate(new Date());

            data = employeeRepository.save(data);


            result = commonResponseEntity.responseEntity(HttpStatus.CREATED,
                    ConstantVariables.EMP_CREATED_SUCCESSFULLY, ConstantVariables.SUCCESS_CODE);

            result.put(ConstantVariables.EMP_ID, data.getId());

            logger.debug(" employee created successfully with emp id"+data.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
            result =  exceptionMap(e.getMessage());
        }
        return result;
	}


	@Override
    public LinkedHashMap<String,Object> deleteEmployee(Long id){
        try {
            Optional<Employee> emp = employeeRepository.findById(id);
            if (!emp.isPresent())
                return commonResponseEntity.responseEntity(HttpStatus.NOT_FOUND,
                        ConstantVariables.NO_DATA_FOUND, ConstantVariables.NOT_EXIST_CODE);

            employeeRepository.deleteById(id);
            logger.debug(" employee deleted successfully");

            return commonResponseEntity.responseEntity(HttpStatus.OK,
                    ConstantVariables.EMP_DELETED_SUCCESSFULLY, ConstantVariables.SUCCESS_CODE);
        }catch(Exception e) {
            e.printStackTrace();
            return exceptionMap(e.getMessage());
        }
    }


    @Override
    public LinkedHashMap<String, Object> updateEmployee(Long empId,EmployeeUpdateDTO emp){
        try {
            Optional<Employee> data = employeeRepository.findById(empId);
            if (!data.isPresent())
                return commonResponseEntity.responseEntity(HttpStatus.NOT_FOUND,
                        ConstantVariables.NO_DATA_FOUND, ConstantVariables.NOT_EXIST_CODE);

            Employee user = data.get();

            if (emp.getAddress() != null && !emp.getAddress().trim().isEmpty()) {
                user.setAddress(emp.getAddress());
            }

            if (emp.getFirstname() != null && !emp.getFirstname().trim().isEmpty()) {
                user.setFirstName(emp.getFirstname());
            }

            if (emp.getLastname() != null && !emp.getLastname().trim().isEmpty()) {
                user.setLastName(emp.getLastname());
            }

            if (emp.getBoss() != null) {
                user.setBoss(emp.getBoss());
            }

            if (emp.getSalary() != null) {
                user.setSalary(emp.getSalary());
            }

            if (emp.getDateOfBirth() != null) {
                user.setBirthDate(emp.getDateOfBirth());
            }

            user.setUpdatedDate(new Date());

            employeeRepository.save(user);
            logger.debug("employee updated successfully with emp id"+ empId);

            return commonResponseEntity.responseEntity(HttpStatus.OK, ConstantVariables.EMP_UPDATE_SUCCESSFULLY,
                    ConstantVariables.SUCCESS_CODE);
        }catch(Exception e){
            e.printStackTrace();
            return exceptionMap(e.getMessage());
        }


    }

    @Override
    public LinkedHashMap<String,Object> getEmployee(Long id){
        try {
            Optional<Employee> emp = employeeRepository.findById(id);
            if (!emp.isPresent())
                return commonResponseEntity.responseEntity(HttpStatus.NOT_FOUND,
                        ConstantVariables.NO_DATA_FOUND, ConstantVariables.NOT_EXIST_CODE);

            LinkedHashMap<String, Object> result = commonResponseEntity.responseEntity(HttpStatus.OK,
                    ConstantVariables.DATA_FOUND, ConstantVariables.SUCCESS_CODE);

            result.put("Data", emp.get());
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return exceptionMap(e.getMessage());
        }

    }

    @Override
	public LinkedHashMap<String, Object> exceptionMap(String msg) {
		LinkedHashMap<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put(ConstantVariables.REQUEST_STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
		resultMap.put(ConstantVariables.RESPONSE_MSG, msg);
		resultMap.put(ConstantVariables.RESPONSE_CODE, ConstantVariables.EXCEPTION_CODE);
		return resultMap;
	}


	@Override
    public LinkedHashMap<String,Object> getAllEmployee(SearchCriteriaDTO data){

        Pageable p = PageRequest.of(data.getPage(),data.getSize()==0?10:data.getSize());

        Page<Employee> pageRecord = employeeRepository.findAll(p);


        LinkedHashMap<String, Object> result = commonResponseEntity.responseEntity(HttpStatus.OK,
                    ConstantVariables.DATA_FOUND, ConstantVariables.SUCCESS_CODE);
        result.put("data",pageRecord.getContent());

	    return result;
    }
}