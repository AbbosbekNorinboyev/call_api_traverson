package uz.pdp.call_api_traverson_task;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.data.web.PagedResourcesAssembler;
import uz.pdp.call_api_traverson_task.post.Post;
import uz.pdp.call_api_traverson_task.post.PostRepository;

import java.net.URL;
import java.util.List;

@SpringBootApplication
public class CallApiRestTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CallApiRestTemplateApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ObjectMapper objectMapper, PostRepository postRepository) {
        return (args -> {
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            List<Post> posts = objectMapper.readValue(url, new TypeReference<>() {
            });
            postRepository.saveAll(posts);
        });
    }

    @Bean(name = "postPagedResourcesAssembler")
    public PagedResourcesAssembler<Post> postPagedResourcesAssembler() {
        return new PagedResourcesAssembler<>(new HateoasPageableHandlerMethodArgumentResolver(), null);
    }
}
