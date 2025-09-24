package org.maksymtiutiunnyk.somcooked.controller;

import org.maksymtiutiunnyk.somcooked.dtos.ReceiptCreationDto;
import org.maksymtiutiunnyk.somcooked.entities.ReceiptEntity;
import org.maksymtiutiunnyk.somcooked.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "redirect:/recipe/myReceipt";
    }

    @GetMapping("/myReceipt")
    public String myReceipt(Model model) {
        model.addAttribute("recipes", receiptService.getAllReceiptsForUser());
        return "receiptPage";
    }

    @GetMapping("/{id}")
    public String showMoreAboutReceipt(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", receiptService.getReceiptById(id));
        return "recipeDetail";
    }

    @PostMapping("/delete/{id}")
    public String deleteReceipt(@PathVariable Long id) {
        receiptService.deleteReceiptById(id);
        return "redirect:/recipe/myReceipt";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        ReceiptEntity recipe = receiptService.getReceiptById(id);
        model.addAttribute("recipe", recipe);
        return "editRecipe";
    }
    @PostMapping("/update/{id}")
    public String updateReceipt(@PathVariable Long id, @ModelAttribute("recipe") ReceiptCreationDto recipe) {
        receiptService.editReceipt(id, recipe);
        return "redirect:/recipe/myReceipt";
    }
    @GetMapping("/all")
    public String getAllReceipts(Model model) {
        model.addAttribute("recipes", receiptService.getAllReceipts());
        return "allRecipes";
    }
}
