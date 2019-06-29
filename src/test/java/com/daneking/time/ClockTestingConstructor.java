package com.daneking.time;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class ClockTestingConstructor {
    private Clock clock;
    private LocalDateTime mockTime;

    private ClockClassToTest classUnderTest;

    @Before
    public void setUp() {
        mockTime = LocalDateTime.of(2018, 11, 10, 12, 0);
        clock = new MutableClockBuilder().setInstant(mockTime.toInstant(ZoneOffset.UTC)).createdClockFixed();
        classUnderTest = new MockClockClass(clock);

    }


    @Test
    public void shouldChangeLocalDateInConstructor() {
        assertThat(classUnderTest.getLocalDateFromConstructor(), equalTo(mockTime.toLocalDate()));
    }

    private class MockClockClass extends ClockClassToTest {
        private LocalDate localDate;

        MockClockClass(Clock clock) {
            super(clock);
        }

        @Override
        LocalDate today() {
            return localDate;
        }
    }


}
