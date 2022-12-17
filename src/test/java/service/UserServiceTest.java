package service;

import newspaper_main.AssertsTools;
import newspaper_main.HomePageNewsPaperApplication;
import entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest(classes = HomePageNewsPaperApplication.class)
@ContextConfiguration(initializers = {UserServiceTest.Initializer.class})
@Testcontainers
public class UserServiceTest {
    @Autowired
    AssertsTools assertsTools;

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13")
            .withDatabaseName("users")
            .withUsername("samapozitiff")
            .withPassword("DocGironimo248");

    static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword(),
                    "spring.liquibase.enabled=true"
            ).applyTo(applicationContext.getEnvironment());
        }
    }

    @Transactional
    @Test
    public void addUserTest(ApplicationContext ctx){
        UserService userService = (UserService) ctx.getBean("userService");
        UserEntity user = userService.newUser("Test@mail.ru", "test", "test", "user", "ROLE_USER");
        userService.save(user);
        assertsTools.compareUser(user, userService.findById(user.getId()));

    }

}
