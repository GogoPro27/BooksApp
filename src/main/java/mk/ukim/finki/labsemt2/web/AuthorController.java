package mk.ukim.finki.labsemt2.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.labsemt2.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.labsemt2.service.application.impl.AuthorApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "Author management API for librarians")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all authors")
    public ResponseEntity<?> listAllAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific author")
    public ResponseEntity<?> listAuthor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new author", description = "Adds an author with details provided in the request body")
    public ResponseEntity<?> addAuthor(@RequestBody CreateAuthorDto authorDto) {
        return ResponseEntity.ok(authorApplicationService.save(authorDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an author", description = "Edits an existing author's details")
    public ResponseEntity<?> editAuthor(@RequestBody CreateAuthorDto authorDto, @PathVariable Long id) {
        return ResponseEntity.ok(authorApplicationService.update(id, authorDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        authorApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
