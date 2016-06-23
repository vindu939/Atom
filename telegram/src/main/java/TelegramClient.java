import com.atom.telegrambot.Callback;
import com.atom.telegrambot.TelegramBot;
import com.atom.telegrambot.TelegramBotAdapter;
import com.atom.telegrambot.model.request.ForceReply;
import com.atom.telegrambot.model.request.InlineKeyboardButton;
import com.atom.telegrambot.model.request.InlineKeyboardMarkup;
import com.atom.telegrambot.model.request.ParseMode;
import com.atom.telegrambot.request.AnswerCallbackQuery;
import com.atom.telegrambot.request.SendMessage;
import com.atom.telegrambot.request.SetWebhook;
import com.atom.telegrambot.response.BaseResponse;

import java.io.IOException;

/**
 * Created by aravindp on 17/6/16.
 */
public class TelegramClient {

    public static void main(String[] args){
        TelegramBot bot = TelegramBotAdapter.build("197123987:AAESOiX9Pl2x_DMbE1P6stXV-OKBGyAE6QU");

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup(new InlineKeyboardButton[]{new InlineKeyboardButton("inline ok").callbackData("callback ok"), new InlineKeyboardButton("inline cancel").callbackData("callback cancel")});
        //bot.execute(new SendMessage(192591982, "message").replyMarkup(keyboardMarkup));

        String htmlMessage = "<a href=\"http://google.com\">Uber Login</a>";
        //bot.execute(new SendMessage(192591982, htmlMessage).parseMode(ParseMode.HTML));
        ForceReply forceReply = new ForceReply(true);
       // bot.execute(new SendMessage(192591982, "forced Reply").replyMarkup(forceReply));

        //
        // bot.execute(new AnswerCallbackQuery("827176265032807615").text("do u really want to cancel?").showAlert(true));

        bot.execute(new SetWebhook().url("https://278e1b31.ngrok.io/webhook"), new Callback<SetWebhook, BaseResponse>() {
            @Override
            public void onResponse(SetWebhook request, BaseResponse response) {
                System.out.println(response);
            }

            @Override
            public void onFailure(SetWebhook request, IOException e) {

            }
        });
    }

}
