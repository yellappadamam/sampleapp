package com.damam.wtestapp.ui;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.damam.wtestapp.R;
import com.damam.wtestapp.http.HttpEngine;
import com.damam.wtestapp.io.ListItem;
import com.damam.wtestapp.io.ListResponse;
import com.damam.wtestapp.listener.GetListListener;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.json_listview)
    RecyclerView listview;

    @InjectView(R.id.no_content_view)
    TextView noContentView;

    @InjectView(R.id.swipe_refresh_view)
    SwipeRefreshLayout refreshLayout;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this, this);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadContent(false);
                refreshLayout.setRefreshing(true);
            }
        });

        listview.addItemDecoration(new SpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.card_space_height)));
        listview.setLayoutManager(new LinearLayoutManager(this));
        loadContent(true);
    }

    private void loadContent(boolean showDialog) {
        if (showDialog)
            progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.loading), true);
        HttpEngine.getInstance().fetchItemList(new GetListListener() {
            @Override
            public void onSuccess(ListResponse response) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();

                ArrayList<ListItem> rows = new ArrayList<>();
                for (ListItem item : response.rows) {
                    if (TextUtils.isEmpty(item.title) && TextUtils.isEmpty(item.description) && TextUtils.isEmpty(item.imageHref)) {
                        continue;
                    }
                    rows.add(item);
                }

                refreshLayout.setRefreshing(false);
                listview.setVisibility(View.VISIBLE);
                noContentView.setVisibility(View.GONE);
                setTitle(response.title);
                listview.setAdapter(new ItemListAdapter(rows));
            }

            @Override
            public void onFailure() {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                refreshLayout.setRefreshing(false);
                listview.setVisibility(View.GONE);
                noContentView.setVisibility(View.VISIBLE);
            }
        });
    }
}
