package fr.cvlaminck.merging.impl.dflt;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.cvlaminck.merging.api.ValueMerger;
import fr.cvlaminck.merging.api.ValueMergers;
import fr.cvlaminck.merging.impl.DefaultValueMergers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DefaultValueMergersTest {

    private ValueMergers valueMergers = null;

    private ValueMerger mockObjectFieldMerger() {
        ValueMerger objectValueMerger = mock(ValueMerger.class);
        when(objectValueMerger.getType()).thenReturn((Class) Object.class);
        when(objectValueMerger.getMergingStrategy()).thenReturn("A");
        return objectValueMerger;
    }

    private ValueMerger mockObjectFieldMergerWithAnotherStrategy() {
        ValueMerger objectValueMerger = mock(ValueMerger.class);
        when(objectValueMerger.getType()).thenReturn((Class) Object.class);
        when(objectValueMerger.getMergingStrategy()).thenReturn("B");
        return objectValueMerger;
    }

    private ValueMerger mockListFieldMerger() {
        ValueMerger listValueMerger = mock(ValueMerger.class);
        when(listValueMerger.getType()).thenReturn((Class)List.class);
        when(listValueMerger.getMergingStrategy()).thenReturn("A");
        return listValueMerger;
    }

    private ValueMerger mockSetFieldMerger() {
        ValueMerger setValueMerger = mock(ValueMerger.class);
        when(setValueMerger.getType()).thenReturn((Class) Set.class);
        when(setValueMerger.getMergingStrategy()).thenReturn("A");
        return setValueMerger;
    }

    private ValueMerger mockCollectionFieldMerger() {
        ValueMerger collectionValueMerger = mock(ValueMerger.class);
        when(collectionValueMerger.getType()).thenReturn((Class) Collection.class);
        when(collectionValueMerger.getMergingStrategy()).thenReturn("A");
        return collectionValueMerger;
    }

    @Before
    public void setUp() {
        this.valueMergers = new DefaultValueMergers();
    }

    @Test
    public void testGetMergerReturnsNullIfNoMatchingMergerRegistered() {
        valueMergers.registerFieldMerger(mockListFieldMerger());

        //Wrong object
        assertNull(valueMergers.getMerger(Set.class, "A"));

        //Wrong strategy
        assertNull(valueMergers.getMerger(List.class, "B"));

        //Both parameters
        assertNull(valueMergers.getMerger(Set.class, "B"));
    }

    @Test
    public void testGetMergerReturnsMergerWithMatchingStrategy() {
        final ValueMerger aValueMerger = mockObjectFieldMerger();
        final ValueMerger bValueMerger = mockObjectFieldMergerWithAnotherStrategy();

        valueMergers.registerFieldMerger(aValueMerger);
        valueMergers.registerFieldMerger(bValueMerger);

        assertEquals(aValueMerger, valueMergers.getMerger(Object.class, "A"));
        assertEquals(bValueMerger, valueMergers.getMerger(Object.class, "B"));
    }

    @Test
    public void testGetMergerReturnsMergerWithMatchingClassOrSubclasses() {
        final ValueMerger listValueMerger = mockListFieldMerger();
        final ValueMerger setValueMerger = mockSetFieldMerger();

        valueMergers.registerFieldMerger(listValueMerger);
        valueMergers.registerFieldMerger(setValueMerger);

        assertEquals(listValueMerger, valueMergers.getMerger(List.class, "A"));
        assertEquals(listValueMerger, valueMergers.getMerger(ArrayList.class, "A"));
    }

    @Test
    public void testGetMergerGivesClosestParentClass() {
        final ValueMerger listValueMerger = mockListFieldMerger();
        final ValueMerger setValueMerger = mockSetFieldMerger();
        final ValueMerger collectionValueMerger = mockCollectionFieldMerger();

        valueMergers.registerFieldMerger(collectionValueMerger);
        valueMergers.registerFieldMerger(listValueMerger);
        valueMergers.registerFieldMerger(setValueMerger);

        assertEquals(listValueMerger, valueMergers.getMerger(ArrayList.class, "A"));
        assertEquals(setValueMerger, valueMergers.getMerger(HashSet.class, "A"));
        assertEquals(collectionValueMerger, valueMergers.getMerger(ArrayDeque.class, "A"));
    }

    @Test
    public void testRegisterFieldMerger() {
        assertNull(valueMergers.getMerger(Object.class, "A"));
        valueMergers.registerFieldMerger(mockObjectFieldMerger());
        assertNotNull(valueMergers.getMerger(Object.class, "A"));
    }

    @Test
    public void testUnregisterFieldMerger() {
        valueMergers.registerFieldMerger(mockObjectFieldMerger());

        assertNotNull(valueMergers.getMerger(Object.class, "A"));
        valueMergers.unregisterFieldMerger(Object.class, "A");
        assertNull(valueMergers.getMerger(Object.class, "A"));
    }

    @Test
    public void testUnregisterAllFieldMergers() {
        valueMergers.registerFieldMerger(mockListFieldMerger());
        valueMergers.registerFieldMerger(mockSetFieldMerger());

        assertNotNull(valueMergers.getMerger(List.class, "A"));
        assertNotNull(valueMergers.getMerger(Set.class, "A"));
        valueMergers.unregisterAllFieldMergers();
        assertNull(valueMergers.getMerger(List.class, "A"));
        assertNull(valueMergers.getMerger(Set.class, "A"));
    }

}
