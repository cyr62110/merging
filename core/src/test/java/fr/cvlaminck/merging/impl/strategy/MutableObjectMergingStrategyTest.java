package fr.cvlaminck.merging.impl.strategy;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class MutableObjectMergingStrategyTest {

    private MutableObjectMergingStrategy mergingStrategy = null;

    @Before
    public void setUp() throws Exception {
        this.mergingStrategy = new MutableObjectMergingStrategy(Object.class);
    }

    @Test
    public void testGetSpecificStrategyForField() {
        mergingStrategy.setSpecificStrategyForField("A", "A");
        assertEquals("A", mergingStrategy.getSpecificStrategyForField("A"));
    }

    @Test
    public void testGetDefaultStrategyForField() {
        mergingStrategy.setDefaultStrategyForType(Object.class, "A");
        assertEquals("A", mergingStrategy.getDefaultStrategyForType(Object.class));
    }

    @Test
    public void testGetSpecificStrategyForFieldReturnsNullIfNoMatchingStrategySet() {
        assertNull(mergingStrategy.getSpecificStrategyForField("A"));
    }

    @Test
    public void testGetDefaultStrategyForTypeReturnsNullIfNoMatchingStrategySet() {
        assertNull(mergingStrategy.getDefaultStrategyForType(Object.class));
    }

    @Test
    public void testGetDefaultStrategyForTypeReturnsMostSpecificStrategy() {
        mergingStrategy.setDefaultStrategyForType(Object.class, "A");
        mergingStrategy.setDefaultStrategyForType(Collection.class, "B");

        assertEquals("A", mergingStrategy.getDefaultStrategyForType(String.class));
        assertEquals("B", mergingStrategy.getDefaultStrategyForType(List.class));
    }

    @Test
    public void testRemoveSpecificStrategyForField() {
        mergingStrategy.setSpecificStrategyForField("A", "A");
        mergingStrategy.removeSpecificStrategyForField("A");
        assertNull(mergingStrategy.getSpecificStrategyForField("A"));
    }

    @Test
    public void testRemoveDefaultStrategyForType() {
        mergingStrategy.setDefaultStrategyForType(Object.class, "A");
        mergingStrategy.removeDefaultStrategyForType(Object.class);
        assertNull(mergingStrategy.getDefaultStrategyForType(Object.class));
    }

}