package com.benzino.karma.ui.messages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.benzino.karma.R;
import com.benzino.karma.model.Chat;
import com.benzino.karma.model.Message;
import com.benzino.karma.ui.BaseActivity;
import com.benzino.karma.utils.Constants;
import com.benzino.karma.utils.Utils;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.HashMap;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class MessagesListActivity extends BaseActivity {
    private Firebase mMessagesRef;
    private ListView mListView;
    private MessagesListAdapter mMessageAdapter;
    private Chat mChat;
    private String mChatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mChatId = getIntent().getStringExtra(Constants.KEY_CHAT_ID);

        mMessagesRef = new Firebase(Constants.FIREBASE_URL_MESSAGES).child(mChatId);

        Firebase mChatsRef = new Firebase(Constants.FIREBASE_URL_CHATS).child(mChatId);

        mMessageAdapter = new MessagesListAdapter(MessagesListActivity.this, Message.class, R.layout.list_item, mMessagesRef);


        mListView = (ListView) findViewById(R.id.messages_list_view);
        mListView.setAdapter(mMessageAdapter);

        mChatsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mChat = dataSnapshot.getValue(Chat.class);

                if (mChat != null){
                    setTitle(mChat.getChatName());

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addNewMessage(View view){
        AddMessageDialogFragment dialogFragment = AddMessageDialogFragment.newInstance(mChatId);
        dialogFragment.show(getFragmentManager(), "AddMessageDialogFragment");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_messages, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.action_settings:
                Toast.makeText(this, getString(R.string.action_settings), Toast.LENGTH_LONG).show();
                break;
            case R.id.action_edit_list:
                editChatNameList();
                break;
            case R.id.action_remove_list:
                removeChatDialog();
                break;
            default:
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void editChatNameList() {
        EditChatNameDialogFragment editChatNameDialogFragment  = EditChatNameDialogFragment.newInstance(mChatId);
        editChatNameDialogFragment.show(getFragmentManager(), "EditChatNameDialogFragment");
    }

    private void removeChatDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove Chat")
                .setMessage("Are you sure you want to delete " + getTitle() + " ? \n You can't undo this action!")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        removeChat();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        builder.create();

        AlertDialog dialog = builder.show();
//        TextView title = (TextView) dialog.findViewById(android.R.id.title);
//        TextView message = (TextView) dialog.findViewById(android.R.id.message);
//        Button yesButton = (Button) dialog.findViewById(android.R.id.button1);
//        Button noButton = (Button) dialog.findViewById(android.R.id.button2);
//
//        Utils.changeTextViewFont(title, this);
//        Utils.changeTextViewFont(message, this);
//        Utils.changeButtonTextFont(yesButton, this);
//        Utils.changeButtonTextFont(noButton, this);


        dialog.show();
    }

    private void removeChat() {
        //Go to root URL
        Firebase chatRef = new Firebase(Constants.FIREBASE_URL);

        //Remove Chat with all the items
        //Set a hashMap with both urls to remove
        HashMap<String, Object> removeMap = new HashMap<>();
        removeMap.put("/" + Constants.FIREBASE_NODE_CHAT + "/" + mChatId, null);
        removeMap.put("/" + Constants.FIREBASE_NODE_MESSAGES + "/" + mChatId, null);

        chatRef.updateChildren(removeMap);
        finish();

    }


}
