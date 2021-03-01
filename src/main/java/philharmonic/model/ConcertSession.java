package philharmonic.model;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "concert_sessions")
public class ConcertSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "concert_id")
    private Concert concert;
    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;
    private LocalDateTime showTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Concert getConcert() {
        return concert;
    }

    public void setConcert(Concert concert) {
        this.concert = concert;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        ConcertSession that = (ConcertSession) object;
        return Objects.equals(id, that.id) && Objects.equals(concert, that.concert)
                && Objects.equals(stage, that.stage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, concert, stage);
    }

    @Override
    public String toString() {
        return "ConcertSession{" + "id=" + id + ", concert=" + concert
                + ", Stage=" + stage + ", showTime=" + showTime + '}';
    }
}
