package com.work.management.service.employee.impl;

import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.service.employee.EmployeeService;
import com.work.management.utils.ExceptionUtils;
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
}
