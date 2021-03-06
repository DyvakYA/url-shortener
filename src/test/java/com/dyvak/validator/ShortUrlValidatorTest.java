package com.dyvak.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShortUrlValidatorTest {

    @Autowired
    private ShortUrlValidator shortUrlValidator;

    @Test
    public void testShortLinkIsValid_whenURISixCharacters() {
        boolean result = shortUrlValidator.isValid("qweqwe");
        assertTrue(result);
    }

    @Test
    public void testShortLinkIsValid_whenURIMoreThenSixCharacters() {
        boolean result = shortUrlValidator.isValid("qweqweq");
        assertFalse(result);
    }

    @Test
    public void testShortLinkIsValid_whenUriLessThenSixCharacters() {
        boolean result = shortUrlValidator.isValid("qwe");
        assertFalse(result);
    }
}
