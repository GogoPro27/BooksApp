package mk.ukim.finki.labsemt2.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labsemt2.model.domain.Enum.Category;
import mk.ukim.finki.labsemt2.model.domain.Logs.BookLog;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Book {
    public Book() {
        bookLogs = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<BookLog> bookLogs;

    public Book( String name, Category category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
        bookLogs = new ArrayList<>();
    }
}