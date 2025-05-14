package mk.ukim.finki.labsemt2.service.application;

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

    void addBookToWishList(Long id, String token);
    void removeBookFromWishList(Long id, String token);
    List<DisplayBookDto> findAllInWishList(String token);
    boolean rentAllFromWishList(String token);
    boolean rentBook(Long id, String token);
    void returnBook(Long id, String token);
}
