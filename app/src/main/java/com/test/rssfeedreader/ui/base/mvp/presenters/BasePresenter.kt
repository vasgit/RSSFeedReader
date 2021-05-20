package com.test.rssfeedreader.ui.base.mvp.presenters

import androidx.annotation.CallSuper
import com.test.rssfeedreader.ui.base.mvp.model.Model
import com.test.rssfeedreader.ui.base.mvp.views.BaseView
import com.test.rssfeedreader.ui.base.mvp.model.BaseModel
import com.test.rssfeedreader.utils.SavedState
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * A base presenter of the MVP architecture.
 */
abstract class BasePresenter<out V, out M>(
    protected val mView: V,
    protected val mModel: M
) : Presenter, BaseModel.BaseActionListener, KoinComponent where
        V : BaseView,
        M : Model {



    override fun start() {
        mModel.start()
    }


    override fun stop() {
        mModel.stop()
    }





    override fun onRequestSent(requestType: Int) {
        // Stub
    }


    override fun onResponseReceived(requestType: Int) {
        // Stub
    }


    override fun onRequestSucceeded(requestType: Int, response: Any, metadata: Any) {
        // Stub
    }


    override fun onRequestFailed(requestType: Int, exception: Throwable, metadata: Any) {
        // Stub
    }


    override fun onViewSelected() {
        // Left for subclass implementations
    }


    override fun onViewUnselected() {
        // Left for subclass implementations
    }


    @CallSuper
    override fun onRestoreState(savedState: SavedState) {
        mModel.onRestoreState(savedState)
    }


    @CallSuper
    override fun onSaveState(savedState: SavedState) {
        mModel.onSaveState(savedState)
    }


    @CallSuper
    override fun onRecycle() {

    }


    override fun toString(): String = javaClass.simpleName


}