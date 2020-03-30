package com.bawei.week2moni.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2moni.R;
import com.bawei.week2moni.adapter.GoodsListAdapter;
import com.bawei.week2moni.base.BaseActivity;
import com.bawei.week2moni.base.BasePresenter;
import com.bawei.week2moni.bean.DataBean;
import com.bawei.week2moni.bean.GoodsBean;
import com.bawei.week2moni.bean.ResultBean;
import com.bawei.week2moni.bean.XBannerListBean;
import com.bawei.week2moni.presenter.GoodsPresenter;
import com.bawei.week2moni.presenter.XBannerDataPresenter;
import com.bawei.week2moni.util.DataCall;
import com.bawei.week2moni.util.MyApplication;
import com.bawei.week2moni.util.OnRecyclerItemClickListener;
import com.bawei.week2moni.util.VolleyUtil;
import com.bumptech.glide.Glide;
import com.google.gson.reflect.TypeToken;
import com.stx.xhb.androidx.XBanner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity implements DataCall<ResultBean> {
    //定义
    private XBanner xbannr;
    private TextView rxxptitle,mlsstitle,pzshtitle;
    private RecyclerView rxxp,mlss,pzsh;
    private GoodsListAdapter goodsListAdapter1,goodsListAdapter2,goodsListAdapter3;
    private GoodsPresenter goodsPresenter;
    private XBannerDataPresenter xBannerDataPresenter;
    private List<XBannerListBean> list = new ArrayList<>();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initView() {
        //初始化控件
        xbannr = findViewById(R.id.xbannr);
        rxxptitle = findViewById(R.id.rxxptitle);
        mlsstitle = findViewById(R.id.mlsstitle);
        pzshtitle = findViewById(R.id.pzshtitle);
        rxxp = findViewById(R.id.rxxp);
        mlss = findViewById(R.id.mlss);
        pzsh = findViewById(R.id.pzsh);
        //设置XBanner
        xbannr.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //图片加载
                Glide.with(banner.getContext()).load(list.get(position).getImageUrl()).into((ImageView) view);
            }
        });
        //设置RecyclerView
        rxxp.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mlss.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        pzsh.setLayoutManager(new GridLayoutManager(this,2));
        //设置适配器
        goodsListAdapter1 = new GoodsListAdapter() {
            @Override
            protected int getListLayout() {
                return R.layout.rxxplistcontent;
            }
        };
        goodsListAdapter2 = new GoodsListAdapter() {
            @Override
            protected int getListLayout() {
                return R.layout.mlsslistcontent;
            }
        };
        goodsListAdapter3 = new GoodsListAdapter() {
            @Override
            protected int getListLayout() {
                return R.layout.pzshlistcontent;
            }
        };
        rxxp.setAdapter(goodsListAdapter1);
        mlss.setAdapter(goodsListAdapter2);
        pzsh.setAdapter(goodsListAdapter3);
        //点击事件
        goodsListAdapter1.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onReclerItemClick(GoodsBean goodsBean) {
                Toast.makeText(MainActivity.this,goodsBean.getCommodityName(),Toast.LENGTH_LONG).show();
            }
        });
        goodsListAdapter2.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onReclerItemClick(GoodsBean goodsBean) {
                Toast.makeText(MainActivity.this,goodsBean.getCommodityName(),Toast.LENGTH_LONG).show();
            }
        });
        goodsListAdapter3.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onReclerItemClick(GoodsBean goodsBean) {
                Toast.makeText(MainActivity.this,goodsBean.getCommodityName(),Toast.LENGTH_LONG).show();
            }
        });
        //判断有没有数据
        if(MyApplication.getSharedPreferences().getString("goods",null) != null){
            Type type = new TypeToken<DataBean<ResultBean>>() {
            }.getType();
            DataBean<ResultBean> dataBean = BasePresenter.getGSON().fromJson(MyApplication.getSharedPreferences().getString("goods", null), type);
            ResultBean result = dataBean.getResult();
            //设置条目文本
            rxxptitle.setText(result.getRxxp().getName());
            goodsListAdapter1.getList().addAll(result.getRxxp().getCommodityList());
            goodsListAdapter1.notifyDataSetChanged();
            mlsstitle.setText(result.getMlss().getName());
            goodsListAdapter2.getList().addAll(result.getMlss().getCommodityList());
            goodsListAdapter2.notifyDataSetChanged();
            pzshtitle.setText(result.getPzsh().getName());
            goodsListAdapter3.getList().addAll(result.getPzsh().getCommodityList());
            goodsListAdapter3.notifyDataSetChanged();
        } else {
            if(VolleyUtil.getVolleyUtil().hasNet()){
                goodsPresenter = new GoodsPresenter(this);
                goodsPresenter.request("goods");
            } else {
                Toast.makeText(MainActivity.this,"没有网络！",Toast.LENGTH_LONG).show();
            }
        }
        //判断有没有数据
        if(MyApplication.getSharedPreferences().getString("banner",null) != null){
            Type type = new TypeToken<DataBean<List<XBannerListBean>>>() {
            }.getType();
            DataBean<List<XBannerListBean>> dataBean = BasePresenter.getGSON().fromJson(MyApplication.getSharedPreferences().getString("banner", null), type);
            ///设置数据
            list = dataBean.getResult();
            xbannr.setBannerData(list);
        } else {
            if(VolleyUtil.getVolleyUtil().hasNet()){
                xBannerDataPresenter = new XBannerDataPresenter(new DataCall<List<XBannerListBean>>() {
                    @Override
                    public void success(DataBean<List<XBannerListBean>> dataBean) {
                        //设置数据
                        list = dataBean.getResult();
                        xbannr.setBannerData(list);
                    }
                    @Override
                    public void error(String error) {
                    }
                });
                xBannerDataPresenter.request("banner");
            }
        }
    }
    @Override
    public void success(DataBean<ResultBean> dataBean) {
        ResultBean result = dataBean.getResult();
        //设置条目文本
        rxxptitle.setText(result.getRxxp().getName());
        goodsListAdapter1.getList().addAll(result.getRxxp().getCommodityList());
        goodsListAdapter1.notifyDataSetChanged();
        mlsstitle.setText(result.getMlss().getName());
        goodsListAdapter2.getList().addAll(result.getMlss().getCommodityList());
        goodsListAdapter2.notifyDataSetChanged();
        pzshtitle.setText(result.getPzsh().getName());
        goodsListAdapter3.getList().addAll(result.getPzsh().getCommodityList());
        goodsListAdapter3.notifyDataSetChanged();
    }
    @Override
    public void error(String error) {
        Toast.makeText(MainActivity.this,"参数获取失败！",Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        goodsListAdapter1.getList().clear();
        goodsListAdapter2.getList().clear();
        goodsListAdapter3.getList().clear();
        list.clear();
    }
}
