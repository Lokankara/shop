package org.store.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.store.service.SecurityService;
import org.store.web.entity.User;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final SecurityService securityService;

    @GetMapping("/")
    public String showLogin() {
        return "index";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User user) {
        securityService.login(user);
        return "redirect:/products";
    }
}
