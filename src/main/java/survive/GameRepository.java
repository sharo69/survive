package survive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by nikolakaloyanov on 6/26/16.
 */
@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {
}
