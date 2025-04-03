package mk.ukim.finki.labsemt2.service.application.impl;

import mk.ukim.finki.labsemt2.model.domain.Country;
import mk.ukim.finki.labsemt2.model.dto.create.CreateCountryDto;
import mk.ukim.finki.labsemt2.model.dto.display.DisplayCountryDto;
import mk.ukim.finki.labsemt2.service.application.ICountryApplicationService;
import mk.ukim.finki.labsemt2.service.domain.impl.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationService implements ICountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationService(CountryService countryService) {
        this.countryService = countryService;
    }


    @Override
    public List<DisplayCountryDto> findAll() {
        return countryService.findAll().stream()
                .map(DisplayCountryDto::from)
                .toList();
    }

    @Override
    public Optional<DisplayCountryDto> findById(long id) {
        return countryService.findById(id).map(DisplayCountryDto::from);
    }

    @Override
    public void deleteById(long id) {
        countryService.deleteById(id);
    }

    @Override
    public Optional<DisplayCountryDto> save(CreateCountryDto country) {
        Country country1 = country.toCountry();
        return countryService.save(country1).map(DisplayCountryDto::from);
    }

    @Override
    public Optional<DisplayCountryDto> update(long id, CreateCountryDto country) {

        return countryService.update(id,country.toCountry()).map(DisplayCountryDto::from);
    }

}
