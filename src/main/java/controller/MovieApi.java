package controller;

import model.Movie;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.listAll;
import static java.util.stream.Collectors.toUnmodifiableList;

@Path("/api/movies")
@RequestScoped
public class MovieApi {
    @Operation(
            summary = "List movies",
            description = "List movies."
    )
    @GET
    @Transactional
    public List<Movie> list() {
        return listAll();
    }

    @Operation(
            summary = "Add movies",
            description = "Add movies with system-generated `code`."
    )
    @POST
    @Transactional
    public List<Movie> add(@RequestBody(required = true) List<Movie> passed) {
        return passed.stream().map(p -> persist(p, new Movie())).collect(toUnmodifiableList());
    }

    private Movie persist(Movie from, Movie to) {
        to.setCode(from.getCode());

        to.setTitle(from.getTitle());
        to.setDescription(from.getDescription());
        to.persist();

        return to;
    }
}
