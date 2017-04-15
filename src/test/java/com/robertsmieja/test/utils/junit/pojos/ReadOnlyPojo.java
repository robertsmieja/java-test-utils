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

public class ReadOnlyPojo {
    private long id;
    private String coolData;

    public ReadOnlyPojo(){}

    public ReadOnlyPojo(long id, String coolData) {
        this.id = id;
        this.coolData = coolData;
    }

    public long getId() {
        return id;
    }

    public String getCoolData() {
        return coolData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReadOnlyPojo that = (ReadOnlyPojo) o;

        if (id != that.id) return false;
        return coolData != null ? coolData.equals(that.coolData) : that.coolData == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (coolData != null ? coolData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReadOnlyPojo{" +
                "id=" + id +
                ", coolData='" + coolData + '\'' +
                '}';
    }
}
