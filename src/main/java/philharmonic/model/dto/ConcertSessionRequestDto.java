package philharmonic.model.dto;

import javax.validation.constraints.NotNull;

public class ConcertSessionRequestDto {
    @NotNull
    private Long concertId;
    @NotNull
    private Long stageId;
    @NotNull
    private String showTime;

    public Long getConcertId() {
        return concertId;
    }

    public void setConcertId(Long concertId) {
        this.concertId = concertId;
    }

    public Long getStageId() {
        return stageId;
    }

    public void setStageId(Long stageId) {
        this.stageId = stageId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
}
