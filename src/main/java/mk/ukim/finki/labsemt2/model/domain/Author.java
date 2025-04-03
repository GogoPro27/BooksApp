package mk.ukim.finki.labsemt2.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne()
    private Country country;
//    @OneToMany(mappedBy = "author")
//    private List<Book> bookList;

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author() {

    }
}
