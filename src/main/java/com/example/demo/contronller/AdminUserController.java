package com.example.demo.contronller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.jwt.JwtIgnore;
import com.example.demo.jwt.Audience;
import com.example.demo.jwt.JwtTokenUtil;
import com.example.demo.uitl.BaseResponse;
import com.example.demo.uitl.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
/**
 * ========================
 * Created with IntelliJ IDEA.
 * User：pyy@Slf4j
 * Date：2019/7/18 10:41
 * Version: v1.0
 * ========================
 */
@Slf4j
@RestController
public class AdminUserController {

    @Autowired
    private Audience audience;

    @PostMapping("/login")
    @JwtIgnore
    public BaseResponse<JSONObject>  adminLogin(HttpServletResponse response, String username,String password) {
        BaseResponse<JSONObject>  rs  =  new BaseResponse<>();
        // 这里模拟测试, 默认登录成功，返回用户ID和角色信息
        String userId = UUID.randomUUID().toString();
        String role = "admin";
        // 创建token
        String token = JwtTokenUtil.createJWT(userId, username, role, audience);
        log.info("### 登录成功, token={} ###", token);
        // 将token放在响应头
        response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
        // 将token响应给客户端
        JSONObject result = new JSONObject();
        result.put("token", token);
        rs.setResponseData(result);
        return  rs;
    }
    @GetMapping("/users")
    public Result userList() {
        log.info("### 查询所有用户列表 ###");
        return Result.SUCCESS();
    }
}
