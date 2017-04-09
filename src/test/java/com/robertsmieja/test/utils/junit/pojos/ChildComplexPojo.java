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

public class ChildComplexPojo extends ComplexPojo {
    String childStringValue;
    int childIntValue;

    public String getChildStringValue() {
        return childStringValue;
    }

    public void setChildStringValue(String childStringValue) {
        this.childStringValue = childStringValue;
    }

    public int getChildIntValue() {
        return childIntValue;
    }

    public void setChildIntValue(int childIntValue) {
        this.childIntValue = childIntValue;
    }

    @Override
    public String toString() {
        return "ChildComplexPojo{" +
                "childStringValue='" + childStringValue + '\'' +
                ", childIntValue=" + childIntValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChildComplexPojo that = (ChildComplexPojo) o;

        if (childIntValue != that.childIntValue) return false;
        return childStringValue != null ? childStringValue.equals(that.childStringValue) : that.childStringValue == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (childStringValue != null ? childStringValue.hashCode() : 0);
        result = 31 * result + childIntValue;
        return result;
    }
}
