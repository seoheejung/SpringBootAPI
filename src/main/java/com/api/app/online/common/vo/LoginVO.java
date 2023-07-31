package com.api.app.online.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginVO {
    @ApiModelProperty(value = "사용자 ID")
    private String userId;

    @ApiModelProperty(value = "사용자 이름")
    private String userName;
}
