package com.ifmvo.sample

import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.ifmvo.quicklist.BaseRecyclerViewFragment


/*
 * (●ﾟωﾟ●)
 * 
 * Created by Matthew_Chen on 2019-04-24.
 */
class SampleListFragment : BaseRecyclerViewFragment<String, BaseViewHolder>() {


    override fun initRecyclerViewAdapter() {

        mAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item_txt), LoadMoreModule {
            override fun convert(holder: BaseViewHolder, item: String) {
                holder.setText(R.id.txt, item)
            }
        }
    }

    override fun getData(currentPage: Int) {
        if (currentPage == 1) {
            recyclerView?.postDelayed({
                val mList = mutableListOf<String>()
                for (index in 1..10) {
                    mList.add("${((currentPage - 1) * 10) + index}")
                }
                handleListData(mList, currentPage)
            }, 4000)
        } else {
            recyclerView?.postDelayed({
                val mList = mutableListOf<String>()
                for (index in 1..10) {
                    mList.add("${((currentPage - 1) * 10) + index}")
                }
                handleListData(mList, currentPage)
            }, 1000)
        }
    }

    override fun getEmptyIcon(): Int {
        return R.drawable.ic_launcher_background
    }

    override fun getThemeColor(): Int {
        return super.getThemeColor()
    }

    override fun beforeGetData() {
        super.beforeGetData()
    }

    override fun getRecyclerViewItemDecoration(): RecyclerView.ItemDecoration {
        return super.getRecyclerViewItemDecoration()
    }

    override fun getRecyclerViewLayoutManager(): RecyclerView.LayoutManager {
        return super.getRecyclerViewLayoutManager()
    }

    override fun canRefresh(): Boolean {
        return true
    }

    override fun canLoadMore(): Boolean {
        return true
    }

    override fun canAutoLoadMore(): Boolean {
        return true
    }
}