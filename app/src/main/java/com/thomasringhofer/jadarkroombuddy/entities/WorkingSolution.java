package com.thomasringhofer.jadarkroombuddy.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Thomas on 19.02.2018.
 * The Working Solution
 */
@Entity(tableName = "working_solution",
        indices = {@Index(
                value="title",
                name = "idx_uq_working_solution_title",
                unique = true)
})
public class WorkingSolution {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    @NonNull
    @ColumnInfo(name = "title",index = true)
    private String title;

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }
    @Nullable
    @ColumnInfo(name="notes")
    private String notes;

    @Nullable
    public String getNotes() {
        return notes;
    }

    public void setNotes(@Nullable String notes) {
        this.notes = notes;
    }

}
