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

package org.thinkit.api.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * HTTP通信のリクエスト送信時に付加するリクエストパラメータのマッピングを行うアノテーションです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterMapping {

    /**
     * HTTO通信時に使用するリクエストパラメータのキー名のエイリアスを管理します。
     * <p>
     * 対象のフィールドへ当該アノテーションを付加し、エイリアスを設定しない場合は、フィールド名がリクエストパラメータのキー名として設定されます。
     * アノテーションを付加するフィールド名とリクエストパラメーターのキー名が異なる場合は、
     * 実際にHTTP通信をする際に使用するパラメータ名をエイリアスとして渡してください。
     * <p>
     * 初期値は空文字列が設定されています。
     *
     * @return HTTP通信時に使用するパラメータのキー名
     */
    String keyAlias() default "";
}