package mk.ukim.finki.labsemt2.repository;

import mk.ukim.finki.labsemt2.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {

}
