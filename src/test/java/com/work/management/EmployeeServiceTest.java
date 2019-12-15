package com.work.management;

import static org.junit.Assert.assertEquals;

import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.service.employee.impl.EmployeeServiceImpl;
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
  private EmployeeServiceImpl employeeService;

  private static final Employee employee = Employee.builder().firstName("Mudit").lastName("Tanwar")
      .userName("Mudit" + "." + "Tanwar").teamId("TRM").phoneNumber("9654092992").manager(3)
      .teamId("4fbb7b55-9827-452b-ad22-24692b3476d9").build();

  @Test
  public void getEmployeeByUserName_forExistingEmployee_returnsEmployee() {

    EmployeeDto expectedEmployeeDto = convert(employee);
    employeeService.save(expectedEmployeeDto);

    EmployeeDto actualEmployeeDto = employeeService
        .getEmployeeByUserName(expectedEmployeeDto.getUserName());

    assertEquals(expectedEmployeeDto, actualEmployeeDto);
  }

  private static EmployeeDto convert(Employee employee) {
    EmployeeDto expectedEmployeeDto = new EmployeeDto();
    BeanUtils.copyProperties(employee, expectedEmployeeDto);
    return expectedEmployeeDto;
  }
}

