package com.atom.telegrambot.model.request;

/**
 * stas
 * 1/12/16.
 */
public abstract class InlineQueryResult<T extends InlineQueryResult> {

    @SuppressWarnings("unchecked")
    private final T thisAsT = (T) this;

    private String type;
    private String id;
    private InputMessageContent input_message_content;
    private InlineKeyboardMarkup reply_markup;

    public InlineQueryResult(String type, String id) {
        this.type = type;
        this.id = id;
    }

    public T inputMessageContent(InputMessageContent inputMessageContent) {
        this.input_message_content = inputMessageContent;
        return thisAsT;
    }

    public T replyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.reply_markup = replyMarkup;
        return thisAsT;
    }
}
