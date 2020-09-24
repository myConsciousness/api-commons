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

import java.util.Arrays;

import lombok.NonNull;

/**
 * APIのリソースパスを抽象化したインターフェースです。<br>
 * APIのリソースパスを管理するクラスを定義する場合はこのインターフェースを実装してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface ResourcePath {

    /**
     * APIのリソースパスを返却します。
     *
     * @return APIのリソースパス
     */
    public String getResourcePath();

    /**
     * リソースパスに引数として渡された値をバインドして返却します。
     * <p>
     * 値をバインドさせるためにはあらかじめリソースパス中にバインド変数を定義する必要があります。リソースパスへの値のバインド処理は先頭から順番に
     * {@link String#format(String, Object...)} メソッドを使用して行われます。
     * <p>
     * 例えば、リソースパス中に3つのバインド変数を定義した場合は以下のように使用してください。
     *
     * <pre>
     * <code>
     * String resourcePath = ConcreteResourcePath.bind(var1, var2, var3);
     * </code>
     * </pre>
     *
     * @param parameters リソースパスへバインドする値。可変長引数なためカンマ区切りでバインドする値を指定してください。
     * @return 引数として渡された値がバインドされたリソースパス
     *
     * @exception NullPointerException     引数として渡された値が {@code null} の場合
     * @exception IllegalArgumentException バインドする対象の値が引数として渡されなかった場合
     */
    default String bind(@NonNull String... parameters) {

        if (parameters.length <= 0) {
            throw new IllegalArgumentException();
        }

        String resourcePath = this.getResourcePath();

        for (String parameter : Arrays.asList(parameters)) {
            resourcePath = String.format(resourcePath, parameter);
        }

        return resourcePath;
    }
}
