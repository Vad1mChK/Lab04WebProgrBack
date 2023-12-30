package org.vad1mchk.webprogr.lab04.model.response;

import org.vad1mchk.webprogr.lab04.model.entity.Shot;
import org.vad1mchk.webprogr.lab04.util.ZonedDateTimeFormatter;

import java.time.ZoneOffset;

public class ShotResponseDto {
    private long id;
    private String ownerName;
    private String xString;
    private String yString;
    private String rString;
    private boolean hit;
    private String creationDateTime;
    private long timeElapsedNs;

    public long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getxString() {
        return xString;
    }

    public String getyString() {
        return yString;
    }

    public String getrString() {
        return rString;
    }

    public boolean isHit() {
        return hit;
    }

    public String getCreationDateTime() {
        return creationDateTime;
    }

    public long getTimeElapsedNs() {
        return timeElapsedNs;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setxString(String xString) {
        this.xString = xString;
    }

    public void setyString(String yString) {
        this.yString = yString;
    }

    public void setrString(String rString) {
        this.rString = rString;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public void setCreationDateTime(String creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public void setTimeElapsedNs(long timeElapsedNs) {
        this.timeElapsedNs = timeElapsedNs;
    }

    public static ShotResponseDto fromShot(Shot shot) {
        ShotResponseDto shotResponseDto = new ShotResponseDto();
        shotResponseDto.setId(shot.getId());
        shotResponseDto.setOwnerName(shot.getOwner().getUsername());
        shotResponseDto.setxString(shot.getX().stripTrailingZeros().toPlainString());
        shotResponseDto.setyString(shot.getY().stripTrailingZeros().toPlainString());
        shotResponseDto.setrString(shot.getR().stripTrailingZeros().toPlainString());
        shotResponseDto.setHit(shot.isHit());
        shotResponseDto.setCreationDateTime(
                ZonedDateTimeFormatter.toString(shot.getCreationDateTime().atZone(
                        ZoneOffset.ofTotalSeconds(shot.getZoneOffsetSeconds())
                ))
        );
        shotResponseDto.setTimeElapsedNs(shot.getTimeElapsedNanoseconds());
        return shotResponseDto;
    }
}
