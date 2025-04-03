package mk.ukim.finki.labsemt2.service.domain.impl;

import jakarta.transaction.Transactional;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.User;
import mk.ukim.finki.labsemt2.repository.AuthorRepository;
import mk.ukim.finki.labsemt2.repository.BookRepository;
import mk.ukim.finki.labsemt2.repository.UserRepository;
import mk.ukim.finki.labsemt2.service.domain.IBookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final UserService userService;
    private final UserRepository userRepository;


    public BookService(BookRepository bookRepository, UserService userService, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
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

    @Override
    public void addBookToWishList(Long id) {
        User authUser = userService.getAuthenticatedUser();
        Book book = findById(id).orElseThrow();

        authUser.getBookWishlist().add(book);
        userRepository.save(authUser);
    }

    @Override
    public void removeBookFromWishList(Long id) {
        User authUser = userService.getAuthenticatedUser();

        authUser.getBookWishlist().removeIf(b->b.getId().equals(id));
        userRepository.save(authUser);
    }

    @Override
    public List<Book> findAllInWishList() {
        return userService.getAuthenticatedUser().getBookWishlist();
    }

    @Override
    public boolean rentAllFromWishList() {
        User user = userService.getAuthenticatedUser();
        return !user.getBookWishlist().stream().map(b-> rentBook(b.getId())).toList().contains(false);
    }

    @Override
    public boolean rentBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        if (book.getAvailableCopies()==0){
            return false;
        }
        User authUser = userService.getAuthenticatedUser();
        authUser.getRentedBooks().add(book);
        removeBookFromWishList(id);
        book.setAvailableCopies(book.getAvailableCopies()-1);
        bookRepository.save(book);
        userRepository.save(authUser);

        return true;
    }

    @Override
    public void returnBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow();
        User authUser = userService.getAuthenticatedUser();
        authUser.getRentedBooks().remove(book);
        book.setAvailableCopies(book.getAvailableCopies()+1);
        userRepository.save(authUser);
        bookRepository.save(book);
    }
}
