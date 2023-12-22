package com.hiringplatform.Contest.model;

import jakarta.persistence.*;
import lombok.*;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class McqQuestion {
   @Id
//   @GeneratedValue(strategy = GenerationType.SEQUENCE)
   private String Qid;
   @Column(length = 3000)
   private String question;
   private String option1;
   private String option2;
   private String option3;
   private String option4;
   private String correctOp;
   private String part;
   private String weightage;
}
