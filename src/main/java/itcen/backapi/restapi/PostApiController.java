package itcen.backapi.restapi;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import itcen.backapi.restapi.Entities.PostDTO;
import itcen.backapi.restapi.Entities.PostEntity;
import itcen.backapi.restapi.Entities.PostResponseDTO;
import itcen.backapi.restapi.Repository.PostRepository;
import itcen.backapi.restapi.Service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
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
    private final PostService postService;


    // 스프링 4 몇버전 부터는 final 붙여놓고 requied 해두면  1. final 생성자 자동 생성 => 2. Autowired 하나일 때 자동 적용 => 한줄로 되긴 함
//    @RequiredArgsConstructor
//    private final PostRepository postRepository;


    // 생성자로 DI => 한번 주입한 객체는 계속해서 유지 ( Single Tone ) => 불변성 유지 => 그래야 신뢰성이 생김 + 여기에 private final 까지 붙여서 완전한 불변성 만들어줌
    // Setter 로 의존성을 주입하면 계속해서 객체가 바뀔 수 있기 때문에 신뢰성이 없음. 사용하면 안됨
    @Autowired
    public PostApiController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }



    // 전체 게시물
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllPosts() {
        log.info("/posts GET Request");

        try {
            List<PostResponseDTO> allList = postService.getAllList();

            return ResponseEntity
                    .ok()
                    .body(allList);
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }


    /** 개별 조회 시 클라이언트에게 수정시간 정보 제공
     * / 게시물 수정은 제목 내용만 수정 가능해야함 **/
    // 상세 게시물
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDetailPost(@PathVariable("id") long id) {
        log.info("/posts/{} GET Request", id);

        try {
            PostEntity oneEntity = postRepository.findOne(id);
            PostResponseDTO onePostDTO = postService.getDetail(id);

            return ResponseEntity
                    .ok()
                    .body(onePostDTO + " - 최종 수정시간 : " + oneEntity.getModifiedDate());
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }



    // swagger 설정
    @Parameters({
            @Parameter(name = "작성자", description = "게시물 작성자를 입력", example = "김철수")
    })

    // 게시물 등록
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> createPost(@Validated @RequestBody PostDTO postDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();

            fieldErrors.forEach(err -> {
                log.warn("너가 쓴게 잘못됬따 - {}", err.toString());
            });

            return ResponseEntity
                    .badRequest()
                    .body(fieldErrors);
        }

        log.info("/posts/ register Posts");
        log.info("게시물 정보 : {}", postDTO);

        try {
            boolean flag = postService.insertPost(postDTO);

            return flag
                    ? ResponseEntity.ok().body("INSERT-성공")
                    : ResponseEntity.badRequest().body("등록 실패");
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    // 제목 내용 만 / 수정시 날짜 갱신
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updatePost(@RequestBody PostDTO postDTO) {
        log.info("PATCH request 받아온 dTO : {}", postDTO );

        PostEntity editedEntity = postDTO.patchToEntity();
        log.info("수정된 entity 저장할 거 : {}", editedEntity);

        try {
            boolean flag = postService.updatePost(postDTO);
            return flag
                    ? ResponseEntity.ok().body("PATCH 성공")
                    : ResponseEntity.badRequest().body("PATCH 실패");
        } catch (Exception e) {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        log.info(("posts/id DELETE request" ));


        boolean flag = postService.deletePost(id);
        return flag
                ? ResponseEntity.ok().body("DELETE 성공")
                : ResponseEntity.badRequest().body("DELETE 실패");
    }

    // commit Test
    //sad f

}
