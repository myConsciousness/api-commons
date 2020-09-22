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

    default String bind(@NonNull String... parameters) {

        // TODO Preconditionに置き換える
        if (parameters.length <= 0) {
            throw new IllegalArgumentException();
        }

        String resourcePath = this.getResourcePath();

        return resourcePath;
    }
}
