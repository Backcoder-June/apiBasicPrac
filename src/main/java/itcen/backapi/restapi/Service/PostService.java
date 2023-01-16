package itcen.backapi.restapi.Service;

import itcen.backapi.restapi.Entities.PostDTO;
import itcen.backapi.restapi.Entities.PostEntity;
import itcen.backapi.restapi.Entities.PostResponseDTO;
import itcen.backapi.restapi.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

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

        Optional<PostEntity> one = postRepository.findById(id);
        PostResponseDTO onePostDTO = new PostResponseDTO(one.get());
        return onePostDTO;
    }

    public boolean insertPost(final PostDTO postDTO) {
        final PostEntity postEntity = postDTO.toEntity();
        postRepository.save(postEntity);
        return true;
    }


    public boolean updatePost(final PostDTO postDTO) {
        Long id = postDTO.getId();
        Optional<PostEntity> originDTO = postRepository.findById(id);

        originDTO.get().setTitle(postDTO.getTitle());
        originDTO.get().setContent(postDTO.getContent());
        originDTO.get().setWriter(postDTO.getWriter());

        postRepository.save(originDTO.get());
        return true;
    }

    public boolean deletePost(final Long id) {
        postRepository.deleteById(id);
        return true;
    }






}




