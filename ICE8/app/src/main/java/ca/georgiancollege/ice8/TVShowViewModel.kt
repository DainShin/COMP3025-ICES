package ca.georgiancollege.ice8

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TVShowViewModel : ViewModel()
{
    // create an alias for the DataManager singleton
    private val dataManager = DataManager.instance()

    private val m_tvShows = MutableLiveData<List<TVShow>>()
    val tvShows: LiveData<List<TVShow>> get() = m_tvShows

    private val m_tvShow = MutableLiveData<TVShow?>()
    val tvShow: LiveData<TVShow?> get() = m_tvShow


    // functions
    fun loadAllTVShows() {
        viewModelScope.launch {
            m_tvShows.value = dataManager.getAllTVShows()
        }
    }
    fun loadTVShowById(id: String) {
        viewModelScope.launch {
            m_tvShow.value = dataManager.getTVShowById(id)
        }
    }

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
    fun deleteTVShow(tvShow: TVShow) {
        viewModelScope.launch {
            dataManager.delete(tvShow)
            loadAllTVShows()
        }
    }
}