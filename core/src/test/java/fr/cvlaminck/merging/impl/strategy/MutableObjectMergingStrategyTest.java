/**
 * Copyright 2014 Cyril Vlaminck
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

    @Test
    public void testClone() throws CloneNotSupportedException {
        mergingStrategy.setSpecificStrategyForField("A", "A");
        mergingStrategy.setDefaultStrategyForType(Object.class, "B");

        MutableObjectMergingStrategy clone = (MutableObjectMergingStrategy) mergingStrategy.clone();

        assertEquals(mergingStrategy.getObject(), clone.getObject());
        assertEquals(mergingStrategy.getSpecificStrategyForField("A"), clone.getSpecificStrategyForField("A"));
        assertNotSame(mergingStrategy.getSpecificStrategyForField("A"), clone.getSpecificStrategyForField("A"));
        assertEquals(mergingStrategy.getDefaultStrategyForType(Object.class), clone.getDefaultStrategyForType(Object.class));
        assertNotSame(mergingStrategy.getDefaultStrategyForType(Object.class), clone.getDefaultStrategyForType(Object.class));
    }

}