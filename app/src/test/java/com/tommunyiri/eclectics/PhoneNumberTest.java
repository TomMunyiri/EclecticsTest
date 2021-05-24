package com.tommunyiri.eclectics;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class PhoneNumberTest {
    @Test
    public void whenMatchesTenDigitsNumber_thenCorrect() {
        Pattern pattern = Pattern.compile("^\\d{10}$");
        Matcher matcher = pattern.matcher("2055550125");
        assertTrue(matcher.matches());
    }
    @Test
    public void whenMatchesTenDigitsNumberWhitespacesDotHyphen_thenCorrect() {
        Pattern pattern = Pattern.compile("^(\\d{3}[- .]?){2}\\d{4}$");
        Matcher matcher = pattern.matcher("202 555 0125");
        assertTrue(matcher.matches());
    }
    @Test
    public void whenMatchesTenDigitsNumberParenthesis_thenCorrect() {
        Pattern pattern = Pattern.compile("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher("(202) 555-0125");
        assertTrue(matcher.matches());
    }
    @Test
    public void whenMatchesTenDigitsNumberPrefix_thenCorrect() {
        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher("+111 (202) 555-0125");

        assertTrue(matcher.matches());
    }
}
