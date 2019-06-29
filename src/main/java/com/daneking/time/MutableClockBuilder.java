package com.daneking.time;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalAmount;

/**
 * Based on https://github.com/robfletcher/test-clock
 * Wrappred in builder
 */
public class MutableClockBuilder {
    private Instant instant = Instant.now();
    private ZoneId zone = ZoneId.systemDefault();

    public MutableClockBuilder setInstant(Instant instant) {
        this.instant = instant;
        return this;
    }

    public MutableClockBuilder setZone(ZoneId zone) {
        this.zone = zone;
        return this;
    }

    public TestClock createMutableClock() {
        return new TestClock(instant, zone);
    }

    public Clock createdClockFixed(){
        return Clock.fixed(instant,zone);
    }

    class TestClock extends Clock {
        private Instant instant;
        private ZoneId zone;

        public TestClock() {
            this(Instant.now(), ZoneId.systemDefault());
        }

        public TestClock(Instant instant) {
            this(instant, ZoneId.systemDefault());
        }

        public TestClock(ZoneId zone) {
            this(Instant.now(), zone);
        }

        public TestClock(Instant instant, ZoneId zone) {
            this.instant = instant;
            this.zone = zone;
        }

        @Override
        public ZoneId getZone() {
            return this.zone;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return new TestClock(instant,zone);
        }

        @Override
        public Instant instant() {
            return this.instant;
        }

        /**
         *
         * @param time, can also use time.negated() to subtract time
         */
        public void adjustTime(TemporalAmount time){
            instant=instant.plus(time);
        }

        public Clock toFixed(){
            return Clock.fixed(instant,zone);
        }

    }
}