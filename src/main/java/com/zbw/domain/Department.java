package com.zbw.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "学院")
public class Department {
    @Schema(description = "学院id")
    private Integer deptId;

    @Schema(description = "学院名称")
    private String deptName;
}