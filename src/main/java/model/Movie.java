package model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import model.support.WithCode;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;

import static java.util.stream.Collectors.toUnmodifiableList;

@Schema(
        description = "Movie."
)
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "movie_uk", columnNames = {"code"}),
})
@Getter
@Setter
public class Movie extends WithCode {
    @Column(length = 100)
    public String title;

    @Column(length = 5000)
    public String description;

    public static List<Movie> listByCodes(List<Movie> passed) {
        return find("code in ?1", collectCodes(passed)).list();
    }

    private static List<String> collectCodes(List<Movie> passed) {
        return passed.stream().map(WithCode::getCode).collect(toUnmodifiableList());
    }

}
