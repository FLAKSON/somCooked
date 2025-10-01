package org.maksymtiutiunnyk.somcooked.controller;

import org.maksymtiutiunnyk.somcooked.services.ReceiptService;
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
    private final ReceiptService receiptService;
    @Autowired
    public ContentController(UserInfoService userInfoService, ReceiptService receiptService) {
        this.userInfoService = userInfoService;
        this.receiptService = receiptService;
    }

    @GetMapping("/home")
    public String mainPage(Model model) {
        model.addAttribute("username", userInfoService.getUsername().toUpperCase());
        model.addAttribute("myRecipes", receiptService.getAllReceiptsForUser());
        model.addAttribute("latestRecipes", receiptService.getAllReceipts());
        return "mainPage";
    }
}
