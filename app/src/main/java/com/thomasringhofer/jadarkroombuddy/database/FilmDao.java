package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Update;

import com.thomasringhofer.jadarkroombuddy.entities.Film;

/**
 * Created by Thomas on 14.03.2018.
 */
@Dao
interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertFilm(Film film);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateFilm(Film film);

    @Delete
    void deleteFilm(Film film);

}
