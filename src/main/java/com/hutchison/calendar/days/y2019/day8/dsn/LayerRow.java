package com.hutchison.calendar.days.y2019.day8.dsn;

import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Value
public class LayerRow {

    List<Integer> row;

    private LayerRow(List<Integer> row) {
        this.row = row;
    }

    protected static LayerRow fromList(List<Integer> row) {
        return new LayerRow(row == null ? new ArrayList<>() : row);
    }

}
