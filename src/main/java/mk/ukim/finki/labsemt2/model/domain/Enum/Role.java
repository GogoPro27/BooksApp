package mk.ukim.finki.labsemt2.model.domain.Enum;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_LIBRARIAN,
    ROLE_ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
