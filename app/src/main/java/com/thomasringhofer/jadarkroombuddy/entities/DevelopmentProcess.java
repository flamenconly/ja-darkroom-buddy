package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

/**
 * Representation of a Developmentprocess.
 * Created by Thomas on 09.02.2018.
 */
@Entity(tableName = "development_process",foreignKeys = @ForeignKey(entity = Film.class,
        parentColumns = "id",
        childColumns = "film_id",
        onDelete = ForeignKey.SET_NULL))
public class DevelopmentProcess implements JsonSerializable<DevelopmentProcess> {

    public static String TYPE_NEGATIVE = "Negative";
    public static String TYPE_POSITIVE = "Positive";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private long id;

    private Long film_id;

    @NonNull
    @ColumnInfo(name = "type")
    private String type;

    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    @NonNull
    @ColumnInfo(name="create_timestamp")
    private Long creationTimestamp;

    public String getType() {
        return type;
    }

    /**
     *
     * @param type, One of the TYPE_* Constants is allowed
     * @throws IllegalArgumentException if the given TYPE is not from one of the TYPE_* Constants
     */
    public void setType(String type) throws IllegalArgumentException {
        if(type.equalsIgnoreCase(TYPE_NEGATIVE) || type.equalsIgnoreCase(TYPE_POSITIVE)){
            this.type = type;
        }else{
            throw new IllegalArgumentException("type is unknown");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Long getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Long film_id) {
        this.film_id = film_id;
    }

    /**
     * Instantiate via {@link DevelopmentProcessFactory}
     */
    public DevelopmentProcess(){}

    public DevelopmentProcess deserialize(String jsonValue) {
        Gson gson = new Gson();
        DevelopmentProcess instance = gson.fromJson(jsonValue,DevelopmentProcess.class);
        return instance;
    }

    @Override
    public String serialize() {
        Gson gson = new Gson();
        String string = gson.toJson(this);
        return string;
    }

    @Override
    public boolean equals(Object obj) {

        boolean result = true;

        if(obj.getClass().equals(this.getClass()))
        {
            DevelopmentProcess incoming = (DevelopmentProcess)obj;
            result &= this.getTitle().equals(incoming.getTitle());
            result &= this.getId() ==incoming.getId();
            result &= this.getCreationTimestamp().equals(incoming.getCreationTimestamp());
            result &= this.getType().equals(incoming.getType());

        }else{
            return false;
        }

        return result;
    }
}
