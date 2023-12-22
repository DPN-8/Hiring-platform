package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Eid;
    private String name;
    private String password;
    private String email;
    private String expertise;
    @OneToMany @JoinColumn(name = "Eid")
    private List<Guest> Gid;
}
