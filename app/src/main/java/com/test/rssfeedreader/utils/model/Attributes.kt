package com.test.rssfeedreader.utils.model

import android.os.Parcel
import android.os.Parcelable


/**
 * A model class that contains a map to store different values.
 */
class Attributes(): Parcelable {


    private var mIsRecycled: Boolean = false

    private var mAttributesMap: MutableMap<String, Any?> = mutableMapOf()




    private constructor(parcel: Parcel): this() {
        with(parcel) {
            mIsRecycled = (readInt() == 1)

            val mapSize = readInt()
            var key: String
            var value: Any?

            for(i in 0 until mapSize) {
                key = readString()!!
                value = readValue(javaClass.classLoader)

                save(key, value)
            }
        }
    }


    /**
     * Saves an attribute value.
     *
     * @param key The key to associate the value with
     * @param value The value to save
     */
    fun save(key: String, value: Any?) {
        mAttributesMap[key] = value
    }


    /**
     * Checks if the attributes contain a value specified by the key.
     *
     * @param key The key of the value to check
     *
     * @return true if contains; false otherwise
     */
    fun contains(key: String): Boolean {
        return mAttributesMap.containsKey(key)
    }


    /**
     * Retrieves an attribute value.
     *
     * @param key The key associated with the value to fetch
     * @param default The default value in case the attribute
     * value with the specified key is not present
     *
     * @return The attribute value
     */
    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: String, default: T): T {
        return (mAttributesMap[key] as? T) ?: default
    }


    override fun describeContents(): Int = 0


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeInt(if(mIsRecycled) 1 else 0)
            writeInt(mAttributesMap.size)

            for(attribute in mAttributesMap) {
                writeString(attribute.key)
                writeValue(attribute.value)
            }
        }
    }


    companion object {


        @JvmField
        val CREATOR = object : Parcelable.Creator<Attributes> {

            override fun createFromParcel(parcel: Parcel): Attributes = Attributes(parcel)

            override fun newArray(size: Int): Array<Attributes?> = arrayOfNulls(size)

        }

    }


}