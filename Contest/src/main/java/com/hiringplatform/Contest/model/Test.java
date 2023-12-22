package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int temp;
    @ManyToMany
    @JoinTable(name = "Guest_Test",
            joinColumns = @JoinColumn(name = "test"),
            inverseJoinColumns = @JoinColumn(name = "Gid"))
    private List<Guest> Gid;
    @ManyToMany
    @JoinTable(name = "Contest_Test",
            joinColumns = @JoinColumn(name = "test"),
            inverseJoinColumns = @JoinColumn(name = "Cid"))
    private List<Contest> Cid;
    private String McqQuestion;
    private String CodeQuestion;
    private  int score;

}
