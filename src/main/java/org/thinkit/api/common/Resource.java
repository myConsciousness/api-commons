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
 * APIのリソースを抽象化したインターフェースです。<br>
 * APIのリソースを管理するクラスを定義する場合はこのインターフェースを実装してください。
 * <p>
 * リソース中の動的要素を生成するための {@link #bind(String...)} メソッドを提供しています。 {@link Resource}
 * インターフェースを実装した具象クラスで {@code "%s"} を含むリソース文字列を定義し、バインドしたい値を
 * {@link #bind(String...)} メソッドの引数として渡すことでバインド処理を行うことができます。
 *
 * <pre>
 * 使用例:
 * <code>
 * String resource = ConcreteResource.AnyResource.bind(var1, var2, var3);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public interface Resource {

    /**
     * APIのリソースを返却します。
     *
     * @return APIのリソース
     */
    public String getResource();

    /**
     * リソースに引数として渡された値をバインドして返却します。
     * <p>
     * 値をバインドさせるためにはあらかじめリソース中にバインド変数を定義する必要があります。リソースへの値のバインド処理は先頭から順番に
     * {@link String#format(String, Object...)} メソッドを使用して行われます。
     * <p>
     * 例えば、リソースパス中に3つのバインド変数を定義した場合は以下のように使用してください。
     *
     * <pre>
     * 使用例:
     * <code>
     * String resource = ConcreteResource.AnyResource.bind(var1, var2, var3);
     * </code>
     * </pre>
     *
     * @param parameters リソースへバインドする値（可変長引数）
     * @return 引数として渡された値がバインドされたリソース
     *
     * @exception NullPointerException     引数として渡された値が {@code null} の場合
     * @exception IllegalArgumentException バインドする対象の値が引数として渡されなかった場合
     */
    default String bind(@NonNull String... parameters) {

        if (parameters.length <= 0) {
            throw new IllegalArgumentException();
        }

        String resource = this.getResource();

        for (String parameter : Arrays.asList(parameters)) {
            resource = String.format(resource, parameter);
        }

        return resource;
    }
}
