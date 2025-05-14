package mk.ukim.finki.labsemt2.service.domain;

import mk.ukim.finki.labsemt2.model.domain.Author;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    List<Author> findAll();
    Optional<Author> findById(long id);
    void deleteById(long id);
    Optional<Author> save(Author author);
    Optional<Author> update(long id,Author author);
}
