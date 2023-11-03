package com.ssafy.tripoline.member.model.dto;

import lombok.Data;

@Data
public class FindRequest {
    private String memberId;
    private String memberPhone;
}
