package fr.cvlaminck.merging.impl.mergers.value.object;

import org.junit.Before;
import org.junit.Test;

import fr.cvlaminck.merging.api.ValueMerger;

import static org.junit.Assert.*;

public class UseRightIfLeftIsNullObjectValueMergerTest {

    private ValueMerger valueMerger = null;

    @Before
    public void setUp() {
        valueMerger = new UseRightIfLeftIsNullObjectValueMerger();
    }

    @Test
    public void testMergeReturnLeftIfNotNull() {
        final Object left = "left";
        final Object right = "right";

        assertEquals(left, valueMerger.merge(left, right));
    }

    @Test
    public void testMergeReturnRightIfLeftIsNull() {
        final Object left = null;
        final Object right = "right";

        assertEquals(right, valueMerger.merge(left, right));
    }
}