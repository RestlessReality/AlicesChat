package com.qaware.mentoring.bean.validation;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Example for bean validation (JSR-303, JSR-349) and Hibernate validation.
 */
public class BeanValidationMain {

    public static void main(String[] args) {

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        Address address = new AddressBuilder()
                .setNumber("12b")
                .setStreet("Adalbertstrasse")
                .setCity("Munich")
                .setZip("8136")
                .setState("")
                .setCountry("Germany")
                .createAddress();

        printViolations(validator, address);

        Contact contact = new ContactBuilder()
                .setMail("max.mustermann@@qaware.de")
                .setMobile("+49(89)5582-712")
                .setPhone("abc")
                .createContact();

        printViolations(validator, contact);
        printViolationsInGroup(validator, contact, Default.class);
        printViolationsInGroup(validator, contact, Contact.CompleteValidation.class);
        printViolationsInGroup(validator, contact, Contact.OrderedValidationMailPhoneMobile.class);
        printViolationsInGroup(validator, contact, Contact.PhoneOnlyValidation.class, Contact.MobileOnlyValidation.class);
        printViolationsInGroup(validator, contact, Contact.MailOnlyValidation.class, Contact.PhoneOnlyValidation.class);
        printViolationsInGroup(validator, contact, Contact.MailOnlyValidation.class, Contact.MobileOnlyValidation.class);

        Person person = new PersonBuilder()
                .setFistName("Peter")
                .setLastName("Pan")
                .setAddress(address)
                .setContact(contact)
                .createPerson();

        printViolationsInGroup(validator, person, Default.class);
    }

    private static <T> void printViolations(Validator validator, T toBeValidated) {
        System.out.println("");
        System.out.println("Validating " + toBeValidated + " without any group:");
        Set<ConstraintViolation<T>> violations = validator.validate(toBeValidated);
        for (ConstraintViolation<T> violation : violations) {
            System.out.println(violation.getPropertyPath() + " " + violation.getMessage() + " -> " + violation.getInvalidValue());
        }
    }

    private static <T> void printViolationsInGroup(Validator validator, T toBeValidated, Class<?>... group) {
        System.out.println("");
        System.out.println("Validating " + toBeValidated + " with " +
                Stream.of(group).map(Class::getSimpleName).collect(Collectors.joining(" and ")) + ": ");
        Set<ConstraintViolation<T>> violations = validator.validate(toBeValidated, group);
        for (ConstraintViolation<T> violation : violations) {
            System.out.println(violation.getPropertyPath() + " " + violation.getMessage() + " -> " + violation.getInvalidValue());
        }
    }
}
