package com.binar.challenge4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String email;
    private String password;
//
//    @OneToMany(mappedBy = "user")
//    @JsonIgnore
//    private List<Invoice> invoice = new LinkedList<>();
}
