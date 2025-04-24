package mk.ukim.finki.labsemt2.service.domain.impl;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.JwToken;
import mk.ukim.finki.labsemt2.repository.JwTokenRepository;
import mk.ukim.finki.labsemt2.service.domain.IJwTokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JwTokenService implements IJwTokenService {

    private final JwTokenRepository jwTokenRepository;

    public JwTokenService(JwTokenRepository jwTokenRepository) {
        this.jwTokenRepository = jwTokenRepository;
    }

    @Override
    public List<JwToken> findAll() {
        return jwTokenRepository.findAll();
    }

    @Override
    public Optional<JwToken> save(JwToken jwToken) {
        return Optional.of(jwTokenRepository.save(jwToken));
    }

}
