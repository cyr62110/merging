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
 * Class enumerating all merging strategies implemented in the core
 * of the library.
 *
 * @since 1.0
 */
public interface MergingStrategies {

    /**
     * Always return the left value even if it is null.
     *
     * @since 1.0
     */
    public static final String alwaysUseLeft = "left";

    /**
     * Return the left value in priority. The right value will be returned
     * if the left value is null.
     *
     * @since 1.0
     */
    public static final String useRightIfLeftIsNull = "leftNull";

    /**
     * All elements contained in the left value will be included in the right one.
     * This strategy is only implemented for collection and its subclasses/subinterfaces.
     *
     * @since 1.0
     */
    public static final String addInRightCollection = "addRight";

}
