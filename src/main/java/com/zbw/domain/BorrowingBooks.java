package com.zbw.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "借阅书籍")
public class BorrowingBooks {
    @Schema(description = "借阅书籍id")
    private Integer id;

    @Schema(description = "借阅人id")
    private Integer userId;

    @Schema(description = "书籍id")
    private Integer bookId;

    @Schema(description = "借阅时间")
    private Date date;
}