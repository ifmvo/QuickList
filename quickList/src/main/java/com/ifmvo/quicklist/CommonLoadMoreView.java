package com.ifmvo.quicklist;

import com.chad.library.adapter.base.loadmore.LoadMoreView;

/*
 * (●ﾟωﾟ●)
 *
 * Created by Matthew Chen on 2019-09-25.
 */
public class CommonLoadMoreView extends LoadMoreView {

    @Override
    public int getLayoutId() {
        return R.layout.view_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end;
    }
}
