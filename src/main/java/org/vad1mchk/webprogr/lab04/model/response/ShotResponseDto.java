package org.vad1mchk.webprogr.lab04.model.response;

import org.vad1mchk.webprogr.lab04.model.entity.Shot;
import org.vad1mchk.webprogr.lab04.util.ZonedDateTimeFormatter;

import java.time.ZoneOffset;

public class ShotResponseDto {
    private long id;
    private String ownerName;
    private String x;
    private String y;
    private String r;
    private boolean hit;
    private String creationDateTime;
    private long timeElapsedNs;

    public long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getR() {
        return r;
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

    public void setX(String x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void setR(String r) {
        this.r = r;
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
        shotResponseDto.setX(shot.getX().stripTrailingZeros().toPlainString());
        shotResponseDto.setY(shot.getY().stripTrailingZeros().toPlainString());
        shotResponseDto.setR(shot.getR().stripTrailingZeros().toPlainString());
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
