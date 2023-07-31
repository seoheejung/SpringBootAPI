package com.api.app.online.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/* 기본 응답 데이터 객체 */
public class DefaultResponse {
    /* 응답 코드 */
    private Integer code;
    /* 응답 메세지 */
    private String msg;
    /* 응답 데이터 */
    private Object result;
}
