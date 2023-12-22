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

public class NoOfQues {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int temp;
    @ManyToMany
    @JoinTable(name = "Contest_Question",
            joinColumns = @JoinColumn(name = "Question"),
            inverseJoinColumns = @JoinColumn(name = "Cid"))
    private List<Contest> Contestques;
    @ManyToMany
    @JoinTable(name = "Part_Question",
            joinColumns = @JoinColumn(name = "Question"),
            inverseJoinColumns = @JoinColumn(name = "Pid"))
    private List<Part> Partques;
    private int easy;
    private int medium;
    private int hard;
}
