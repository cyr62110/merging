package fr.cvlaminck.merging.impl.mergers.value.object;

import org.junit.Before;
import org.junit.Test;

import fr.cvlaminck.merging.api.ValueMerger;

import static org.junit.Assert.*;

public class AlwaysUseLeftObjectValueMergerTest {

    private ValueMerger valueMerger = null;

    @Before
    public void setUp() throws Exception {
        valueMerger = new AlwaysUseLeftObjectValueMerger();
    }

    @Test
    public void testMerge() {
        final Object left = "left";
        final Object right = "right";

        assertEquals(left, valueMerger.merge(left, right));
    }

}