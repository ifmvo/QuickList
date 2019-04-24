package com.ifmvo.quicklist

import com.chad.library.adapter.base.loadmore.LoadMoreView

/* 
 * (●ﾟωﾟ●) 通用的加载更多的自定义 View
 * 
 * Created by Matthew_Chen on 2018/11/23.
 */
class CommonLoadMoreView : LoadMoreView() {

    override fun getLayoutId(): Int {
        return R.layout.view_load_more
    }

    protected override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    protected override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    protected override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}