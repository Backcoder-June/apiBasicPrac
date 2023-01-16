package itcen.backapi.restapi.Service;

import itcen.backapi.restapi.Entities.PostDTO;
import itcen.backapi.restapi.Entities.PostEntity;
import itcen.backapi.restapi.Entities.PostResponseDTO;
import itcen.backapi.restapi.Repository.PostRepository;
import itcen.backapi.restapi.Repository.PostRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostRepository2 postRepository2;

    public PostService(PostRepository postRepository, PostRepository2 postRepository2) {
        this.postRepository = postRepository;
        this.postRepository2 = postRepository2;
    }

    @Autowired


    public List<PostResponseDTO> getAllList() {
        List<PostEntity> allPosts = postRepository.findAll();

//        if (allPosts.isEmpty()) {
//            throw new RuntimeException("조회 결과 Empty!");
//        }

        // Entity => DTO ( 필요한 정보만 클라이언트에 필요한 꼴로 보내줌 )
        List<PostResponseDTO> collect = allPosts.stream()
                .map(et -> new PostResponseDTO(et))
                .collect(Collectors.toList());

        return collect;
    }

    public PostResponseDTO getDetail(Long id) {
        PostEntity one = postRepository.findOne(id);
        PostResponseDTO onePostDTO = new PostResponseDTO(one);

        return onePostDTO;
    }

    public boolean insertPost(final PostDTO postDTO) {
        final PostEntity postEntity = postDTO.toEntity();
        postRepository2.savePost(postEntity);
        return true;
    }


    public boolean updatePost(final PostDTO postDTO) {
        PostEntity editedEntity = postDTO.patchToEntity();

        return postRepository.update(postDTO.getId(), editedEntity);
    }

    public boolean deletePost(final Long id) {
        return postRepository.delete(id);
    }






}




