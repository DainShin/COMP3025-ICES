package ca.georgiancollege.ice8

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class DataManager private constructor()
{
    private val db: FirebaseFirestore = Firebase.firestore

    companion object
    {
        private  val TAG = "DataManager"

        @Volatile
        private var m_instance: DataManager? = null

        fun instance(): DataManager
        {
            if(m_instance == null)
            {
                synchronized(this) {
                    if(m_instance == null) {
                        m_instance = DataManager()
                    }
                }
            }
            return m_instance!!
        }
    }

    //  CRUD functionalities
    suspend fun insert(tvShow: TVShow) {
        try {
            db.collection("tvShows").document(tvShow.id).set(tvShow).await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error inserting TVShow: ${e.message}", e)
        }
    }

    suspend fun update(tvShow: TVShow) {
        try {
            db.collection("tvShows").document(tvShow.id).set(tvShow).await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error updating TVShow: ${e.message}", e)
        }
    }

    suspend fun delete(tvShow: TVShow) {
        try {
            db.collection("tvShows").document(tvShow.id).delete().await()
        }
        catch (e: Exception) {
            Log.e(TAG, "Error deleting TVShow: ${e.message}", e)
        }
    }

    suspend fun getAllTVShows() : List<TVShow> {
        return try {
            val result = db.collection("tvShows").get().await()
            result?.toObjects(TVShow::class.java) ?: emptyList()
        }
        catch (e:Exception) {
            Log.e(TAG, "Error getting all TVShows: ${e.message}", e)
            emptyList()
        }
    }

    suspend fun getTVShowById(id: String) : TVShow? {
        return try {
            val result = db.collection("tvShows").document(id).get().await()
            result?.toObject(TVShow::class.java)
        }
        catch(e:Exception) {
            Log.e(TAG, "Error getting TVShow by ID: ${e.message}", e)
            null
        }
    }
}