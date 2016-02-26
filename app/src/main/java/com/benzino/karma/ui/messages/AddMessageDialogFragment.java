package com.benzino.karma.ui.messages;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.benzino.karma.R;
import com.benzino.karma.model.Message;
import com.benzino.karma.utils.Constants;
import com.benzino.karma.utils.Utils;
import com.firebase.client.Firebase;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class AddMessageDialogFragment extends DialogFragment {
    String mChatId;
    EditText mEditTextNewMessage;

    /*Empty constructor required*/
    public AddMessageDialogFragment() {
    }

    /*Creates new instance of message dialog*/
    public static AddMessageDialogFragment newInstance(String mChatId){
        AddMessageDialogFragment instance = new AddMessageDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_CHAT_ID, mChatId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mChatId = getArguments().getString(Constants.KEY_CHAT_ID);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog, null);

        mEditTextNewMessage = (EditText) view.findViewById(R.id.dialog_edit_text);
        Button buttonAddNewMessage = (Button) view.findViewById(R.id.dialog_button);

        mEditTextNewMessage.setHint(R.string.dialog_add_message);
        buttonAddNewMessage.setText(R.string.dialog_add_message_button);


        Utils.changeButtonTextFont(buttonAddNewMessage, getActivity());
        Utils.changeEditTextFont(mEditTextNewMessage, getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        mEditTextNewMessage.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN ){
                    addNewMessage();
                }
                return false;
            }
        });

        buttonAddNewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMessage();
            }
        });


        builder.setView(view);


        return builder.create();
    }

    private void addNewMessage() {
        String messageText = mEditTextNewMessage.getText().toString();
        Message message = new Message(messageText, "Benzino");

        Firebase messageRef = new Firebase(Constants.FIREBASE_URL_MESSAGES).child(mChatId);

        if (!messageText.equals("")){
            messageRef.push().setValue(message);
        }

        AddMessageDialogFragment.this.getDialog().cancel();
    }

}
