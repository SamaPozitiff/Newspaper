package services;

import entities.ArticleEntity;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

public class RestArticleService implements ArticleServiceForRest{
    private RestTemplate restTemplate;

    public RestArticleService(String accessToken){
        this.restTemplate = new RestTemplate();
        if(accessToken!= null){
            this.restTemplate
                    .getInterceptors()
                    .add(getBearerTokenInterceptor(accessToken));
        }
    }

    @Override
    public Iterable<ArticleEntity> findArticlesFor24Hours(){
        return Arrays.asList(restTemplate.getForObject("http://localhost:8080/api/ingredients", ArticleEntity.class));
    }



    private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken){
        ClientHttpRequestInterceptor interceptor = new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                request.getHeaders().add("Authorization", "Bearer" + accessToken);
                return execution.execute(request, body);
            }
        };
        return interceptor;
    }

}
