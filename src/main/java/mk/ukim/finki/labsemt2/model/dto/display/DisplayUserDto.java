package mk.ukim.finki.labsemt2.model.dto.display;

import mk.ukim.finki.labsemt2.model.domain.Book;
import mk.ukim.finki.labsemt2.model.domain.Enum.Role;
import mk.ukim.finki.labsemt2.model.domain.User;

import java.util.List;

public record DisplayUserDto (String username, String name, Role role, List<Long>wishlist,List<Long>rented){

    public static DisplayUserDto from(User user){
        return new DisplayUserDto(
                user.getUsername(),
                user.getName(),
                user.getRole(),
                user.getBookWishlist().stream().map(Book::getId).toList(),
                user.getRentedBooks().stream().map(Book::getId).toList()
                );
    }
}
