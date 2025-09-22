package org.maksymtiutiunnyk.somcooked.controller;

import org.maksymtiutiunnyk.somcooked.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/main")
public class ContentController {

    private final UserInfoService userInfoService;
    @Autowired
    public ContentController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/home")
    public String mainPage(Model model) {
        model.addAttribute("username", userInfoService.getUsername().toUpperCase());
        return "mainPage";
    }
}
