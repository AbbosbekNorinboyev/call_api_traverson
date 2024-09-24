package uz.pdp.call_api_traverson_task.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostRepository postRepository;
    private final PostModelAssembler postModelAssembler;
    private final PagedResourcesAssembler<Post> pagedResourcesAssembler;

    PostController(PostRepository postRepository,
                   PostModelAssembler postModelAssembler,
                   @Qualifier("postPagedResourcesAssembler") PagedResourcesAssembler<Post> pagedResourcesAssembler) {
        this.postRepository = postRepository;
        this.postModelAssembler = postModelAssembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<Post>> getPosts() {
        List<Post> posts = postRepository.findAll();
        return postModelAssembler.toCollectionModel(posts);
    }

    @GetMapping("/paged")
    public org.springframework.hateoas.PagedModel<EntityModel<Post>> getPage(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAll(pageable);
        return pagedResourcesAssembler.toModel(posts, postModelAssembler);
    }

    @GetMapping("/{id}")
    public EntityModel<Post> getPost(@PathVariable Integer id) throws Exception {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found: " + id));
        return postModelAssembler.toModel(post);
    }
}
