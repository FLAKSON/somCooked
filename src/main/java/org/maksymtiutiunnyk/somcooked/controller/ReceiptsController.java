package org.maksymtiutiunnyk.somcooked.controller;

import org.maksymtiutiunnyk.somcooked.dtos.ReceiptCreationDto;
import org.maksymtiutiunnyk.somcooked.entities.ReceiptEntity;
import org.maksymtiutiunnyk.somcooked.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class ReceiptsController {

    private final ReceiptService receiptService;
    @Autowired
    public ReceiptsController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @GetMapping("/new")
    public String newReceipt(Model model) {
        model.addAttribute("recipe", new ReceiptEntity());
        return "addReceipt";
    }

    @PostMapping("/add")
    public String newReceipt(@ModelAttribute("recipe") ReceiptCreationDto recipe) {
        receiptService.addReceipt(recipe);
        return "redirect:/main/home";
    }
}
