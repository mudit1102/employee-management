package com.work.management.service.employee.impl;

import com.work.management.dto.EmployeeDto;
import com.work.management.entity.Employee;
import com.work.management.repository.EmployeeRepository;
import com.work.management.service.employee.EmployeeService;
import com.work.management.utils.ExceptionUtils;
import com.work.management.web.rest.resource.EmployeeResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
final class EmployeeServiceImpl implements EmployeeService<EmployeeDto, Employee> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);
    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(EmployeeDto employeeDto) {

        LOGGER.info("Saving employee{} ", employeeDto.getFirstName() + "." + employeeDto.getLastName());
        if (Objects.nonNull(employeeRepository.findByPhoneNumber(employeeDto.getPhoneNumber()))) {
            ExceptionUtils.throwEntityAlreadyExistsException("Employee already exists, choose a different phone Number");
            return;
        }

        Employee employee = Employee.builder().firstName(employeeDto.getFirstName()).lastName(employeeDto.getLastName()).manager(employeeDto.getManager())
                .phoneNumber(employeeDto.getPhoneNumber()).createdBy(employeeDto.getManager()).lastUpdatedBy(employeeDto.getManager())
                .userName(employeeDto.getUserName()).teamId(employeeDto.getTeamId()).build();

        employeeRepository.save(employee);
        LOGGER.info("Saved employee{} ", employeeDto.getFirstName() + "." + employeeDto.getLastName());
        employeeDto.setId(employee.getId());
    }
}
