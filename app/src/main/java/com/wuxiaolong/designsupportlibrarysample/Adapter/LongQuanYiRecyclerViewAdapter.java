package com.wuxiaolong.designsupportlibrarysample.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wuxiaolong.designsupportlibrarysample.API.ApiStories;
import com.wuxiaolong.designsupportlibrarysample.AppConstants;
import com.wuxiaolong.designsupportlibrarysample.R;
import com.wuxiaolong.designsupportlibrarysample.UI.WebViewActivity;

import java.util.List;

public class LongQuanYiRecyclerViewAdapter extends RecyclerView.Adapter<LongQuanYiRecyclerViewAdapter.ViewHolder> {
    private List<String> dataList;
    private Activity mActivity;

    public LongQuanYiRecyclerViewAdapter(Activity activity, List<String> dataList) {
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
        holder.title.setText(dataList.get(position).split(",")[1]);
        if (position % 2 == 0) {
            holder.showImage.setBackgroundResource(R.mipmap.show_img1);
        } else {
            holder.showImage.setBackgroundResource(R.mipmap.show_img2);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView showImage;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            showImage = (ImageView) itemView.findViewById(R.id.showImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(mActivity, WebViewActivity.class);
                    intent.putExtra(AppConstants.WEBVIEWTITLE, "");
                    intent.putExtra(AppConstants.WEBVIEWURL, ApiStories.LONGQUANBASEURL + dataList.get(getLayoutPosition()).split(",")[0]);
                    mActivity.startActivity(intent);
                }
            });
        }
    }
}
