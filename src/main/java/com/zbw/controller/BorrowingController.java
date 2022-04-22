package com.zbw.controller;

import com.zbw.domain.Vo.BorrowingBooksVo;
import com.zbw.service.IBorrowingBooksRecordService;
import com.zbw.utils.page.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@Tag(name = "借阅管理")
public class BorrowingController {

    @Resource
    private IBorrowingBooksRecordService borrowingBooksRecordService;

    /**
     * 返回所有用户借书记录页面
     *
     * @return
     */
    @RequestMapping("/allBorrowBooksRecordPage")
    @Operation(summary = "返回所有用户借书记录页面", parameters = {
            @Parameter(name = "model", description = "模型", required = true),
            @Parameter(name = "page", description = "当前页", required = true)
    })
    public String allBorrowingBooksRecordPage(Model model, @RequestParam("pageNum") int pageNum) {
        Page<BorrowingBooksVo> page = borrowingBooksRecordService.selectAllByPage(pageNum);
        model.addAttribute("page", page);
        return "admin/allBorrowingBooksRecord";
    }
}
