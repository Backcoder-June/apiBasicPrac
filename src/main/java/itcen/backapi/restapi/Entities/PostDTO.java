package itcen.backapi.restapi.Entities;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PostDTO {  // 실제로 조작할 필요한 데이터들로만 추려서 만든 Entity 의 작은 버전 그릇


    private Long id;
    @NotBlank
    @Parameter(name = "작성자", description = "게시물 작성자 입력", example = "JUNE")
    private String writer;
    @NotBlank
    @Size(min = 2,max = 20)
    private String title;
    @NotBlank
    private String content;

    private List<String> hashTagEntity;


    //toEntity
    public PostEntity toEntity() {
        return PostEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .createdDate(LocalDateTime.now())
                .build();
    }

    public PostEntity patchToEntity() {
        return PostEntity.builder()
                .id(this.id)
                .title(this.title)
                .content(this.content)
                .writer(this.writer)
                .createdDate(LocalDateTime.now())
                .build();
    }



}




