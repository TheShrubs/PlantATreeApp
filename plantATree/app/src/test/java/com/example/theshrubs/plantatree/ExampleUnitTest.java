package com.example.theshrubs.plantatree;

import android.util.Patterns;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void checkEmail() {
        String email = "bigboy@gmail.com";

        if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            assertTrue(true);
        }
        else
        {
            assertFalse(false);
        }

    }
}