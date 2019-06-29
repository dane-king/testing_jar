package com.daneking.time;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Clock;
import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class MutableClockBuilderTest {

    public static final Duration TWO_HOURS = Duration.ofHours(2);
    private MutableClockBuilder.TestClock clock;
    private Clock fixed;

    @Before
    public void setUp() {
        clock = new MutableClockBuilder().createMutableClock();
        fixed = clock.toFixed();
    }

    @Test
    public void shouldBeAbleToAddTime() {
        clock.adjustTime(TWO_HOURS);
        assertThat(clock.millis(), equalTo(fixed.millis()+ TWO_HOURS.toMillis()));
    }

    @Test
    public void shouldBeAbleToMinusTime() {
        clock.adjustTime(TWO_HOURS.negated());
        assertThat(clock.millis(), equalTo(fixed.millis()- TWO_HOURS.toMillis()));
    }

    @Test
    public void shouldBeNowIfDefaults() {
        assertThat(clock.millis(), equalTo(fixed.millis()));
    }

}
