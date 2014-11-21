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