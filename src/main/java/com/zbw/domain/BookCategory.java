package com.zbw.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "书的分类")
public class BookCategory {
    @Schema(description = "书的分类id" +
            "")
    private Integer categoryId;

    @Schema(description = "书的分类")
    private String categoryName;
}