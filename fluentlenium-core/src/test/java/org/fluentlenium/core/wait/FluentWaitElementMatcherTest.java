package org.fluentlenium.core.wait;


import org.assertj.core.api.ThrowableAssert;
import org.fluentlenium.adapter.FluentAdapter;
import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.FluentThread;
import org.fluentlenium.core.conditions.WebElementConditions;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.core.search.Search;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FluentWaitElementMatcherTest {
    @Mock
    private Search search;

    @Mock
    private Fluent fluent;

    private FluentWait wait;

    @Mock
    private FluentAdapter fluentAdapter;

    @Mock
    private FluentWebElement fluentWebElement;

    @Mock
    private WebElement element;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        FluentThread.set(fluentAdapter);

        wait = new FluentWait(fluent, search);

        when(fluentWebElement.conditions()).thenReturn(new WebElementConditions(fluentWebElement));
        when(fluentWebElement.getElement()).thenReturn(element);
    }

    @After
    public void after() {
        reset(search);
        reset(fluent);
        reset(fluentWebElement);
    }


    @Test
    public void isEnabled() {
        final FluentWaitElementMatcher matcher = new FluentWaitElementMatcher(search, wait, fluentWebElement);
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                matcher.isEnabled();
            }
        }).isExactlyInstanceOf(TimeoutException.class);

        verify(fluentWebElement, atLeastOnce()).isEnabled();

        when(fluentWebElement.isEnabled()).thenReturn(true);
        matcher.isEnabled();

        verify(fluentWebElement, atLeastOnce()).isEnabled();
    }

    @Test
    public void isDisplayed() {
        final FluentWaitElementMatcher matcher = new FluentWaitElementMatcher(search, wait, fluentWebElement);
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                matcher.isDisplayed();
            }
        }).isExactlyInstanceOf(TimeoutException.class);

        verify(fluentWebElement, atLeastOnce()).isDisplayed();

        when(fluentWebElement.isDisplayed()).thenReturn(true);
        matcher.isDisplayed();

        verify(fluentWebElement, atLeastOnce()).isDisplayed();
    }

    @Test
    public void isClickable() {
        final FluentWaitElementMatcher matcher = new FluentWaitElementMatcher(search, wait, fluentWebElement);
        assertThatThrownBy(new ThrowableAssert.ThrowingCallable() {
            @Override
            public void call() throws Throwable {
                matcher.isClickable();
            }
        }).isExactlyInstanceOf(TimeoutException.class);

        verify(fluentWebElement, atLeastOnce()).isClickable();

        when(fluentWebElement.isClickable()).thenReturn(true);
        matcher.isClickable();

        verify(fluentWebElement, atLeastOnce()).isClickable();
    }
}
