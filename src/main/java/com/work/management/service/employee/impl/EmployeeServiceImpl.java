package com.work.management.service.employee.impl;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.service.employee.EmployeeService;
import com.work.management.utils.ExceptionUtils;
import com.work.management.web.rest.resource.AcceptedFields;
import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
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

  private String[] getNullPropertyByNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    PropertyDescriptor[] propertyDescriptors = src.getPropertyDescriptors();

    Set<String> emptyFields = new HashSet<>();
    for (PropertyDescriptor pds : propertyDescriptors) {
      Object srcValue = src.getPropertyValue(pds.getName());
      if (srcValue == null) {
        emptyFields.add(pds.getName());
      }
    }
    String[] result = new String[emptyFields.size()];
    return emptyFields.toArray(result);

  }

  @Override
  public void save(EmployeeDto employeeDto) {
    if (employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber()).isPresent()) {
      ExceptionUtils.throwEntityAlreadyExistsException(
          "Employee already exists, choose a different phone Number");
      return;
    }

    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDto, employee);
    employee.setUserName(employeeDto.getFirstName() + "." + employeeDto.getLastName());

    employeeRepository.save(employee);
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

    //Updating employee resource
    BeanUtils.copyProperties(employeeDto, employee.get(), getNullPropertyByNames(employeeDto));
    employeeRepository.save(employee.get());

    //Sending employee resource db view
    BeanUtils.copyProperties(employee.get(), employeeDto);
    return employeeDto;
  }

  @Override
  public void bulkEmployeeUpdate(BulkEmployeeDto bulkEmployeeDto) {

    Map<AcceptedFields, String> acceptedFieldsValueMap = bulkEmployeeDto
        .getAcceptedFieldsValueMap();

    Integer managerId = Integer
        .parseInt(bulkEmployeeDto.getAcceptedFieldsValueMap().get(AcceptedFields.MANAGER_ID));
    String teamId = bulkEmployeeDto.getAcceptedFieldsValueMap().get(AcceptedFields.TEAM_ID);
    bulkEmployeeDto.getEmployeeId().forEach(empId -> {
      Employee employee = employeeRepository.findById(empId).get();
      employee.setManager(managerId);
      employee.setTeamId(teamId);
      employeeRepository.save(employee);
    });

  }


}
