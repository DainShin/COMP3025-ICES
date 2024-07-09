package ca.georgiancollege.dain.ice7

import android.content.Context

// DataManager Singleton is a wrapper that allows me to create all the functions that I want
class DataManager private constructor(private val database: AppDatabase)
{

    companion object
    {
        // store the database in the parameter to make it a variable
        private lateinit var database: AppDatabase

        @Volatile
        private var m_instance: DataManager? = null

        fun instance(context: Context): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this) {
                    if(m_instance == null) {
                        m_instance = DataManager(AppDatabase.getDatabase(context))  // DataManager passes the context to AppDatabase
                        database = m_instance!!.database
                    }
                }
            }
            return m_instance!!
        }
    }

    // In the AppDatabase, there is tvShowDao. In the tvShowDao, there are functions
    // DataManager controls when we use these functions
    suspend fun insert(tvShow: TVShow) = Companion.database.tvShowDao().insert(tvShow)

    suspend fun update(tvShow: TVShow) = Companion.database.tvShowDao().update(tvShow)

    suspend fun delete(tvShow: TVShow) = Companion.database.tvShowDao().delete(tvShow)

    suspend fun getAllTVShows() = Companion.database.tvShowDao().getAllTVShows()

    suspend fun getTVShowById(id: Int) = Companion.database.tvShowDao().getTVShowById(id)
}