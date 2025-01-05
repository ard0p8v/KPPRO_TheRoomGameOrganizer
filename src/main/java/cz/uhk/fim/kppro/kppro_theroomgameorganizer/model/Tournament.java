package cz.uhk.fim.kppro.kppro_theroomgameorganizer.model;

import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentStatus;
import cz.uhk.fim.kppro.kppro_theroomgameorganizer.enums.TournamentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Název turnaje musí být vyplněn!")
    private String title;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date date;

    @NotBlank(message = "Místo konání turnaje musí být vyplněno!")
    private String location;

    @Enumerated(EnumType.STRING)
    private TournamentType type;

    @Enumerated(EnumType.STRING)
    private TournamentStatus status;

    @NotNull
    private int freePlaces;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @OneToMany(mappedBy = "tournament")
    private List<Registration> registrations;

    @OneToMany(mappedBy = "tournament")
    private List<Result> results;

    public Tournament() {}

    public Tournament(long id, String title, Date date, String location, TournamentType type, TournamentStatus status, int freePlaces, Game game, List<Result> results, List<Registration> registrations) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.location = location;
        this.type = type;
        this.status = status;
        this.freePlaces = freePlaces;
        this.game = game;
        this.results = results;
        this.registrations = registrations;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public TournamentType getType() {
        return type;
    }

    public void setType(TournamentType type) {
        this.type = type;
    }

    public TournamentStatus getStatus() {
        return status;
    }

    public void setStatus(TournamentStatus status) {
        this.status = status;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<Registration> registrations) {
        this.registrations = registrations;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", freePlaces=" + freePlaces +
                ", game=" + game +
                ", registrations=" + registrations +
                ", results=" + results +
                '}';
    }
}
