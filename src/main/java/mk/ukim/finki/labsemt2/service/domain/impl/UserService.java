package mk.ukim.finki.labsemt2.service.domain.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import mk.ukim.finki.labsemt2.model.domain.User;
import mk.ukim.finki.labsemt2.repository.UserRepository;
import mk.ukim.finki.labsemt2.service.domain.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new RuntimeException();

        if (!password.equals(repeatPassword)) throw new RuntimeException();

        if (userRepository.findById(username).isPresent())
            throw new RuntimeException(username);

        User user = new User(username, passwordEncoder.encode(password), name, role);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password, HttpServletRequest request) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = findByUsername(username);
        setAuthentication(user, request);

        return user;
    }

    public void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    @Override
    public void logout() {
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            SecurityContextHolder.clearContext();
        } else {
            throw new RuntimeException();
        }
    }


    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();


        return findByUsername(username);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findById(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findById(username).orElseThrow();
    }
}
