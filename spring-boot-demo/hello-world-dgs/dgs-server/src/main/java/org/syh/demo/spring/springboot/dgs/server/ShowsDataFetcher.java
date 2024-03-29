package org.syh.demo.spring.springboot.dgs.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.syh.demo.spring.springboot.dgs.type.types.Company;
import org.syh.demo.spring.springboot.dgs.type.types.Director;
import org.syh.demo.spring.springboot.dgs.type.types.Show;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

@DgsComponent
public class ShowsDataFetcher {
    private final List<Show> shows;

    public ShowsDataFetcher() {
        Company companyX = new Company("CompanyX", "US");
        Company companyY = new Company("CompanyY", "UK");

        Director directorX = new Director("DirectorX", 32, companyX);
        Director directorY = new Director("DirectorY", 45, companyY);

        Show showX = new Show("ShowX", 2021, directorX);
        Show showY = new Show("ShowY", 2022, directorY);
        Show showZ = new Show("ShowZ", 2023, directorY);

        this.shows = new ArrayList<>();
        this.shows.add(showX);
        this.shows.add(showY);
        this.shows.add(showZ);
    }

    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        if (titleFilter == null) {
            return shows;
        }

        return shows
            .stream()
            .filter(show -> show.getTitle().contains(titleFilter))
            .collect(Collectors.toList());
    }

    @DgsMutation
    public Show addShow(
        @InputArgument String title, 
        @InputArgument Integer year, 
        @InputArgument Director director) {
        validate(title, year, director);
        Show show = new Show(title, year, director);
        shows.add(show);
        return show;
    }

    private void validate(String title, Integer year, Director director) {
        if (title.length() == 0) {
            throw new InvalidInputException("title", title);
        }
        
        if (year < 1000 || year > 9999) {
            throw new InvalidInputException("year", year.toString());
        }
    }
}
