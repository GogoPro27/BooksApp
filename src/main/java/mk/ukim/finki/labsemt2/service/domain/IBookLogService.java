package mk.ukim.finki.labsemt2.service.domain;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Logs.BookLog;

import java.util.List;

public interface IBookLogService {
    void saveFromIdAndAction(Long book,String action);
    List<BookLog> findAllByBookId(Long id);
}
