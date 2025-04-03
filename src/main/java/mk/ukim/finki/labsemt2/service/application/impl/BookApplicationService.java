package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.dto.create.CreateBookDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayBookDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayUserDto;
import mk.ukim.finki.labsemt2.service.application.IBookApplicationService;
import mk.ukim.finki.labsemt2.service.domain.impl.AuthorService;
import mk.ukim.finki.labsemt2.service.domain.impl.BookService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationService implements IBookApplicationService{
    public final BookService bookService;
    public final AuthorService authorService;


    public BookApplicationService(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @Override
    public List<DisplayBookDto> findAll() {
        return bookService.findAll().stream()
                .map(DisplayBookDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayBookDto> findById(long id) {
        return bookService.findById(id).map(DisplayBookDto::from);
    }

    @Override
    public void deleteById(long id) {
        bookService.deleteById(id);
    }

    @Override
    public Optional<DisplayBookDto> save(CreateBookDto book) {
        Author author = authorService.findById(book.author()).orElseThrow();
        Book book1 = book.toBook(author);
        return bookService.save(book1).map(DisplayBookDto::from);
    }

    @Override
    public Optional<DisplayBookDto> update(long id, CreateBookDto book) {
        Author author = authorService.findById(book.author()).orElseThrow();
        Book book1 = book.toBook(author);
        return bookService.update(id,book1).map(DisplayBookDto::from);
    }

    @Override
    public void addBookToWishList(Long id) {
        bookService.addBookToWishList(id);
    }

    @Override
    public void removeBookFromWishList(Long id) {
        bookService.removeBookFromWishList(id);
    }

    @Override
    public List<DisplayBookDto> findAllInWishList() {
        return bookService.findAllInWishList().stream()
                .map(DisplayBookDto::from)
                .toList();
    }

    @Override
    public boolean rentAllFromWishList() {
        return bookService.rentAllFromWishList();
    }

    @Override
    public boolean rentBook(Long id) {
        return bookService.rentBook(id);
    }

    @Override
    public void returnBook(Long id) {
        bookService.returnBook(id);
    }
}
