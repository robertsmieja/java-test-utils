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

package com.robertsmieja.test.utils.junit4test.domain;

class SimplePojo {
    String stringValue
    Integer integerValue
    Long longValue
    def defValue

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        SimplePojo that = (SimplePojo) o

        if (defValue != that.defValue) return false
        if (integerValue != that.integerValue) return false
        if (longValue != that.longValue) return false
        if (stringValue != that.stringValue) return false

        return true
    }

    int hashCode() {
        int result
        result = (stringValue != null ? stringValue.hashCode() : 0)
        result = 31 * result + (integerValue != null ? integerValue.hashCode() : 0)
        result = 31 * result + (longValue != null ? longValue.hashCode() : 0)
        result = 31 * result + (defValue != null ? defValue.hashCode() : 0)
        return result
    }

    @Override
    public String toString() {
        return "SimplePojo{" +
                "stringValue='" + stringValue + '\'' +
                ", integerValue=" + integerValue +
                ", longValue=" + longValue +
                ", defValue=" + defValue +
                '}';
    }
}
