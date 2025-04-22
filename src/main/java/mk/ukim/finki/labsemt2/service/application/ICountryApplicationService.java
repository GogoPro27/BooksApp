package mk.ukim.finki.labsemt2.service.application;

import mk.ukim.finki.labsemt2.model.domain.Country;
import mk.ukim.finki.labsemt2.model.dto.create.CreateBookDto;
import mk.ukim.finki.labsemt2.model.dto.create.CreateCountryDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayCountryDto;
import mk.ukim.finki.labsemt2.model.views.AuthorsPerCountryView;

import java.util.List;
import java.util.Optional;

public interface ICountryApplicationService {
    List<DisplayCountryDto> findAll();
    Optional<DisplayCountryDto> findById(long id);
    void deleteById(long id);
    Optional<DisplayCountryDto> save(CreateCountryDto country);
    Optional<DisplayCountryDto> update(long id, CreateCountryDto country);

    List<AuthorsPerCountryView> findAllAuthorsPerCountry();
    AuthorsPerCountryView findAuthorsPerCountry(Long id);
    void refreshMaterializedView();
}
