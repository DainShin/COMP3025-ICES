package ca.georgiancollege.ice6

import android.content.Context
import android.provider.ContactsContract.Data
import android.util.Log
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


// singleton
class DataManager private constructor()
{
    fun getTextFromResource(context: Context, resourceId: Int): String
    {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use { it.readText()}
    }

    fun getTextFromAsset(context: Context, fileName: String): String
    {
        return context.resources.assets.open(fileName)
            .bufferedReader()
            .use { it.readText()}
    }

    fun deserializeJSON(context:Context): List<ContactModel>? // ? it may return null
    {
        val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build() // builder
        val listType = Types.newParameterizedType(List::class.java, ContactModel::class.java) // defining the list type, contactModel -> List type
        val adapter: JsonAdapter<List<ContactModel>> = moshi.adapter(listType)
        val contactListRawString = getTextFromResource(context, R.raw.contacts)

        //Log.i("deserializeJSON", contactListRawString)

        val contactList: List<ContactModel>? = adapter.fromJson(contactListRawString)
        return contactList
    }

    companion object
    {
        // 방법 1 if DataManager doesn't exist create DataManager
        val instance: DataManager by lazy { DataManager() }

        // Long way to create a Singleton
       /*
        @JvmStatic
        private var INSTANCE: DataManager? = null

        fun instance(): DataManager
        {
            if (INSTANCE == null)
            {
                INSTANCE = DataManager()
            }
            return INSTANCE as DataManager
        }
        */

    }
}