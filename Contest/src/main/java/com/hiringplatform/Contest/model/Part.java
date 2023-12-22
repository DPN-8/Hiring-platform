package com.hiringplatform.Contest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.Collection;
import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Part {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Pid;
    @ManyToOne @JoinColumn(name = "contest1")
    @JsonBackReference
    private  Contest contest1;
    private String name;
    @OneToOne@JoinColumn (name = "w_id")
    private Weightage weight;
//    @OneToMany(mappedBy = "Part_id")
//    private Collection<McqQuestion> mcq1;
//    @OneToMany(mappedBy = "part7")
//    private Collection<CodeQuestion> code1;
//    @ManyToMany(mappedBy = "part")
//    private List<Weightage> weight2;
//    @ManyToMany(mappedBy = "Partques")
//    private List<NoOfQues> partnum;

    /*

     */
}
