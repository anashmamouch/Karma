package com.benzino.karma.ui.chats;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.benzino.karma.R;
import com.benzino.karma.model.Chat;
import com.benzino.karma.utils.Constants;
import com.benzino.karma.utils.Utils;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import org.w3c.dom.Text;

/**
 * Created on 24/2/16.
 *
 * @author Anas
 */
public class AddChatDialogFragment extends DialogFragment {
    EditText mEditTextNewChat;


    /*Requires empty constructor*/
    public AddChatDialogFragment() {
    }

    /*Creates a new instance of the Dialog fragment and pass bundle with arguments in it*/
    public static AddChatDialogFragment newInstance(){
        AddChatDialogFragment instance = new AddChatDialogFragment();
        Bundle bundle = new Bundle();
//        bundle.putString(Constants.KEY_CHAT_ID, chatId);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    /*Opens keyboard automatically when the dialog is created*/
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog, null);
        mEditTextNewChat = (EditText) view.findViewById(R.id.dialog_edit_text);
        Button button  = (Button) view.findViewById(R.id.dialog_button);

        mEditTextNewChat.setHint(R.string.create_chat_edit_text_hint);
        button.setText(R.string.create_chat_button_text);

        //Set font
        Utils.changeEditTextFont(mEditTextNewChat, getActivity());
        Utils.changeButtonTextFont(button, getActivity());

        //Calls addChat() when user presses Done on the keyboard
        mEditTextNewChat.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    addChat();
                }
                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChat();
            }
        });

        builder.setView(view);

        return builder.create();

    }

    private void addChat() {
        //Get the string that the user entered within the editText
        String newChat = mEditTextNewChat.getText().toString();
        String admin = "Anas";

        if (!newChat.equals("")){
            final Firebase ref = new Firebase(Constants.FIREBASE_URL_CHATS);

            Chat chat = new Chat(newChat, admin);

            ref.push().setValue(chat);



            //Close dialog fragment
            AddChatDialogFragment.this.getDialog().cancel();

        }

    }
}
