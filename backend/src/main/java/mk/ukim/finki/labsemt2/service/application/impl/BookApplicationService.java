package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.dto.create.CreateBookDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayBookDto;
import mk.ukim.finki.labsemt2.service.application.IBookApplicationService;
import mk.ukim.finki.labsemt2.service.domain.IAuthorService;
import mk.ukim.finki.labsemt2.service.domain.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationService implements IBookApplicationService{
    public final IBookService bookService;
    public final IAuthorService authorService;


    public BookApplicationService(IBookService bookService, IAuthorService authorService) {
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
    public void addBookToWishList(Long id, String token) {
        bookService.addBookToWishList(id,token);
    }

    @Override
    public void removeBookFromWishList(Long id, String token) {
        bookService.removeBookFromWishList(id,token);
    }

    @Override
    public List<DisplayBookDto> findAllInWishList(String token) {
        return bookService.findAllInWishList(token).stream()
                .map(DisplayBookDto::from)
                .toList();
    }

    @Override
    public boolean rentAllFromWishList(String token) {
        return bookService.rentAllFromWishList(token);
    }

    @Override
    public boolean rentBook(Long id, String token) {
        return bookService.rentBook(id,token);
    }

    @Override
    public void returnBook(Long id, String token) {
        bookService.returnBook(id,token);
    }
}
