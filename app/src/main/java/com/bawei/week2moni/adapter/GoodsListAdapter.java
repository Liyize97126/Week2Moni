package com.bawei.week2moni.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.week2moni.R;
import com.bawei.week2moni.bean.GoodsBean;
import com.bawei.week2moni.util.OnRecyclerItemClickListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView热销新品适配器
 */
public abstract class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.MyViewHouler> {
    //定义
    private List<GoodsBean> list = new ArrayList<>();
    public List<GoodsBean> getList() {
        return list;
    }
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }
    @NonNull
    @Override
    public MyViewHouler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载视图
        View inflate = LayoutInflater.from(parent.getContext()).inflate(getListLayout(), parent, false);
        return new MyViewHouler(inflate);
    }
    //方法封装
    protected abstract int getListLayout();
    @Override
    public void onBindViewHolder(@NonNull MyViewHouler holder, int position) {
        //获取并设置数据
        GoodsBean goodsBean = list.get(position);
        //设置Glide
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.mipmap.zhanpict)
                .error(R.mipmap.errorpict)
                .fallback(R.mipmap.zhanpict)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)));
        Glide.with(holder.masterPic.getContext())
                .applyDefaultRequestOptions(requestOptions)
                .load(goodsBean.getMasterPic())
                .into(holder.masterPic);
        //设置文本
        holder.commodityName.setText(goodsBean.getCommodityName());
        holder.price.setText("￥" + goodsBean.getPrice());
        //添加点击事件
        holder.itemView.setTag(goodsBean);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onRecyclerItemClickListener != null){
                    onRecyclerItemClickListener.onReclerItemClick((GoodsBean) v.getTag());
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        //获取条目总数
        return list.size();
    }
    //内部类
    class MyViewHouler extends RecyclerView.ViewHolder{
        //定义
        protected ImageView masterPic;
        protected TextView commodityName;
        protected TextView price;
        public MyViewHouler(@NonNull View itemView) {
            super(itemView);
            masterPic = itemView.findViewById(R.id.masterPic);
            commodityName = itemView.findViewById(R.id.commodityName);
            price = itemView.findViewById(R.id.price);
        }
    }
}
