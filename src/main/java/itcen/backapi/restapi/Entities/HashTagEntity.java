package itcen.backapi.restapi.Entities;

import itcen.backapi.restapi.Entities.PostEntity;
import lombok.*;

import javax.persistence.*;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@ToString @EqualsAndHashCode
@Entity
@Table(name = "hash_tag")
public class HashTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tagName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private PostEntity postEntity;

}
