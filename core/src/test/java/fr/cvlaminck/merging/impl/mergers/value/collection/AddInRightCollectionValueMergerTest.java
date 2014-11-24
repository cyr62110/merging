package fr.cvlaminck.merging.impl.mergers.value.collection;

import fr.cvlaminck.merging.api.ValueMerger;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.junit.Assert.*;

public class AddInRightCollectionValueMergerTest {

    private ValueMerger valueMerger = null;

    @Before
    public void setUp() {
        this.valueMerger = new AddInRightCollectionValueMerger();
    }

    @Test
    public void testMerge(){
        Collection<String> left = new ArrayList<>();
        Collection<String> right = Collections.singleton("A");

        valueMerger.merge(left, right);

        assertTrue(left.contains("A"));
    }
}