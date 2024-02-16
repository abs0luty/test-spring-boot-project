package me.abs0luty.school.entity;

public enum AccountStatus {
    CREATED, ACTIVE, REMOVED;

    public String toString() {
        return name().toLowerCase();
    }
}
