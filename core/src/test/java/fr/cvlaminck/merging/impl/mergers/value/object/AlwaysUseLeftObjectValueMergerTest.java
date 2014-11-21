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