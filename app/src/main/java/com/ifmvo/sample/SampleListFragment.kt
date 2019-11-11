package com.ifmvo.sample

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ifmvo.quicklist.BaseRecyclerViewFragment

/* 
 * (●ﾟωﾟ●)
 * 
 * Created by Matthew_Chen on 2019-04-24.
 */
class SampleListFragment : BaseRecyclerViewFragment<String, BaseViewHolder>() {

    private val mList = mutableListOf<String>()

    override fun initRecyclerViewAdapter() {
        mAdapter = object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item_txt) {
            override fun convert(helper: BaseViewHolder, item: String?) {
                helper.setText(R.id.txt, item)
            }
        }

    }

//    fun godAction() {
//
//        val textView = mAdapter?.getViewByPosition(recyclerView, 0, R.id.txt) as? TextView
//        textView?.text = "replace"

//        mAdapter?.addData(0, "add")
//        recyclerView.scrollToPosition(0)

//        val index = 2
//        mList.removeAt(index)
//        mList.add(index, "replace")
//        mAdapter?.notifyItemChanged(index)
//    }

    override fun getData(currentPage: Int) {
        mList.clear()

        mList.add("1")
        mList.add("2")
        mList.add("3")
        mList.add("4")
        mList.add("5")
        mList.add("6")
        mList.add("7")
        mList.add("8")
        mList.add("9")
        mList.add("10")

        handleListData(mList, currentPage)
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
        return false
    }

    override fun canLoadMore(): Boolean {
        return false
    }
}