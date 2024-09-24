package uz.pdp.call_api_traverson_task.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.lang.NonNull;
import uz.pdp.call_api_traverson_task.post.Post;

import java.net.URL;

@Slf4j
public class CommentClient {
    @Value("${hateoas.url.base}")
    private String baseURL;

    public Post getPost(@NonNull Integer id) throws Exception {
        Traverson traverson = new Traverson(new URL(baseURL + id).toURI(), MediaTypes.HAL_JSON);
        EntityModel<Post> entityModel = traverson.follow("self")
                .toObject(new ParameterizedTypeReference<>() {
                });
        if (entityModel == null) {
            return null;
        }
        for (Link link : entityModel.getLinks()) {
            log.info("Links From Hateoas API: " + link.getHref());
        }
        return entityModel.getContent();
    }

}
