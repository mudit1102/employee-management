package com.work.management;

import static org.junit.Assert.assertEquals;

import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.service.employee.EmployeeService;
import com.work.management.web.rest.assembler.EmployeeAssembler;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    EmployeeDto employeeDto = EmployeeAssembler.convert(EMPLOYEE);
    employeeService.save(employeeDto);
    EmployeeDto actualEmployeeDto = employeeService
        .getEmployeeByUserName(employeeDto.getUserName());

    assertEquals(employeeDto, actualEmployeeDto);
  }

  private static final Employee getEmployee() {
    StringBuilder userName = new StringBuilder();
    Employee employee = Employee.builder().firstName("Mudit").lastName("Tanwar")
        .teamId("TRM").phoneNumber("9654092992").manager(3)
        .build();
    userName.append(employee.getFirstName());
    userName.append(".");
    userName.append(employee.getLastName());
    employee.setUserName(userName.toString());
    return employee;
  }
}

