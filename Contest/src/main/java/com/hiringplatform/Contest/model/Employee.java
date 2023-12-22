package com.hiringplatform.Contest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Employee {
    @Id
    private int Eid;
    private String name;
    private String password;
    private String email;
    private String expertise;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @JsonManagedReference("employee")
    private List<Guest> guest2;

    @Enumerated(EnumType.STRING)
    private Role role;

}
