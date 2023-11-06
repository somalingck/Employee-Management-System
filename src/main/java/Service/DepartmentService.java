package Service;

import Model.Department;
import Repository.DepartmentRepository;
import exception.DepartmentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    public Department createDepartment(Department department) {


        return departmentRepository.save(department);
    }

    public Department getDepartmentById(String departmentId) {
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        if (optionalDepartment.isEmpty()) {
            throw new DepartmentNotFoundException(departmentId);
        }
        return optionalDepartment.get();
    }

    public List<Department> getAllDepartments() {

        return departmentRepository.findAll();
    }




    public Department updateDepartment(String departmentId, Department updatedDepartment) {
        // Check if the department with the given departmentId exists
        Department existingDepartment = departmentRepository.findById(departmentId)
                .orElse(null);

        if (existingDepartment != null) {
            // Update the fields of the existing department with the new values
            existingDepartment.setDepartmentName(updatedDepartment.getDepartmentName());
            existingDepartment.setCreatedAt(updatedDepartment.getCreatedAt());
            existingDepartment.setUpdatedAt(updatedDepartment.getUpdatedAt());

            // Save the updated department
            return departmentRepository.save(existingDepartment);
        } else {
            // Handle the case when the department is not found
            return null;
        }
    }


    public void deleteDepartment(String departmentId) {

        departmentRepository.deleteById(departmentId);
    }
}
