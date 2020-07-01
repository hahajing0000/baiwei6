package com.zy.finalce.view;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.zy.common.utils.LogUtils;
import com.zy.core.view.BaseFragment;
import com.zy.finalce.R;
import com.zy.finalce.adapter.FinalceListAdapter;
import com.zy.finalce.databinding.LayoutFinalceBinding;
import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.viewmodel.FinalceViewModel;
import com.zy.net.protocol.BaseRespEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:zhangyue
 * @date:2020/6/27
 */
public class FinalceFragment extends BaseFragment<LayoutFinalceBinding, FinalceViewModel> {
    private final String TAG = FinalceFragment.class.getSimpleName();

    private TabLayout tablayoutMain;
    private RecyclerView rvFinalceMain;
    private SmartRefreshLayout srlFinalceMain;
    private FinalceListAdapter adapter;

    /**
     * 当前理财产品类型
     */
    private int currentType = 0;
    /**
     * 当前页码
     */
    private int currentPage = 0;
    /**
     * 每页数据行数
     */
    private int pagesize = 10;


    @Override
    protected void initContentView(View view) {
        tablayoutMain = (TabLayout) view.findViewById(R.id.tablayout_main);
        rvFinalceMain = (RecyclerView) view.findViewById(R.id.rv_finalce_main);
        srlFinalceMain = (SmartRefreshLayout) view.findViewById(R.id.srl_finalce_main);
        srlFinalceMain.setRefreshHeader(new BezierRadarHeader(getActivity()));
        srlFinalceMain.setRefreshFooter(new BallPulseFooter(getActivity()));
    }

    @Override
    protected void initEvent() {
        tablayoutMain.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //TODO: tag 问题 国际化的问题  英文 日文
                if (tab.getText().toString().equals("新手标")) {
                    currentType = 0;
                    currentPage=0;

                } else if (tab.getText().toString().equals("理财标")) {
                    currentType = 1;
                    currentPage=0;

                } else {
                    currentType = 2;
                    currentPage=0;
                }
                loadFinalceData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        srlFinalceMain.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

            }
        });
        srlFinalceMain.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败

            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFinalceMain.setLayoutManager(linearLayoutManager);
        adapter = new FinalceListAdapter(getActivity());
        rvFinalceMain.setAdapter(adapter);

        loadFinalceData();
    }

    /**
     * 从VM获取数据
     */
    private void loadFinalceData() {
        LiveData<BaseRespEntity<List<FinalceEntity>>> result = vm.getFinalceByType(currentType, currentPage, pagesize);
        result.observe(getActivity(), new Observer<BaseRespEntity<List<FinalceEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<FinalceEntity>> listBaseRespEntity) {
                if (listBaseRespEntity == null || listBaseRespEntity.getData() == null) {
                    LogUtils.INSTANCE.w(TAG, "server return value maybe is null...");
                    return;
                }
                adapter.loadDataSource(listBaseRespEntity.getData());
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_finalce;
    }

    @Override
    protected FinalceViewModel createVM() {
        return new FinalceViewModel(getActivity());
    }

    @Override
    protected void initBinding() {

    }
}
