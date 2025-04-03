package mk.ukim.finki.labsemt2.service.domain;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import mk.ukim.finki.labsemt2.model.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService {
    User register(String username, String password, String repeatPassword, String name, Role role);

    User login(String username, String password, HttpServletRequest request);

    void logout();

    User getAuthenticatedUser();
    User findByUsername(String username);

}
