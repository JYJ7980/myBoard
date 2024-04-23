package com.example.myBoard.controller;

import com.example.myBoard.Dto.ArticleDto;
import com.example.myBoard.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("insert")
    public String insertView(Model model) {
        model.addAttribute("articleDto", new ArticleDto());
        return "/articles/new";
    }

    @PostMapping("insert")
    public String insertArticle(@ModelAttribute("articleDto") ArticleDto dto) {
        articleService.insertArticle(dto);
        return "redirect:/";
    }

    @GetMapping("update/{id}")
    public String updateView(@PathVariable("id")Long id,
                             Model model) {
        ArticleDto articleDto = articleService.findById(id);
        model.addAttribute("articleDto", articleDto);
        return "/articles/update";
    }

    @PostMapping("update")
    public String update(@ModelAttribute("articleDto") ArticleDto dto) {
        articleService.updateArticle(dto);
        return "redirect:/";
    }

    @GetMapping("delete/{id}")
    public String deleteView(@PathVariable("id")Long id) {
        articleService.deleteArticle(id);
        return "redirect:/";
    }

}
