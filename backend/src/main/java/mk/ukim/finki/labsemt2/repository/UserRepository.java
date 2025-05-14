package mk.ukim.finki.labsemt2.repository;

import io.micrometer.common.lang.NonNull;
import mk.ukim.finki.labsemt2.model.domain.User;
import mk.ukim.finki.labsemt2.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    @NonNull
    @Override
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"bookWishlist"}
    )
    List<User> findAll();
    List<UserProjection> findAllProjectedBy();
}
