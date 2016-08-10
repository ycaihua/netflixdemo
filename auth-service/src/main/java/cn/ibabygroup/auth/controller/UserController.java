package cn.ibabygroup.auth.controller;

import cn.ibabygroup.auth.domain.User;
import cn.ibabygroup.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by zengxiaobo on 2016/8/4 for netflixdemo
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/current",method = RequestMethod.GET)
    public Principal getUser(Principal principal){
        return principal;
    }
    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user){
        userService.create(user);
    }
}
