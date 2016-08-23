package com.qaware.mentoring.i18n;


import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Minimal example for loading resource bundles.
 * <p>
 * There are four resource bundles:
 * - ExampleResourceBundle.properties       -> default resource bundle
 * - ExampleResourceBundle_de.properties    -> resource bundle for locale GERMAN
 * - ExampleResourceBundle_en.properties    -> resource bundle for locale ENGLISH
 * - ExampleResourceBundle_fr.properties    -> resource bundle for locale FRENCH
 * <p>
 * The resource default resource bundle is used as a last resort if no appropriate bundle is found.
 * In the example the locale ITALY is used but there exists no resource bundle for it.
 * Therefore the default locale is used (which is determined by the host environment and is probably GERMAN).
 * If there is no resource bundle for the default locale, the default resource bundle is used.
 * <p>
 * Try to rename or delete the ExampleResourceBundle_de.properties and see what happens.
 */
public class ResourceBundleApp {

    private static final String EXAMPLE_RESOURCE_BUNDLE_NAME = "ExampleResourceBundle";
    private static Set<Locale> locales = new HashSet<>();

    static {
        locales.add(Locale.FRENCH);
        locales.add(Locale.GERMAN);
        locales.add(Locale.ENGLISH);
        locales.add(Locale.ITALY);
    }

    public static void main(String[] args) {
        for (Locale locale : locales) {
            ResourceBundle bundle = ResourceBundle.getBundle(EXAMPLE_RESOURCE_BUNDLE_NAME, locale);
            System.out.println("------------------------------------------------");
            System.out.println("Locale: " + locale);
            String HELLO_WORLD_KEY = "com.qaware.mentoring.i18n.hello.world";
            System.out.println(bundle.getString(HELLO_WORLD_KEY));
            String ANSWER_TO_EVERYTHING_KEY = "com.qaware.mentoring.i18n.answer.to.everything";
            System.out.println(bundle.getString(ANSWER_TO_EVERYTHING_KEY));
        }
    }
}
