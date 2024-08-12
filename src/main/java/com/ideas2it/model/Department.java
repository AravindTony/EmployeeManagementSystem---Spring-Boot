package com.ideas2it.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;
/** 
* This class is used to get and set the 
* information about the Department like
* Department id for Unique Departments
* Department Name and List of Employees in the Department
* @author Aravind 
*/

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int departmentId;

    @Column(name = "name")
    private String departmentName;

    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Employee> employees;

    /**
     * This Constructor Initialize a new name and list
     * of Employees while add the new Department
     *
     * @param departmentName - Name of the Department
     */
    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    public Department() { }
}