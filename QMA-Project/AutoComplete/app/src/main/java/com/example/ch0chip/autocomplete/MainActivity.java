package com.example.ch0chip.autocomplete;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.directtap.DirectTap;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            AutoCompleteTextView textView = (AutoCompleteTextView) rootView.findViewById(R.id.autocomplete_country);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.auto_complete_text_view, DataBase.COUNTRIES);
            textView.setAdapter(adapter);
            //ドロップダウンリスト最下段に表示されるヒント
            textView.setCompletionHint("Choose Country");
            //オートコンプリート開始までの文字数(0以下は指定できない)
            textView.setThreshold(1);
            new DirectTap.Starter(getActivity(), "4398d07a811a758c2891b735383c3e74ec84130501").setTestMode(true).setFullScreenOrientation(DirectTap.Starter.ORIENTATION_AUTO).start();
            new DirectTap.FullScreen(getActivity()).show();
            return rootView;


        }
    }
}
