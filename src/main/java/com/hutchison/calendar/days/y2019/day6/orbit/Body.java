package com.hutchison.calendar.days.y2019.day6.orbit;

import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Value
public class Body {

    String name;
    Body parent;
    boolean isCOM;

    @Builder
    private Body(String name, Body parent) {
        this.name = name;
        this.parent = parent;
        this.isCOM = parent == null;
    }

    public int getTotalParents() {
        return getTotalParents(0);
    }

    private int getTotalParents(int total) {
        if (isCOM) return total;
        return parent.getTotalParents(total + 1);
    }

    public List<Body> getParents() {
        List<Body> parents = new ArrayList<>();
        getParents(parents);
        return parents;
    }

    private void getParents(List<Body> parents) {
        if (!isCOM) {
            parents.add(this.parent);
            this.parent.getParents(parents);
        }
    }

    public static class BodyBuilder {
        public Body build() {
            if (this.name == null) throw new RuntimeException("Name required for body.");
            return new Body(this.name, this.parent);
        }
    }
}
