package com.atom.telegrambot.response;

import com.atom.telegrambot.model.ChatMember;

/**
 * Stas Parshin
 * 28 May 2016
 */
public class GetChatMemberResponse extends BaseResponse {

    private ChatMember result;

    public ChatMember chatMember() {
        return result;
    }

    @Override
    public String toString() {
        return "GetChatMemberResponse{" +
                "result=" + result +
                '}';
    }
}
