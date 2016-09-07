package com.qaware.mentoring.bean.validation;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * Uses validation annotations and Hibernate validator annotations.
 */
public class Contact {

    // Marker interface
    public interface MailOnlyValidation {
    }

    // Marker interface
    public interface PhoneOnlyValidation {
    }

    // Marker interface
    public interface MobileOnlyValidation {
    }

    // Marker interface
    public interface CompleteValidation extends MailOnlyValidation, PhoneOnlyValidation, MobileOnlyValidation {
    }

    /**
     * Group sequence aborts validation after first violation.
     * Most likely to be used for performing cheap validations first.
     */
    @GroupSequence({MailOnlyValidation.class, PhoneOnlyValidation.class, MobileOnlyValidation.class})
    public interface OrderedValidationMailPhoneMobile {
    }

    /**
     * Value constructor.
     *  @param mail   the mail
     * @param phone  the phone
     * @param mobile the mobile
     * @param fax
     */
    public Contact(String mail, String phone, String mobile, String fax) {
        this.mail = mail;
        this.phone = phone;
        this.mobile = mobile;
        this.fax = fax;
    }

    @Email(groups = MailOnlyValidation.class)
    private final String mail;

    /**
     * Format: +99(99)9999-9999
     */
    @NotNull(groups = PhoneOnlyValidation.class)
    @Pattern(regexp = "[\\+]\\d{2}[\\(]\\d{2}[\\)]\\d{4}[\\-]\\d{4}", groups = PhoneOnlyValidation.class)
    private final String phone;

    /**
     * Format: +99(99)9999-9999
     */
    @NotNull(groups = MobileOnlyValidation.class)
    @Pattern(regexp = "[\\+]\\d{2}[\\(]\\d{2}[\\)]\\d{4}[\\-]\\d{4}", groups = MobileOnlyValidation.class)
    private final String mobile;

    /**
     * If no groups are specified, the Default.class group is assumed.
     */
    @NotEmpty
    private final String fax;

    @Override
    public String toString() {
        return "Contact{" +
                "mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", fax='" + fax + '\'' +
                '}';
    }
}
