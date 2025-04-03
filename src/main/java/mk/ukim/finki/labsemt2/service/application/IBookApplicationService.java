package mk.ukim.finki.labsemt2.service.application;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.dto.create.CreateBookDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayBookDto;

import java.util.List;
import java.util.Optional;

public interface IBookApplicationService {
    List<DisplayBookDto> findAll();
    Optional<DisplayBookDto> findById(long id);
    void deleteById(long id);
    Optional<DisplayBookDto> save(CreateBookDto book);
    Optional<DisplayBookDto> update(long id,CreateBookDto book);

    //wishlist
    void addBookToWishList(Long id);
    void removeBookFromWishList(Long id);
    List<DisplayBookDto> findAllInWishList();
    //rent
    boolean rentAllFromWishList();
    boolean rentBook(Long id);
    void returnBook(Long id);
}
