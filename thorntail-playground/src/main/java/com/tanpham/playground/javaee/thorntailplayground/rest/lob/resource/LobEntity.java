package com.tanpham.playground.javaee.thorntailplayground.rest.lob.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Objects;

@Entity
public class LobEntity {

    @Id
    Long id;

    String name;

    @Lob
    String longText;

    public LobEntity() {
    }

    public LobEntity(Long id, String name, String longText) {
        this.id = id;
        this.name = name;
        this.longText = longText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLongText() {
        return longText;
    }

    public void setLongText(String longText) {
        this.longText = longText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LobEntity lobEntity = (LobEntity) o;
        return Objects.equals(id, lobEntity.id) &&
                Objects.equals(name, lobEntity.name) &&
                Objects.equals(longText, lobEntity.longText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, longText);
    }
}
