package com.liukhtenko.ticket.entity;

/**
 * This is an entity class that describes user.
 *
 * @author Vladimir Liukhtenko
 * @version 1.25 02 Feb 2020
 */
public class User extends Entity {
    private long id;
    private String phone;
    private String name;
    private String surName;
    private String fatherName;
    private byte gender;
    private String password;
    private String mail;
    private long roleId;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public User() {
    }

    public User(long id, String phone, String name, String surName, String fatherName,
                byte gender, String password, String mail, long roleID) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.surName = surName;
        this.fatherName = fatherName;
        this.gender = gender;
        this.password = password;
        this.mail = mail;
        this.roleId = roleID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public long getRoleID() {
        return roleId;
    }

    public void setRoleID(long roleID) {
        this.roleId = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (id != user.id) {
            return false;
        }
        if (gender != user.gender) {
            return false;
        }
        if (roleId != user.roleId) {
            return false;
        }
        if ((phone != null && user.phone == null) || (!phone.equals(user.phone))) {
            return false;
        }

        if ((name != null && user.name == null) || (!name.equals(user.name))) {
            return false;
        }
        if ((surName != null && user.surName == null) || (!surName.equals(user.surName))) {
            return false;
        }
        if ((fatherName != null && user.fatherName == null) || (!fatherName.equals(user.fatherName))) {
            return false;
        }

        if ((password != null && user.password == null) || (!password.equals(user.password))) {
            return false;
        }
        return mail != null ? mail.equals(user.mail) : user.mail == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = (prime * result + gender);
        result = (int) (prime * result + roleId);
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((surName == null) ? 0 : surName.hashCode());
        result = prime * result + ((fatherName == null) ? 0 : fatherName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((mail == null) ? 0 : mail.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{").append("id=").append(id);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surName='").append(surName).append('\'');
        sb.append(", fatherName='").append(fatherName).append('\'');
        sb.append(", gender=").append(gender);
        sb.append(", password='").append(password).append('\'');
        sb.append(", mail='").append(mail).append('\'');
        sb.append(", roleID=").append(roleId).append('}');
        return sb.toString();
    }
}