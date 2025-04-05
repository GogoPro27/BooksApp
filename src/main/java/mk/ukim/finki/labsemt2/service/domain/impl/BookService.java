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
    private final BookLogService bookLogService;


    public BookService(BookRepository bookRepository, UserService userService, UserRepository userRepository, BookLogService bookLogService) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.bookLogService = bookLogService;
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
        bookLogService.saveFromIdAndAction(id,"DELETED");
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> save(Book book) {
        Book book1 = bookRepository.save(book);
        bookLogService.saveFromIdAndAction(book1.getId(),"SAVED");
        return Optional.of(book1);
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
            Book book1 =  bookRepository.save(b);
            bookLogService.saveFromIdAndAction(book1.getId(),"UPDATED");
            return book1;
        });
    }

    @Override
    public void addBookToWishList(Long id) {
        User authUser = userService.getAuthenticatedUser();
        Book book = findById(id).orElseThrow();

        authUser.getBookWishlist().add(book);
        userRepository.save(authUser);
        bookLogService.saveFromIdAndAction(id,String.format("ADDED TO %s'S WISHLIST",authUser.getUsername()));
    }

    @Override
    public void removeBookFromWishList(Long id) {
        User authUser = userService.getAuthenticatedUser();

        boolean isRemoved = authUser.getBookWishlist().removeIf(b->b.getId().equals(id));
        userRepository.save(authUser);
        if (isRemoved)
            bookLogService.saveFromIdAndAction(id,String.format("REMOVED FROM %s'S WISHLIST",authUser.getUsername()));

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
        bookLogService.saveFromIdAndAction(id,String.format("RENTED BY %s'",authUser.getUsername()));
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
        bookLogService.saveFromIdAndAction(id,String.format("RETURNED BY %s'",authUser.getUsername()));

    }

    public String bookToLog(Book book) {
        return String.format("Book[id=%d, name='%s', author='%s %s', category=%s, availableCopies=%d]",
                book.getId(),
                book.getName(),
                book.getAuthor().getName(),
                book.getAuthor().getSurname(),
                book.getCategory(),
                book.getAvailableCopies());
    }
}
