package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.model.domain.JwToken;
import mk.ukim.finki.labsemt2.security.JwtHelper;
import mk.ukim.finki.labsemt2.service.application.IJwTokenApplicationService;
import mk.ukim.finki.labsemt2.service.domain.IJwTokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JwTokenApplicationService implements IJwTokenApplicationService {
    private final JwtHelper jwtHelper;
    private final IJwTokenService jwTokenService;

    public JwTokenApplicationService(JwtHelper jwtHelper, IJwTokenService jwTokenService) {
        this.jwtHelper = jwtHelper;
        this.jwTokenService = jwTokenService;
    }


    @Override
    public List<JwToken> findAll() {
        return jwTokenService.findAll();
    }

    @Override
    public Optional<JwToken> save(String token) {
        String username = jwtHelper.extractUsername(token);
        Long exp = jwtHelper.extractExpiration(token).getTime()/1000;
        Long iat = jwtHelper.extractIssuedAt(token).getTime()/1000;
        JwToken jwToken = new JwToken(token,username,iat,exp);
        return jwTokenService.save(jwToken);
    }
}
