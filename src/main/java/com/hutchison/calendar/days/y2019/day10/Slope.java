package com.hutchison.calendar.days.y2019.day10;

import lombok.Builder;
import lombok.Value;

import java.util.Objects;
import java.util.stream.IntStream;

import static java.lang.Integer.signum;
import static java.lang.Math.abs;
import static java.lang.Math.min;

@Value
public class Slope {
    int rise;
    int run;

    @Builder
    private Slope(int rise, int run) {
        this.rise = rise;
        this.run = run;
    }

    public Float getQuotient() {
        return (float) run / rise;
    }

    public int compareTo(Slope s) {
        return compare(this, s);
    }

    public Quadrant getQuadrant() {
        return Quadrant.fromRiseRun(this.rise, this.run);
    }

    private static int compare(Slope s1, Slope s2) {
        return s1.getAngle().compareTo(s2.getAngle());
    }

    private Double getAngle() {
        Double angle = this.getQuadrant().getQuadrantAngle().apply(this);
//        System.out.println(String.format("%s angle: %.5f", this.toString(), angle));
        return angle;
    }

    @Override
    public String toString() {
        int quadNum = Quadrant.indexOf(this.getQuadrant()) + 1;
        return String.format("Q%d - [%d, %d]", quadNum, rise, run);
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

        return this.getQuotient().equals(s.getQuotient()) &&
                (this.run >= 0) == (s.run >= 0) &&
                (this.rise >= 0) == (s.rise >= 0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getQuotient());

    }

    static class SlopeBuilder {

        public Slope build() {
            int rise = this.rise;
            int run = this.run;
            int gcd = findGCD(rise, run);
            while (gcd != -1) {
                rise = rise / gcd;
                run = run / gcd;
                gcd = findGCD(rise, run);
            }
            return new Slope(
                    run == 0 ? signum(rise) : rise,
                    rise == 0 ? signum(run) : run
            );
        }

        private int findGCD(int n1, int n2) {
            if (n1 == n2) return -1;
            int min = min(abs(n1), abs(n2));
            if (min < 2) return -1;
            return IntStream.range(2, min + 1)
                    .filter(i -> n1 % i == 0 && n2 % i == 0)
                    .reduce((first, second) -> second)
                    .orElse(-1);
        }
    }
}
