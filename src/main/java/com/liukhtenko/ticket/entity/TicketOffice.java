package com.liukhtenko.ticket.entity;

import java.util.Objects;
/**
 * This is an entity class that describes ticket office.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class TicketOffice extends Entity {
    private String address;
    private String operatingMode;
    private String phone;

    public TicketOffice() {
    }

    public TicketOffice(String address, String operatingMode, String phone) {
        this.address = address;
        this.operatingMode = operatingMode;
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOperatingMode() {
        return operatingMode;
    }

    public void setOperatingMode(String operatingMode) {
        this.operatingMode = operatingMode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TicketOffice office = (TicketOffice) o;
        if ((address != null && office.address == null) || (!address.equals(office.address))) {
            return false;
        }
        if ((operatingMode != null && office.operatingMode == null) || (!operatingMode.equals(office.operatingMode))) {
            return false;
        }
        return phone != null ? phone.equals(office.phone) : office.phone == null;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, operatingMode, phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TicketOffice{").append("address='").append(address).append('\'');
        sb.append(", operatingMode='").append(operatingMode).append('\'');
        sb.append(", phone='").append(phone).append('\'').append('}');
        return sb.toString();
    }
}
