package com.benzino.karma.ui.messages;

import android.app.Activity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;

import com.benzino.karma.R;
import com.benzino.karma.model.Message;
import com.benzino.karma.utils.Utils;
import com.firebase.client.Firebase;
import com.firebase.client.Query;
import com.firebase.ui.FirebaseListAdapter;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class MessagesListAdapter extends FirebaseListAdapter<Message> {
    String mChatId;

    public MessagesListAdapter(Activity activity, Class<Message> modelClass, int modelLayout, Query ref) {
        super(activity, modelClass, modelLayout, ref);
        this.mActivity = activity;
    }

    @Override
    protected void populateView(View view, Message message, int position) {
        TextView title = (TextView) view.findViewById(R.id.text_view_list_item);
        TextView owner = (TextView) view.findViewById(R.id.text_view_created_by_user);
        TextView createdBy = (TextView) view.findViewById(R.id.created_by);
        TextView timeText = (TextView) view.findViewById(R.id.text_view_edit_time);

        Utils.changeTextViewFont(title, this.mActivity);
        Utils.changeTextViewFont(owner, this.mActivity);
        Utils.changeTextViewFont(createdBy, this.mActivity);
        Utils.changeTextViewFont(timeText, this.mActivity);

        long time = message.createdAtLong();
        long now = System.currentTimeMillis();

        String date = (String)DateUtils.getRelativeTimeSpanString(time, now, DateUtils.SECOND_IN_MILLIS);

        title.setText(message.getMessageName());
        owner.setText(message.getOwner());
        timeText.setText(date);
    }
}
