package fr.cvlaminck.merging.impl.strategy;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbstractObjectMergingStrategyTest {

    @Test
    public void testGetStrategyForFieldReturnsSpecificStrategyIfSet() {
        AbstractObjectMergingStrategy strategy = mock(AbstractObjectMergingStrategy.class);
        when(strategy.getSpecificStrategyForField("B")).thenReturn("A");
        when(strategy.getStrategyForField("B", Object.class)).thenCallRealMethod();

        assertEquals("A", strategy.getStrategyForField("B", Object.class));
    }

    @Test
    public void testGetStrategyForFieldReturnsDefaultStrategyIfNoSpecificHasBeenSet() {
        AbstractObjectMergingStrategy strategy = mock(AbstractObjectMergingStrategy.class);
        when(strategy.getDefaultStrategyForType(Object.class)).thenReturn("C");
        when(strategy.getStrategyForField("B", Object.class)).thenCallRealMethod();

        assertEquals("C", strategy.getStrategyForField("B", Object.class));
    }

    @Test(expected = RuntimeException.class)
    public void testGetStrategyForFieldThrowsIfNoSpecificNorDefaultHasBeenSet() {
        AbstractObjectMergingStrategy strategy = mock(AbstractObjectMergingStrategy.class);
        when(strategy.getStrategyForField("B", Object.class)).thenCallRealMethod();

        assertEquals("C", strategy.getStrategyForField("B", Object.class));
    }


}