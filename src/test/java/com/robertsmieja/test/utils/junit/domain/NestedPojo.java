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

package com.robertsmieja.test.utils.junit.domain;

public class NestedPojo {
    public static Class<InnerNestedPojo> getInnerClass() {
        return InnerNestedPojo.class;
    }

    public InnerNestedPojo createInnerValue() {
        InnerNestedPojo value = new InnerNestedPojo();
        value.setStringValue("value");
        return value;
    }

    public InnerNestedPojo createInnerDifferentValue() {
        InnerNestedPojo value = new InnerNestedPojo();
        value.setStringValue("differentValue");
        return value;
    }

    //Non-static inner class creates a synthetic filed
    class InnerNestedPojo {
        String stringValue;

        public String getStringValue() {
            return stringValue;
        }

        public void setStringValue(String stringValue) {
            this.stringValue = stringValue;
        }
    }
}
