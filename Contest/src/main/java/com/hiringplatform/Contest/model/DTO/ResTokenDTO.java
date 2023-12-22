package com.hiringplatform.Contest.model.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResTokenDTO {

    private String token;
    private String role;
    private int id;
}
