package com.hutchison.calendar.days.y2019.day7.amplifier;

import com.hutchison.calendar.intcode.IntcodeComputer;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Value
public class AmplifierSeries {

    List<Amplifier> amplifiers;
    PhaseSequence phaseSequence;

    private AmplifierSeries(List<Amplifier> amplifiers, PhaseSequence phaseSequence) {
        this.amplifiers = Collections.unmodifiableList(amplifiers);
        this.phaseSequence = phaseSequence;
    }

    public int getOutput() {
        AtomicInteger output = new AtomicInteger();
        output.set(0);
        amplifiers.forEach(amplifier -> output.set(amplifier.compute(output.get())));
        return output.get();
    }

    public static AmplifierSeriesBuilder builder() {
        return new AmplifierSeriesBuilder();
    }

    public static class AmplifierSeriesBuilder {

        private List<Integer> codes;
        private PhaseSequence phaseSequence;

        private AmplifierSeriesBuilder() {
        }

        public AmplifierSeriesBuilder codes(List<Integer> codes) {
            this.codes = codes;
            return this;
        }

        public AmplifierSeriesBuilder phases(PhaseSequence phaseSequence) {
            this.phaseSequence = phaseSequence;
            return this;
        }

        public AmplifierSeries build() {
            if (this.phaseSequence == null)
                throw new RuntimeException("PhaseSequence required to build AmplifierSeries");
            if (codes == null) throw new RuntimeException("Codes required to build AmplifierSeries");
            List<Amplifier> amplifiers = phaseSequence.getPhases().stream()
                    .map(phase -> Amplifier.builder()
                            .computer(IntcodeComputer.fromList(codes))
                            .phase(phase)
                            .build())
                    .collect(Collectors.toList());
            return new AmplifierSeries(amplifiers, phaseSequence);
        }
    }
}
