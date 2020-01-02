package com.hutchison.calendar.days.y2019.day7.amplifier;

import com.hutchison.calendar.intcode.IntcodeComputer;
import lombok.Value;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class AmplifierSeries {

    List<Amplifier> amplifiers;
    PhaseSequence phaseSequence;

    private AmplifierSeries(List<Amplifier> amplifiers, PhaseSequence phaseSequence) {
        this.amplifiers = Collections.unmodifiableList(amplifiers);
        this.phaseSequence = phaseSequence;
    }

    public double getOutput() {
        double output = 0;
        List<Amplifier> tempAmps = amplifiers.stream().map(a -> a.toBuilder().build()).collect(Collectors.toList());
        while (true) {
            for (Amplifier amplifier : tempAmps) {
                double returnedValue = amplifier.compute(output);
                if (returnedValue == -1) return output;
                output = returnedValue;
            }
        }
    }

    public static AmplifierSeriesBuilder builder() {
        return new AmplifierSeriesBuilder();
    }

    public static class AmplifierSeriesBuilder {

        private List<Double> codes;
        private PhaseSequence phaseSequence;

        private AmplifierSeriesBuilder() {
        }

        public AmplifierSeriesBuilder codes(List<Double> codes) {
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
