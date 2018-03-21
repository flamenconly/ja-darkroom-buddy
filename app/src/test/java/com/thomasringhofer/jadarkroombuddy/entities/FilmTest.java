package com.thomasringhofer.jadarkroombuddy.entities;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.JUnit4;

/**
 * Created by Thomas on 14.03.2018.
 */

public class FilmTest {

    @Test
    public void testFilmToString(){
        Film film = new Film();
        film.setIso(200);
        film.setName("Kodak TMax");

        Assert.assertEquals(film.toString(),"Kodak TMax 200");
    }
}
