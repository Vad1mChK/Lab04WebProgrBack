package org.vad1mchk.webprogr.lab04.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Entity class representing a disposed JSON Web Token.
 */
@Entity
@Table(name = "w_jwt", schema = "s322864")
public class DisposedJwt {
    private String jwt;

    /**
     * Gets the JWT string.
     * @return The JWT string.
     */
    @Id
    @NotNull
    @Column(columnDefinition = "TEXT")
    public String getJwt() {
        return jwt;
    }

    /**
     * Sets the JWT string.
     * @param jwt The JWT string to set.
     */
    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
