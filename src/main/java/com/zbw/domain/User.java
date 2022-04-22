package com.zbw.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "用户信息")
public class User {
    @Schema(description = "用户 id")
    private Integer userId;

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "密码")
    private String userPwd;

    @Schema(description = "邮箱")
    private String userEmail;
}