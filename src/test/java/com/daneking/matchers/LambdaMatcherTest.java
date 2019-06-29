package com.daneking.matchers;

import org.junit.Before;
import org.junit.Test;

import java.util.function.Predicate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class LambdaMatcherTest {

    private TestObj testObj;
    private Predicate<TestObj> predicate;

    @Before
    public void setUp() {
        testObj=new TestObj("Name", 25);
        predicate = (e) -> e.getName().equals(testObj.getName());
    }

    @Test
    public void shouldBeAbleToMatchItself() {
        LambdaMatcher<TestObj> matcher = new LambdaMatcher<>(predicate, "Employee names must match");
        assertThat(matcher.matches(testObj),equalTo(true));
    }
    @Test
    public void shouldBeMatchDifferentInstance() {
        TestObj differentObj=new TestObj("Name", 25);
        Predicate newPredicate=predicate.and(e->e.getAge().equals(testObj.getAge()));
        LambdaMatcher<TestObj> matcher = new LambdaMatcher<>(newPredicate, "Employee names must match");
        assertThat(matcher.matches(differentObj),equalTo(true));
    }
    @Test
    public void shouldBeNotMatchDifferentInstanceWithDifferentParams() {
        TestObj differentObj=new TestObj("Other Name", 20);
        Predicate newPredicate=predicate.and(e->e.getAge().equals(testObj.getAge()));
        LambdaMatcher<TestObj> matcher = new LambdaMatcher<>(newPredicate, "Employee names must match");
        assertThat(matcher.matches(differentObj),equalTo(false));
    }
    @Test
    public void shouldBeMatchDifferentObj() {
        OtherObj differentObj=new OtherObj("Name", 25);
        LambdaMatcher<OtherObj> matcher = new LambdaMatcher<>((e) -> e.getName().equals(testObj.getName()) && e.getAge().equals(testObj.getAge()), "Employee names must match");
        assertThat(matcher.matches(differentObj),equalTo(true));
    }
    private class TestObj {
        private String name;
        private Integer age;

        public TestObj(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public TestObj(String name) {
            this.name = name;
            this.age=0;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }

    private class OtherObj {
        private String name;
        private Integer age;

        public OtherObj(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
