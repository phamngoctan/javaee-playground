package com.tanpham.playground.javaee.thorntailplayground.rest.lob.resource;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class LobBasicEntity {
    @Id
    Long id;

    String name;

    /*
    Having only the @Lob annotation is problematic, since this may lead to orphaned large objects in the database. If
    you persist an entity and later modify a @Log property, a new large object is created, but the original large object
    is not deleted!

    However there are some workarounds, but all of them introduce PostgreSQL specific code:

    @Column(columnDefinition = "text")
    Adding this annotation forces hibernate to use the "text" data type. This data type is able to handle up to
    1 GB of data (compressed) per row without the usage of large objects (i.e. PostgreSQL handles it).

    @Column(columnDefinition = "oid")
    Adding this annotation would force hibernate to use the "oid" data type. That still leads to orphaned large objects
    as described above, but since the data type is "oid", PostgreSQL knows about what large objects are still in use. If
    all large objects in a database are referenced like this, those large objects can be cleaned up with the
    vacuumlo command. But you have to be absolutely sure that all large objects are referenced by "oid"s, e.g. there are
    no @Lob String whatever; attributes anymore throughout the whole database!
    */
    @Lob
    String longText;


    /*
    If the @Lob annotation is used with a byte[] jave data type, hibernate translates it to a "oid" data type on the
    database. This may also lead to orphaned large objects as described above, but vacuumlo may be used to clean up
    if the rule mentioned above are applied.

    @Column(columnDefinition = "bytea")
    If you want PostgreSQL to handle the large binary objects for you, the database data type "bytea" may be used. However,
    be aware that this is PostgreSQL specific.
     */
    @Lob
    byte[] bytearray;

    public LobBasicEntity() {
    }

    public LobBasicEntity(Long id, String name, String longText, byte[] bytearray) {
        this.id = id;
        this.name = name;
        this.longText = longText;
        this.bytearray = bytearray;
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

    public byte[] getBytearray() {
        return bytearray;
    }

    public void setBytearray(byte[] bytearray) {
        this.bytearray = bytearray;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LobBasicEntity that = (LobBasicEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(longText, that.longText) &&
                Arrays.equals(bytearray, that.bytearray);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name, longText);
        result = 31 * result + Arrays.hashCode(bytearray);
        return result;
    }
}