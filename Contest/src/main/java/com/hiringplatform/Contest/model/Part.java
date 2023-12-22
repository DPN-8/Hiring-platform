package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Part {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Pid;
    private String name;
    @ManyToMany(mappedBy = "part1")
    private List<Marks> markpart;
    @ManyToMany(mappedBy = "partques")
    private List<NoOfQues> partnum;
}
