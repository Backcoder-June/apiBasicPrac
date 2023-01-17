package itcen.backapi.jpabasic.repository;

import itcen.backapi.jpabasic.entity.Gender;
import itcen.backapi.jpabasic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {


    //'메소드이름'을 이용 => keyword를 인식하고 => 자동으로 쿼리 생성해줌 (JPA) - 은행원의 노가다
    // JPA의 이런 keyword 이용하는 방법은 간단한 처리할 때 쓰고
    // 좀 복잡해지면 query dsl / 혹은 JPQL
    // 더 복잡해지면 Mybatis / nativeQuery 사용

    List<MemberEntity> findByGender(Gender gender);

    List<MemberEntity> findByEmailAndGender(String email, Gender gender);

    List<MemberEntity> findByNicknameContaining(String nickname);


    // JPQL
    // select 별칭 from 엔티티 as 별치 where 별칭.필드명
    @Query("SELECT m from MemberEntity m where m.email=:email")
    MemberEntity findByEmail(@Param("email") String email);

    @Query("SELECT m from MemberEntity m where m.nickname=:nickname AND m.gender=:gender")
    MemberEntity findByNickNameAndGender(@Param("nickname") String nickname, @Param("gender") Gender gender);

    @Query("SELECT m FROM MemberEntity m where m.nickname like %:nickname%")
    List<MemberEntity> getMembersByNickNameContaining(@Param("nickname") String nickname);

    @Query(value = "SELECT * FROM jpamember where nickname=:nickname", nativeQuery = true)
    MemberEntity getmembernick(@Param("nickname") String nick);

    @Modifying
    @Query(value = "INSERT INTO jpamember(email, nickname, password) VALUES(:#{#memberEntity.email}, :#{#memberEntity.nickname}, :#{#memberEntity.password})", nativeQuery = true)
    void insertMember(@Param("memberEntity") MemberEntity memberEntity);
}
