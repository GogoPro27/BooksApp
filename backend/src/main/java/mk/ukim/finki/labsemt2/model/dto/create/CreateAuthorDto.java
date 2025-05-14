package mk.ukim.finki.labsemt2.model.dto.create;

import mk.ukim.finki.labsemt2.model.domain.Author;
import mk.ukim.finki.labsemt2.model.domain.Country;

import java.util.List;

public record CreateAuthorDto (String name,String surname,Long country){
    public Author toAuthor(Country country){
        return new Author(name,surname,country);
    }
    public static CreateAuthorDto from(Author author){
        return new CreateAuthorDto(author.getName(),author.getSurname(),author.getCountry().getId());
    }
    public static List<CreateAuthorDto> from(List<Author> authors){
        return authors.stream()
                .map(a->new CreateAuthorDto(a.getName(),a.getSurname(),a.getCountry().getId()))
                .toList();
    }
}
