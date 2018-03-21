package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Thomas on 14.03.2018.
 */
@Entity(tableName = "film", indices = {@Index(name = "idx_uq_film_name", value = "name",unique = true)})
public class Film {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;

    @NonNull
    @ColumnInfo(name = "name",index = true)
    public String name;

    @NonNull
    @ColumnInfo(name = "iso")
    public int iso;

    @NonNull
    @ColumnInfo(name = "type")
    public String type;

    @NonNull
    public long getId() {
        return id;
    }

    public void setId(@NonNull long id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public int getIso() {
        return iso;
    }

    public void setIso(@NonNull int iso) {
        this.iso = iso;
    }

    @NonNull
    public String getType() {
        return type;
    }

    public void setType(@NonNull String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %d",getName(),getIso());
    }
}
