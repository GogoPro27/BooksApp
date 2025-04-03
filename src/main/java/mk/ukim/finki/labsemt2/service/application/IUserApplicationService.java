package mk.ukim.finki.labsemt2.service.application;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.dto.create.CreateUserDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayUserDto;
import mk.ukim.finki.labsemt2.model.dto.login.LoginUserDto;

import java.util.Optional;

public interface IUserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto, HttpServletRequest request);

    void logout();
    Optional<DisplayUserDto> findByUsername(String username);
}
