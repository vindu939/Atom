package com.atom.telegrambot.request;

import com.atom.telegrambot.response.BaseResponse;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class LeaveChat extends BaseRequest<LeaveChat, BaseResponse> {

    public LeaveChat(Object chatId) {
        super(BaseResponse.class);
        add("chat_id", chatId);
    }
}
