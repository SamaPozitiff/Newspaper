package controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewspaperController {
    NewspaperFacade newspaperFacade;

    public NewspaperController(NewspaperFacade newspaperFacade){
        this.newspaperFacade = newspaperFacade;
    }

    @GetMapping(path = "/" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public List homepage(){
        return newspaperFacade.homepage();
    }

    @GetMapping(path = "/comments/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List getComments(@RequestParam Long articleId, Integer page){
        return newspaperFacade.getCommentsOnArticle(page,articleId);
    }

    @PostMapping(path = "/comments/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestParam String description, Long articleId){
        newspaperFacade.addComment(description, articleId);
    }

    @RequestMapping(path = "/like/", method={RequestMethod.DELETE, RequestMethod.POST})
    public void likeArticle(@RequestParam Long articleId){
        newspaperFacade.likeArticle(articleId);
    }




}
