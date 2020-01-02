package com.hutchison.calendar.days.y2019.day8.dsn;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class LayerRow {

    List<Pixel> row;

    private LayerRow(List<Pixel> row) {
        this.row = row;
    }

    protected static LayerRow fromList(List<Integer> row) {
        return new LayerRow(row == null ? new ArrayList<>() : row.stream()
                .map(Pixel::valueOf)
                .collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return row.toString();
    }

    public String printVisual() {
        return row.stream()
                .map(Pixel::getCharacter)
                .map(String::valueOf)
                .reduce(String::concat)
                .orElse("");
    }
}
