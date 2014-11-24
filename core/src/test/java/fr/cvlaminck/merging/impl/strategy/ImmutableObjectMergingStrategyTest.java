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

import fr.cvlaminck.merging.api.CloneableObjectMergingStrategy;
import fr.cvlaminck.merging.api.ObjectMergingStrategy;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImmutableObjectMergingStrategyTest {

    @Test
    public void testConstructorWithCloneableObjectMergingStrategy() throws CloneNotSupportedException {
        final ObjectMergingStrategy strategy = mock(ObjectMergingStrategy.class);
        when(strategy.getObject()).thenReturn((Class) Object.class);
        when(strategy.getSpecificStrategyForField("A")).thenReturn("A");
        when(strategy.getDefaultStrategyForType(Object.class)).thenReturn("B");

        final CloneableObjectMergingStrategy cloneableStrategy = mock(CloneableObjectMergingStrategy.class);
        when(cloneableStrategy.clone()).thenReturn(strategy);

        final ImmutableObjectMergingStrategy immutable = new ImmutableObjectMergingStrategy(cloneableStrategy);

        assertEquals(Object.class, immutable.getObject());
        assertEquals("A", immutable.getSpecificStrategyForField("A"));
        assertEquals("B", immutable.getDefaultStrategyForType(Object.class));
    }

    @Test
    public void testConstructorWithVariableArgs() {
        final ImmutableObjectMergingStrategy immutable = new ImmutableObjectMergingStrategy(
                Object.class,
                new ImmutablePair<>("A", "A"),
                new ImmutablePair<>(Object.class, "B")
        );

        assertEquals(Object.class, immutable.getObject());
        assertEquals("A", immutable.getSpecificStrategyForField("A"));
        assertEquals("B", immutable.getDefaultStrategyForType(Object.class));
    }

}