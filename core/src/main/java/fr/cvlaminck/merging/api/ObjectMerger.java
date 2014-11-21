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
 * @since 1.0
 */
public interface ObjectMerger {

    /**
     * Returns the collection of value mergers that can be used by this instance of ObjectMerger
     * to merge values contained in object fields.
     *
     * @since 1.0
     */
    ValueMergers getValueMergers();

    /**
     * Merge values contained in all fields of the right object with the one in the left object and
     * returns the left object. Merging of values contained in a field is done according to the
     * merging strategy defined for this field in the ObjectMergingStrategy.
     * <p/>
     * /!\ The left object is modified by this process.
     *
     * @since 1.0
     */
    <T> T merge(T left, T right, ObjectMergingStrategy strategy);

}
