package controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
/*
Контроллер страниц газеты
 */
@RestController
public class NewspaperController {
    NewspaperFacade newspaperFacade;

    public NewspaperController(NewspaperFacade newspaperFacade){
        this.newspaperFacade = newspaperFacade;
    }
    /**
    Главная страница
     */
    @GetMapping(path = "/" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List homepage() throws IOException {
        return newspaperFacade.homepage();
    }
    /**
    Комментарии постранично
     @param articleId - id статьи
     @param page - номер страницы

     */
    @GetMapping(path = "/comments/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List getComments(@RequestParam Long articleId, Integer page){
        return newspaperFacade.getCommentsOnArticle(page,articleId);
    }
    /**
    Добавление комментария
     @param description  - текст комментария
     @param articleId - id статьи
     */
    @PostMapping(path = "/comments/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestParam String description, Long articleId){
        newspaperFacade.addComment(description, articleId);
    }
    /**
    простановка/снятие лайка
     @param articleId - id статьи
     */
    @RequestMapping(path = "/like/", method={RequestMethod.DELETE, RequestMethod.POST})
    public void likeArticle(@RequestParam Long articleId){
        newspaperFacade.likeArticle(articleId);
    }




}
