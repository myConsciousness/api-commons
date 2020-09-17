/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.api.common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * {@link Communicable} インターフェースのテストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
class CommunicableTest {

    @Test
    void testToString() {
        final String actual = new CommunicableToString().send();
        assertNotNull(actual);
        assertEquals("success", actual);
    }

    @Test
    void testToInteger() {
        assertEquals(1, new CommunicableToInteger().send());
    }

    @Test
    void testToBoolean() {
        assertEquals(true, new CommunicableToBoolean().send());
    }

    /**
     * String型の返却値を返却する {@link Communicable#convert()} メソッドを実装するクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    class CommunicableToString implements Communicable<String> {

        @Override
        public String send() {
            return "success";
        }
    }

    /**
     * int型の返却値を返却する {@link Communicable#convert()} メソッドを実装するクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    class CommunicableToInteger implements Communicable<Integer> {

        @Override
        public Integer send() {
            return 1;
        }
    }

    /**
     * boolean型の返却値を返却する {@link Communicable#convert()} メソッドを実装するクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    class CommunicableToBoolean implements Communicable<Boolean> {

        @Override
        public Boolean send() {
            return true;
        }
    }
}
