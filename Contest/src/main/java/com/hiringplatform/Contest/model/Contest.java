package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
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
    @ManyToMany(mappedBy = "contest1")
    private List<Marks> markcontest;
    @ManyToMany(mappedBy = "contestques")
    private List<NoOfQues> contestnum;
}
