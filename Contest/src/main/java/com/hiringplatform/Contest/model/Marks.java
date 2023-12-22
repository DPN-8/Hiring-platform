package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Marks {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int temp;
    @ManyToMany
    @JoinTable(name = "Guest_Mark",
            joinColumns = @JoinColumn(name = "mark1"),
            inverseJoinColumns = @JoinColumn(name = "Gid"))
        private List<Guest> guest1;
    @ManyToMany
    @JoinTable(name = "Contest_Mark",
            joinColumns = @JoinColumn(name = "mark1"),
            inverseJoinColumns = @JoinColumn(name = "Cid"))
    private List<Contest> contest1;
    @ManyToMany
    @JoinTable(name = "Part_Mark",
            joinColumns = @JoinColumn(name = "mark1"),
            inverseJoinColumns = @JoinColumn(name = "Pid"))
    private List<Part> part1;
    private int mark;

}
