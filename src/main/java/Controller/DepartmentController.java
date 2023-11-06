package Controller;

import Model.Department;
import Service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
@Autowired
DepartmentService departmentService;
@PostMapping("/add")
 public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department createdDepartment = departmentService.createDepartment(department);
        return new ResponseEntity(createdDepartment, HttpStatus.CREATED);
    }


@GetMapping
public ResponseEntity<List<Department>> getAllDepartments() {
    List<Department> departments = departmentService.getAllDepartments();
    return new ResponseEntity(departments, HttpStatus.OK);
}
@GetMapping("/departmentId")
public ResponseEntity<Department> getDepartmentById(@PathVariable String departmentId) {
    Department department = departmentService.getDepartmentById(departmentId);
    if (department != null) {
        return new ResponseEntity(department, HttpStatus.OK);
    } else {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
@PutMapping("/departmentId")
public ResponseEntity<Department> updateDepartment(@PathVariable String departmentId, @RequestBody Department department) {
    Department updatedDepartment = departmentService.updateDepartment(departmentId, department);

    if (updatedDepartment != null) {
        return new ResponseEntity(updatedDepartment, HttpStatus.OK); // 200 OK
    } else {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}


    @DeleteMapping("/departmentId")
    public void deleteDepartment(@PathVariable String departmentId){
    departmentService.deleteDepartment(departmentId);

    }
}
