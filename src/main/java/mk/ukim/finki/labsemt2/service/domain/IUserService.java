package mk.ukim.finki.labsemt2.service.domain;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import mk.ukim.finki.labsemt2.model.domain.User;
import mk.ukim.finki.labsemt2.model.projections.UserProjection;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, Role role);
    User login(String username, String password);
    User getAuthenticatedUser(String token);
    User findByUsername(String username);
    List<UserProjection> getAllUserNames();

}
