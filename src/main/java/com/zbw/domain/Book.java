package com.zbw.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "书实体类")
public class Book {
    @Schema(description = "书 id")
    private Integer bookId;

    @Schema(description = "书的名称")
    private String bookName;

    @Schema(description = "书的作者")
    private String bookAuthor;

    @Schema(description = "书的发行时间")
    private String bookPublish;

    @Schema(description = "书的分类")
    private Integer bookCategory;

    @Schema(description = "书的价格")
    private Double bookPrice;

    @Schema(description = "书的介绍")
    private String bookIntroduction;

}