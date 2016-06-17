package com.atom.telegrambot.request;

import com.atom.telegrambot.model.request.InlineKeyboardMarkup;
import com.atom.telegrambot.response.BaseResponse;
import com.atom.telegrambot.response.SendResponse;

/**
 * Stas Parshin
 * 07 May 2016
 */
public class EditMessageCaption extends BaseRequest<EditMessageCaption, BaseResponse> {

    public EditMessageCaption(Object chatId, int messageId, String text) {
        super(SendResponse.class);
        add("chat_id", chatId).add("message_id", messageId).add("text", text);
    }

    public EditMessageCaption(String inlineMessageId, String text) {
        super(BaseResponse.class);
        add("inline_message_id", inlineMessageId).add("text", text);
    }

    public EditMessageCaption caption(String caption) {
        return add("caption", caption);
    }

    public EditMessageCaption replyMarkup(InlineKeyboardMarkup replyMarkup) {
        return add("reply_markup", replyMarkup);
    }

}
