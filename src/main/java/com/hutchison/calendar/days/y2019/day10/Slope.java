package com.hutchison.calendar.days.y2019.day10;

import lombok.Value;

import java.util.Objects;

@Value
public class Slope {
    int run;
    int rise;

    public Float getQuotient() {
        return (float) run / rise;
    }

    public int compareTo(Slope s) {
        return compare(this, s);
    }

    public Quadrant getQuadrant() {
        return Quadrant.fromRiseRun(this.rise, this.run);
    }

    // Returns
    // -1 if s1 < s2
    // 0 if equal
    // 1 if s1 > s2
    private static int compare(Slope s1, Slope s2) {
        int i = s1.getQuadrant().compareTo(s2.getQuadrant());
        if (i == 0) {
            return s1.getQuotient().compareTo(s2.getQuotient());
        } else return i;

    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Slope)) {
            return false;
        }

        Slope s = (Slope) o;

        return this.getQuotient() == s.getQuotient() &&
                (this.run >= 0) == (s.run >= 0) &&
                (this.rise >= 0) == (s.rise >= 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuotient());

    }

}
