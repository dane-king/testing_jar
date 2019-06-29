package com.daneking.time;

import java.time.Clock;
import java.time.LocalDate;

class ClockClassToTest {
    private LocalDate localDate;

    ClockClassToTest(Clock clock){
        localDate=LocalDate.now(clock);
    }
    public ClockClassToTest(){
        this(Clock.systemDefaultZone());
    }

    LocalDate today() {
        return LocalDate.now();
    }

    public LocalDate getLocalDate() {
        return today();
    }
    public LocalDate getLocalDateFromConstructor() {
        return this.localDate;
    }
}
