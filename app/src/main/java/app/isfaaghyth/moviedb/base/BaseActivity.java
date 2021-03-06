package app.isfaaghyth.moviedb.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import app.isfaaghyth.moviedb.utils.ProgressLoader;
import butterknife.ButterKnife;
import de.mateware.snacky.Snacky;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by isfaaghyth on 7/24/18.
 * github: @isfaaghyth
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    public abstract int contentView();
    public abstract void onCreated();

    private ProgressLoader loader;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loader = new ProgressLoader(this);
        setContentView(contentView());
        ButterKnife.bind(this);
        onCreated();
    }

    @Override protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void showLoader() {
        loader.show();
    }

    @Override public void hideLoader() {
        loader.hide();
    }

    protected String intentValue(String key) {
        return getIntent().getStringExtra(key);
    }

    protected void showBackBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    protected void onError(int resId) {
        onError(getString(resId));
    }

    protected void onInfo(String message) {
        Snacky.builder()
                .setActivity(this)
                .setText(message)
                .setDuration(Snacky.LENGTH_LONG)
                .info()
                .show();
    }

    protected void onError(String message) {
        Snacky.builder()
                .setActivity(this)
                .setText(message)
                .setDuration(Snacky.LENGTH_SHORT)
                .error()
                .show();
    }

    protected void onNotify(String message) {
        Snacky.builder()
                .setActivity(this)
                .setText(message)
                .setDuration(Snacky.LENGTH_SHORT)
                .success()
                .show();
    }

}
