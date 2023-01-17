package itcen.backapi.restapi.Repository;

import itcen.backapi.restapi.Entities.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO post (writer, title, content, createdDate) " +
            "VALUES (:#{#postEntity.writer}, :#{#postEntity.title}, :#{#postEntity.content}, now())", nativeQuery = true)
    void savePost(@Param("postEntity")PostEntity postEntity);

    @Query(value = "SELECT * FROM post WHERE title = :#{#title}", nativeQuery = true)
    PostEntity findByTitle(@Param("title")String title);


    // Page 타입으로 받고, Pageable 매개변수로 받아두면 => PageRequest 자식객체 사용해서 페이지정보 사용 가능
    Page<PostEntity> findByTitleContaining(String title, Pageable pageable);


}
