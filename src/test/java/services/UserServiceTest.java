package services;

import com.example.homepagenewspaper.AssertsTools;
import com.example.homepagenewspaper.HomePageNewsPaperApplication;
import entities.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
public class UserServiceTest {
    @Autowired
    AssertsTools assertsTools;


    @Test
    public void addUserTest(ApplicationContext ctx){
        UserService userService = (UserService) ctx.getBean("userService");
        UserEntity user = userService.newUser("Test@mail.ru", "test", "test", "user", "ROLE_USER");
        userService.save(user);
        assertsTools.compareUser(user, userService.findById(user.getId()));

    }

}
