package itcen.backapi.restapi.Repository;

import itcen.backapi.restapi.Entities.PostEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepository {
    // memory 저장소 생성
    private static final Map<Long, PostEntity> posts = new HashMap<>();
    // 전체조회
    public List<PostEntity> findAll() {
        List<PostEntity> postEntityList = new ArrayList<>();
//        for (PostEntity eachPost : posts.values()
//             ) {
//            postEntityList.add(eachPost);
//        }
//        return postEntityList;

        // map => keyset(Set) 로 뽑아서 stream 으로 돌리는 방법 ( List / Set 은 Stream 돌릴 수 있으니. )
        return posts.keySet().stream()
                .map(posts::get)
                .collect(Collectors.toList());

    }

    //개별조회
    public PostEntity findOne(Long id) {
        return posts.get(id);
    }

    //게시물 등록
    public boolean save(PostEntity postEntity) {
        PostEntity newpost = posts.put(postEntity.getId(), postEntity);
        return true; // void -> boolean 으로 받아서 써먹을 수 있음
    }


    //게시물 수정
    public boolean update(Long id, PostEntity postEntity) {
        PostEntity updateTarget = posts.get(id);

        // 제목 내용만 set 해줌. 나머지는 바뀐 값이 들어가도 적용 안됨 ( View 단에서 readonly 걸어두고 2차적 방어 및 효율성능 )
        updateTarget.setTitle(postEntity.getTitle());
        updateTarget.setContent(postEntity.getContent());
        updateTarget.setModifiedDate(LocalDateTime.now()); // 게시물 수정 시 현재 DateTime 으로
        posts.put(updateTarget.getId(), updateTarget);
        return true;
    }




    //게시물 삭제
    public boolean delete(Long id) {
        PostEntity removeTarget = posts.remove(id);
        return true;
    }


}
