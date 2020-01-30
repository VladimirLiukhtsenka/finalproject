package com.liukhtenko.ticket.entity;

public class Role extends Entity {
    private long id;
    private String description;

    public Role() {
    }

    public Role(long id, String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        if (id != role.id) {
            return false;
        }
        return description != null ? description.equals(role.description) : role.description == null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (int) (prime * result + id);
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Role{").append("id=").append(id).append(", description='");
        sb.append(description).append('\'').append('}');
        return sb.toString();
    }
}
