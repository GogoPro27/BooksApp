package mk.ukim.finki.labsemt2.repository;

import mk.ukim.finki.labsemt2.model.domain.JwToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwTokenRepository extends JpaRepository<JwToken,Long> {
}
