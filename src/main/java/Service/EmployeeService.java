package Service;

import Model.Employee;
import Repository.EmployeeRepository;
import exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);

    }
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }


    public Employee getEmployeeById(Long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new EmployeeNotFoundException(employeeId);
        }
        return optionalEmployee.get();
    }

//update employee by there id
    public Employee updateEmployee(Long employeeId,Employee updatedEmployee) {
        // Check if the employee with the given employeeId exists
        Employee existingEmployee = employeeRepository.findById(employeeId)
                .orElse(null);
        if (existingEmployee != null) {
            // Update the fields of the existing employee with the new values
            existingEmployee.setFirstName(updatedEmployee.getFirstName());
            existingEmployee.setLastName(updatedEmployee.getLastName());
            existingEmployee.setEmail(updatedEmployee.getEmail());
            existingEmployee.setDepartmentId(updatedEmployee.getDepartmentId());
            existingEmployee.setCreatedAt(updatedEmployee.getCreatedAt());
            existingEmployee.setUpdatedAt(updatedEmployee.getUpdatedAt());
            //save updated employee
            return employeeRepository.save(existingEmployee);
        }
        {
           return null;
        }


    }
    //delete employee
    public void deleteEmployee(Long employeeId) {
     employeeRepository.deleteById(employeeId);
    }
}
