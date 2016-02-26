package com.benzino.karma.ui.messages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.benzino.karma.R;
import com.benzino.karma.model.Chat;
import com.benzino.karma.utils.Constants;
import com.benzino.karma.utils.Utils;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created on 26/2/16.
 *
 * @author Anas
 */
public class EditChatNameDialogFragment extends DialogFragment {
    String mChatId;
    EditText changeChatNameEditText;

    /*Empty constructor*/
    public EditChatNameDialogFragment() {
    }

    public static EditChatNameDialogFragment newInstance(String chatId){
        EditChatNameDialogFragment instance = new EditChatNameDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_CHAT_ID, chatId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChatId = getArguments().getString(Constants.KEY_CHAT_ID);
    }



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog,null);
        changeChatNameEditText = (EditText) view.findViewById(R.id.dialog_edit_text);
        Button changeChatNameButton = (Button) view.findViewById(R.id.dialog_button);

        /*Set text*/
        changeChatNameEditText.setHint(R.string.dialog_edit_text_change_chat_name);
        changeChatNameButton.setText(R.string.dialog_button_change_chat_name);

        /*Set font*/
        Utils.changeEditTextFont(changeChatNameEditText, getActivity());
        Utils.changeButtonTextFont(changeChatNameButton, getActivity());

        changeChatNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    editChatName(mChatId);
                }
                return false;
            }
        });

        changeChatNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editChatName(mChatId);
            }
        });

        builder.setView(view);



        return builder.create();
    }

    private void editChatName(String chatId) {
        String changedName = changeChatNameEditText.getText().toString();
        Firebase chatRef = new Firebase(Constants.FIREBASE_URL_CHATS).child(chatId);


        if (!changedName.equals("")){
            Chat chat = new Chat(changedName, "Anas");
            chatRef.setValue(chat);
        }

        EditChatNameDialogFragment.this.getDialog().cancel();

    }
}
