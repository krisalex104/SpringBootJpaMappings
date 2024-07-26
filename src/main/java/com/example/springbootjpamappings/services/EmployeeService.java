package com.example.springbootjpamappings.services;


import com.example.springbootjpamappings.dtos.EmployeeDto;
import com.example.springbootjpamappings.entities.EmployeeEntity;
import com.example.springbootjpamappings.exception.ResourceNotFoundException;
import com.example.springbootjpamappings.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(id);
//        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDto.class));
//
        return employeeRepository.findById(id).map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDto.class));
    }

    public List<EmployeeEntity> getAllEmployee() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        return employeeEntityList;
    }

    public EmployeeEntity saveEmployeeDetails(EmployeeEntity employeeEntity) {
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        isExistsByEmployeeId(id);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDto, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity employee = employeeRepository.save(employeeEntity);
        return modelMapper.map(employee, EmployeeDto.class);

    }

    public boolean deleteEmployeeById(Long id) {

        isExistsByEmployeeId(id);
        employeeRepository.deleteById(id);
        return true;
    }


    public EmployeeDto updatePartialEmployeeById(Map<String, Object> updates, Long employeeId) {
        isExistsByEmployeeId(employeeId);

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);

        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDto.class);
    }

    public void isExistsByEmployeeId(Long employeeId) {
        boolean exists = employeeRepository.existsById(employeeId);
        if (!exists) throw new ResourceNotFoundException("employee not found with id :" + employeeId);
    }

}
