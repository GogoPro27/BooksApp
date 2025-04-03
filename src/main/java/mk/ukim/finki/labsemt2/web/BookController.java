package mk.ukim.finki.labsemt2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.labsemt2.model.dto.create.CreateBookDto;
import mk.ukim.finki.labsemt2.service.application.impl.BookApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book management API for librarians")
@RestController
public class BookController {

    private final BookApplicationService bookApplicationService;


    public BookController(BookApplicationService bookApplicationService) {
        this.bookApplicationService = bookApplicationService;
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

}
