package org.vad1mchk.webprogr.lab04.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "w_shot", schema = "s322864")
public class Shot {
    public static final int FRACTIONAL_DIGITS = 6;

    private long id;
    private User owner;
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal r;
    private boolean hit;
    private LocalDateTime creationDateTime;
    private int zoneOffsetSeconds;
    private long timeElapsedNanoseconds;

    public Shot() {
        super();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "owner_id")
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Column(columnDefinition = "TEXT")
    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    @Column(columnDefinition = "TEXT")
    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    @Column(columnDefinition = "TEXT")
    public BigDecimal getR() {
        return r;
    }

    public void setR(BigDecimal r) {
        this.r = r;
    }

    @Column
    public boolean isHit() {
        return hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    @Column(name = "creation_date_time")
    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Column(name = "zone_offset_seconds")
    public int getZoneOffsetSeconds() {
        return zoneOffsetSeconds;
    }

    public void setZoneOffsetSeconds(int zoneOffsetSeconds) {
        this.zoneOffsetSeconds = zoneOffsetSeconds;
    }

    @Column(name = "time_elapsed_ns")
    public long getTimeElapsedNanoseconds() {
        return timeElapsedNanoseconds;
    }

    public void setTimeElapsedNanoseconds(long timeElapsedNanoseconds) {
        this.timeElapsedNanoseconds = timeElapsedNanoseconds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shot)) return false;
        Shot shot = (Shot) o;
        return id == shot.id &&
                hit == shot.hit &&
                timeElapsedNanoseconds == shot.timeElapsedNanoseconds &&
                zoneOffsetSeconds == shot.zoneOffsetSeconds &&
                Objects.equals(x, shot.x) &&
                Objects.equals(y, shot.y) &&
                Objects.equals(r, shot.r) &&
                Objects.equals(creationDateTime, shot.creationDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, hit, creationDateTime, zoneOffsetSeconds, timeElapsedNanoseconds);
    }

    @Override
    public String toString() {
        return "Shot{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hit=" + hit +
                ", creationDateTime=" + creationDateTime +
                ", zoneOffsetSeconds=" + zoneOffsetSeconds +
                ", timeElapsedNanoseconds=" + timeElapsedNanoseconds +
                '}';
    }
}