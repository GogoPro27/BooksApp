package mk.ukim.finki.labsemt2.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labsemt2.model.domain.Enum.Category;


@Entity
@Data
@Table(name = "book")
public class Book {
    public Book() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Category category;
    @ManyToOne
    private Author author;
    private Integer availableCopies;

    public Book( String name, Category category, Author author, int availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }
}