package com.example.springbootjpamappings.controllers;

import com.example.springbootjpamappings.dtos.DepartmentDto;
import com.example.springbootjpamappings.entities.DepartmentEntity;
import com.example.springbootjpamappings.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody @Valid DepartmentEntity departmentEntity) {
        DepartmentEntity newDepartment = departmentService.createNewDepartment(departmentEntity);
        return new ResponseEntity<>(newDepartment, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable Long id) {
        DepartmentEntity department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getAllDepartment() {
        List<DepartmentEntity> allDepartment = departmentService.getAllDepartment();
        return ResponseEntity.ok(allDepartment);
    }

    @PutMapping(path = "/{departmentId}/manager/{employeeId}")
     public ResponseEntity<DepartmentEntity> assignManagerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){

        DepartmentEntity departmentEntity = departmentService.assignManagerToDepartment(departmentId, employeeId);
        return  ResponseEntity.ok(departmentEntity);
    }

    @GetMapping(path = "/assignedDepartmentOfManager/{employeeId}")
    public ResponseEntity<DepartmentEntity> getAssignedDepartmentOfManager(@PathVariable Long employeeId){
        DepartmentEntity assignedDepartmentOfManager = departmentService.getAssignedDepartmentOfManager(employeeId);
        return  ResponseEntity.ok(assignedDepartmentOfManager);
    }

    @PutMapping(path = "/{departmentId}/worker/{employeeId}")
    public ResponseEntity<DepartmentEntity> assignWorkerToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){

        DepartmentEntity departmentEntity = departmentService.assignWorkerToDepartment(departmentId, employeeId);
        return  ResponseEntity.ok(departmentEntity);
    }

    @PutMapping(path = "/{departmentId}/freelancers/{employeeId}")
    public ResponseEntity<DepartmentEntity> assignFreelancersToDepartment(@PathVariable Long departmentId,@PathVariable Long employeeId){

        DepartmentEntity departmentEntity = departmentService.assignFreelancersToDepartment(departmentId, employeeId);
        return  ResponseEntity.ok(departmentEntity);
    }

}
