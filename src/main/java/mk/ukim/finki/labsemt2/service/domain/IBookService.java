package mk.ukim.finki.labsemt2.service.domain;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    List<Book> findAll();
    Optional<Book> findById(long id);
    void deleteById(long id);
    Optional<Book> save(Book book);
    Optional<Book> update(long id,Book book);

}
