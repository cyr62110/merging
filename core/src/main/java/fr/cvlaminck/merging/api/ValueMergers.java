package fr.cvlaminck.merging.api;

/**
 * @since 0.1
 */
public interface ValueMergers {

    ValueMerger getMerger(Class<?> fieldType, String mergingStrategy);

    void registerFieldMerger(ValueMerger valueMerger);

    void unregisterFieldMerger(Class<?> mergerType, String mergingStrategy);

    void unregisterAllFieldMergers();

}
