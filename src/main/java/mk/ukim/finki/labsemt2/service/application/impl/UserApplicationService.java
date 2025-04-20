package mk.ukim.finki.labsemt2.service.application.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.domain.User;
import mk.ukim.finki.labsemt2.model.dto.create.CreateUserDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayUserDto;
import mk.ukim.finki.labsemt2.model.dto.login.LoginUserDto;
import mk.ukim.finki.labsemt2.model.projections.UserProjection;
import mk.ukim.finki.labsemt2.service.application.IUserApplicationService;
import mk.ukim.finki.labsemt2.service.domain.IUserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationService implements IUserApplicationService {
    private final IUserService userService;

    public UserApplicationService(IUserService userService) {
        this.userService = userService;
    }


    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto, HttpServletRequest request) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password(),
                request
        )));
    }

    @Override
    public void logout() {
        userService.logout();
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<UserProjection> getAllUserNames() {
        return userService.getAllUserNames();
    }
}
