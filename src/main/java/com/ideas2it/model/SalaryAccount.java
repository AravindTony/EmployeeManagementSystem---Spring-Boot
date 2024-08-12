package com.ideas2it.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** 
* This class is used to get and set the 
* information about the Salary Account like
* @author Aravind 
*/

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "salaryaccount")
public class SalaryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private int accountId;

    @Column (name = "number")
    private long accountNumber;

    @Column (name = "name")
    private String bankName;

    public SalaryAccount(long accountNumber, String bankName) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }
}