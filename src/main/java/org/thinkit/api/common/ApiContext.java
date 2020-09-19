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

import java.net.http.HttpResponse;

import org.thinkit.api.catalog.BiCatalog;
import org.thinkit.api.common.catalog.HttpStatus;

import lombok.NonNull;

/**
 * {@link Communicable} インターフェースを実装したクラスの安全な呼び出しと汎用的な処理を定義したコンテキストクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ApiContext {

    /**
     * APIオブジェクト
     */
    private Communicable api;

    /**
     * リトライ可否
     */
    private boolean retry;

    /**
     * リトライ数
     */
    private int retryCount;

    /**
     * リトライ時の待機時間
     */
    private int latency;

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
    public static class Builder {

        /**
         * APIオブジェクト
         */
        private Communicable api;

        /**
         * リトライ可否
         */
        private boolean retry;

        /**
         * リトライ数
         */
        private int retryCount;

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
        public Builder of(@NonNull Communicable api) {
            this.api = api;
            return this;
        }

        /**
         * 通信時にリトライを行うように設定します。
         *
         * @return 自分自身のインスタンス
         */
        public Builder withRetry() {
            this.retry = true;
            return this;
        }

        /**
         * リトライ数を設定します。
         *
         * @param retryCount リトライ数
         * @return 自分自身のインスタンス
         */
        public Builder withRetryCount(int retryCount) {
            this.retryCount = retryCount;
            return this;
        }

        /**
         * 引数として渡された {@code latency} の値をリトライ時の待機時間として設定します。 待機時間は初期設定として5秒が設定されています。
         *
         * @param latency リトライ時の待機時間
         * @return 自分自身のインスタンス
         *
         * @throws InvalidContextStateException リトライ時の待機時間として負数が渡された場合
         */
        public Builder withLatencyOnRetry(int latency) {

            if (latency < 0) {
                throw new InvalidContextStateException();
            }

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
        public ApiContext build() {

            if (this.api == null) {
                throw new InvalidContextStateException();
            }

            final ApiContext context = new ApiContext();
            context.api = this.api;
            context.retry = this.retry;
            context.retryCount = this.retryCount;
            context.latency = this.latency * 1000;

            return context;
        }
    }

    /**
     * HTTP通信を行いAPIを実行したレスポンスを文字列型として返却します。リトライ処理を行う場合は {@link ApiContext}
     * クラスのインスタンスを生成する際に、 {@link Builder} クラスの処理で {@link Builder#withRetry()}
     * メソッドを実行し、 {@link Builder#withRetryCount(int)} メソッドと
     * {@link Builder#withLatencyOnRetry(int)} メソッドを必要に応じて呼び出しリトライ時のパラメータを設定してください。
     * <p>
     * リトライ処理を行わない場合で、APIからのレスポンスでHTTPステータスが {@code 200} 以外の場合は {@code null} を返却します。
     * <p>
     * リトライ処理の対象はAPIからのレスポンスでHTTPステータスが {@code 200} 以外で、かつHTTPステータスがタイムアウト
     * {@code 408} 、またはHTTPステータスが内部サーバーエラー {@code 500} の場合です。リトライ処理が失敗した場合は
     * {@code null} を返却します。
     *
     * @return APIから返却された文字列型のレスポンスボディ
     *
     * @exception UnsupportedHttpStatusException {@link HttpStatus}
     *                                           で定義されていないHTTPステータスを検知した場合
     */
    public String send() {
        return this.send(0);
    }

    /**
     * HTTP通信を行いAPIを実行したレスポンスを文字列型として返却します。リトライ処理を行う場合は {@link ApiContext}
     * クラスのインスタンスを生成する際に、 {@link Builder} クラスの処理で {@link Builder#withRetry()}
     * メソッドを実行し、 {@link Builder#withRetryCount(int)} メソッドと
     * {@link Builder#withLatencyOnRetry(int)} メソッドを必要に応じて呼び出しリトライ時のパラメータを設定してください。
     * <p>
     * リトライ処理を行わない場合で、APIからのレスポンスでHTTPステータスが {@code 200} 以外の場合は {@code null} を返却します。
     * <p>
     * リトライ処理の対象はAPIからのレスポンスでHTTPステータスが {@code 200} 以外で、かつHTTPステータスがタイムアウト
     * {@code 408} 、またはHTTPステータスが内部サーバーエラー {@code 500} の場合です。リトライ処理が失敗した場合は
     * {@code null} を返却します。
     *
     * @return APIから返却された文字列型のレスポンスボディ
     *
     * @exception UnsupportedHttpStatusException {@link HttpStatus}
     *                                           で定義されていないHTTPステータスを検知した場合
     */
    public String send(int retryCount) {

        final HttpResponse<String> response = api.send();
        final HttpStatus statusCode = BiCatalog.getEnum(HttpStatus.class, response.statusCode());

        if (statusCode == null) {
            throw new UnsupportedHttpStatusException();
        }

        if (statusCode != HttpStatus.OK) {
            if (this.retry && retryCount <= this.retryCount && this.isStatusRetryable(statusCode)) {
                this.sleep();
                return this.send(++retryCount);
            } else {
                return null;
            }
        }

        return response.body();
    }

    /**
     * HTTPステータスが {@code 200} ではなかった場合にリトライ可能なステータスコードか判定します。
     *
     * @param statusCode HTTPステータスコード
     * @return HTTPステータスコードがリクエストタイムアウトの場合、または内部サーバーエラーの場合は {@code true} 、それ以外は
     *         {@code false}
     */
    private boolean isStatusRetryable(@NonNull HttpStatus statusCode) {
        return statusCode == HttpStatus.REQUEST_TIMEOUT || statusCode == HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * {@link ApiContext} クラスのインスタンスを生成する際に設定されたリトライ時の待機時間を基にスレッドをスリープさせます。
     */
    private void sleep() {
        try {
            Thread.sleep(this.latency);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
