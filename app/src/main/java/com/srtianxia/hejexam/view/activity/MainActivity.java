package com.srtianxia.hejexam.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.srtianxia.hejexam.R;
import com.srtianxia.hejexam.app.App;
import com.srtianxia.hejexam.model.bean.Message;
import com.srtianxia.hejexam.model.bean.Stock;
import com.srtianxia.hejexam.presenter.MainActivityPresenter;
import com.srtianxia.hejexam.service.TimeService;
import com.srtianxia.hejexam.util.HttpUtils;
import com.srtianxia.hejexam.view.adapter.StockAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.IMainActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.rv_stock)
    RecyclerView rvStock;

    private MainActivityPresenter presenter;

    private static StockAdapter adapter;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        if (!HttpUtils.isNetworkConnected(this)){
            Toast.makeText(MainActivity.this, R.string.toast_no_internet, Toast.LENGTH_SHORT).show();
        }
        presenter = new MainActivityPresenter(this);
        initView();
        initService();
        presenter.requestFromJsonFile();
    }

    private void initService() {
        intent = new Intent(this, TimeService.class);
        startService(intent);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        rvStock = (RecyclerView) findViewById(R.id.rv_stock);
        adapter = new StockAdapter(this);
        rvStock.setAdapter(adapter);
        rvStock.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        setSupportActionBar(toolbar);
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

    @Override
    public void initJsonFileData(List<Message> messages) {
        adapter.addItems(messages);
    }

    @Override
    public void requestDataFromNetSuccess(List<Stock> stocks) {

    }

    @Override
    public void requestDataFromNetFailure(String cause) {
        Toast.makeText(MainActivity.this, cause, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onRelieveView();
        stopService(intent);
    }

    public static class TimeBroadCastReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            //接受广播后进行判断有无网络
            if (HttpUtils.isNetworkConnected(App.getContext())) {
                adapter.notifyDataSetChanged();
            }else {
                Toast.makeText(App.getContext(), R.string.toast_no_internet, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
