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
     * リトライ可否
     */
    private boolean retry;

    /**
     * リトライ時の待機時間
     */
    private int latency = 10;

    /**
     * デフォルトコンストラクタ
     */
    private ApiContext() {
    }

    /**
     * {@link ApiContext} オブジェクトを生成するビルダークラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    public static class Builder<R> {

        /**
         * APIオブジェクト
         */
        private Communicable<R> api;

        /**
         * リトライ可否
         */
        private boolean retry;

        /**
         * リトライ時の待機時間
         */
        private int latency = 5;

        /**
         * デフォルトコンストラクタ
         */
        private Builder() {
        }

        /**
         * 引数として渡された {@code api} をオブジェクトに設定します。
         *
         * @param api APIオブジェクト
         * @return 自分自身のインスタンス
         */
        public Builder<R> of(@NonNull Communicable<R> api) {
            this.api = api;
            return this;
        }

        /**
         * 通信時にリトライを行うように設定します。
         *
         * @return 自分自身のインスタンス
         */
        public Builder<R> withRetry() {
            this.retry = true;
            return this;
        }

        /**
         * 引数として渡された {@code latency} の値をリトライ時の待機時間として設定します。 待機時間は初期設定として5秒が設定されています。
         *
         * @param latency リトライ時の待機時間
         * @return 自分自身のインスタンス
         */
        public Builder<R> withLatencyOnRetry(int latency) {
            this.latency = latency;
            return this;
        }

        /**
         * 設定された値を基に {@link ApiContext} クラスの新しいインスタンスを生成し返却します。
         *
         * @return {@link ApiContext} クラスの新しいインスタンス
         *
         * @throws InvalidContextStateException {@link #of(Communicable)}
         *                                      メソッドが呼び出されていない場合、または、APIオブジェクトが
         *                                      {@code null} の場合
         */
        public ApiContext<R> build() {

            if (this.api == null) {
                throw new InvalidContextStateException();
            }

            final ApiContext<R> context = new ApiContext<>();
            context.api = this.api;
            context.retry = this.retry;
            context.latency = this.latency;

            return context;
        }
    }
}
