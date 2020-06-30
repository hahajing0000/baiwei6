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
import com.zy.core.view.BaseFragment;
import com.zy.finalce.R;
import com.zy.finalce.adapter.FinalceListAdapter;
import com.zy.finalce.databinding.LayoutFinalceBinding;
import com.zy.finalce.entity.FinalceEntity;
import com.zy.finalce.viewmodel.FinalceViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author:zhangyue
 * @date:2020/6/27
 */
public class FinalceFragment extends BaseFragment<LayoutFinalceBinding, FinalceViewModel> {
    private TabLayout tablayoutMain;
    private RecyclerView rvFinalceMain;
    private SmartRefreshLayout srlFinalceMain;
    private FinalceListAdapter adapter;


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
                if (tab.getText().toString().equals("新手标")){

                }
//                Object tag1 = tablayoutMain.getTabAt(tablayoutMain.getSelectedTabPosition()).getTag();
//                Object tag = tab.getTag();
//                int index = (int) tab.getTag();
//                switch (index){
//                    case 0:
//
//                        showMsg("000");
//                        break;
//                    case 1:
//                        showMsg("111");
//                        break;
//                    case 2:
//                        showMsg("222");
//                        break;
//                }
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
                List<FinalceEntity> testList=new ArrayList<>();
                for (int i=10;i<20;i++){
                    testList.add(new FinalceEntity("append testdata "+i));
                }
                adapter.appendDataSource(testList);
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvFinalceMain.setLayoutManager(linearLayoutManager);
        adapter = new FinalceListAdapter(getActivity());
        rvFinalceMain.setAdapter(adapter);

        List<FinalceEntity> testList=new ArrayList<>();
        for (int i=0;i<10;i++){
            testList.add(new FinalceEntity("testdata "+i));
        }
        adapter.loadDataSource(testList);


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
