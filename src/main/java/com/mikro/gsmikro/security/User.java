package com.mikro.gsmikro.security;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long id;

    @Column(name = "emailaddress")
    private String emailAddress;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "balance")
    private double balance;

    @Column(name = "fixeddeposits")
    private double fixeddeposits;

    @Column(name = "investments")
    private double investments;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column(name = "password")
    private String password;

    @Column(name = "tempinputrole")
    private String tempInputRole;

    @Column(name = "lastlogin")
    private LocalDateTime lastLogin;

    // Many to Many join with Roles in user_role
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getFixeddeposits() {
        return fixeddeposits;
    }

    public void setFixeddeposits(double fixeddeposits) {
        this.fixeddeposits = fixeddeposits;
    }

    public double getInvestments() {
        return investments;
    }

    public void setInvestments(double investments) {
        this.investments = investments;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTempInputRole() {
        return tempInputRole;
    }

    public void setTempInputRole(String tempInputRole) {
        this.tempInputRole = tempInputRole;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", balance='" + balance + '\'' +
                ", fixeddeposits='" + fixeddeposits + '\'' +
                ", investments='" + investments + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", password='" + password + '\'' +
                ", tempInputRole='" + tempInputRole + '\'' +
                ", lastLogin=" + lastLogin +
                ", roles=" + roles +
                '}';
    }
}
