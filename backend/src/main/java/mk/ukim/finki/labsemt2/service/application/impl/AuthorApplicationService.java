package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.events.AuthorChangedEvent;
import mk.ukim.finki.labsemt2.events.AuthorCreatedEvent;
import mk.ukim.finki.labsemt2.events.AuthorDeletedEvent;
import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Country;
import mk.ukim.finki.labsemt2.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayAuthorDto;
import mk.ukim.finki.labsemt2.model.views.BooksPerAuthorView;
import mk.ukim.finki.labsemt2.repository.BooksPerAuthorViewRepository;
import mk.ukim.finki.labsemt2.service.application.IAuthorApplicationService;
import mk.ukim.finki.labsemt2.service.domain.IAuthorService;
import mk.ukim.finki.labsemt2.service.domain.ICountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorApplicationService implements IAuthorApplicationService {
    private final IAuthorService authorService;
    private final ICountryService countryService;
    private final BooksPerAuthorViewRepository booksPerAuthorViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;


    public AuthorApplicationService(IAuthorService authorService, ICountryService countryService, BooksPerAuthorViewRepository booksPerAuthorViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorService = authorService;
        this.countryService = countryService;
        this.booksPerAuthorViewRepository = booksPerAuthorViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
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
        Author author =authorService.findById(id).orElseThrow();
        authorService.deleteById(id);

        this.applicationEventPublisher.publishEvent(new AuthorDeletedEvent(author));
    }

    @Override
    public Optional<DisplayAuthorDto> save(CreateAuthorDto author) {
        Optional<Country> country = countryService.findById(author.country());
        Author author1 = author.toAuthor(country.orElse(null));

        this.applicationEventPublisher.publishEvent(new AuthorCreatedEvent(author1));

        return authorService.save(author1).map(DisplayAuthorDto::from);
    }

    @Override
    public Optional<DisplayAuthorDto> update(long id, CreateAuthorDto author) {
        Country country = countryService.findById(author.country()).orElseThrow();
        Author author1 = author.toAuthor(country);
        this.applicationEventPublisher.publishEvent(new AuthorChangedEvent(author1));

        return authorService.update(id, author1).map(DisplayAuthorDto::from);
    }


    //views

    @Override
    public List<BooksPerAuthorView> findAllBooksPerAuthor() {
        return booksPerAuthorViewRepository.findAll();
    }

    @Override
    public BooksPerAuthorView findBooksPerAuthor(Long id) {
        return booksPerAuthorViewRepository.findById(id).orElseThrow();
    }

    @Override
    public void refreshMaterializedView() {
        booksPerAuthorViewRepository.refreshMaterializedView();
    }
}
