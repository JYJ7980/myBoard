package com.example.myBoard.service;

import com.example.myBoard.Dto.ArticleDto;
import com.example.myBoard.entity.Article;
import com.example.myBoard.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void insertArticle(ArticleDto dto) {
//        Article article = dto.fromMemberDto(dto);
        Article article = Article.builder()
                        .title(dto.getTitle())
                        .content(dto.getContent())
                        .build();
        articleRepository.save(article);
    }

    public List<ArticleDto> showAllArticles() {
        List<ArticleDto> articleDtoList =new ArrayList<>();
        return articleRepository.findAll()
                .stream()
                .map(x -> ArticleDto.fromArticleEntity(x))
                .toList();
    }

    public void updateArticle(ArticleDto dto) {
        Article article = dto.fromMemberDto(dto);
        articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    public ArticleDto findById(Long id) {
        return ArticleDto.fromArticleEntity(articleRepository.findById(id).orElse(null));
    }
}
