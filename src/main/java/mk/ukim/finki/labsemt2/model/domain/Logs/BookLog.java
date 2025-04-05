package mk.ukim.finki.labsemt2.model.domain.Logs;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.labsemt2.model.domain.Book;

@Entity
@Data
public class BookLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String log;
    private String action;


    @ManyToOne(optional = true)
    @JoinColumn(name = "book_id", nullable = true)
    private Book book;

    public BookLog(String log, Book book,String action) {
        this.log = log;
        this.book = book;
        this.action = action;
    }

    public BookLog() {
    }
}
