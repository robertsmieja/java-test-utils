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

package com.robertsmieja.test.utils.junit.pojos;

import com.robertsmieja.test.utils.junit.annotations.IgnoreForTests;

public class ComplexPojo {
    @IgnoreForTests
    private Object fieldToIgnore;
    private String stringValue;
    private Integer integerValue;
    private Long longValue;
    private boolean booleanValue;
    private Boolean bigBooleanValue;

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Integer getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(Integer integerValue) {
        this.integerValue = integerValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public boolean isBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Boolean getBigBooleanValue() {
        return bigBooleanValue;
    }

    public void setBigBooleanValue(Boolean bigBooleanValue) {
        this.bigBooleanValue = bigBooleanValue;
    }

    @Override
    public String toString() {
        return "ComplexPojo{" +
                "stringValue='" + stringValue + '\'' +
                ", integerValue=" + integerValue +
                ", longValue=" + longValue +
                ", booleanValue=" + booleanValue +
                ", bigBooleanValue=" + bigBooleanValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComplexPojo that = (ComplexPojo) o;

        if (booleanValue != that.booleanValue) return false;
        if (stringValue != null ? !stringValue.equals(that.stringValue) : that.stringValue != null) return false;
        if (integerValue != null ? !integerValue.equals(that.integerValue) : that.integerValue != null) return false;
        if (longValue != null ? !longValue.equals(that.longValue) : that.longValue != null) return false;
        return bigBooleanValue != null ? bigBooleanValue.equals(that.bigBooleanValue) : that.bigBooleanValue == null;
    }

    @Override
    public int hashCode() {
        int result = stringValue != null ? stringValue.hashCode() : 0;
        result = 31 * result + (integerValue != null ? integerValue.hashCode() : 0);
        result = 31 * result + (longValue != null ? longValue.hashCode() : 0);
        result = 31 * result + (booleanValue ? 1 : 0);
        result = 31 * result + (bigBooleanValue != null ? bigBooleanValue.hashCode() : 0);
        return result;
    }
}
