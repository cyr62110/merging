package fr.cvlaminck.merging.samples.contacts;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import fr.cvlaminck.merging.api.MergingStrategies;
import fr.cvlaminck.merging.api.ObjectMerger;
import fr.cvlaminck.merging.api.ValueMergers;
import fr.cvlaminck.merging.impl.DefaultValueMergers;
import fr.cvlaminck.merging.impl.mergers.object.DefaultObjectMerger;
import fr.cvlaminck.merging.impl.mergers.value.collection.AddInRightCollectionValueMerger;
import fr.cvlaminck.merging.impl.mergers.value.object.UseRightIfLeftIsNullObjectValueMerger;
import fr.cvlaminck.merging.impl.strategy.MutableObjectMergingStrategy;
import fr.cvlaminck.merging.samples.contacts.entities.Contact;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * In this sample, you are currently working on an address book and you want to add
 * a new feature to help your users to deduplicate entries. The feature is quite simple :
 * When the user visualize the details of a contact, he can select another contact in its
 * list that he considers as a duplicate of the current contact. Information will be automatically
 * merged and displayed to him.
 */
public class Application {

    public static void main(String[] args) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, false);

        /**
         * The first entry only contains mailing address.
         */
        final Contact entry1 = new Contact(
                "John",
                "Doe",
                "777. Rainbow road",
                "777",
                "City"
        );
        System.out.println("/*-----------------------------------------*/");
        System.out.println("/*             Entry : John Doe            */");
        System.out.println("/*-----------------------------------------*/");
        objectMapper.writeValue(System.out, entry1);
        System.out.println(); System.out.println();

        /**
         * The second entry considered as duplicate contains some phone numbers
         * for the contact
         */
        final Contact entry2 = new Contact(
                "John",
                "Doe",
                "+33777777777"
        );
        System.out.println("/*-----------------------------------------*/");
        System.out.println("/*           Duplicated : John Doe         */");
        System.out.println("/*-----------------------------------------*/");
        objectMapper.writeValue(System.out, entry2);
        System.out.println(); System.out.println();

        /**
         * Before doing any merge operation, we need to configure the merging library.
         * To configure the library, you need to instantiate a ValueMergers.
         * The ValuesMergers is a collection ValueMerger. This collection defines which
         * type of field can be merged by the library and which merging strategy can be
         * used for this type of field.
         *
         * For the sample, we uses a PreConfiguredValueMergers that contains all ValueMerger
         * implemented in the core library. There is other implementation of ValueMergers that
         * you can use in your application.
         */
        ValueMergers valueMergers = new DefaultValueMergers(); //TODO
        valueMergers.registerFieldMerger(new UseRightIfLeftIsNullObjectValueMerger());
        valueMergers.registerFieldMerger(new AddInRightCollectionValueMerger());

        /**
         * Then we instantiate the core element of the library.
         * The ObjectMerger is the object that will allow you to merge your objects.
         */
        ObjectMerger objectMerger = new DefaultObjectMerger(valueMergers);

        /**
         * The last step before merging two objects together is to create an ObjectMergingStrategy.
         * This defines which merging strategy must be used to merge values contained in a field.
         *
         * For this sample, we will use MutableObjectMergingStrategy implementation that allow you to define
         * the strategy at runtime.
         */
        MutableObjectMergingStrategy objectMergingStrategy = new MutableObjectMergingStrategy(Contact.class);
        objectMergingStrategy.setDefaultStrategyForType(Object.class, MergingStrategies.useRightIfLeftIsNull);
        objectMergingStrategy.setDefaultStrategyForType(Collection.class, MergingStrategies.addInRightCollection);

        /**
         * Finally, we merge both objects and take a look at the result.
         */
        final Contact result = objectMerger.merge(entry1, entry2, objectMergingStrategy);

        System.out.println("/*-----------------------------------------*/");
        System.out.println("/*             Merged : John Doe           */");
        System.out.println("/*-----------------------------------------*/");
        objectMapper.writeValue(System.out, result);
    }

}
