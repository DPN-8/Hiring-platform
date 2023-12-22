package com.hiringplatform.Contest.model;

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
    @ManyToOne
    @JoinColumn(name = "Contest_id")
    private Contest cid;
    @ManyToMany(mappedBy = "guest1")
    private List<Marks> markguest;
}
