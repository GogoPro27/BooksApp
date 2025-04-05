package mk.ukim.finki.labsemt2.service.domain.impl;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Logs.BookLog;
import mk.ukim.finki.labsemt2.repository.BookLogRepository;
import mk.ukim.finki.labsemt2.repository.BookRepository;
import mk.ukim.finki.labsemt2.service.domain.IBookLogService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLogService implements IBookLogService {
    private final BookLogRepository bookLogRepository;
    private final BookService bookService;
    private final BookRepository bookRepository;

    public BookLogService(BookLogRepository bookLogRepository, @Lazy BookService bookService, BookRepository bookRepository) {
        this.bookLogRepository = bookLogRepository;
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void saveFromIdAndAction(Long book,String action) {
        Book book1 = bookService.findById(book).orElseThrow();
        BookLog bookLog = new BookLog(bookService.bookToLog(book1),book1,action );
        bookLogRepository.save(bookLog);
    }

    @Override
    public List<BookLog> findAllByBookId(Long id) {
        return bookRepository.findById(id).get().getBookLogs();
    }
}
