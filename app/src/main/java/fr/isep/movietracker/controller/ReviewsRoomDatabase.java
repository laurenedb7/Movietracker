/**
 * Project : Personal movie tracker
 *
 * @author Laurène de Blauwe
 * @version 1.00 2022/11/09
 */

package fr.isep.movietracker.controller;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.isep.movietracker.model.Review;
import fr.isep.movietracker.model.ReviewDao;

/**
 * This is the database.
 */
@Database(entities = {Review.class}, exportSchema = false, version = 1)
@TypeConverters({Converters.class})
public abstract class ReviewsRoomDatabase extends RoomDatabase {

    /** The Review DAO */
    public abstract ReviewDao reviewDao();

    /** The name of the database */
    private static final String DB_NAME = "reviews-db";

    /** The instance */
    private static ReviewsRoomDatabase instance;

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized ReviewsRoomDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (ReviewsRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(), ReviewsRoomDatabase.class, DB_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }

    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     */
    private static final RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ReviewDao reviewDao = instance.reviewDao();
                reviewDao.deleteAll();
            });
        }
    };
}
