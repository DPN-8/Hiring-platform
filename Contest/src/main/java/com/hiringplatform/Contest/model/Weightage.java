package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Weightage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int Wid;
    private int easy;
    private int medium;
    private int hard;
//    @OneToOne
//    @JoinColumn()
//    private Part p;
}
