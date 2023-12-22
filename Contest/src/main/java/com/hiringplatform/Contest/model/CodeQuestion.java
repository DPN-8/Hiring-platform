package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;
import net.minidev.json.JSONObject;

import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class CodeQuestion {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String Qid;
    @Column(length = 3000)
    private String question;
    private String input;
    private  String output;
    private String weightage;
}
