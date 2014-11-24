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
package fr.cvlaminck.merging.api;

/**
 * An ObjectMergingStrategy that can be cloned.
 *
 * @since 1.1
 */
public interface CloneableObjectMergingStrategy
    extends ObjectMergingStrategy, Cloneable {

    /**
     * Clone this strategy.
     * Any further modification to this strategy will not affect the clone, same for modifications made
     * on the clone.
     *
     * @return a clone of this strategy
     * @since 1.1
     */
    Object clone() throws CloneNotSupportedException;

}
