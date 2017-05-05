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

package com.robertsmieja.test.utils.grails3test

import com.robertsmieja.test.utils.junit.*
import com.robertsmieja.test.utils.junit.interfaces.ObjectFactory
import grails.test.mixin.TestFor
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test

import java.lang.reflect.Field

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(SimpleDomain)
class SimpleDomainTests {
    static ObjectFactory objectFactory
    SimpleDomain value
    SimpleDomain differentValue

    @BeforeClass
    static void oneTimeSetup() {
        objectFactory = new GenericObjectFactory();
        objectFactory.setFieldFilterPredicate { Field field ->
            List<String> packagesPrefixesToExclude = [
                    "org.grails.", "grails.", "org.codehaus.groovy.grails.",
                    "org.slf4j.",
                    "org.springframework."
            ]
            List<String> fieldNamesToExclude = [
                    "org_grails_datastore_mapping_dirty_checking_DirtyCheckable__\$changedProperties",
                    "org_grails_datastore_gorm_GormValidateable__skipValidate"
            ]
            String fieldTypeName = field.getType().getCanonicalName()
            if (packagesPrefixesToExclude.any { String prefix -> fieldTypeName.startsWith(prefix) }) {
                return false
            }
            if (fieldNamesToExclude.any { String fieldName -> field.getName().contains(fieldName) }) {
                return false
            }
            return true
        }
    }

    @Before
    void setup() {
        value = objectFactory.getInstanceOfClass(SimpleDomain)
        differentValue = objectFactory.getInstanceOfClassWithDifferentValues(SimpleDomain)
    }

    @Test
    void testGettersAndSetters() {
        GettersAndSettersUtils.runAllGettersAndSettersTests(value, differentValue)
    }

    @Test
    void testHashCode() {
        HashCodeUtils.runAllHashCodeTests(value, differentValue)
    }

    @Test
    void testEquals() {
        EqualsUtils.runAllEqualsTests(value, differentValue)
    }

    @Test
    void testToString() {
        ToStringUtils.runAllToStringTests(value, differentValue)
    }
}
