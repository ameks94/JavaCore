package com.labs;

import com.labs.types.SkiPassType;

import java.time.LocalDate;
import java.util.UUID;

public class SkiPass {
    protected UUID id;
    protected SkiPassType type;
    protected LocalDate expireDate;
    protected SkiPassUsageLimitator limitator;

    public SkiPass(SkiPassType type, LocalDate expireDate, SkiPassUsageLimitator limitator) {
        this.type = type;
        this.expireDate = expireDate;
        this.limitator = limitator;
        this.id = UUID.randomUUID();
    }

    public boolean isTripAllowed() {
        return !isDateExpired() && limitator.isTripAvailable();
    }

    public void useCard() {
        limitator.useSkiPass();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SkiPassType getType() {
        return type;
    }

    public void setType(SkiPassType type) {
        this.type = type;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    protected boolean isDateExpired() {
        return  LocalDate.now().isAfter(expireDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SkiPass skiPass = (SkiPass) o;

        if (id != null ? !id.equals(skiPass.id) : skiPass.id != null) return false;
        return type == skiPass.type;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
