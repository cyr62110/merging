package fr.cvlaminck.merging.impl.mergers.object;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import fr.cvlaminck.merging.api.ObjectMerger;
import fr.cvlaminck.merging.api.ObjectMergingStrategy;
import fr.cvlaminck.merging.api.ValueMerger;
import fr.cvlaminck.merging.api.ValueMergers;

import static org.junit.Assert.*;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultObjectMergerTest {

    private ObjectMerger objectMerger = null;

    private ObjectMergingStrategy mockObjectMergingStrategy() {
        ObjectMergingStrategy strategy = mock(ObjectMergingStrategy.class);
        when(strategy.getObject()).thenReturn((Class)TestSubject.class);
        when(strategy.getStrategyForField("withGetterAndSetter", String.class)).thenReturn("A");
        return strategy;
    }

    @Test(expected = RuntimeException.class)
    public void testMergeThrowsRuntimeExceptionIfRightAndLeftDoesNotMatchStrategyObject() {
        ObjectMergingStrategy strategy = mock(ObjectMergingStrategy.class);
        when(strategy.getObject()).thenReturn((Class)List.class);

        new DefaultObjectMerger().merge(new TestSubject(), new TestSubject(), strategy);
    }

    @Test(expected = RuntimeException.class)
    public void testMergeThrowsRuntimeExceptionWithNoMatchingValueMergerConfigured() {
        ObjectMergingStrategy strategy = mockObjectMergingStrategy();

        new DefaultObjectMerger().merge(new TestSubject(), new TestSubject(), strategy);
    }

    @Test
    public void testMerge() {
        String left = "before";
        String right = "after";

        ValueMerger valueMerger = mock(ValueMerger.class);
        when(valueMerger.getType()).thenReturn((Class)Object.class);
        when(valueMerger.getMergingStrategy()).thenReturn("A");
        when(valueMerger.merge(left, right)).thenReturn(right);

        ValueMergers valueMergers = mock(ValueMergers.class);
        when(valueMergers.getMerger(String.class, "A")).thenReturn(valueMerger);

        ObjectMergingStrategy strategy = mockObjectMergingStrategy();

        TestSubject merged = new DefaultObjectMerger(valueMergers).merge(new TestSubject(left, left, left), new TestSubject(right, right, right), strategy);
        assertEquals(right, merged.withGetterAndSetter);
        assertEquals(left, merged.withGetterOnly);
        assertEquals(left, merged.withSetterOnly);
    }

    public static class TestSubject {

        public String withGetterAndSetter;

        public String withGetterOnly;

        public String withSetterOnly;

        public TestSubject() {
            this(null, null, null);
        }

        public TestSubject(String withGetterAndSetter, String withGetterOnly, String withSetterOnly) {
            this.withGetterAndSetter = withGetterAndSetter;
            this.withGetterOnly = withGetterOnly;
            this.withSetterOnly = withSetterOnly;
        }

        public String getWithGetterAndSetter() {
            return withGetterAndSetter;
        }

        public void setWithGetterAndSetter(String withGetterAndSetter) {
            this.withGetterAndSetter = withGetterAndSetter;
        }

        public String getWithGetterOnly() {
            return withGetterOnly;
        }


        public void setWithSetterOnly(String withSetterOnly) {
            this.withSetterOnly = withSetterOnly;
        }
    }

}