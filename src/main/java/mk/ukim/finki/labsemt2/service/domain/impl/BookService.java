package mk.ukim.finki.labsemt2.service.domain.impl;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.repository.BookRepository;
import mk.ukim.finki.labsemt2.service.domain.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> save(Book book) {
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> update(long id, Book book) {
        return bookRepository.findById(id).map(b->{
            if(book.getAuthor()!=null){
                b.setAuthor(book.getAuthor());
            }
            if(book.getName()!=null){
                b.setName(book.getName());
            }
            if(book.getCategory()!=null){
                b.setCategory(book.getCategory());
            }
            if(book.getAvailableCopies()!=null){
                b.setAvailableCopies(book.getAvailableCopies());
            }
            return bookRepository.save(b);
        });
    }
}
