package com.robertsmieja.test.utils.junit;

public class SimplePojo {

    String stringValue;
    Integer integerValue;
    Long longValue;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimplePojo that = (SimplePojo) o;

        if (stringValue != null ? !stringValue.equals(that.stringValue) : that.stringValue != null) return false;
        if (integerValue != null ? !integerValue.equals(that.integerValue) : that.integerValue != null) return false;
        return longValue != null ? longValue.equals(that.longValue) : that.longValue == null;
    }

    @Override
    public int hashCode() {
        int result = stringValue != null ? stringValue.hashCode() : 0;
        result = 31 * result + (integerValue != null ? integerValue.hashCode() : 0);
        result = 31 * result + (longValue != null ? longValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SimplePojo{" +
                "stringValue='" + stringValue + '\'' +
                ", integerValue=" + integerValue +
                ", longValue=" + longValue +
                '}';
    }
}
