package ru.savimar.stafftime.entity;

public enum StatusEnum {
    COME(1L), LEAVE(2L);

    private Long id;

    StatusEnum(Long id) {
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

