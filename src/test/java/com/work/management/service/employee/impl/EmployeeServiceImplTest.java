package com.work.management.service.employee.impl;


import static com.google.common.truth.Truth.assertThat;

import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.service.employee.EmployeeService;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public final class EmployeeServiceImplTest {

  private static final Employee EMPLOYEE = getEmployee();

  @Resource
  private EmployeeService employeeService;

  @Test
  public void getEmployeeByUserName_forExistingEmployee_returnsEmployee() {
    EmployeeDto employeeDto = getEmployeeDto(EMPLOYEE);
    employeeService.save(employeeDto);

    EmployeeDto actualEmployeeDto = employeeService
        .getEmployeeByUserName(employeeDto.getUserName());

    assertThat(actualEmployeeDto).isEqualTo(employeeDto);
  }

  @Test
  public void updateEmployeeEntity_forExistingEmployee_returnsUpdatedEmployee() {
    EmployeeDto employeeDto = getEmployeeDto(EMPLOYEE);
    employeeService.save(employeeDto);

    employeeDto.setPhoneNumber("9999745902");
    EmployeeDto actualEmployeeDto = employeeService.updateEmployeeEntity(employeeDto);

    assertThat(actualEmployeeDto).isEqualTo(employeeDto);
  }

  private static EmployeeDto getEmployeeDto(Employee employee) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, employeeDto);
    return employeeDto;
  }

  private static Employee getEmployee() {
    return Employee.builder().firstName("Mudit").lastName("Tanwar").userName("Mudit.Tanwar")
        .teamId("c7f6fbab-22fb-41bc-9300-0cc27c0de5c5")
        .phoneNumber("9654092992").manager(3)
        .build();
  }
}

