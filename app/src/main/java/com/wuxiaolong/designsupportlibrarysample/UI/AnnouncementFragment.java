package com.wuxiaolong.designsupportlibrarysample.UI;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.designsupportlibrarysample.Adapter.LongQuanYiRecyclerViewAdapter;
import com.wuxiaolong.designsupportlibrarysample.R;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴小龙同學
 * on 2016/9/11.
 * 官网文档：https://material.google.com/
 * 个人博客：http://wuxiaolong.me/
 * 公众号：吴小龙同学
 */
public class AnnouncementFragment extends Fragment implements AnnouncementConstract.BaseView<AnnoucementPresenter> {
    // 标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;
    private PullLoadMoreRecyclerView mPullLoadMoreRecyclerView;
    private List<String> mDataList = new ArrayList<>();
    private LongQuanYiRecyclerViewAdapter mRecyclerViewAdapter;
    private String mTitle;
    private Handler handler;
    private Runnable runnable;
    private AnnoucementPresenter annoucementPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = this.getArguments().getString("title");
        mPullLoadMoreRecyclerView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
        mPullLoadMoreRecyclerView.setLinearLayout();
        mPullLoadMoreRecyclerView.setRefreshing(true);
        mRecyclerViewAdapter = new LongQuanYiRecyclerViewAdapter(getActivity(), mDataList);
        mPullLoadMoreRecyclerView.setAdapter(mRecyclerViewAdapter);
        mPullLoadMoreRecyclerView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mDataList.clear();
                annoucementPresenter.getAnnoucement();
            }

            @Override
            public void onLoadMore() {

            }
        });
        isPrepared = true;
        lazyLoad();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }

    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
        if (!isVisible || !isPrepared) {
            return;
        }
        annoucementPresenter.getAnnoucement();
    }

    protected void onInvisible() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (handler != null)
            handler.removeCallbacks(runnable);
    }


    @Override
    public void setPresenter(AnnoucementPresenter annoucementPresenter) {
        this.annoucementPresenter = annoucementPresenter;
    }

    @Override
    public void showList(final List<Element> e) {
        runnable = new Runnable() {
            @Override
            public void run() {
                for (Element element : e) {
                    mDataList.add(element.getElementsByTag("a").attr("href") + "," + element.text());
                }
                mRecyclerViewAdapter.notifyDataSetChanged();
                mPullLoadMoreRecyclerView.setPullLoadMoreCompleted();
            }
        };
        handler = new Handler();
        handler.postDelayed(runnable, 500);
    }
}
