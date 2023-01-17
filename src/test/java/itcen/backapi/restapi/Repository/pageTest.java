package itcen.backapi.restapi.Repository;

import itcen.backapi.restapi.Entities.PageRequestDTO;
import itcen.backapi.restapi.Entities.PageResponseDTO;
import itcen.backapi.restapi.Entities.PostEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
class pageTest {

    @Autowired
    PostRepository postRepository;


    @BeforeEach
    void bulkInsert() {
        for (int i = 1; i <= 500; i++) {
            PostEntity dummy = PostEntity.builder()
                    .title("더미제목" + i)
                    .content("더미내용입니다" + i)
                    .writer("더미작성자" + i)
                    .build();
            postRepository.save(dummy);
        }
    }


    @Test
    void pagingTest() {
        PageRequestDTO dto = new PageRequestDTO();

        // [ PageRequest ] JPA 페이징 기능 제공
        PageRequest pageInfo = PageRequest.of(dto.getPage() - 1, dto.getSizePerPage(), // page 정보는 index카운트다. ( 0번 인덱스 => 1 페이지, limit 과 마찬가지 )
                // 클라이언트에서 받아온 page 정보는 보이는 숫자. => 페이징 처리할 때는 index 로 처리해줘야 되니까 -1
                Sort.Direction.DESC, "createdDate");  // 정렬 설정 => Sort + 정렬 기준 properties 까지 설정 필요 ( 필드 명으로 )
        //위에서 정해놓은 pageInfo ( 0페이지부터 10개 )의 정보대로, findAll 해줘라 => Page<엔티티> 타입으로 받아짐.
        Page<PostEntity> postEntities = postRepository.findAll(pageInfo);

        // 이렇게 Page 클래스로 받아두면 => Page 객체 메소드들 사용 가능
        int totalPages = postEntities.getTotalPages(); // 총 페이지수
        long totalElements = postEntities.getTotalElements();  // 총 데이터 개수
        boolean prev = postEntities.getPageable().hasPrevious();

        // Page 객체에서 getContent => 내용꺼내오면 그게 객체 리스트
        // => 페이지별로 이 contents 를 뿌리면 되고,
        List<PostEntity> contents = postEntities.getContent();
        // 총 페이지 수는 getTotalPages 뽑은거로 링크 만들어서 사용
        System.out.println("totalplage : " + totalPages);
        System.out.println("totalElements : " + totalElements);
        System.out.println(contents.size());

        // 페이지 prev / next 설정은 어떻게?

    }


    @Test
    @DisplayName("페이징처리 테스트")
    void pageTest2() {
        //given
        String title = "3";
        PageRequest pageRequest = PageRequest.of(11, 10, Sort.Direction.DESC, "createdDate");

        Page<PostEntity> postEntityPage = postRepository.findByTitleContaining(title, pageRequest);

        // Slice 로 받으면 hasNext, hasPrevious Boolean 정보 받을 수 있다.
        Slice<PostEntity> postEntityPage2 = postRepository.findByTitleContaining(title, pageRequest);
        List<PostEntity> content1 = postEntityPage2.getContent();
        boolean previous = postEntityPage2.hasPrevious();
        boolean next = postEntityPage2.hasNext();

        System.out.println("previous : " + previous);
        System.out.println("next : " + next);


        List<PostEntity> content = postEntityPage.getContent();

        for (PostEntity postEntity : content) {
            System.out.println(postEntity);
        }

        // 페이지 정보
        PageResponseDTO<PostEntity> dto = new PageResponseDTO<>(postEntityPage);

        System.out.println(dto);



    }




    //
}
