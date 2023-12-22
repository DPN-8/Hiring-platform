package com.hiringplatform.Contest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity @Data @Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Admin {
    @Id
    private String email;
    private String password;
}
