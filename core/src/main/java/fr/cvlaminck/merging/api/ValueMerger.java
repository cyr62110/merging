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
 * Merge two values. Some value merger may be specialized in the merging of
 * a given type. For ex. a value merger may be designed to merge only java.util.Date values.
 * <p/>
 * If a merger is designed to support a given type, it also means that is supports all types that inherit
 * from this type. For ex. a value merger designed to support Collection must also support Set, List, etc...
 * <p/>
 * Each implementation has also its own merging strategy. The merging strategy defines the value that
 * will be returned by the ValueMerger. For ex, a value merger can implements a strategy that make it
 * returns always the first parameter.
 *
 * @since 1.0
 */
public interface ValueMerger {

    /**
     * Returns the type of objects that can be merged with this field merger instance.
     *
     * @since 1.0
     */
    Class<?> getType();

    /**
     * Returns the merging strategy implemented by this field merger.
     *
     * @since 1.0
     */
    String getMergingStrategy();

    /**
     * Merge the two values and return the result according to the merging strategy implemented.
     * Left and Right values may be modified in the process. For ex. element can be added if values
     * are collection.
     *
     * @param left  Left value
     * @param right Right value
     * @return Merged value
     * @since 1.0
     */
    Object merge(Object left, Object right);

}
