package me.abs0luty.school.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@AllArgsConstructor
@Data
public class UserDataResponse {

    private String email;
    private Date createdAt;
}
