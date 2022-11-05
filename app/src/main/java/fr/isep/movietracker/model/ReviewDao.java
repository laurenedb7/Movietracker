/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * DAO to map java method call to SQL queries
 */
@Dao
public interface ReviewDao {

    @Query("SELECT * FROM review_table ORDER BY filmName ASC")
    LiveData<List<Review>> getAlphabetizedReviews();

    @Query("DELETE FROM review_table")
    void deleteAll();

    @Insert
    void insertReview(Review review);

    @Update
    void updateReview(Review review);

    @Query("DELETE FROM review_table WHERE filmName = :name")
    void deleteReview(String name);
}
