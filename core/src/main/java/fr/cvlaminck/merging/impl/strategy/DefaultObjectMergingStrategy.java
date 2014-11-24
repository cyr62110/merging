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

import fr.cvlaminck.merging.api.MergingStrategies;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Collection;

/**
 * A default object merging strategy that can be used out-of-the-box with the library.
 *
 * @since 1.1
 */
public class DefaultObjectMergingStrategy
    extends ImmutableObjectMergingStrategy {

    public DefaultObjectMergingStrategy(Class<?> object) {
        super(object,
                new ImmutablePair<>(Object.class, MergingStrategies.useRightIfLeftIsNull),
                new ImmutablePair<>(Collection.class, MergingStrategies.addInRightCollection));
    }

}
