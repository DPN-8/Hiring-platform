package com.hiringplatform.Contest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nimbusds.jose.shaded.gson.JsonObject;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Guest {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String name;
    private String password;
    private String email;
    private String Stack;
    private String mcqQues;
    private int mcqMarks;
    private String mcqAns;
    @Column(length = 3000)
    private String codeQues;
    private int codeMarks;
    private int totalMarks;
    private String userFeedback;
    private String adminFeedback;
    @ManyToOne
    @JoinColumn(name = "Contest_id")
    @JsonBackReference("contest")
    private Contest contest;
    @ManyToOne
    @JoinColumn(name = "Employee_id")
    @JsonBackReference("employee")
    private Employee employee;
    private Role role;

}
