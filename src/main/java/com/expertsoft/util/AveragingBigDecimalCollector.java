package com.expertsoft.util;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * This collector is designed to compute average value from a stream of <code>java.math.BigDecimal</code>
 */
public class AveragingBigDecimalCollector implements Collector<BigDecimal, BigDecimal[], BigDecimal> {

    @Override
    public Supplier<BigDecimal[]> supplier() {
        return () -> new BigDecimal[] {BigDecimal.ZERO, BigDecimal.ZERO};
    }

    @Override
    public BiConsumer<BigDecimal[], BigDecimal> accumulator() {
        return (acc, b) -> {
            acc[0] = acc[0].add(b);
            acc[1] = acc[1].add(BigDecimal.ONE);
        };
    }

    @Override
    public BinaryOperator<BigDecimal[]> combiner() {
        return (acc1, acc2) ->
                new BigDecimal[] {
                    acc1[0].add(acc2[0]),
                    acc1[1].add(acc2[1])
        };
    }

    @Override
    public Function<BigDecimal[], BigDecimal> finisher() {
        return acc -> acc[1].intValue() > 0 ?
                acc[0].divide(acc[1], BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.UNORDERED, Characteristics.CONCURRENT));
    }
}
