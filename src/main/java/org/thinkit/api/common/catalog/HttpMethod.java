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

package org.thinkit.api.common.catalog;

import org.thinkit.api.catalog.Catalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * HTTPメソッドを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum HttpMethod implements Catalog<HttpMethod> {

    /**
     * 取得メソッド
     */
    GET(0),

    /**
     * 送信メソッド
     */
    POST(1),

    /**
     * 更新メソッド
     */
    PUT(2),

    /**
     * 更新メソッド
     */
    PATCH(3),

    /**
     * 削除メソッド
     */
    DELETE(4);

    /**
     * コード値
     */
    @Getter
    private final int code;
}
