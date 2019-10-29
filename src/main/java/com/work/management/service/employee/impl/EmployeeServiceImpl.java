package com.work.management.service.employee.impl;

import com.work.management.dto.BulkEmployeeDto;
import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.service.employee.EmployeeService;
import com.work.management.utils.ExceptionUtils;
import com.work.management.web.rest.resource.AcceptedFields;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
class EmployeeServiceImpl implements EmployeeService {

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
  @Transactional
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
  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public List<Employee> bulkUpdate(BulkEmployeeDto bulkEmployeeDto) {
    final Map<AcceptedFields, String> acceptedFieldsValueMap = bulkEmployeeDto
        .getAcceptedFieldsValueMap();

    return bulkEmployeeDto.getEmployeeId().stream()
        .map(employeeRepository::findById).map(employee -> {
          for (Entry<AcceptedFields, String> entry : acceptedFieldsValueMap.entrySet()) {

            switch (entry.getKey()) {
              case MANAGER_ID:
                employee.get().setManager(Integer.parseInt(entry.getValue()));
                break;
              case TEAM_ID:
                employee.get().setTeamId(entry.getValue());
            }
          }
          employeeRepository.save(employee.get());
          return employee;
        }).map(employee -> employee.get()).collect(Collectors.collectingAndThen(Collectors.toList(),
            Collections::unmodifiableList));

  }

}

