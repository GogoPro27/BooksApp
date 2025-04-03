package mk.ukim.finki.labsemt2.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    private String username;

    @JsonIgnore
    private String password;

    private String name;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @ManyToMany
    private List<Book> bookWishlist;

    @ManyToMany
    private List<Book> rentedBooks;


    public User(String username, String password, String name, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        bookWishlist = new ArrayList<>();
        rentedBooks = new ArrayList<>();
    }
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = Role.ROLE_USER;
        rentedBooks = new ArrayList<>();
        bookWishlist = new ArrayList<>();
    }

    public User(UserDetails userDetails) {
        this.username = userDetails.getUsername();
        this.password = userDetails.getPassword();
        this.role = Role.ROLE_USER;
        bookWishlist = new ArrayList<>();
        rentedBooks = new ArrayList<>();
    }

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((GrantedAuthority) role);
    }





    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
