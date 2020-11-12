package com.code.dojo.kata.order_receipt.baseline;

import java.util.List;

public class Order {
    String nm;
    String addr;
    List<LineItem> li;

    public Order(String nm, String addr, List<LineItem> li) {
        this.nm = nm;
        this.addr = addr;
        this.li = li;
    }

    public String getCustomerName() {
        return nm;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<LineItem> getLineItems() {
        return li;
    }
}