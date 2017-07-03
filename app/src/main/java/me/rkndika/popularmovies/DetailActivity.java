package me.rkndika.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import me.rkndika.popularmovies.model.Movie;
import me.rkndika.popularmovies.network.Config;

public class DetailActivity extends AppCompatActivity {
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // get movie detail from clicked list
        movie = getIntent().getExtras().getParcelable(getString(R.string.PUT_EXTRA_MOVIES));

        // initialize view
        initViews();
    }

    private void initViews(){
        TextView tvTitle, tvReleaseDate, tvUserRating, tvSynopsis;
        ImageView ivPoster;

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvUserRating = (TextView) findViewById(R.id.tv_user_rating);
        tvSynopsis = (TextView) findViewById(R.id.tv_synopsis);
        ivPoster = (ImageView) findViewById(R.id.iv_poster);

        // set title from movie original title
        tvTitle.setText(movie.getOriginalTitle());
        // set rating from movie vote average
        tvUserRating.setText(String.valueOf(movie.getVoteAverage()));
        // set release date from movie release date
        tvReleaseDate.setText(parseDate(movie.getReleaseDate()));
        // set synopsis from movie overview
        tvSynopsis.setText(movie.getOverview());

        // set poster image from movie poster path
        Picasso.with(this)
                .load(Config.IMAGE_URL + movie.getPosterPath())
                .placeholder(R.drawable.rec_grey)
                .error(R.drawable.rec_grey)
                .into(ivPoster);
    }

    // method for parse date from "yyyy-MM-dd" format to "MMM d, yyyy" format
    private String parseDate(String date){
        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat to = new SimpleDateFormat("MMM d, yyyy");

        // date not initialize
        if(date == null || date.isEmpty()){
            return getString(R.string.unknown_date);
        }

        try {
            // return new format
            return to.format(from.parse(movie.getReleaseDate()));
        } catch (ParseException e) {
            e.printStackTrace();
            // unknown date format
            return getString(R.string.unknown_date);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            // finish activity when clicked back button on actionbar
            case android.R.id.home :
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
