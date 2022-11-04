package Controllers;

import Entities.NewspaperArticleEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/articles")
@SessionAttributes("articles")
public class ArticleController {

    @ModelAttribute
    public void articleModel(){
        List<NewspaperArticleEntity> articles  = Arrays.asList(
                new NewspaperArticleEntity(1L, "котятки", null, "мурчат сладко", null)
        );
    }

    @GetMapping
    public String showDesign(){
        return "design";
    }
}
