package com.thomasringhofer.jadarkroombuddy.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.thomasringhofer.jadarkroombuddy.entities.Film;

import java.util.List;
import java.util.Set;

/**
 * Created by Thomas on 14.03.2018.
 */
@Dao
public interface FilmDao {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    long insertFilm(Film film);

    @Update(onConflict = OnConflictStrategy.FAIL)
    int updateFilm(Film film);

    @Delete
    void deleteFilm(Film film);

    @Query(value = "select * from film order by name")
    List<Film> getAll();

    @Query(value = "select * from film where name=:name")
    Film getFilmByName(String name);

    @Query(value="select name from film")
    List<String> getFilmNames();

}
