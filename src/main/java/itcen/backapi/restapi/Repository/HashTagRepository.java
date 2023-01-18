package itcen.backapi.restapi.Repository;

import itcen.backapi.restapi.Entities.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTagEntity, Long> {

}
