package lanou.foodpartydemo;



import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.RadioButton;

import lanou.foodpartydemo.base.BaseActivity;
import lanou.foodpartydemo.homepage.HomePageFragment;
import lanou.foodpartydemo.library.LibraryFragment;
import lanou.foodpartydemo.mine.MineFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener{


    private RadioButton library;
    private RadioButton homepage;
    private RadioButton mine;

    @Override
    protected void initData() {
        library.setChecked(true);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_replace,new ListFragment());
        transaction.commit();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        library = bindView(R.id.rb_library);
        homepage = bindView(R.id.rb_homepage);
        mine = bindView(R.id.rb_mine);
        setOnClick(this, library, homepage, mine);
    }


    @Override
    public void onClick(View view) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (view.getId()){
            case R.id.rb_library:
                transaction.replace(R.id.fl_replace,new LibraryFragment());
                break;
            case R.id.rb_homepage:
                transaction.replace(R.id.fl_replace,new HomePageFragment());
                break;
            case R.id.rb_mine:
                transaction.replace(R.id.fl_replace,new MineFragment());
                break;
        }
        transaction.commit();

    }
}
