package com.work.management;

import static org.junit.Assert.assertEquals;

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

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public final class EmployeeServiceTest {

  @Resource
  private EmployeeService employeeService;
  private static final Employee EMPLOYEE = getEmployee();

  @Test
  public void getEmployeeByUserName_forExistingEmployee_returnsEmployee() {
    EmployeeDto employeeDto = getEmployeeDto(EMPLOYEE);
    employeeService.save(employeeDto);

    EmployeeDto actualEmployeeDto = employeeService
        .getEmployeeByUserName(employeeDto.getUserName());

    assertEquals(employeeDto, actualEmployeeDto);
  }

  private static Employee getEmployee() {
    return Employee.builder().firstName("Mudit").lastName("Tanwar").userName("Mudit.Tanwar")
        .teamId("c7f6fbab-22fb-41bc-9300-0cc27c0de5c5")
        .phoneNumber("9654092992").manager(3)
        .build();
  }

  private static EmployeeDto getEmployeeDto(final Employee employee) {
    EmployeeDto employeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, employeeDto);
    return employeeDto;
  }
}

