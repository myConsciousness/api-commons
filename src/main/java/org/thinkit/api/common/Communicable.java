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
import java.util.Arrays;

import org.thinkit.api.common.annotation.ParameterMapping;
import org.thinkit.api.common.entity.RequestParameter;

import lombok.NonNull;

/**
 * 外部APIとのHTTP通信処理を抽象化したインターフェースです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Communicable {

    /**
     * 引数として渡されたパラメータマップを基にリクエストパラメータを生成し文字列型として返却します。
     * 引数として渡すリクエストマップにはリクエストパラメータのキーとリクエストパラメータの値が 1:1 で紐づくように格納してください。
     * <p>
     * 生成されたリクエストパラメータは {@code "key1=value1&key2=value2"} の形式で返却します。
     *
     * @param parameters リクエストパラメータを生成する際のパラメータマップ
     * @return 生成されたリクエストパラメータ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    default String createRequestParameter(@NonNull Class<? extends RequestParameter> parameter) {

        final StringBuilder requestParameter = new StringBuilder();

        Arrays.asList(parameter.getClass().getFields()).forEach(field -> {
            if (field.isAnnotationPresent(ParameterMapping.class)) {
                try {
                    final String key = field.getAnnotation(ParameterMapping.class).key();
                    final String value = field.get(parameter).toString();
                    requestParameter.append(String.format("%s=%s&", key, value));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new InvalidParameterStateException(e);
                }
            }
        });

        requestParameter.setLength(requestParameter.length() - 1);

        return requestParameter.toString();
    }

    /**
     * HTTPリクエストを送信し、文字列型のbody要素を持つHTTPレスポンスを返却します。
     *
     * @return 文字列型のbody要素を持つHTTPレスポンス
     */
    public HttpResponse<String> send();
}
