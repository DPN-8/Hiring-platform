package com.hiringplatform.Contest.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Contest {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Cid;
    private String name;
    private Timestamp startTime;
    private Timestamp endTime;
    private String details;
    private String rules;
    private String scoreRules;
    private int totalScore;
    private int passPercentage;
    @OneToMany(mappedBy = "contest")
    @JsonManagedReference("contest")
    private List<Guest> guest1;
    @OneToMany(mappedBy = "contest1")
    @JsonManagedReference
    private List<Part> part1;
}
