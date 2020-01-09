package com.hutchison.calendar.days.y2019.day11;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Getter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Hull {
    final List<List<Panel>> panels;
    int width;
    int height;

    private Hull(List<List<Panel>> panels, int width, int height) {
        this.panels = panels;
        this.width = width;
        this.height = height;
    }

    public Panel getPanel(int x, int y) {
        if (y < 0) throw new RuntimeException("Y value must be positive");
        if (x < 0) throw new RuntimeException("X value must be positive.");
        if (y > panels.size() - 1) createPanelRows(y - panels.size() - 1);
        return panels.get(y).get(x);
    }

    private void createPanelRows(int total) {
        IntStream.range(0, total).forEach(i -> panels.add(new ArrayList<>(
                IntStream.range(0, width)
                        .mapToObj(j -> new Panel())
                        .collect(Collectors.toList())
        )));
        height += total;
    }

    public static Hull fromDimensions(int width, int height) {
        return new Hull(
                IntStream.range(0, height)
                        .mapToObj(i ->
                                IntStream.range(0, width)
                                        .mapToObj(j -> new Panel())
                                        .collect(Collectors.toList()))
                        .collect(Collectors.toList()),
                width,
                height
        );
    }
}
