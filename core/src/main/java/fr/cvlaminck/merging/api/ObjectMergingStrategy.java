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
 * @since 0.1
 */
public interface ObjectMergingStrategy {

    /**
     * Returns the object for which this strategy is made for.
     */
    Class<?> getObject();

    /**
     * Returns the specific merging strategy to use for the field.
     *
     * @param fieldName Name of the field in the object.
     * @param type      Type of the field
     * @return the merging strategy to use
     */
    String getStrategyForField(String fieldName, Class<?> type);

    /**
     * Returns the specific merging strategy to use for the field.
     *
     * @param fieldName Name of the field in the object.
     * @return the merging strategy to use
     */
    String getSpecificStrategyForField(String fieldName);

    /**
     * Returns the merging strategy to use for a field of the provided type
     * if no specific strategy is defined for this field.
     *
     * @param type Type of the field
     * @return the merging strategy to use
     */
    String getDefaultStrategyForType(Class<?> type);

}
