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
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.util.List;

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

    private class PostRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{
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
            if (!posts.get(i).data.thumbnail.equals(""))
                Picasso.with(context).load(posts.get(i).data.thumbnail).into(viewHolder.thumbnail);
            Log.d("Set Title", posts.get(i).data.title);
            viewHolder.title.setText(posts.get(i).data.title);

            viewHolder.view.setOnClickListener(new View.OnClickListener() {
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
    }

    private class ViewHolder extends  RecyclerView.ViewHolder{
        public final View view;
        public final ImageView thumbnail;
        public final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            thumbnail = (ImageView) itemView.findViewById(R.id.thumbnail);
            title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
