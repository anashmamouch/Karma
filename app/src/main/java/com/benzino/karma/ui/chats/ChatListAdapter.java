package com.benzino.karma.ui.chats;

import android.app.Activity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.benzino.karma.R;
import com.benzino.karma.model.Chat;
import com.benzino.karma.utils.Utils;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

import junit.framework.TestCase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class ChatListAdapter extends FirebaseListAdapter<Chat> {
    String mChatId;

    public ChatListAdapter(Activity activity, Class<Chat> chat, int layout, Query ref) {
        super(activity, chat, layout, ref);
        this.mActivity = activity;
        this.mChatId = ref.getRef().getKey();
    }

    @Override
    protected void populateView(View view, Chat chat, int position) {
        TextView title = (TextView) view.findViewById(R.id.text_view_list_item);
        TextView createdBy = (TextView) view.findViewById(R.id.created_by);
        TextView createdByUser = (TextView) view.findViewById(R.id.text_view_created_by_user);
        TextView time = (TextView) view.findViewById(R.id.text_view_edit_time);

        //Change fonts
        Utils.changeTextViewFont(title, this.mActivity);
        Utils.changeTextViewFont(createdBy, this.mActivity);
        Utils.changeTextViewFont(createdByUser, this.mActivity);
        Utils.changeTextViewFont(time, this.mActivity);

        Date date = new Date(chat.getTimestampCreatedLong());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy | HH:mm:ss");
        String dateFormatted = formatter.format(date);
        final long now = System.currentTimeMillis();
        final long createdAt = chat.getTimestampCreatedLong();
        String timeAgo = (String) DateUtils.getRelativeDateTimeString(mActivity, createdAt,DateUtils.SECOND_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE );
        //timeAgo = (String)DateUtils.getRelativeTimeSpanString(now, createdAt, DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_NO_NOON);

        //Set text to appropriate data
        title.setText(chat.getChatName());
        createdByUser.setText(chat.getAdmin());
        time.setText(timeAgo);
    }



}
