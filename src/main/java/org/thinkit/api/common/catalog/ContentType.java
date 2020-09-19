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

import org.thinkit.api.catalog.BiCatalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Content-Typeを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum ContentType implements BiCatalog<ContentType, String> {

    /**
     * プレーン
     */
    PLAIN(0, "text/plain"),

    /**
     * csv
     */
    CSV(1, "text/csv"),

    /**
     * HTML
     */
    HTML(2, "text/html"),

    /**
     * CSS
     */
    CSS(3, "text/css"),

    /**
     * JavaScript
     */
    JAVASCRIPT(4, "text/javascript"),

    /**
     * exe
     */
    EXE(5, "application/octet-stream"),

    /**
     * json
     */
    JSON(6, "application/json"),

    /**
     * PDF
     */
    PDF(7, "application/pdf"),

    /**
     * Office 2007以前のExcel
     */
    EXCEL_BEFORE_2007(8, "application/vnd.ms-excel"),

    /**
     * Excel
     */
    EXCEL(9, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),

    /**
     * jpeg
     */
    JPEG(10, "image/jpeg"),

    /**
     * png
     */
    PNG(11, "image/png"),

    /**
     * gif
     */
    GIF(12, "image/gif"),

    /**
     * bmp
     */
    BMP(13, "image/bmp"),

    /**
     * svg
     */
    SVG(14, "image/svg+xml"),

    /**
     * zip
     */
    ZIP(15, "application/zip"),

    /**
     * lzh
     */
    LZH(16, "application/x-lzh"),

    /**
     * tar
     */
    TAR(17, "application/x-tar"),

    /**
     * mp3
     */
    MP3(18, "audio/mpeg"),

    /**
     * mp4
     */
    MP4(19, "video/mp4"),

    /**
     * mpeg
     */
    MPEG(20, "video/mpeg");

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * タグ
     */
    @Getter
    private final String tag;
}
