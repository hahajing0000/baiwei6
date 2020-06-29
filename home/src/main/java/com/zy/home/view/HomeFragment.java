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

import com.bumptech.glide.Glide;
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
import com.zy.home.viewmodel.HomeViewModel;
import com.zy.net.protocol.BaseRespEntity;

import java.util.ArrayList;
import java.util.List;

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

    public ObservableField<String> imgPath=new ObservableField<>();

    public HomeFragment(){
        imgPath.set("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
    }

    @Override
    protected void initContentView(View view) {
        initView(view);
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
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //初始化Banner数据
        initBannerData();

        for (int i=0;i<10;i++){
            View view = getLayoutInflater().inflate(R.layout.item_viewffipper, null);
            TextView textView = view.findViewById(R.id.tv_item_ffipper);
            textView.setText("我是系统通知，内容:"+i);
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

//        //加载网络资源图片到imageview上
//        Glide.with(getActivity()).load("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658").into(ivAdv);
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
