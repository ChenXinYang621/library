package com.zbw.controller;

import com.zbw.domain.Admin;
import com.zbw.domain.BookCategory;
import com.zbw.domain.User;
import com.zbw.domain.Vo.BookVo;
import com.zbw.service.IAdminService;
import com.zbw.service.IBookCategoryService;
import com.zbw.service.IUserService;
import com.zbw.utils.page.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@Tag(name = "管理员控制器", description = "管理员控制器")
public class AdminController {

    @Resource
    private IAdminService adminService;
    @Resource
    private IBookCategoryService bookCategoryService;
    @Resource
    private IUserService userService;

    /**
     * 判断admin是否存在
     *
     * @param adminName
     * @return
     */
    @RequestMapping("/isAdminExist")
    @ResponseBody
    @Operation(summary = "判断admin是否存在", description = "判断admin是否存在",
            parameters = {
                    @Parameter(name = "adminName", description = "adminName", required = true)
            })
    public String adminIsExist(@Param("adminName") String adminName) {
        boolean b = adminService.adminIsExist(adminName);
        if (b) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 管理员登陆
     *
     * @param userName
     * @param password
     * @return
     */
    @Operation(summary = "管理员登陆", description = "管理员登陆",
            parameters = {
                    @Parameter(name = "userName", description = "userName", required = true),
                    @Parameter(name = "password", description = "password", required = true)
            })
    @PostMapping("/adminLogin")
    public String adminLogin(@Param("userName") String userName, @Param("password") String password, HttpServletRequest request) {
        Admin admin = adminService.adminLogin(userName, password);

        if (admin == null) {
            // flag 为 1 表示 登录失败 
            request.getSession().setAttribute("flag", 1);
            return "index";
        }

        // flag = 0 表示用户名密码校验成功
        request.getSession().setAttribute("flag", 0);
        request.getSession().setAttribute("admin", admin);
        return "admin/index";
    }

    /**
     * 返回添加书籍页面
     */
    @RequestMapping("/addBookPage")
    @Operation(summary = "返回添加书籍页面", description = "返回添加书籍页面")
    public String addBookPage() {
        return "admin/addBook";
    }

    /**
     * 返回添加类别页面
     */
    @RequestMapping("/addCategoryPage")
    @Operation(summary = "返回添加类别页面", description = "返回添加类别页面", parameters = {
            @Parameter(name = "categoryName", description = "categoryName", required = true)
    })
    public String addCategoryPage(@RequestParam("pageNum") int pageNum, Model model) {
        Page<BookCategory> page = bookCategoryService.selectBookCategoryByPageNum(pageNum);
        model.addAttribute("page", page);
        return "admin/addCategory";
    }

    /**
     * 返回查询状态页面
     */
    @RequestMapping("/showStausPage")
    @Operation(summary = "返回查询状态页面", description = "返回查询状态页面")
    public String showStatusPage() {
        return "admin/showStaus";
    }

    /**
     * 返回管理员首页
     */
    @RequestMapping("/adminIndex")
    @Operation(summary = "返回管理员首页", description = "返回管理员首页")
    public String returnAdminIndexPage() {
        return "admin/index";
    }


    /**
     * 返回查询用户页面
     */
    @RequestMapping("/showUsersPage")
    @Operation(summary = "返回查询用户页面", description = "返回查询用户页面", parameters = {
            @Parameter(name = "pageNum", description = "pageNum", required = true)
    })
    public String showUsersPage(Model model, @RequestParam("pageNum") int pageNum) {
        Page<User> page = userService.findUserByPage(pageNum);
        model.addAttribute("page", page);
        return "admin/showUsers";
    }

    /**
     * 返回&emsp;&emsp;查询书籍页面
     */
    @RequestMapping("/showBooksPage")
    @Operation(summary = "返回查询书籍页面", description = "返回查询书籍页面", parameters = {
            @Parameter(name = "pageNum", description = "pageNum", required = true)
    })
    public String showBooksPage(Model model) {
        Page<BookVo> page = new Page<BookVo>();
        page.setPageCount(1);
        page.setPageNum(1);
        model.addAttribute("page", page);
        return "admin/showBooks";
    }


    /**
     * 管理员退出登陆
     *
     * @param request
     * @return
     */
    @RequestMapping("/adminLogOut")
    @Operation(summary = "管理员退出登陆", description = "管理员退出登陆")
    public String userLogOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    /**
     * 返回新增用户页面
     */
    @RequestMapping("/addUserPage")
    @Operation(summary = "返回新增用户页面", description = "返回新增用户页面")
    public String addUserPage() {
        return "admin/addUser";
    }

    @RequestMapping("/adminInfoPage")
    @Operation(summary = "返回管理员信息页面", description = "返回管理员信息页面")
    public String adminInfo() {
        return "admin/adminInfo";
    }

    /**
     * 更新管理员信息
     *
     * @param admin
     * @param request
     * @return
     */
    @RequestMapping("/updateAdmin")
    @ResponseBody
    @Operation(summary = "更新管理员信息", description = "更新管理员信息", parameters = {
            @Parameter(name = "admin", description = "admin", required = true)
    })
    public boolean updateAdmin(Admin admin, HttpServletRequest request) {
        return adminService.updateAdmin(admin, request);
    }

}
