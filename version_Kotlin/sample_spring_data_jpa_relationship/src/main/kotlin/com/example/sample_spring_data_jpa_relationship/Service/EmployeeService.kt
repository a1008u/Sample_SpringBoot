package com.example.sample_spring_data_jpa_relationship.Service

import com.example.sample_spring_data_jpa_relationship.domain.Department
import com.example.sample_spring_data_jpa_relationship.domain.Employee
import com.example.sample_spring_data_jpa_relationship.domain.EmployeeDto
import com.example.sample_spring_data_jpa_relationship.repository.DepartmentRepository
import com.example.sample_spring_data_jpa_relationship.repository.EmployeeRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class EmployeeService(
        private val employeeRepository: EmployeeRepository
        , private val departmentRepository: DepartmentRepository) {

     fun getemployeementList() = requireNotNull(employeeRepository.findAll())
     fun getdepartmentList() = requireNotNull(departmentRepository.findAll())

    fun getemployeement(id: Int) = requireNotNull(employeeRepository.findOne(id))
    fun getdepartment(id: Int) = requireNotNull(departmentRepository.findOne(id))

//    fun getEmployeeList(employee: Employee): List<Employee> {
//        return employeeRepository.findAll(Specifications
//                .where(EmployeeSpecification.firstName(employee.getFirstName()))
//                .and(EmployeeSpecification.lastName(employee.getLastName()))
//                .and(EmployeeSpecification.sex(employee.getSex()))
//                .and(EmployeeSpecification.birthdayEquals(employee.getBirthday()))
//                .and(EmployeeSpecification.mailAddress(employee.getMailAddress()))
//                .and(EmployeeSpecification.department(employee.getDepartment())))
//    }

    @Transactional
    fun saveEmployee(employeeDto: EmployeeDto): Department {

        val dep =requireNotNull(departmentRepository.findOne(employeeDto.departmentCode))

        val emp =EmployeeDto.fromDto(employeeDto, dep)

        dep.employee = arrayListOf(emp)

        return departmentRepository.save(dep)
    }

    @Transactional
    fun updateEmployee(targetFirstName:String, employeeDtoList: List<Employee>) : Int{
        val countI = employeeDtoList
                .map { e -> employeeRepository.updateFirstName(targetFirstName, e.firstName)}
                .count()
        employeeRepository.flush()
        return countI
    }

    @Transactional
    fun deleteEmployee(no: Int) = employeeRepository.delete(no)
}