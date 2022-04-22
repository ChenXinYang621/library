package com.zbw.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "管理员")
public class Admin {
    @Schema(description = "管理员id")
    private Integer adminId;

    @Schema(description = "管理员名称")
    private String adminName;

    @Schema(description = "管理员密码")
    private String adminPwd;

    @Schema(description = "管理员角色")
    private String adminEmail;
}