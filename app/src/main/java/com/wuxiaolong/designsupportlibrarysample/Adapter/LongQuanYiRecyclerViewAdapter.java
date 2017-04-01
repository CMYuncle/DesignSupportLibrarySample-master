package com.wuxiaolong.designsupportlibrarysample.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiaolong.designsupportlibrarysample.AppConstants;
import com.wuxiaolong.designsupportlibrarysample.R;
import com.wuxiaolong.designsupportlibrarysample.UI.WebViewActivity;
import com.wuxiaolong.designsupportlibrarysample.datas.LongquanData;

import java.util.List;

public class LongQuanYiRecyclerViewAdapter extends RecyclerView.Adapter<LongQuanYiRecyclerViewAdapter.ViewHolder> {
    private List<LongquanData> dataList;
    private Activity mActivity;

    public LongQuanYiRecyclerViewAdapter(Activity activity, List<LongquanData> dataList) {
        this.dataList = dataList;
        this.mActivity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_longquanyi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getDes());
        holder.time.setText(dataList.get(position).getTime());
        if (position % 5 == 0) {
            holder.showImage.setBackgroundResource(R.mipmap.cat1);
        } else if (position % 5 == 1) {
            holder.showImage.setBackgroundResource(R.mipmap.cat2);
        } else if (position % 5 == 2) {
            holder.showImage.setBackgroundResource(R.mipmap.cat3);
        } else if (position % 5 == 3) {
            holder.showImage.setBackgroundResource(R.mipmap.cat4);
        } else if (position % 5 == 4) {
            holder.showImage.setBackgroundResource(R.mipmap.cat5);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        ImageView showImage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            time = (TextView) itemView.findViewById(R.id.time);
            showImage = (ImageView) itemView.findViewById(R.id.showImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mActivity, WebViewActivity.class);
                    intent.putExtra(AppConstants.WEBVIEWTITLE, "");
                    intent.putExtra(AppConstants.WEBVIEWURL, dataList.get(getLayoutPosition()).getUrl());
                    mActivity.startActivity(intent);
                }
            });
        }
    }

    public void updateDatas(List<LongquanData> list) {
        this.dataList = list;
        notifyDataSetChanged();
    }

    public void insertDatas(List<LongquanData> list) {
        int start = dataList.size();
        this.dataList.addAll(list);
        notifyItemRangeInserted(start, list.size());
    }
}
