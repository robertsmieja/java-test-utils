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

package com.robertsmieja.test.utils.junit.exceptions;

public class ObjectFactoryException extends Exception {

    public ObjectFactoryException() {
        super();
    }

    public ObjectFactoryException(String message) {
        super(message);
    }

    public ObjectFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectFactoryException(Throwable cause) {
        super(cause);
    }

    protected ObjectFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}