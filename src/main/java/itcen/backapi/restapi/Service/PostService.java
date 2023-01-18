package itcen.backapi.restapi.Service;

import itcen.backapi.restapi.Entities.*;
import itcen.backapi.restapi.Repository.HashTagRepository;
import itcen.backapi.restapi.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final HashTagRepository hashTagRepository;

    @Autowired
    public PostService(PostRepository postRepository, HashTagRepository hashTagRepository) {
        this.postRepository = postRepository;
        this.hashTagRepository = hashTagRepository;
    }


    public PostListResponseDTO getAllList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = PageRequest.of(
                pageRequestDTO.getPage() - 1, pageRequestDTO.getSizePerPage(),
                Sort.Direction.DESC, "createdDate"
        );

        final Page<PostEntity> pageData = postRepository.findAll(pageable);
        List<PostEntity> allPosts = pageData.getContent();

//        if (allPosts.isEmpty()) {
//            throw new RuntimeException("조회 결과 Empty!");
//        }

        // Entity => DTO ( 필요한 정보만 클라이언트에 필요한 꼴로 보내줌 )
        List<PostResponseDTO> responseDTOList = allPosts.stream()
                .map(et -> new PostResponseDTO(et))
                .collect(Collectors.toList());


        PostListResponseDTO listResponseDTO = PostListResponseDTO.builder()
                .count(responseDTOList.size())
                .pageInfo(new PageResponseDTO<PostEntity>(pageData))
                .posts(responseDTOList)
                .build();

        return listResponseDTO;
    }

    public PostResponseDTO getDetail(Long id) {

        Optional<PostEntity> one = postRepository.findById(id);
        PostResponseDTO onePostDTO = new PostResponseDTO(one.get());
        return onePostDTO;
    }

    @Transactional
    public PostResponseDTO insertPost(final PostDTO postDTO) {
        final PostEntity postEntity = postDTO.toEntity(); //받아서 Entity 로 만들어서 DB 저장하고
        PostEntity savedEntity = postRepository.save(postEntity); //save : 예외 발생할 수 있는 메소드임 ( throws 있으면 )

        // hashtag db 저장
        List<String> hashTags = postDTO.getHashTagEntity();

        // hashtag 문자열 리스트 => 문자열 추출 => hashtag Entity build => db 저장
        List<HashTagEntity> hashTagEntityList = new ArrayList<>();
        for (String ht : hashTags) {
            HashTagEntity tagEntity = HashTagEntity.builder()
                    .postEntity(savedEntity)
                    .tagName(ht)
                    .build();
            HashTagEntity savedTag = hashTagRepository.save(tagEntity);
            hashTagEntityList.add(savedTag);
        }
        savedEntity.setHashtags(hashTagEntityList); //심어주기

        return new PostResponseDTO(savedEntity); //응답줄 때는 다시 응답용 DTO 로 만들어서 보내주고
    }


    public PostResponseDTO updatePost(final PostDTO postDTO) {
        Long id = postDTO.getId();
        PostEntity originDTO = postRepository.findById(id).orElseThrow(
                () -> new RuntimeException("수정 전 dto 데이터가 없습니다.")
        );

        originDTO.setTitle(postDTO.getTitle());
        originDTO.setContent(postDTO.getContent());
        originDTO.setWriter(postDTO.getWriter());

        postRepository.save(originDTO);

        return new PostResponseDTO(originDTO);
    }

    public void deletePost(final Long id) throws RuntimeException {
        postRepository.deleteById(id);
    }
}




