package ca.georgiancollege.ice9

import android.util.Log
import androidx.lifecycle.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import kotlinx.coroutines.launch

class TVShowViewModel : ViewModel()
{
    // create an alias for the DataManager singleton
    private val dataManager = DataManager.instance()

    // LiveData to hold the list of TVShows
    private val m_tvShows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> get() = m_tvShows

    // LiveData to hold the selected TVShow
    private val m_tvShow = MutableLiveData<TVShow?>()
    val tvShow: LiveData<TVShow?> get() = m_tvShow

    // LiveData to hold the selected User
    private val m_user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = m_user

    // Firestore instance
    private val firestore = FirebaseFirestore.getInstance()
    private var tvShowsListener: ListenerRegistration? = null

    // Function to load all TVShows from the DataManager
    fun loadAllTVShows() {
        // Remove the previous listener if it exists
        tvShowsListener?.remove()

        tvShowsListener = firestore.collection("tvShows")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w("TVShowViewModel", "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && !snapshot.isEmpty) {
                    val tvShowList = snapshot.toObjects(TVShow::class.java)
                    m_tvShows.postValue(tvShowList)
                } else {
                    Log.d("TVShowViewModel", "Current data: null")
                    m_tvShows.postValue(emptyList())
                }
            }
    }

    // Function to load a specific TVShow by ID from the DataManager
    fun loadTVShowById(id: String) {
        viewModelScope.launch {
            m_tvShow.value = dataManager.getTVShowById(id)
        }
    }

    // Function to save or update a TVShow in the DataManager
    fun saveTVShow(tvShow: TVShow) {
        viewModelScope.launch {
            if (tvShow.id.isEmpty()) {
                dataManager.insert(tvShow)
            } else {
                dataManager.update(tvShow)
            }
            loadAllTVShows()
        }
    }

    // Function to delete a TVShow from the DataManager
    fun deleteTVShow(tvShow: TVShow) {
        viewModelScope.launch {
            dataManager.delete(tvShow)
            loadAllTVShows()
        }
    }

    // User CRUD Operations
    // Function to save or update a User in the DataManager
    fun insertUser(user: User) {
        viewModelScope.launch {
            dataManager.insertUser(user)
        }
    }

    // Function to load a User by ID from the DataManager
    fun loadUserById(id: String) {
        viewModelScope.launch {
            m_user.value = dataManager.getUserById(id)
        }
    }
}