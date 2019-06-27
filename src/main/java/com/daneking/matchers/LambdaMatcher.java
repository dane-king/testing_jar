package com.daneking.matchers;

import java.util.Optional;
import java.util.function.Predicate;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * Taken from https://source.coveo.com/2014/10/01/java8-mockito/
 * @param <T> Type of matcher
 * use: new LambdaMatcher<>((e) -> e.getName()
 *           .equals(employee.getName()),
 *           "Employee names must match"
 * also can wrap in a function to use at class level
 *  Function<Employee>,LambdaMatcher<Employee>> isEmployeeEqual=(e)-> new LambdaMatcher<>((r) ->
 *             r.getRecipeName().equals(recipe.getRecipeName())
 *                     && r.getRecipeCategory().equals(recipe.getRecipeCategory())
 *
 *  and use assertThat(employee,isEmployeeEqual(employee))
 */
public class LambdaMatcher<T> extends BaseMatcher<T>
{
    private final Predicate<T> matcher;
    private final Optional<String> description;
    private T matchInstance;

    public LambdaMatcher(Predicate<T> matcher)
    {
        this(matcher, null);
    }

    public LambdaMatcher(Predicate<T> matcher, String description)
    {
        this.matcher = matcher;
        this.description = Optional.ofNullable(description);

    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean matches(Object argument)
    {
        this.matchInstance=(T) argument;
        return matcher.test(this.matchInstance);
    }

    @Override
    public void describeTo(Description description)
    {
        this.description
                .ifPresent(description::appendText);
    }
}