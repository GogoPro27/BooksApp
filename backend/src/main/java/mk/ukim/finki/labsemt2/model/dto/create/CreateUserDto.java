package mk.ukim.finki.labsemt2.model.dto.create;

import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import mk.ukim.finki.labsemt2.model.domain.User;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        Role role
) {

    public User toUser() {
        return new User(username, password, name, role);
    }
}