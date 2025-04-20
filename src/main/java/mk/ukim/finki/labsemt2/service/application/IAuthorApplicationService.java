package mk.ukim.finki.labsemt2.service.application;

import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.dto.create.CreateAuthorDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayAuthorDto;
import mk.ukim.finki.labsemt2.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface IAuthorApplicationService {
    List<DisplayAuthorDto> findAll();
    Optional<DisplayAuthorDto> findById(long id);
    void deleteById(long id);
    Optional<DisplayAuthorDto> save(CreateAuthorDto author);
    Optional<DisplayAuthorDto> update(long id,CreateAuthorDto author);

    //views
     List<BooksPerAuthorView> findAllBooksPerAuthor();
     BooksPerAuthorView findBooksPerAuthor(Long id);
     void refreshMaterializedView();
}
