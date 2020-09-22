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

import java.net.URLEncoder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
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
     * 引数として渡された {@code parameter}
     * オブジェクトに設定された情報を基にHTTP通信時に使用するリクエストパラメーターを生成し返却します。
     * <p>
     * リクエストパラメータは {@link RequestParameter} インターフェースを実装したクラス内に定義された
     * {@link ParameterMapping} アノテーションを付与されたフィールドを対象に生成されます。生成されたリクエストパラメータは返却される前に
     * {@code UTF-8} 形式でURLエンコード処理がされるため、この
     * {@link Communicable#createRequestParameter(RequestParameter)}
     * メソッドの呼び出し元でURLエンコードを行う必要はありません。
     * <p>
     * 引数として渡された {@link RequestParameter}
     * クラスに設定された特定フィールドの値が空の場合、その値が空であったフィールドに対してのパラメータの設定処理は無視されます。
     * <p>
     * 返却時のリクエストパラメータの形式は {@code "key1=value1&key2=value2"} です。
     *
     * @param parameter リクエストパラメーターを生成する際に使用するキーと値が格納されたオブジェクト
     * @return 引数として渡された {@link RequestParameter}
     *         クラスに設定された値を基に生成されたリクエストパラメーター。このリクエストパラメーターは {@code UTF-8}
     *         形式でURLエンコード済みです。
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    default String createRequestParameter(@NonNull RequestParameter parameter) {

        final StringBuilder requestParameter = new StringBuilder();

        Arrays.asList(parameter.getClass().getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(ParameterMapping.class)) {
                try {
                    field.setAccessible(true);
                    final String value = field.get(parameter).toString();

                    if (!StringUtils.isEmpty(value)) {

                        final String keyAlias = field.getAnnotation(ParameterMapping.class).keyAlias();

                        requestParameter.append(
                                String.format("%s=%s&", StringUtils.isEmpty(keyAlias) ? field.getName() : keyAlias,
                                        URLEncoder.encode(value, StandardCharsets.UTF_8)));
                    }
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
