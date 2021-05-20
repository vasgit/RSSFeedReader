package com.test.rssfeedreader.ui.base.mvp.activities


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.test.rssfeedreader.ui.base.mvp.presenters.BasePresenter
import com.test.rssfeedreader.ui.base.mvp.views.BaseView


/**
 * A base activity that contains common functionality for all activities.
 */
abstract class BaseActivity<P : BasePresenter<*, *>> : AppCompatActivity(), BaseView {



    protected abstract val mPresenter: P




    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    override fun onResume() {
        super.onResume()

        mPresenter.start()

    }


    override fun onPause() {
        super.onPause()

        mPresenter.stop()

    }


}