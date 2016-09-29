package com.labs;

import com.labs.types.SkiPassType;

import java.time.LocalDate;

public abstract class SkiPass {
    protected String id;
    protected SkiPassType type;
    protected LocalDate expireDate;

    public abstract boolean isTripExists();

    public abstract void useCard();

    public boolean isDateExpired() {
        return  LocalDate.now().isAfter(getExpireDate());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
