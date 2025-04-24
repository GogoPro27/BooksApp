package mk.ukim.finki.labsemt2.service.domain;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.JwToken;

import java.util.List;
import java.util.Optional;

public interface IJwTokenService {
    List<JwToken> findAll();
    Optional<JwToken> save(JwToken jwToken);
}
