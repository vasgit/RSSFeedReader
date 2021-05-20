package com.test.rssfeedreader.ui.base.mvp.model

import androidx.annotation.CallSuper
import com.test.rssfeedreader.utils.handlers.CoroutineHandler
import com.test.rssfeedreader.utils.model.RepositoryResult
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import com.test.rssfeedreader.ui.base.mvp.model.BaseModel.BaseActionListener
import com.test.rssfeedreader.utils.SavedState
import com.test.rssfeedreader.utils.extensions.onFailure
import com.test.rssfeedreader.utils.extensions.onSuccess
import org.koin.core.inject

/**
 * A base model of the MVP architecture.
 */
abstract class BaseModel<
    ActionListener : BaseActionListener
> : Model {


    /**
     * A map that stores what requests have been sent.
     */
    protected var mIsRequestSentMap: MutableMap<Int, Boolean> = mutableMapOf()

    /**
     * A coroutine handler to provide coroutines functionality.
     */
    protected val mCoroutineHandler: CoroutineHandler by inject()

    /**
     * A listener to interact with a presenter.
     */
    protected var mActionListener: ActionListener? = null




    @CallSuper
    override fun start() {
        // Stub
    }


    @CallSuper
    override fun stop() {
        mCoroutineHandler.cancelChildren()
        mIsRequestSentMap.clear()
    }


    /**
     * Runs [block] of code inside coroutine on the main thread.
     *
     * @param startOption The start option specifying how to start the coroutine
     * @param block The block of code to run
     */
    protected fun createUiLaunchCoroutine(
        startOption: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend (() -> Unit)
    ): Job {
        return mCoroutineHandler.createUiLaunchCoroutine(startOption, block)
    }


    /**
     * Runs [block] of code inside coroutine on the background thread.
     *
     * @param startOption The start option specifying how to start the coroutine
     * @param block The block of code to run
     */
    protected fun createBgLaunchCoroutine(
        startOption: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend (() -> Unit)
    ): Job {
        return mCoroutineHandler.createBgLaunchCoroutine(startOption, block)
    }


    /**
     * Performs a particular request.
     *
     * @param requestType The type of the request
     * @param params The parameters for the request
     * @param metadata Any type of metadata that should be available
     * after receiving the response
     */
    @Suppress("UNCHECKED_CAST")
    protected fun performRequest(requestType: Int, params: Any = Any(), metadata: Any = Any()) {
        if(isRequestSent(requestType)) {
            return
        }

        createBgLaunchCoroutine {
            val repositoryResult = getRequestRepositoryResult(requestType, params)

            withContext(Dispatchers.Main) {
                (repositoryResult as? RepositoryResult<Any>)
                    ?.onSuccess { handleSuccessfulResponse(requestType, it, metadata) }
                    ?.onFailure { handleErroneousResponse(requestType, it, metadata) }
            }
        }

        onRequestSent(requestType)
    }


    /**
     * Retrieves a repository result. Should be overridden to provide
     * an implementation in case [performRequest] is called.
     *
     * @param requestType The type of the request
     * @param params The parameters for the request
     *
     * @return The result
     */
    protected open suspend fun getRequestRepositoryResult(requestType: Int, params: Any): Any? = null


    private fun handleSuccessfulResponse(requestType: Int, data: Any, metadata: Any) {
        onResponseReceived(requestType)

        mActionListener?.onRequestSucceeded(requestType, data, metadata)
    }


    private fun handleErroneousResponse(requestType: Int, error: Throwable, metadata: Any) {
        onResponseReceived(requestType)

        mActionListener?.onRequestFailed(requestType, error, metadata)
    }


    private fun onRequestSent(requestType: Int) {
        mIsRequestSentMap[requestType] = true

        mActionListener?.onRequestSent(requestType)
    }


    private fun onResponseReceived(requestType: Int) {
        mIsRequestSentMap[requestType] = false

        mActionListener?.onResponseReceived(requestType)
    }


    fun setActionListener(actionListener: ActionListener) {
        mActionListener = actionListener
    }


    fun isRequestSent(requestType: Int): Boolean {
        return (mIsRequestSentMap[requestType] == true)
    }


    override fun onRestoreState(savedState: SavedState) {
        // Stub
    }


    override fun onSaveState(savedState: SavedState) {
        // Stub
    }


    interface BaseActionListener {

        fun onRequestSent(requestType: Int)

        fun onResponseReceived(requestType: Int)

        fun onRequestSucceeded(requestType: Int, response: Any, metadata: Any)

        fun onRequestFailed(requestType: Int, exception: Throwable, metadata: Any)

    }


}