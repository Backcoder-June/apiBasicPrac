package itcen.backapi.restapi.Repository;

import itcen.backapi.restapi.Entities.PostEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostRepositoryTest {

    @Autowired
    PostRepository postRepository;

    @BeforeEach
    void dummyinsert() {
        PostEntity post1 = PostEntity.builder()
                .writer("주니어")
                .title("첫번째더미")
                .content("1더미")
                .build();

        PostEntity post2 = PostEntity.builder()
                .writer("시니어")
                .title("두번째더미")
                .content("2더미")
                .build();

        PostEntity post3 = PostEntity.builder()
                .writer("마스터")
                .title("마지막더미")
                .content("999더미")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);
        postRepository.save(post3);
    }



    @Test
    @DisplayName("게시물 insert 테스트")
    @Rollback
    @Transactional
    void insertTest() {

        //given
        PostEntity postEntity = PostEntity.builder()
                .title("안녕제목")
                .writer("안녕 글쓴이")
                .content("내용무")
                .build();

        //when
        postRepository.save(postEntity);
        Optional<PostEntity> byId = Optional.ofNullable(postRepository.findByTitle("안녕제목"));

        //then
        assertEquals("내용무", byId.get().getContent());
        assertEquals(4, postRepository.findAll().size());
    }

    @Test
    @DisplayName("게시물 조회 테스트")
    @Rollback
    @Transactional
    void selectAllpostTest() {
        //given
        // 3 dummies
        //when
        PostEntity foundDummy = postRepository.findByTitle("마지막더미");
        //then
        assertEquals("마스터", foundDummy.getWriter());
    }

    @Test
    @DisplayName("게시물 수정 테스트")
    @Rollback
    @Transactional
    void updatePostTest() {
        //given
        //3 dummies
        PostEntity newpost = PostEntity.builder()
                .writer("새로운 작성자")
                .content("새로운 내용")
                .build();

        //when
        PostEntity targetDummy = postRepository.findByTitle("마지막더미");

        targetDummy.setWriter(newpost.getWriter());
        targetDummy.setContent(newpost.getContent());

        postRepository.save(targetDummy);

        //then
        assertEquals("새로운 내용",postRepository.findByTitle("마지막더미").getContent());
    }





}


