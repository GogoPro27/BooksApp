package mk.ukim.finki.labsemt2.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class JwToken {
    public JwToken(String token, String username, Long iat, Long exp) {
        this.token = token;
        this.username = username;
        this.iat = iat;
        this.exp = exp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String token;
    String username;
    Long iat;
    Long exp;

    public JwToken() {

    }
}
