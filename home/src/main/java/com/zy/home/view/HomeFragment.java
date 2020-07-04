package com.zy.home.view;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.bumptech.glide.Glide;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoaderInterface;
import com.zy.common.app.BaseAppcation;
import com.zy.common.utils.LogUtils;
import com.zy.core.view.BaseFragment;
import com.zy.home.R;
import com.zy.home.databinding.LayoutHomeBinding;
import com.zy.home.entity.BannerEntity;
import com.zy.home.entity.SysMsgEntity;
import com.zy.home.viewmodel.HomeViewModel;
import com.zy.net.protocol.BaseRespEntity;
import com.zy.router.RouterManager;
import com.zy.router.RouterPath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

/**
 * @author:zhangyue
 * @date:2020/6/27
 */
public class HomeFragment extends BaseFragment<LayoutHomeBinding, HomeViewModel> implements OnBannerListener {
    private final String TAG=HomeFragment.class.getSimpleName();
    private Banner bannerHomeMain;
    private ViewFlipper vfMain;
    private ImageView ivAdv;
    private SmartRefreshLayout srlMain;

    public ObservableField<String> imgPath=new ObservableField<>();

    public HomeFragment(){
        imgPath.set("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
    }

    @Override
    protected void initContentView(View view) {
        initView(view);
    }

    @Override
    protected void initEvent() {
        srlMain.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败

                initData();
            }
        });
        srlMain.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        initData();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_home;
    }

    @Override
    protected HomeViewModel createVM() {
        return new HomeViewModel(getActivity());
    }

    @Override
    protected void initBinding() {
        binding.setMine(this);
    }

    /**
     * 初始化视图
     * @param view
     */
    private void initView(View view) {
        bannerHomeMain = (Banner) view.findViewById(R.id.banner_home_main);
        vfMain = (ViewFlipper) view.findViewById(R.id.vf_main);
        ivAdv = (ImageView) view.findViewById(R.id.iv_adv);

        srlMain = (SmartRefreshLayout) view.findViewById(R.id.srl_main);
        srlMain.setRefreshHeader(new BezierRadarHeader(getActivity()));
        srlMain.setRefreshFooter(new BallPulseFooter(getActivity()));

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化Banner数据
        initBannerData();
        //初始化系统消息
        initSysMsgs();



//        //加载网络资源图片到imageview上
//        Glide.with(getActivity()).load("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658").into(ivAdv);
    }

    /**
     * 初始化系统消息
     */
    private void initSysMsgs() {
        LiveData<BaseRespEntity<List<SysMsgEntity>>> systemMsgs = vm.getSystemMsgs();
        systemMsgs.observe(getActivity(), new Observer<BaseRespEntity<List<SysMsgEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<SysMsgEntity>> listBaseRespEntity) {
                if (listBaseRespEntity==null||listBaseRespEntity.getData()==null||listBaseRespEntity.getData().size()==0){
                    return;
                }
                for (SysMsgEntity entity:
                     listBaseRespEntity.getData()) {
                    View view = getLayoutInflater().inflate(R.layout.item_viewffipper, null);
                    TextView textView = view.findViewById(R.id.tv_item_ffipper);
                    textView.setText(entity.getMsg());
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            TextView tv= (TextView) v;
                            Toast.makeText(getActivity(),""+tv.getText(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    vfMain.addView(view);
                }
                vfMain.setFlipInterval(2000);
                vfMain.startFlipping();
            }
        });
    }

    /**
     * 初始化Banner数据
     */
    private void initBannerData() {
        final LiveData<BaseRespEntity<List<BannerEntity>>> banner = vm.getBanner();
        banner.observe(getActivity(), new Observer<BaseRespEntity<List<BannerEntity>>>() {
            @Override
            public void onChanged(BaseRespEntity<List<BannerEntity>> bannerEntities) {
                if (bannerEntities==null||bannerEntities.getData()==null){
                    LogUtils.INSTANCE.i(TAG,"the bannerEntities is null or empty...");
                    return;
                }
                List<String> imgs=new ArrayList<>();
                List<String> titles=new ArrayList<>();
                for (BannerEntity entity:
                     bannerEntities.getData()) {
                    imgs.add(entity.getImgurl());
                    titles.add(entity.getDesc());
                }
                bindBannerControl(imgs,titles);
            }
        });

    }

    /**
     * 绑定数据到Banner控件
     * @param imgs
     * @param titles
     */
    private void bindBannerControl(List<String> imgs,List<String> titles){
        bannerHomeMain.setImages(imgs);
        bannerHomeMain.setBannerTitles(titles);
        bannerHomeMain.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        bannerHomeMain.setImageLoader(new MyBannerLoader());
        bannerHomeMain.setBannerAnimation(Transformer.Default);
        bannerHomeMain.setDelayTime(3000);
        bannerHomeMain.isAutoPlay(true);

        bannerHomeMain.setIndicatorGravity(Gravity.CENTER)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getActivity(),"position="+position,Toast.LENGTH_SHORT).show();;
    }

    @BindingAdapter({"imgSrc"})
    public static void bindImgPath(ImageView view,String path){
        Glide.with(BaseAppcation.getAppContext()).load(path).into(view);
    }

    public void imgOnClick(View view){
        Map<String,Object> params=new HashMap<>();
        params.put("procductid",10);
        RouterManager.getInstance().route(getActivity(), RouterPath.Finalce_BUY, params, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                LogUtils.INSTANCE.i(TAG,"已找到页面");
            }

            @Override
            public void onLost(Postcard postcard) {
                LogUtils.INSTANCE.i(TAG,"走丢了");
            }

            @Override
            public void onArrival(Postcard postcard) {
                LogUtils.INSTANCE.i(TAG,"已跳转成功");
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                LogUtils.INSTANCE.e(TAG,"被拦截");
                if (postcard.getPath().equals(RouterPath.Finalce_BUY)){
                    RouterManager.getInstance().routeGreenChannel(RouterPath.USERCENTER_LOGIN);
                }

            }
        });
    }


    private class MyBannerLoader implements ImageLoaderInterface {

        @Override
        public void displayImage(Context context, Object path, View imageView) {
            Glide.with(context).load(path).into((ImageView) imageView);
        }

        @Override
        public View createImageView(Context context) {
            return null;
        }
    }
}
