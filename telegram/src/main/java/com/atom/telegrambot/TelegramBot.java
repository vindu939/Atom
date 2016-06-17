package com.atom.telegrambot;

import com.atom.telegrambot.impl.TelegramBotClient;
import com.atom.telegrambot.model.File;
import com.atom.telegrambot.response.BaseResponse;
import com.atom.telegrambot.impl.FileApi;
import com.atom.telegrambot.request.BaseRequest;

/**
 * Stas Parshin
 * 16 October 2015
 */
public class TelegramBot extends OldTelegramBot {

    private final TelegramBotClient api;
    private final FileApi fileApi;

    TelegramBot(TelegramBotClient api, FileApi fileApi) {
        super(fileApi);
        this.api = api;
        this.fileApi = fileApi;
    }

    public String getFullFilePath(File file) {
        return fileApi.getFullFilePath(file.getFilePath());
    }

    @Override
    public <T extends BaseRequest, R extends BaseResponse> R execute(BaseRequest<T, R> request) {
        return api.send(request);
    }

    public <T extends BaseRequest<T, R>, R extends BaseResponse> void execute(T request, Callback<T, R> callback) {
        api.send(request, callback);
    }
}
