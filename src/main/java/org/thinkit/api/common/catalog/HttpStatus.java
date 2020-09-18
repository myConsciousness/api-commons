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
 * HTTPステータスを管理するカタログです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@RequiredArgsConstructor
public enum HttpStatus implements BiCatalog<HttpStatus, Integer> {

    // --- 1xx Information ---

    /**
     * 100 Continue
     */
    CONTINUE(0, 100),

    /**
     * 101 Switching Protocols
     */
    SWITCHING_PROTOCOLS(1, 101),

    /**
     * 102 Processing
     */
    PROCESSING(2, 102),

    // --- 2xx Success ---

    /**
     * 200 OK
     */
    OK(3, 200),

    /**
     * 201 Created
     */
    CREATED(4, 201),

    /**
     * 202 Accepted
     */
    ACCEPTED(5, 202),

    /**
     * 203 Non Authoritative Information
     */
    NON_AUTHORITATIVE_INFORMATION(6, 203),

    /**
     * 204 No Content
     */
    NO_CONTENT(7, 204),

    /**
     * 205 Reset Content
     */
    RESET_CONTENT(8, 205),

    /**
     * 206 Partial Content
     */
    PARTIAL_CONTENT(9, 206),

    /**
     * 207 Multi-Status or 207 Partial Update OK
     */
    MULTI_STATUS(10, 207),

    // --- 3xx Redirection ---

    /**
     * 300 Mutliple Choices
     */
    MULTIPLE_CHOICES(11, 300),

    /**
     * 301 Moved Permanently
     */
    MOVED_PERMANENTLY(12, 301),

    /**
     * 302 Moved Temporarily
     */
    MOVED_TEMPORARILY(13, 302),

    /**
     * 303 See Other
     */
    SEE_OTHER(14, 303),

    /**
     * 304 Not Modified
     */
    NOT_MODIFIED(15, 304),

    /**
     * 305 Use Proxy
     */
    USE_PROXY(16, 305),

    /**
     * 307 Temporary Redirect
     */
    TEMPORARY_REDIRECT(17, 307),

    // --- 4xx Client Error ---

    /**
     * 400 Bad Request
     */
    BAD_REQUEST(18, 400),

    /**
     * 401 Unauthorized
     */
    UNAUTHORIZED(19, 401),

    /**
     * 402 Payment Required
     */
    PAYMENT_REQUIRED(20, 402),

    /**
     * 403 Forbidden
     */
    FORBIDDEN(21, 403),

    /**
     * 404 Not Found
     */
    NOT_FOUND(22, 404),

    /**
     * 405 Method Not Allowed
     */
    METHOD_NOT_ALLOWED(23, 405),

    /**
     * 406 Not Acceptable
     */
    NOT_ACCEPTABLE(24, 406),

    /**
     * 407 Proxy Authentication Required
     */
    PROXY_AUTHENTICATION_REQUIRED(25, 407),

    /**
     * 408 Request Timeout
     */
    REQUEST_TIMEOUT(26, 408),

    /**
     * 409 Conflict
     */
    CONFLICT(27, 409),

    /**
     * 410 Gone
     */
    GONE(28, 410),

    /**
     * 411 Length Required
     */
    LENGTH_REQUIRED(29, 411),

    /**
     * 412 Precondition Failed
     */
    PRECONDITION_FAILED(30, 412),

    /**
     * 413 Request Entity Too Large
     */
    REQUEST_TOO_LONG(31, 413),

    /**
     * 414 Request-URI Too Long
     */
    REQUEST_URI_TOO_LONG(32, 414),

    /**
     * 415 Unsupported Media Type
     */
    UNSUPPORTED_MEDIA_TYPE(33, 415),

    /**
     * 416 Requested Range Not Satisfiable
     */
    REQUESTED_RANGE_NOT_SATISFIABLE(34, 416),

    /**
     * 417 Expectation Failed
     */
    EXPECTATION_FAILED(35, 417),

    /**
     * 419 Insufficient Space on Resource or 419 Proxy Reauthentication Required
     */
    INSUFFICIENT_SPACE_ON_RESOURCE(36, 419),

    /**
     * 420 Method Failure
     */
    METHOD_FAILURE(37, 420),

    /**
     * 422 Unprocessable Entity
     */
    UNPROCESSABLE_ENTITY(38, 422),

    /**
     * 423 Locked
     */
    LOCKED(39, 423),

    /**
     * 424 Failed Dependency
     */
    FAILED_DEPENDENCY(40, 424),

    // --- 5xx Server Error ---

    /**
     * 500 Server Error
     */
    INTERNAL_SERVER_ERROR(41, 500),

    /**
     * 501 Not Implemented
     */
    NOT_IMPLEMENTED(42, 501),

    /**
     * 502 Bad Gateway
     */
    BAD_GATEWAY(43, 502),

    /**
     * 503 Service Unavailable
     */
    SERVICE_UNAVAILABLE(44, 503),

    /**
     * 504 Gateway Timeout
     */
    GATEWAY_TIMEOUT(45, 504),

    /**
     * 505 HTTP Version Not Supported
     */
    HTTP_VERSION_NOT_SUPPORTED(46, 505),

    /**
     * 507 Insufficient Storage
     */
    INSUFFICIENT_STORAGE(47, 507);

    /**
     * コード値
     */
    @Getter
    private final int code;

    /**
     * タグ
     */
    @Getter
    private final Integer tag;
}
