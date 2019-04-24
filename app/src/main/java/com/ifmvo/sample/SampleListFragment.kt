package com.ifmvo.sample

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ifmvo.quicklist.BaseRecyclerViewFragment

/* 
 * (●ﾟωﾟ●)
 * 
 * Created by Matthew_Chen on 2019-04-24.
 */
class SampleListFragment : BaseRecyclerViewFragment<String, BaseViewHolder>() {

    override fun initBeforeGetData() {

    }

    override fun getRecyclerViewAdapter(): BaseQuickAdapter<String, BaseViewHolder> {
        return object : BaseQuickAdapter<String, BaseViewHolder>(R.layout.list_item_txt) {
            override fun convert(helper: BaseViewHolder, item: String?) {
                helper.setText(R.id.txt, item)
            }
        }
    }

    override fun getData(currentPage: Int, showLoading: Boolean) {
        val list = mutableListOf<String>()
        list.add("1")
        list.add("2")
        list.add("3")
        list.add("4")
        list.add("5")
        list.add("6")
        list.add("7")
        list.add("8")
        list.add("9")
        list.add("10")
        handleListData(list, currentPage)
    }
}