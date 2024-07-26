package com.example.springbootjpamappings.services;

;
import com.example.springbootjpamappings.dtos.DepartmentDto;
import com.example.springbootjpamappings.entities.DepartmentEntity;
import com.example.springbootjpamappings.entities.EmployeeEntity;
import com.example.springbootjpamappings.exception.ResourceNotFoundException;
import com.example.springbootjpamappings.repositories.DepartmentRepository;
import com.example.springbootjpamappings.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;


    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }

    public DepartmentEntity createNewDepartment(DepartmentEntity departmentEntity) {
        DepartmentEntity department = departmentRepository.save(departmentEntity);
        return department;
    }

    public DepartmentEntity getDepartmentById(Long departmentId) {
        Optional<DepartmentEntity> departmentEntityOptional = departmentRepository.findById(departmentId);

        isExistsByDepartmentId(departmentId);
        return departmentEntityOptional.get();

    }

    public List<DepartmentEntity> getAllDepartment() {
        List<DepartmentEntity> departmentEntities = departmentRepository.findAll();
        return departmentEntities;
    }

    public DepartmentDto updateDepartmentById(Long departmentId, DepartmentDto departmentDto) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = modelMapper.map(departmentDto, DepartmentEntity.class);
        departmentEntity.setId(departmentId);
        DepartmentEntity updatedDepartment = departmentRepository.save(departmentEntity);
        return modelMapper.map(updatedDepartment, DepartmentDto.class);
    }

    public Boolean deleteDepartmentById(Long departmentId) {
        isExistsByDepartmentId(departmentId);
        departmentRepository.deleteById(departmentId);
        return true;
    }

    public DepartmentDto updatePartialDepartmentById(Long departmentId, Map<String, Object> updatedData) {
        isExistsByDepartmentId(departmentId);
        DepartmentEntity departmentEntity = departmentRepository.findById(departmentId).get();
        updatedData.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(DepartmentEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, departmentEntity, value);
        });

        return modelMapper.map(departmentEntity, DepartmentDto.class);
    }

    public DepartmentEntity assignManagerToDepartment(Long departmentId,Long employeeId){

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(departmentEntity1 ->
                employeeEntity.map(employeeEntity1 -> {
                    departmentEntity1.setManager(employeeEntity1);
                    return departmentRepository.save(departmentEntity1);
                })).orElse(null);

    }

    public DepartmentEntity getAssignedDepartmentOfManager(Long employeeId){

        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
//        return  employeeEntity.map(employeeEntity1 ->
//                employeeEntity1.getManagedDepartment()).orElse(null);

        EmployeeEntity employee=EmployeeEntity.builder().id(employeeId).build();

        return departmentRepository.findByManager(employee);

    }

    public DepartmentEntity assignWorkerToDepartment(Long departmentId,Long employeeId){

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(departmentEntity1 ->
                employeeEntity.map(employeeEntity1 -> {
                    employeeEntity1.setWorkerDepartment(departmentEntity1);
                    employeeRepository.save(employeeEntity1);
                    departmentEntity1.getWorkers().add(employeeEntity1);
                    return departmentEntity1;
                })).orElse(null);

    }

    public DepartmentEntity assignFreelancersToDepartment(Long departmentId,Long employeeId){

        Optional<DepartmentEntity> departmentEntity = departmentRepository.findById(departmentId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        return departmentEntity.flatMap(departmentEntity1 ->
                employeeEntity.map(employeeEntity1 -> {
                    employeeEntity1.getFreelanceDepartments().add(departmentEntity1);
                    employeeRepository.save(employeeEntity1);
                    departmentEntity1.getFreelancers().add(employeeEntity1);
                    return departmentEntity1;
                })).orElse(null);

    }

    public void isExistsByDepartmentId(Long departmentId) {
        boolean exists = departmentRepository.existsById(departmentId);
        if (!exists) {
            throw new ResourceNotFoundException("Department not found with id :" + departmentId);
        }
    }
}
