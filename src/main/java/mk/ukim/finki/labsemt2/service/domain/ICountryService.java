package mk.ukim.finki.labsemt2.service.domain;

import mk.ukim.finki.labsemt2.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface ICountryService {
    List<Country> findAll();
    Optional<Country> findById(long id);
    void deleteById(long id);
    Optional<Country> save(Country country);
    Optional<Country> update(long id,Country country);
}
