package com.benzino.karma.ui.users;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.benzino.karma.R;

import java.util.List;

/**
 * Created on 23/2/16.
 *
 * @author Anas
 */
public class UsersFragment extends Fragment {
    private ListView mListView;

    /*Required empty constructor*/
    public UsersFragment() {
    }

    /*Creates a fragment and pass a bundle with data as it's arguments*/
    public static UsersFragment newInstance(){
        UsersFragment instance = new  UsersFragment();
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
        View view = inflater.inflate(R.layout.fragment_users, container, false);

        mListView = (ListView) view.findViewById(R.id.users_list_view);

        return view;

    }

    private void initializeScreen(View view){


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
