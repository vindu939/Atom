package com.atom.telegrambot.request;

import com.atom.telegrambot.response.GetUpdatesResponse;

/**
 * stas
 * 5/2/16.
 */
public class GetUpdates extends BaseRequest<GetUpdates, GetUpdatesResponse> {

    public GetUpdates() {
        super(GetUpdatesResponse.class);
    }

    public GetUpdates offset(int offset) {
        return add("offset", offset);
    }

    public GetUpdates limit(int limit) {
        return add("limit", limit);
    }

    public GetUpdates timeout(int timeout) {
        return add("timeout", timeout);
    }
}
