package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Country;
import mk.ukim.finki.labsemt2.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayAuthorDto;
import mk.ukim.finki.labsemt2.service.application.IAuthorApplicationService;
import mk.ukim.finki.labsemt2.service.domain.impl.AuthorService;
import mk.ukim.finki.labsemt2.service.domain.impl.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationService implements IAuthorApplicationService {
    private final AuthorService authorService;
    private final CountryService countryService;


    public AuthorApplicationService(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return authorService.findAll().stream()
                .map(DisplayAuthorDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayAuthorDto> findById(long id) {
        return authorService.findById(id).map(DisplayAuthorDto::from);
    }

    @Override
    public void deleteById(long id) {
        authorService.deleteById(id);
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto author) {
        Optional<Country> country = countryService.findById(author.country());
        Author author1 = author.toAuthor(country.orElse(null));
        return authorService.save(author1).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(long id, CreateAuthorDto author) {
        Country country = countryService.findById(author.country()).orElseThrow();
        return authorService.update(id, author.toAuthor(country)).map(DisplayAuthorDto::from);
    }
}
