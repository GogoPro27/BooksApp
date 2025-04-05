package mk.ukim.finki.labsemt2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.labsemt2.model.domain.Logs.BookLog;
import mk.ukim.finki.labsemt2.model.dto.create.CreateBookDto;
import mk.ukim.finki.labsemt2.service.application.impl.BookApplicationService;
import mk.ukim.finki.labsemt2.service.application.impl.BookLogApplicationService;
import mk.ukim.finki.labsemt2.service.domain.impl.BookLogService;
import mk.ukim.finki.labsemt2.service.domain.impl.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Collection;


@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book management API for librarians")
@RestController
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final BookLogApplicationService bookLogApplicationService;
    private final BookService bookService;
    private final BookLogService bookLogService;


    public BookController(BookApplicationService bookApplicationService, BookLogApplicationService bookLogApplicationService, BookService bookService, BookLogService bookLogService) {
        this.bookApplicationService = bookApplicationService;
        this.bookLogApplicationService = bookLogApplicationService;
        this.bookService = bookService;
        this.bookLogService = bookLogService;
    }

    @GetMapping
    @Operation(summary = "List all books")
    public ResponseEntity<?> listAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific books")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Adds a book with details provided in the request body")
    public ResponseEntity<?> addBook(@RequestBody CreateBookDto bookDto) {
        return ResponseEntity.ok(bookApplicationService.save(bookDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit a book", description = "Edits an existing book's details")
    public ResponseEntity<?> editBook(@RequestBody CreateBookDto bookDto, @PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.update(id, bookDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/wishlist/add/{id}")
    @Operation(summary = "Add book to wishlist", description = "Adds a specific book to the authenticated user's wishlist")
    public ResponseEntity<?> addBookToWishList(@PathVariable Long id) {
        bookApplicationService.addBookToWishList(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/wishlist/remove/{id}")
    @Operation(summary = "Remove book from wishlist", description = "Removes a specific book from the authenticated user's wishlist")
    public ResponseEntity<?> removeBookFromWishList(@PathVariable Long id) {
        bookApplicationService.removeBookFromWishList(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wishlist")
    @Operation(summary = "List all books in wishlist", description = "Retrieves all books from the authenticated user's wishlist")
    public ResponseEntity<?> listAllInWishList() {
        return ResponseEntity.ok(bookApplicationService.findAllInWishList());
    }

    // --- Rent Endpoints ---

    @PostMapping("/rent/all")
    @Operation(summary = "Rent all books from wishlist", description = "Rents all books present in the authenticated user's wishlist")
    public ResponseEntity<?> rentAllFromWishList() {
        boolean result = bookApplicationService.rentAllFromWishList();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/rent/{id}")
    @Operation(summary = "Rent a book", description = "Rents a specific book by its ID from the available stock")
    public ResponseEntity<?> rentBook(@PathVariable Long id) {
        boolean result = bookApplicationService.rentBook(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/return/{id}")
    @Operation(summary = "Return a rented book", description = "Returns a specific rented book by its ID, increasing available copies")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        bookApplicationService.returnBook(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/booklogs")
    public ResponseEntity<List<BookLog>> getAllBookLogs() {
        List<BookLog> bookLogs = bookService.findAll().stream()
                .map(b->bookLogService.findAllByBookId(b.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        return new ResponseEntity<>(bookLogs, HttpStatus.OK);
    }
    @GetMapping("/booklogs/{bookId}")
    public ResponseEntity<?> getBookLogForBook(@PathVariable Long bookId) {
        List<BookLog> bookLogs = bookLogApplicationService.findAllByBookId(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(bookLogs);
    }


}
