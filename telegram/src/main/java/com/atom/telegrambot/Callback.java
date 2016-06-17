package com.atom.telegrambot;

import com.atom.telegrambot.response.BaseResponse;
import com.atom.telegrambot.request.BaseRequest;

import java.io.IOException;

/**
 * stas
 * 5/3/16.
 */
public interface Callback<T extends BaseRequest, R extends BaseResponse> {

    void onResponse(T request, R response);

    void onFailure(T request, IOException e);
}
