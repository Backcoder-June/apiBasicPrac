package itcen.backapi.restapi;

import itcen.backapi.restapi.Entities.PostDTO;
import itcen.backapi.restapi.Entities.PostEntity;
import itcen.backapi.restapi.Entities.PostResponseDTO;
import itcen.backapi.restapi.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/posts") // 게시물에 대한 상위 URL 관례
public class PostApiController {

//    @Resource(name = "postRepository")
    private final PostRepository postRepository;

    // 스프링 4 몇버전 부터는 final 붙여놓고 requied 해두면  1. final 생성자 자동 생성 => 2. Autowired 하나일 때 자동 적용 => 한줄로 되긴 함
//    @RequiredArgsConstructor
//    private final PostRepository postRepository;

    // 생성자로 DI => 한번 주입한 객체는 계속해서 유지 ( Single Tone ) => 불변성 유지 => 그래야 신뢰성이 생김 + 여기에 private final 까지 붙여서 완전한 불변성 만들어줌
    // Setter 로 의존성을 주입하면 계속해서 객체가 바뀔 수 있기 때문에 신뢰성이 없음. 사용하면 안됨
    @Autowired
    public PostApiController(PostRepository postRepository) { //Spring Container 가 직접 DI 해주고 => 그걸 생성자로 this 값으로 사용 ( 초기화 )
        this.postRepository = postRepository;
    }

    // 전체 게시물
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPosts() {
        log.info("/posts GET Request");
        List<PostEntity> allPosts = postRepository.findAll();

        // Entity => DTO ( 필요한 정보만 클라이언트에 필요한 꼴로 보내줌 )
        List<PostResponseDTO> collect = allPosts.stream()
                .map(et -> new PostResponseDTO(et))
                .collect(Collectors.toList());

        return ResponseEntity
                .ok()
                .body(collect);
    }


    /** 개별 조회 시 클라이언트에게 수정시간 정보 제공
     * / 게시물 수정은 제목 내용만 수정 가능해야함 **/
    // 상세 게시물
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetailPost(@PathVariable("id") long id) {
        log.info("/posts/{} GET Request", id);
        PostEntity oneEntity = postRepository.findOne(id);

        //Entity 를 클라이언트에게 필요한 DTO 형태로
        PostResponseDTO onePostDTO = new PostResponseDTO(oneEntity);

        return ResponseEntity
                .ok()
                .body(onePostDTO + " - 최종 수정시간 : " + oneEntity.getModifiedDate());
    }

    // 게시물 등록
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@RequestBody PostDTO postDTO) {
        log.info("/posts/ register Posts");
        log.info("게시물 정보 : {}", postDTO);

        //DTO => Entity
        PostEntity postEntity = postDTO.toEntity();

        boolean flag = postRepository.save(postEntity);
        return flag
                ? ResponseEntity.ok().body("INSERT-성공")
                : ResponseEntity.badRequest().body("등록 실패");
    }


    // 제목 내용 만 / 수정시 날짜 갱신
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
        log.info("PATCH request 받아온 dTO : {}", postDTO );

        // DTO => Entity
        PostEntity editedEntity = postDTO.patchToEntity();

        log.info("수정된 entity 저장할 거 : {}", editedEntity);

        boolean flag = postRepository.update(postDTO.getId(),editedEntity);

        return flag
                ? ResponseEntity.ok().body("PATCH 성공")
                : ResponseEntity.badRequest().body("PATCH 실패");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(Long id) {
        log.info(("posts/id DELETE request" ));

        boolean flag = postRepository.delete(id);
        return flag
                ? ResponseEntity.ok().body("DELETE 성공")
                : ResponseEntity.badRequest().body("DELETE 실패");
    }

    // commit Test
    //sad f

}
