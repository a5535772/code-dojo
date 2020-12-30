package com.code.dojo.demo.zjmszm34.Id.generator.rf;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class IdGeneratorRFTest {
    @InjectMocks
    RandomIdGenerator randomIdGenerator;

    @Test
    public void getLastSubstrSplittedByDotTest() {
        String actualSubStr = randomIdGenerator.getLastSubstrSplittedByDot("field1.field2.field3");
        assertThat(actualSubStr).isEqualTo("field3");

        actualSubStr = randomIdGenerator.getLastSubstrSplittedByDot("field1");
        assertThat(actualSubStr).isEqualTo("field1");

        actualSubStr = randomIdGenerator.getLastSubstrSplittedByDot("field1|field2#field3");
        assertThat(actualSubStr).isEqualTo("field1|field2#field3");
    }



    @Test
    public void testGetLastSubstrSplittedByDot_nullOrEmpty() {
        try {
            randomIdGenerator.getLastSubstrSplittedByDot(null);
        } catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }

        try {
            randomIdGenerator.getLastSubstrSplittedByDot("");
        } catch (Exception e) {
            assertThat(e).isExactlyInstanceOf(IllegalArgumentException.class);
        }

    }

    @Test
    public void testGenerateRandomAlphameric() {
        String actualRandomString = randomIdGenerator.generateRandomAlphameric(6);
        assertThat(actualRandomString).isNotNull();
        assertThat(actualRandomString.length()).isEqualTo(6);
        for (char c : actualRandomString.toCharArray()) {
            assertThat(('0' < c && c < '9') || ('a' < c && c < 'z') || ('A' < c && c < 'Z')).isTrue();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateRandomAlphameric_lengthEqualsZero() {
        randomIdGenerator.generateRandomAlphameric(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGenerateRandomAlphameric_lengthLessThanZero() {
        randomIdGenerator.generateRandomAlphameric(-1);
    }
}
