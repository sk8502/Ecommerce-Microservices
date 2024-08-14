package com.sk.ecommerce.email;

import lombok.Getter;

public enum EmailTempletes {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment sucessfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "order confirmation ");

    @Getter
    private final String template;
    @Getter
    private final String subject;

    EmailTempletes(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
