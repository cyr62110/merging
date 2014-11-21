package fr.cvlaminck.merging.api;

/**
 *
 *
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
     * @param type Type of the field
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
