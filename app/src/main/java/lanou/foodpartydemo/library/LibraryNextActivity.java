package lanou.foodpartydemo.library;

import android.content.Intent;
import android.widget.ListView;
import android.widget.TextView;

import lanou.foodpartydemo.R;
import lanou.foodpartydemo.base.BaseActivity;

/**
 * Created by dllo on 16/11/4.
 */
public class LibraryNextActivity extends BaseActivity{

    private TextView titleKind;
    private ListView listView;

    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        titleKind.setText(name);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_library_next;
    }

    @Override
    protected void initViews() {
        titleKind = (TextView) findViewById(R.id.title_kind);
        listView = (ListView) findViewById(R.id.lv_library_next);

    }
}
