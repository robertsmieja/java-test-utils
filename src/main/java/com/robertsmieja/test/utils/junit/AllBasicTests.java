package com.robertsmieja.test.utils.junit;

/**
 * Convenience interface to implement all tests
 * @param <T> The class under test
 */
public interface AllBasicTests<T> extends EqualsTests<T>, GettersAndSettersTests<T>, HashCodeTests<T>, ToStringTests<T> {
}
