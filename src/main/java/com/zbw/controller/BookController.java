package com.zbw.controller;

import com.zbw.domain.Book;
import com.zbw.domain.BookCategory;
import com.zbw.domain.Vo.BookVo;
import com.zbw.service.IAdminService;
import com.zbw.service.IBookCategoryService;
import com.zbw.service.IBookService;
import com.zbw.utils.page.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@Tag(name = "图书管理")
public class BookController {
    @Resource
    private IAdminService adminService;
    @Resource
    private IBookService bookService;
    @Resource
    private IBookCategoryService bookCategoryService;

    /**
     * 管理员&emsp;&emsp;录入新书
     *
     * @param book
     * @return
     */
    @RequestMapping("/addBook")
    @ResponseBody
    @Operation(summary = "录入新书", description = "管理员录入新书", parameters = {
            @Parameter(name = "book", description = "书籍信息", required = true)
    })
    public String addBook(Book book) {
        boolean res = adminService.addBook(book);
        if (res) {
            return "true";
        }
        return "false";
    }

    /**
     * 返回&emsp;&emsp;查询书籍结果页
     *
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping("/showBooksResultPageByCategoryId")
    @Operation(summary = "查询书籍结果页", description = "查询书籍结果页", parameters = {
            @Parameter(name = "pageNum", description = "页码", required = true)
    })
    public String showBooksResultPageByCategoryId(@RequestParam("pageNum") int pageNum, @RequestParam("bookCategory") int bookCategory, Model model) {
        Page<BookVo> page = bookService.findBooksByCategoryId(bookCategory, pageNum);
        model.addAttribute("page", page);
        model.addAttribute("bookCategory", bookCategory);
        return "admin/showBooks";
    }

    /**
     * 返回用户&emsp;&emsp;查询书籍结果页
     *
     * @param bookPartInfo
     * @return
     */
    @RequestMapping("/findBookByBookPartInfo")
    @Operation(summary = "查询书籍结果页", description = "查询书籍结果页", parameters = {
            @Parameter(name = "bookPartInfo", description = "书籍部分信息", required = true)
    })
    public String findBooksResultPage(@RequestParam("bookPartInfo") String bookPartInfo, Model model) {

        List<BookVo> bookVos = bookService.selectBooksByBookPartInfo(bookPartInfo);

        model.addAttribute("bookList", bookVos);
        return "user/findBook";
    }

    /**
     * 查询所有书籍种类
     *
     * @return
     */
    @RequestMapping("/findAllBookCategory")
    @ResponseBody
    @Operation(summary = "查询所有书籍种类", description = "查询所有书籍种类")
    public List<BookCategory> findAllBookCategory() {
        return adminService.getBookCategories();
    }

    /**
     * 新建书籍种类
     *
     * @param bookCategory
     * @return
     */
    @RequestMapping("/addBookCategory")
    @ResponseBody
    @Operation(summary = "新建书籍种类", description = "新建书籍种类", parameters = {
            @Parameter(name = "bookCategory", description = "书籍种类", required = true)
    })
    public String addBookCategory(BookCategory bookCategory) {
        boolean b = adminService.addBookCategory(bookCategory);
        if (b) {
            return "true";
        }
        return "false";
    }

    /**
     * 根据书籍种类id删除种类
     *
     * @param bookCategoryId
     * @return
     */
    @RequestMapping("/deleteCategory")
    @ResponseBody
    @Operation(summary = "根据书籍种类id删除种类", description = "根据书籍种类id删除种类", parameters = {
            @Parameter(name = "bookCategoryId", description = "书籍种类id", required = true)
    })
    public String deleteBookCategoryById(@RequestParam("bookCategoryId") int bookCategoryId) {
        int res = bookCategoryService.deleteBookCategoryById(bookCategoryId);
        if (res > 0) {
            return "true";
        }
        return "false";
    }

}
