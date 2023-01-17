package itcen.backapi.restapi.Entities;

import lombok.*;

import java.util.List;

@Setter @Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class PostListResponseDTO {

    private int count;
    // 페이지정보 객체
    private PageResponseDTO pageInfo;
    // 데이터 List
    private List<PostResponseDTO> posts;
}