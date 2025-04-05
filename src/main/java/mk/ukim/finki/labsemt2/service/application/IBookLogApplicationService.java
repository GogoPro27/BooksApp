package mk.ukim.finki.labsemt2.service.application;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Logs.BookLog;

import java.util.List;

public interface IBookLogApplicationService {
    void saveFromIdAndAction(Long book,String action);
    List<BookLog> findAllByBookId(Long id);
}
