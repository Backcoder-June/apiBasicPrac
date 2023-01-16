package itcen.backapi.jpabasic.repository;

import itcen.backapi.jpabasic.entity.Gender;
import itcen.backapi.jpabasic.entity.MemberEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static itcen.backapi.jpabasic.entity.Gender.FEMALE;
import static itcen.backapi.jpabasic.entity.Gender.MALE;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void bulkInsert() {
        MemberEntity saveMember1 = MemberEntity.builder()
                .email("zzz1234")
                .password("1234")
                .nickname("꾸러긔")
                .gender(FEMALE)
                .build();
        MemberEntity saveMember2 = MemberEntity.builder()
                .email("abc4321")
                .password("4321")
                .nickname("궁예")
                .gender(MALE)
                .build();
        MemberEntity saveMember3 = MemberEntity.builder()
                .email("ppp9999")
                .password("9888")
                .nickname("찬호박")
                .gender(MALE)
                .build();

        memberRepository.save(saveMember1);
        memberRepository.save(saveMember2);
        memberRepository.save(saveMember3);
    }

    @Test
    @DisplayName("~해야한다. 단언")
    @Transactional // Transaction 걸고
    @Rollback
        // 테스트 끝나면 롤백시키는 것
    void saveTest() {
        //given
        MemberEntity june = MemberEntity.builder()
                .email("backcoder@sdaf.com")
                .password("123123")
                .nickname("june")
                .gender(MALE)
                .build();
        //when
        MemberEntity savedEntity = memberRepository.save(june);


        Optional<MemberEntity> byId = memberRepository.findById(1L);

        //then => 테스트케이스 작성
        MemberEntity memberEntity = byId.get();

        assertFalse(byId.isPresent());

//        assertEquals(1L, memberEntity.getUserId());

        assertEquals("june", memberEntity.getNickname());

    }

    // 목록 조회 테스트
    @Test
    @DisplayName("회원 목록을 조회하면 3명의 회원정보가 조회되어야 한다.")
    @Transactional
    @Rollback
    void findAllTest() {
        // given
        // when

        List<MemberEntity> memberEntityList = memberRepository.findAll();

        // then
        // 조회된 리스트의 사이즈는 3이어야 한다.
        assertEquals(3, memberEntityList.size());
        // 조회된 리스트의 2번째 회원 닉네임은 궁예여야한다.
        assertEquals("궁예", memberEntityList.get(1).getNickname());
    }

    @Test
    @DisplayName("회원 데이터 3개중 하나의 회원 삭제 테스트")
    @Transactional
    @Rollback
    void deleteTest() {
        //given
        Long userId = 2L;
        //when
        memberRepository.deleteById(userId);
        Optional<MemberEntity> byId = memberRepository.findById(userId);

        //then
        assertFalse(byId.isPresent());  //지웠으니까 findbyid 해봤자 null 일 것임을 단언
        // 이거 assertNull 로 테스트하면서 .get() 으로 뽑으면 .get 뽑을 때 오류나서 테스트 안됨. => isPresent 로 뽑아서 boolean 테스트 하자 .

        assertEquals(2, memberRepository.findAll().size()); //지웠으니까 총 3개중 하나 지워지고 size = 2 일 것임을 단언
    }

    @Test
    @DisplayName("2번 회원 수정 테스트_닉네임_성별")
    @Transactional
    @Rollback
    void modifyTest() {
        //given
        Long userId = 2L;
        String newNickname = "닭강정";
        Gender newGender = FEMALE;

        //when
        // JPA 에서 수정은 조회후  setter 로 변경
        Optional<MemberEntity> byId = memberRepository.findById(userId);
        byId.ifPresent(m -> {
            m.setNickname(newNickname);
            m.setGender(newGender);
        });
        Optional<MemberEntity> newbyId = memberRepository.findById(userId); // set 해서 수정된 새로운 객체

        // save 까지 해줘야 수정 완료 ( 영속성 )
        MemberEntity newMember = memberRepository.save(newbyId.get());

        //then
        assertEquals("닭강정", newMember.getNickname());
        assertEquals(FEMALE, newMember.getGender());
    }
}