package mk.ukim.finki.labsemt2.model.dto.create;

import mk.ukim.finki.labsemt2.model.domain.Country;

import java.util.List;

public record CreateCountryDto (String name, String continent){
    public Country toCountry(){
        return new Country(name,continent);
    }
    public static CreateCountryDto from(Country country){
        return new CreateCountryDto(country.getName(),country.getContinent());
    }
    public static List<CreateCountryDto> from(List<Country> countryList){
        return countryList.stream()
                .map(c->new CreateCountryDto(c.getName(),c.getContinent()))
                .toList();
    }
}
