package com.example.homepagenewspaper;

import DAO.CommentDAO;
import DAO.NewspaperArticleDAO;
import DAO.UserDAO;
import DTO.CommentDTO;
import DTO.NewspaperArticleDTO;
import DTO.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HomePageNewsPaperApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void testAdd(){

        NewspaperArticleDTO dto = new NewspaperArticleDTO();
        NewspaperArticleDAO dao = new NewspaperArticleDAO();
        dto.setTitle("Заработало?");
        dao.openSessionWithTransaction();
        dao.persist(dto);
        dao.closeSessionWithTransaction();

    }

    @Test
    public void testGet(){
        NewspaperArticleDAO dao = new NewspaperArticleDAO();
        dao.openSessionWithTransaction();
        NewspaperArticleDTO dto = dao.getArticles(1L);
        dao.closeSessionWithTransaction();
    }

    @Test
    public void testAddUser(){
        UserDAO dao = new UserDAO();
        UserDTO dto = new UserDTO();
        dto.setId(2L);
        dto.setLogin("First");
        dto.setPassword("1111");
        dao.openSessionWithTransaction();
        dao.persist(dto);
        dao.closeSessionWithTransaction();
    }

    @Test
    public void testAddComment(){
        CommentDAO dao = new CommentDAO();
        CommentDTO dto = new CommentDTO();
        dto.setDescription("Шикарно!!!");
        dto.setAuthor("гений");
        dao.openSessionWithTransaction();
        dao.persist(dto);
        dao.closeSessionWithTransaction();

    }

}
