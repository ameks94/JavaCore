package com.labs;

import com.labs.limitators.DaysCountLimitator;
import com.labs.limitators.SeasonRangeLimitator;
import com.labs.limitators.TripsCountLimitator;
import com.labs.types.DaysCountType;
import com.labs.types.SkiPassType;
import com.labs.types.TripsCountType;

import java.time.LocalDate;
import java.util.UUID;

public class SkiPass {
    private UUID id;
    private SkiPassType type;
    private LocalDate expireDate;
    private SkiPassUsageLimitator limitator;

    private SkiPass(SkiPassType type, LocalDate expireDate, SkiPassUsageLimitator limitator) {
        this.type = type;
        this.expireDate = expireDate;
        this.limitator = limitator;
        this.id = UUID.randomUUID();
    }

    public static SkiPass createSkiPass(SkiPassType type, LocalDate expiredDate, TripsCountType tripsCountType) {
        if (type.equals(SkiPassType.SEASON))
            throw new RuntimeException("TripsCountType is not allowed for Season skipass");
        return new SkiPass(type, expiredDate, new TripsCountLimitator(tripsCountType));
    }

    public static SkiPass createSkiPass(SkiPassType type, LocalDate expiredDate, DaysCountType daysCountType) {
        if (type.equals(SkiPassType.SEASON))
            throw new RuntimeException("DaysCountType is not allowed for Season skipass");
        return new SkiPass(type, expiredDate, new DaysCountLimitator(daysCountType));
    }

    public static SkiPass createSeasonSkiPass(LocalDate expiredDate) {
        return new SkiPass(SkiPassType.SEASON, expiredDate, new SeasonRangeLimitator());
    }


    public boolean isTripAllowed() {
        return !isDateExpired() && limitator.isTripAvailable();
    }

    public boolean useCardIfAllowed() {
        if (!isTripAllowed())
            return false;
        limitator.useSkiPass();
        return true;
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

    private boolean isDateExpired() {
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
