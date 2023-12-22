package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CodeQuestion {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String Qid;
    private String question;
    private String input;
    private  String output;
    @ManyToOne @JoinColumn(name = "Pid")
    private Part Pid;
    @Enumerated(EnumType.STRING)
    private Weightage weightage;
}
