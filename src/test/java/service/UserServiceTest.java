package service;

import entity.UserEntity;
import newspaper_main.HomePageNewsPaperApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {PSQLContainer.Initializer.class})
@Testcontainers
public class UserServiceTest extends PSQLContainer{


    @Transactional
    @Test
    public void addUserTest(ApplicationContext ctx){
        UserService userService = (UserService) ctx.getBean("userService");
        UserEntity user = userService.newUser("Test@mail.ru", "test", "test", "user", "ROLE_USER");
        userService.save(user);
        Assertions.assertTrue(user.equals(userService.findById(user.getId())));

    }

}
