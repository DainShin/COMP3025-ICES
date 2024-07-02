package ca.georgiancollege.dain.ice7

import androidx.room.*

// This is a wrapper of basic functions
@Dao
interface TVShowDao
{
    // Inserts a TVShow into the database
    // If TVshow with the same ID already exists, it will be replaced
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TVShow)

    @Update
    suspend fun update(tvShow: TVShow)

    @Delete
    suspend fun delete(tvShow: TVShow)

    @Query("SELECT * FROM tv_shows")
    suspend fun getAllTVShows(): List<TVShow>

    @Query("SELECT * FROM tv_shows WHERE id = :id")
    suspend fun getTVShowById(id: Int): TVShow?
}