package com.ifmvo.quicklist

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.matthewchen.netlive.base.CommonItemDecoration

/*
 * (●ﾟωﾟ●) 继承这个Fragment之后，实现所有的函数即可
 *
 * Created by Matthew_Chen on 2018/11/23.
 */
abstract class BaseRecyclerViewFragment<T, P : BaseViewHolder> : LazyFragment() {

    var mCurrentPage = 1
    lateinit var flTopView: FrameLayout
    lateinit var recyclerView: RecyclerView
    lateinit var rlParent: RelativeLayout
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var flBottomView: FrameLayout
    lateinit var llLoading: LinearLayout

    var mAdapter: BaseQuickAdapter<T, P>? = null

    override fun onCreateViewLazy(savedInstanceState: Bundle?) {
        super.onCreateViewLazy(savedInstanceState)

        setContentView(R.layout.base_fragment_recylerview)

        flTopView = findViewById(R.id.flTopView) as FrameLayout
        flBottomView = findViewById(R.id.flBottomView) as FrameLayout
        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        llLoading = findViewById(R.id.llLoading) as LinearLayout
        rlParent = findViewById(R.id.rlParent) as RelativeLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout

        initRecyclerViewAdapter()
        beforeGetData()

        recyclerView.layoutManager = getRecyclerViewLayoutManager()
        if (recyclerView.itemDecorationCount > 0){
            recyclerView.removeItemDecorationAt(0)
        }
        recyclerView.addItemDecoration(getRecyclerViewItemDecoration())

        swipeRefreshLayout.setColorSchemeColors(getThemeColor())

        if (canRefresh()) {
            swipeRefreshLayout.setOnRefreshListener {
                mCurrentPage = 1
                getData(mCurrentPage)
            }
        }
        swipeRefreshLayout.isEnabled = canRefresh()

        if (canLoadMore()) {
            mAdapter?.setOnLoadMoreListener({
                mCurrentPage++
                getData(mCurrentPage)
            }, recyclerView)
        }

        mAdapter?.setLoadMoreView(CommonLoadMoreView())

        if (!canAutoLoadMore()) {
            mAdapter?.disableLoadMoreIfNotFullPage(recyclerView)
        }

        recyclerView.adapter = mAdapter

        /*
         * 初始化完成，首次 显示 加载 loading ， 并请求数据
         */
        getData(mCurrentPage)
    }

    /**
     * 再网络请求的 error 回调使用
     */
    protected fun handleError(errorMsg: String?) {
        swipeRefreshLayout.isRefreshing = false
        llLoading.visibility = View.GONE
        mAdapter?.loadMoreFail()
        setEmpty(errorMsg, getEmptyIcon())
    }

    /**
     * 在处理List 中 第一页就没有数据，会自动调用
     */
    private fun setEmpty(msg: String? = "没有数据", iconRes: Int = 0) {
        //没有数据
        val view = View.inflate(mContext, R.layout.view_empty, null)

        if (msg?.isNotEmpty() == true){
            val tvText = view.findViewById<TextView>(R.id.tv_empty)
            tvText.text = msg
        }
        if (iconRes != 0){
            val ivImg = view.findViewById<ImageView>(R.id.iv_empty)
            ivImg.setImageResource(iconRes)
        }
        mAdapter?.emptyView = view
    }

    /**
     * 统一处理 List 数据
     * @param listData
     * @param page
     */
    fun handleListData(listData: List<T>?, page: Int) {
        if (page == 1) {
            if (listData?.isNotEmpty() == true) {
                mAdapter?.setNewData(listData)
            } else {
                mAdapter?.setNewData(mutableListOf())
                if (showEmpty()) {
                    setEmpty(getEmptyTxt(), getEmptyIcon())
                }
            }
        } else {
            mAdapter?.loadMoreComplete()

            if (listData?.isNotEmpty() == true) {
                mAdapter?.addData(listData)
            } else {
                mAdapter?.loadMoreEnd(false)
            }
        }
        swipeRefreshLayout.isRefreshing = false
        llLoading.visibility = View.GONE
    }

    /**
     * 初始化 Adapter 之后，getData 之前
     */
    open fun beforeGetData() {

    }

    /**
     * 必须重写
     */
    protected abstract fun initRecyclerViewAdapter()

    /**
     * 必须重写
     */
    protected abstract fun getData(currentPage: Int)

    /**
     * 提供重写
     */
    open fun getRecyclerViewLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(mContext)

    /**
     * 提供重写
     */
    open fun getRecyclerViewItemDecoration(): RecyclerView.ItemDecoration =
        CommonItemDecoration((0.5 * mContext.resources.displayMetrics.density).toInt())

    /**
     * 提供重写 主题颜色
     */
    open fun getThemeColor() = Color.BLACK

    /**
     * 提供重写 设置是否 下拉刷新
     */
    open fun canRefresh(): Boolean = true

    /**
     * 提供重写 设置是否 上拉加载
     */
    open fun canLoadMore(): Boolean = true

    /**
     * 提供重写 设置是否 在数据为空的时候显示空的界面
     */
    open fun showEmpty(): Boolean = true

    /**
     * 提供重写 是否到底，自动加载
     */
    open fun canAutoLoadMore(): Boolean = true

    /**
     * 提供重写
     */
    open fun getEmptyTxt(): String = "这里什么都没有"

    open fun getEmptyIcon(): Int = 0
}
