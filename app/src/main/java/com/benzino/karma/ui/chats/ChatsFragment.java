package com.benzino.karma.ui.chats;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.benzino.karma.R;
import com.benzino.karma.model.Chat;
import com.benzino.karma.ui.messages.MessagesListActivity;
import com.benzino.karma.utils.Constants;
import com.firebase.client.Firebase;

/**
 * Created on 23/2/16.
 *
 * @author Anas
 */
public class ChatsFragment extends Fragment {
    private Firebase mChatsRef;

    private ListView mListView;
    private ChatListAdapter mChatListAdapter;
    private String mChatId;

    private TextView timeTextView;

    /*Required empty constructors*/
    public ChatsFragment() {
    }

    /*Creates fragment and passes bundle with data in its argument*/
    public static ChatsFragment newInstance(){
        ChatsFragment instance = new ChatsFragment();
        Bundle bundle = new Bundle();
        instance.setArguments(bundle);
        return instance;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*Inflate the layout for this screen*/
        View view = inflater.inflate(R.layout.fragment_chats, container, false);

        mChatsRef = new Firebase(Constants.FIREBASE_URL_CHATS);

        mListView = (ListView) view.findViewById(R.id.chats_list_view);
        mChatListAdapter = new ChatListAdapter(getActivity(), Chat.class, R.layout.list_item, mChatsRef);

        mListView.setAdapter(mChatListAdapter);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getActivity(), MessagesListActivity.class);
                String chatId = mChatListAdapter.getRef(position).getKey();
                intent.putExtra(Constants.KEY_CHAT_ID, chatId);
                startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        mListView.setAdapter(mChatListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void initializeScreen(View view){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
