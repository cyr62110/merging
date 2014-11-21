package fr.cvlaminck.merging.api;

/**
 * Class enumerating all merging strategies implemented in the core
 * of the library.
 */
public interface MergingStrategies {

    /**
     * Always return the left value even if it is null.
     */
    public static final String alwaysUseLeft = "left";

    /**
     * Return the left value in priority. The right value will be returned
     * if the left value is null.
     */
    public static final String useRightIfLeftIsNull = "leftNull";

    /**
     * All elements contained in the left value will be included in the right one.
     * This strategy is only implemented for collection and its subclasses/subinterfaces.
     */
    public static final String addInRightCollection = "addRight";

}
