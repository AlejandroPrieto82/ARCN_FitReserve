package com.fitreserve.domain.valueobject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void shouldCreateEmail_WhenFormatIsValid() {

        Email email = new Email("user@test.com");

        assertEquals("user@test.com", email.getValue());
    }

    @Test
    void shouldNormalizeToLowercase_WhenEmailHasUppercaseLetters() {

        Email email = new Email("ADMIN@EXAMPLE.COM");

        assertEquals("admin@example.com", email.getValue());
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEmailHasLeadingOrTrailingSpaces() {

        assertThrows(IllegalArgumentException.class, () -> new Email("  user@test.com  "));
    }

    @Test
    void shouldBeEqual_WhenTwoEmailsHaveSameNormalizedValue() {

        Email email1 = new Email("user@test.com");
        Email email2 = new Email("USER@TEST.COM");

        assertEquals(email1, email2);
    }

    @Test
    void shouldHaveSameHashCode_WhenTwoEmailsAreEqual() {

        Email email1 = new Email("user@test.com");
        Email email2 = new Email("user@test.com");

        assertEquals(email1.hashCode(), email2.hashCode());
    }

    @Test
    void shouldNotBeEqual_WhenEmailsAreDifferent() {

        Email email1 = new Email("one@test.com");
        Email email2 = new Email("two@test.com");

        assertNotEquals(email1, email2);
    }

    @Test
    void shouldReturnTrue_WhenComparingEmailToItself() {

        Email email = new Email("user@test.com");

        assertEquals(email, email);
    }

    @Test
    void shouldReturnFalse_WhenComparingEmailToNonEmailObject() {

        Email email = new Email("user@test.com");

        assertNotEquals(email, "user@test.com");
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEmailIsNull() {

        assertThrows(IllegalArgumentException.class, () -> new Email(null));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEmailHasNoAtSign() {

        assertThrows(IllegalArgumentException.class, () -> new Email("notanemail"));
    }

    @Test
    void shouldThrowIllegalArgumentException_WhenEmailIsEmpty() {

        assertThrows(IllegalArgumentException.class, () -> new Email(""));
    }
}
