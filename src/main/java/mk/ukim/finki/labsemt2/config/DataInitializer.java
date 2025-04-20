package mk.ukim.finki.labsemt2.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Enum.Category;
import mk.ukim.finki.labsemt2.model.domain.Country;
import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import mk.ukim.finki.labsemt2.model.domain.User;
import mk.ukim.finki.labsemt2.repository.AuthorRepository;
import mk.ukim.finki.labsemt2.repository.BookRepository;
import mk.ukim.finki.labsemt2.repository.CountryRepository;
import mk.ukim.finki.labsemt2.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CountryRepository countryRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AuthorRepository authorRepository, BookRepository bookRepository, CountryRepository countryRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.countryRepository = countryRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
    public void init() {
        // Create Countries
        var usa = countryRepository.save(new Country("United States", "North America"));
        var uk = countryRepository.save(new Country("United Kingdom", "Europe"));
        var germany = countryRepository.save(new Country("Germany", "Europe"));
        var france = countryRepository.save(new Country("France", "Europe"));
        var japan = countryRepository.save(new Country("Japan", "Asia"));
        var canada = countryRepository.save(new Country("Canada", "North America"));
        var italy = countryRepository.save(new Country("Italy", "Europe"));
        var spain = countryRepository.save(new Country("Spain", "Europe"));
        var sweden = countryRepository.save(new Country("Sweden", "Europe"));
        var australia = countryRepository.save(new Country("Australia", "Oceania"));

        // Create Authors
        var author1 = authorRepository.save(new Author("Mark", "Twain", usa));
        var author2 = authorRepository.save(new Author("Jane", "Austen", uk));
        var author3 = authorRepository.save(new Author("Johann", "Goethe", germany));
        var author4 = authorRepository.save(new Author("Victor", "Hugo", france));
        var author5 = authorRepository.save(new Author("Haruki", "Murakami", japan));
        var author6 = authorRepository.save(new Author("Margaret", "Atwood", canada));
        var author7 = authorRepository.save(new Author("Dante", "Alighieri", italy));
        var author8 = authorRepository.save(new Author("Miguel", "de Cervantes", spain));
        var author9 = authorRepository.save(new Author("Astrid", "Lindgren", sweden));
        var author10 = authorRepository.save(new Author("Tim", "Winton", australia));

        // Create Books
        bookRepository.save(new Book("The Adventures of Tom Sawyer", Category.CLASSICS, author1, 10));
        bookRepository.save(new Book("Pride and Prejudice", Category.NOVEL, author2, 15));
        bookRepository.save(new Book("Faust", Category.DRAMA, author3, 5));
        bookRepository.save(new Book("Les Misérables", Category.HISTORY, author4, 8));
        bookRepository.save(new Book("Kafka on the Shore", Category.FANTASY, author5, 12));
        bookRepository.save(new Book("The Handmaid's Tale", Category.THRILLER, author6, 9));
        bookRepository.save(new Book("The Divine Comedy", Category.CLASSICS, author7, 4));
        bookRepository.save(new Book("Don Quixote", Category.CLASSICS, author8, 6));
        bookRepository.save(new Book("Cloudstreet", Category.NOVEL, author10, 7));


        List<User> users = new ArrayList<>();
        if(this.userRepository.count() == 0) {
            users.add(new User("gf", passwordEncoder.encode("gf"), "Gorazd", Role.ROLE_LIBRARIAN));
            users.add(new User("user",  passwordEncoder.encode("12345"), "User", Role.ROLE_USER));
            userRepository.saveAll(users);
        }
    }
}


