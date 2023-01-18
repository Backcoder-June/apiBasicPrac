package itcen.backapi.rdbjpa.entity;

import itcen.backapi.rdbjpa.repository.DepartmentRepository;
import itcen.backapi.rdbjpa.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    void insertDummy() {
        Department dept1 = Department.builder()
                .deptName("상품기획부")
                .build();
        Department dept2 = Department.builder()
                .deptName("영업부")
                .build();
        Employee emp1 = Employee.builder()
                .empName("뉴사원")
                .department(dept1)
                .build();
        Employee emp2 = Employee.builder()
                .empName("헬사원")
                .department(dept1)
                .build();

        departmentRepository.save(dept1);
        departmentRepository.save(dept2);

        employeeRepository.save(emp1);
        employeeRepository.save(emp2);
    }


    @Test
    @Transactional
    void empTest1() {

        Employee employee = employeeRepository.findById(2L)
                .orElseThrow();

        // join 없이 emplyee 만 ( LAZY 적용 )
//        System.out.println("foundOne : " + employee.getEmpName());
        // join 해서 다 가져옴
        System.out.println("사원의 depart name 가져오기 : " + employee.getDepartment().getDeptName());
    }



    // 양방향 테스트
    @Test
    @Transactional
    void empTest3() {
        Department dept
                = departmentRepository.findById(1L)
                .orElseThrow();
//        List<Employee> employees = dept.getEmployee();
//        employees.forEach(System.out::println);
    }












}