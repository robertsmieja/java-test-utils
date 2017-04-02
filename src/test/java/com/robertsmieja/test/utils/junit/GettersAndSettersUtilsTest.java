package com.robertsmieja.test.utils.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 */
class GettersAndSettersUtilsTest {

    @Test
    public void testConstructor(){
        GettersAndSettersUtils objectUnderTest = new GettersAndSettersUtils();
        assertNotNull(objectUnderTest);
    }
}
