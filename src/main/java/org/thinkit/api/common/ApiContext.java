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

import lombok.NonNull;

/**
 * {@link Communicable} インターフェースを実装したクラスの安全な呼び出しと汎用的な処理を定義したコンテキストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ApiContext<R> {

    /**
     * APIオブジェクト
     */
    private Communicable<R> api;

    /**
     * デフォルトコンストラクタ
     */
    private ApiContext() {
    }

    /**
     * コンストラクタ
     *
     * @param api APIオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ApiContext(@NonNull Communicable<R> api) {
        this.api = api;
    }

    /**
     * 引数として渡された {@code api} を基に {@link ApiContext} オブジェクトの新しいインスタンスを生成し返却します。
     *
     * @param api APIオブジェクト
     * @return {@link ApiContext} オブジェクトの新しいインスタンス
     */
    public static <R> ApiContext<R> of(@NonNull Communicable<R> api) {
        return new ApiContext<R>(api);
    }

}
