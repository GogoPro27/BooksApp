package mk.ukim.finki.labsemt2.service.application;

import mk.ukim.finki.labsemt2.model.domain.JwToken;

import java.util.List;
import java.util.Optional;

public interface IJwTokenApplicationService {
    List<JwToken> findAll();
    Optional<JwToken> save(String token);
}
