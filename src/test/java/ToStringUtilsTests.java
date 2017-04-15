/*
 *    Copyright 2017 Robert Smieja
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

import com.robertsmieja.test.utils.junit.GenericObjectFactory;
import com.robertsmieja.test.utils.junit.ToStringUtils;
import com.robertsmieja.test.utils.junit.exceptions.ObjectFactoryException;
import com.robertsmieja.test.utils.junit.interfaces.ObjectFactory;
import com.robertsmieja.test.utils.junit.pojos.SimplePojo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ToStringUtilsTests {
    static ObjectFactory objectFactory;

    @BeforeAll
    public static void setupOnce(){
        objectFactory = new GenericObjectFactory();
    }

    @Test
    @DisplayName("Test constructor")
    public void testConstructor(){
        assertNotNull(new com.robertsmieja.test.utils.junit.ToStringUtils());
    }

    @Test
    @DisplayName("Test runAllToStringTests on SimplePojo")
    public void testRunAllToStringTestsOnSimplePojo() throws ObjectFactoryException {
        SimplePojo value = objectFactory.getInstanceOfClass(SimplePojo.class);
        SimplePojo differentValue = objectFactory.getInstanceOfClassWithDifferentValues(SimplePojo.class);

        ToStringUtils.runAllToStringTests(value, differentValue);
    }
}