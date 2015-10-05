package com.jmhummel.redditmaterialdesign;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jmhummel.redditmaterialdesign.Model.Child;
import com.jmhummel.redditmaterialdesign.Model.Data_;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    RedditClient redditClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        redditClient = new RedditClient();
        redditClient.getAll();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        BusProvider.getInstance().register(this);

    }

    @Override
    public void onPause(){
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }


    @Subscribe
    public void onListingEvent(ListingEvent event) {
        List<Child> posts = event.listing.data.children;

        PostRecyclerViewAdapter adapter = new PostRecyclerViewAdapter(this, posts);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapter);
    }

    public class PostRecyclerViewAdapter extends RecyclerView.Adapter<PostRecyclerViewAdapter.ViewHolder>{
        private Context context;
        private List<Child> posts;

        public PostRecyclerViewAdapter(Context context, List<Child> posts) {
            this.context = context;
            this.posts = posts;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context)
                    .inflate(R.layout.list_item, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            Data_ post = posts.get(i).data;

            if (!post.thumbnail.equals(""))
                Picasso.with(context).load(post.thumbnail).into(viewHolder.thumbnail);
            else
                viewHolder.thumbnail.setImageDrawable(null);
            Log.d("Set Title", post.title);
            viewHolder.title.setText(post.title);

            viewHolder.age.setText(post.getTimeAgo());
            viewHolder.submitter.setText(post.author);
            viewHolder.subreddit.setText("/r/" + post.subreddit);

            viewHolder.comments.setText(post.numComments + " comments");

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Log.d("Clicked", viewHolder.title.getText().toString());
                    //Intent intent = new Intent(context, CheeseDetailActivity.class);
                    //intent.putExtra(CheeseDetailActivity.EXTRA_NAME, holder.movie);

                    //context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        class ViewHolder extends  RecyclerView.ViewHolder{
            @Bind(R.id.title)       TextView title;
            @Bind(R.id.thumbnail)   ImageView thumbnail;
            @Bind(R.id.age)         TextView age;
            @Bind(R.id.submitter)   TextView submitter;
            @Bind(R.id.subreddit)   TextView subreddit;
            @Bind(R.id.comments)    TextView comments;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }


}
