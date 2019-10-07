package com.work.management.service.employee.impl;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.BulkEmployeeDtoResp;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.service.employee.EmployeeService;
import com.work.management.utils.ExceptionUtils;
import com.work.management.web.rest.resource.AcceptedFields;
import com.work.management.web.rest.resource.EmployeeResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
final class EmployeeServiceImpl implements EmployeeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
  private final EmployeeRepository employeeRepository;

  @Autowired
  EmployeeServiceImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public EmployeeDto save(EmployeeDto employeeDto) {
    if (employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber()).isPresent()) {
      ExceptionUtils.throwEntityAlreadyExistsException(
          "Employee already exists, choose a different phone Number");
    }

    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDto, employee);

    employeeRepository.save(employee);

    BeanUtils.copyProperties(employee, employeeDto);
    return employeeDto;
  }

  @Override
  public EmployeeDto getEmployeeByUserName(String username) {
    Optional<Employee> employee = employeeRepository.findByUserName(username);
    if (!employee.isPresent()) {
      ExceptionUtils
          .throwEntityNotFoundException(
              String.format("Employee with UserName %s doesn't exists.", username));
    }

    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee.get(), employeeDto);
    return employeeDto;
  }

  @Override
  public EmployeeDto updateEmployeeEntity(EmployeeDto employeeDto) {
    Optional<Employee> employee = employeeRepository.findById(employeeDto.getId());
    if (!employee.isPresent()) {
      ExceptionUtils
          .throwEntityNotFoundException(
              String
                  .format("Employee with Id %d doesn't exists.", employeeDto.getId()));
    }

    Employee oldEmployee = employee.get();
    if (!Objects.isNull(employeeDto.getUserName()) && !employeeDto.getUserName()
        .equals(oldEmployee.getUserName()) || !Objects.isNull(employeeDto.getId()) && !employeeDto
        .getId()
        .equals(oldEmployee.getId())) {
      ExceptionUtils.throwBadRequestException("Cannot update username or id");
    }
    Employee newEmployee = new Employee();
    BeanUtils.copyProperties(employeeDto, newEmployee);
    newEmployee.setUserName(oldEmployee.getUserName());
    newEmployee.setId(oldEmployee.getId());

    employeeRepository.save(newEmployee);

    EmployeeDto newEmployeeDto = new EmployeeDto();
    BeanUtils.copyProperties(newEmployee, newEmployeeDto);
    return newEmployeeDto;
  }

  @Override
  public BulkEmployeeDtoResp bulkUpdate(BulkEmployeeDto bulkEmployeeDto) {
    //list of employees having updated details
    List<Employee> newEmployeeList = new ArrayList<>();
    BulkEmployeeDtoResp bulkEmployeeDtoResp = new BulkEmployeeDtoResp(new ArrayList<>());
    for (Integer empId : bulkEmployeeDto.getEmployeeId()) {

      Optional<Employee> oldEmployee = employeeRepository.findById(empId);
      if (!oldEmployee.isPresent()) {
        ExceptionUtils
            .throwBadRequestException(String.format("Employee with id %d doesn't exist", empId));
      }
      Employee newEmployee = new Employee();
      BeanUtils.copyProperties(oldEmployee.get(), newEmployee);
      for (Entry<AcceptedFields, String> entry : bulkEmployeeDto.getAcceptedFieldsValueMap()
          .entrySet()) {
        if (entry.getKey().getDataType().equals("INTEGER")) {
          newEmployee.setManager(Integer.parseInt(entry.getValue()));
        } else {
          newEmployee.setTeamId(entry.getValue());
        }
      }
      newEmployeeList.add(newEmployee);
    }
    //saving to db
    for (int i = 0; i < newEmployeeList.size(); i++) {
      employeeRepository.save(newEmployeeList.get(i));
    }
    //building response for bulk update query
    for (int i = 0; i < newEmployeeList.size(); i++) {
      EmployeeResource employeeResource = new EmployeeResource();
      BeanUtils.copyProperties(newEmployeeList.get(i), employeeResource);
      bulkEmployeeDtoResp.getEmployeeResourceList().add(employeeResource);
    }
    //return bulk update response
    return bulkEmployeeDtoResp;
  }
}

