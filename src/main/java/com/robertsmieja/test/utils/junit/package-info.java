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

/**
 * Various utilities to reduce redundant code in JUnit tests.
 *
 * For the most common use-case, have your test class implement '{@link com.robertsmieja.test.utils.junit.AllBasicTests}'
 * and override {@link com.robertsmieja.test.utils.junit.TestProducer#getTypeClass()},
 * {@link com.robertsmieja.test.utils.junit.TestProducer#createValue()}, and
 * {@link com.robertsmieja.test.utils.junit.TestProducer#createDifferentValue()}.
 *
 * @see com.robertsmieja.test.utils.junit.EqualsTests
 * @see com.robertsmieja.test.utils.junit.GettersAndSettersTests
 * @see com.robertsmieja.test.utils.junit.HashCodeTests
 */
package com.robertsmieja.test.utils.junit;