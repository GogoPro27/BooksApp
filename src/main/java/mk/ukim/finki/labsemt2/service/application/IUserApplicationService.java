package mk.ukim.finki.labsemt2.service.application;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.labsemt2.model.dto.create.CreateUserDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayUserDto;
import mk.ukim.finki.labsemt2.model.dto.login.LoginResponseDto;
import mk.ukim.finki.labsemt2.model.dto.login.LoginUserDto;
import mk.ukim.finki.labsemt2.model.projections.UserProjection;

import java.util.List;
import java.util.Optional;

public interface IUserApplicationService {
    Optional<DisplayUserDto> register(CreateUserDto createUserDto);
    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);
    Optional<DisplayUserDto> findByUsername(String username);
    List<UserProjection> getAllUserNames();
}
