package com.zbw.controller;


import com.zbw.domain.Department;
import com.zbw.domain.User;
import com.zbw.domain.Vo.BorrowingBooksVo;
import com.zbw.service.IBookService;
import com.zbw.service.IBorrowingBooksRecordService;
import com.zbw.service.IUserService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@Tag(name = "用户控制器")
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private IBorrowingBooksRecordService borrowingBooksRecordService;

    @Resource
    private IBookService bookService;

    /**
     * 用户登录
     *
     * @param userName
     * @return
     */
    @PostMapping("/userLogin")
    @Operation(summary = "用户登录", description = "用户登录", parameters = {
            @Parameter(name = "userName", description = "用户名", required = true),
            @Parameter(name = "password", description = "密码", required = true)
    })
    public String userLogin(@Param("userName") String userName,
                            @Param("password") String password, HttpServletRequest request) {
        User user = userService.userLogin(userName, password);

        if (null != user) {
            // flag = 0 表示用户名密码校验成功  【用于前端校验】
            request.getSession().setAttribute("flag", 0);

            request.getSession().setAttribute("user", user);
            return "user/index";
        }

        // flag 为 1 表示 登录失败 【用于前端校验】
        request.getSession().setAttribute("flag", 1);
        return "index";
    }

    /**
     * //验证用户是否存在
     *
     * @param userName
     * @return
     */
    @RequestMapping("/isUserExist")
    @ResponseBody
    @Operation(summary = "验证用户是否存在", description = "验证用户是否存在", parameters = {
            @Parameter(name = "userName", description = "用户名", required = true)
    })
    public String isUserExist(@Param("userName") String userName) {
        List<User> users = userService.findUserByUserName(userName);
        if (null == users) {
            return "false";
        }
        if (users.size() < 1) {
            return "false";
        }
        return "true";
    }

    /**
     * 查找所有部门
     */
    @RequestMapping("/getDepts")
    @ResponseBody
    @Operation(summary = "查找所有部门", description = "查找所有部门")
    public List<Department> getDepts() {
        List<Department> depts = userService.findAllDepts();
        return depts;
    }


    /**
     * 返回用户借书记录页面
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/userBorrowBookRecord")
    @Operation(summary = "返回用户借书记录页面", description = "返回用户借书记录页面")
    public String userBorrowBookRecord(Model model, HttpServletRequest request) {
        ArrayList<BorrowingBooksVo> res = borrowingBooksRecordService.selectAllBorrowRecord(request);
        model.addAttribute("borrowingBooksList", res);

        return "user/borrowingBooksRecord";
    }

    /**
     * 返回还书页面
     */
    @RequestMapping("/userReturnBooksPage")
    @Operation(summary = "返回还书页面", description = "返回还书页面")
    public String userReturnBooksPage() {
        return "user/returnBooks";
    }

    /**
     * 返回个人信息页面
     */
    @RequestMapping("/userMessagePage")
    @Operation(summary = "返回个人信息页面", description = "返回个人信息页面")
    public String userMessagePage(Model model, HttpServletRequest request) {
        User session_user = (User) request.getSession().getAttribute("user");
        User user = userService.findUserById(session_user.getUserId());
        model.addAttribute("message_user", user);
        return "user/userMessage";
    }

    /**
     * 返回借书页面
     */
    @RequestMapping("/borrowingPage")
    @Operation(summary = "返回借书页面", description = "返回借书页面")
    public String borrowing() {
        return "user/borrowingBooks";
    }

    /**
     * 返回用户首页
     */
    @RequestMapping("/userIndex")
    @Operation(summary = "返回用户首页", description = "返回用户首页")
    public String userIndex() {
        return "user/index";
    }

    /**
     * @param user
     * @param request
     * @return
     * @author zbw
     * 更新用户信息
     */
    @RequestMapping("/updateUser")
    @ResponseBody
    @Operation(summary = "更新用户信息", description = "更新用户信息")
    public boolean updateUser(User user, HttpServletRequest request) {
        return userService.updateUser(user, request);
    }

    /**
     * 用户还书
     *
     * @param bookId
     * @param request
     * @return
     */
    @RequestMapping("/userReturnBook")
    @ResponseBody
    @Operation(summary = "用户还书", description = "用户还书")
    public boolean returnBook(int bookId, HttpServletRequest request) {
        return userService.userReturnBook(bookId, request);
    }

    /**
     * 用户借书
     *
     * @param bookId
     * @param request
     * @return
     */
    @RequestMapping("/userBorrowingBook")
    @ResponseBody
    @Operation(summary = "用户借书", description = "用户借书")
    public boolean borrowingBook(int bookId, HttpServletRequest request) {
        System.out.println(bookId);
        return userService.userBorrowingBook(bookId, request);
    }

    /**
     * 返回管理员登陆界面
     */
    @RequestMapping("/adminLoginPage")
    @Operation(summary = "返回管理员登陆界面", description = "返回管理员登陆界面")
    public String adminLoginPage() {
        return "adminLogin";
    }

    /**
     * 用户退出登陆
     *
     * @param request
     * @return
     */
    @RequestMapping("/userLogOut")
    @Operation(summary = "用户退出登陆", description = "用户退出登陆")
    public String userLogOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    /**
     * 返回用户索书页面
     */
    @RequestMapping("/findBookPage")
    @Operation(summary = "返回用户索书页面", description = "返回用户索书页面")
    public String findBookPage() {
        return "user/findBook";
    }


    /**
     * 根据用户id删除用户
     *
     * @param userId
     * @return
     */
    @RequestMapping("/deleteUser")
    @ResponseBody
    @Operation(summary = "根据用户id删除用户", description = "根据用户id删除用户")
    public String deleteUserByUserId(@RequestParam("userId") int userId) {
        int res = userService.deleteUserById(userId);
        if (res > 0) {
            return "true";
        }
        return "false";
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    @ResponseBody
    @Operation(summary = "添加用户", description = "添加用户")
    public String addUser(User user) {
        int res = userService.insertUser(user);
        if (res > 0) {
            return "true";
        } else {
            return "false";
        }
    }
}
