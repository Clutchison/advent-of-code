package com.hutchison.calendar.days.day2;

import lombok.Getter;
import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Getter
@Value
class IdCount {

    private int twoCount;
    private int threeCount;
    private int product;
    private Set<String> ids;

    private IdCount(int twoCount,
                    int threeCount,
                    int product,
                    Set<String> ids) {
        this.twoCount = twoCount;
        this.threeCount = threeCount;
        this.product = product;
        this.ids = ids;
    }

    static IdCount count(Set<String> ids) {
        AtomicInteger twoCount = new AtomicInteger();
        AtomicInteger threeCount = new AtomicInteger();
        ids.forEach(id -> {
            Set<Integer> integerList = id.chars().mapToObj(i -> (char) i)
                    .collect(Collectors.groupingBy(c -> c)).values().stream()
                    .map(List::size)
                    .collect(Collectors.toSet());
            if (integerList.contains(2)) twoCount.getAndIncrement();
            if (integerList.contains(3)) threeCount.getAndIncrement();
        });
        return new IdCount(
                twoCount.get(),
                threeCount.get(),
                twoCount.get() * threeCount.get(),
                ids
        );
    }
}
