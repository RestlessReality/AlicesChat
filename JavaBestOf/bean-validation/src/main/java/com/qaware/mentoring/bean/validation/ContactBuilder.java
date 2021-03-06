package com.qaware.mentoring.bean.validation;

/**
 * Generated by IntellJ (Refactor -> Replace Contructor with Builder)
 */
public class ContactBuilder {

    private String mail;
    private String phone;
    private String mobile;
    private String fax;

    public ContactBuilder setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public ContactBuilder setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactBuilder setFax(String fax) {
        this.fax = fax;
        return this;
    }

    public Contact createContact() {
        return new Contact(mail, phone, mobile, fax);
    }
}