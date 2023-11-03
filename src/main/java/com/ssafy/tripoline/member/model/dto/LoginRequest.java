package com.ssafy.tripoline.member.model.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String memberId;
    private String memberPassword;
}
