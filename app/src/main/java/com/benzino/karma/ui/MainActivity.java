package com.benzino.karma.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.benzino.karma.R;
import com.benzino.karma.ui.chats.AddChatDialogFragment;
import com.benzino.karma.ui.chats.ChatsFragment;
import com.benzino.karma.ui.users.UsersFragment;
import com.benzino.karma.utils.Constants;
import com.benzino.karma.utils.Utils;
import com.firebase.client.Firebase;

public class MainActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mFirebase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeScreen();


        mFirebase = new Firebase(Constants.FIREBASE_URL);
    }

    /* Floating button listener on Chats fragment */
    public void addNewChat(View view){

        AddChatDialogFragment chatDialog = AddChatDialogFragment.newInstance();
        chatDialog.show(getFragmentManager(), "AddChatDialogFragment");
    }

    /* Floating button listener on User fragment */
    public void addNewUser(View view){

        Toast.makeText(getApplicationContext(), "Add new user", Toast.LENGTH_SHORT).show();
    }

    private void initializeScreen(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        Utils.changeTextViewFont(toolbarTitle, getApplicationContext());
        setSupportActionBar(toolbar);
        setTitle("");

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(2);
        pager.setAdapter(adapter);

        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        Utils.changeTabsFont(tabLayout, getApplicationContext());

    }


    /*MyPagerAdapter class that extends FragmentStatePagerAdapter to save fragments state*/
    public class MyPagerAdapter extends FragmentStatePagerAdapter{

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        /**
         * Return the Fragment associated with a specified position.
         *
         * @param position
         */
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position){
                case 0:
                    fragment = ChatsFragment.newInstance();
                    break;
                case 1:
                    fragment = UsersFragment.newInstance();
                    break;
                default:
                    fragment = ChatsFragment.newInstance();
                    break;

            }
            return fragment;
        }

        /**
         * Return the number of views available.
         */
        @Override
        public int getCount() {
            return 2;
        }

        /**
         * This method may be called by the ViewPager to obtain a title string
         * to describe the specified page. This method may return null
         * indicating no title for this page. The default implementation returns
         * null.
         *
         * @param position The position of the title requested
         * @return A title for the requested page
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Chats";
                case 1:
                default:
                    return "Users";
            }

        }
    }


}
