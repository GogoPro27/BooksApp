package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.model.domain.Logs.BookLog;
import mk.ukim.finki.labsemt2.service.application.IBookLogApplicationService;
import mk.ukim.finki.labsemt2.service.domain.impl.BookLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookLogApplicationService implements IBookLogApplicationService {
    private final BookLogService bookLogService;

    public BookLogApplicationService(BookLogService bookLogService) {
        this.bookLogService = bookLogService;
    }


    @Override
    public void saveFromIdAndAction(Long book,String action) {
        bookLogService.saveFromIdAndAction(book,action);
    }

    @Override
    public List<BookLog> findAllByBookId(Long id) {
        return bookLogService.findAllByBookId(id);
    }
}
