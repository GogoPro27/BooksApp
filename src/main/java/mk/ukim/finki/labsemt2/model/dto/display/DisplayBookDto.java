package mk.ukim.finki.labsemt2.model.dto.display;


import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Enum.Category;

import java.util.List;

public record DisplayBookDto(Long id, String name,Category category, Long author,Integer availableCopies) {
    public Book toBook(Author author){
        return new Book(name,category,author,availableCopies);
    }

    public static DisplayBookDto from(Book book){
        return new DisplayBookDto(book.getId(),book.getName(),book.getCategory(),book.getAuthor().getId(),book.getAvailableCopies());
    }
    public static List<DisplayBookDto> from(List<Book> books){
        return books.stream()
                .map(b->new DisplayBookDto(b.getId(),b.getName(),b.getCategory(),b.getAuthor().getId(),b.getAvailableCopies()))
                .toList();
    }
}
//prashanje za konsultacii - zashto se kreiraat novi Entiteti a ne da se zeme od repository koga se pravi toBook() na primer od Dto?????

