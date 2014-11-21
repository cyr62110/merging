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
