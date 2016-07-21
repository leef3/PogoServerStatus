package pogostatus.studios.redleef.pogoserverstatus;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import net.steamcrafted.loadtoast.LoadToast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerActivity extends AppCompatActivity {

    ListRecyclerAdapter mAdapter;
    ArrayList<StatusItem> toReturn;
    RecyclerView recyclerView;
    public static CountryList mCountries;
    Context context;
    LoadToast lt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Server Status for Pokemon Go");
        setSupportActionBar(toolbar);

        context = this;

        mCountries = new CountryList();

        toReturn = new ArrayList<StatusItem>();

        ImageView bgImage = (ImageView)findViewById(R.id.ivBigImage);
        bgImage.setImageResource(R.drawable.pokebg);

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        //Initial set
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;


            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Pokemon Go Server Status");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }
            }
        });

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(llm);
        mAdapter = new ListRecyclerAdapter(toReturn);

        recyclerView.setNestedScrollingEnabled(false);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);
        // 5. set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        new RetrieveHtmlTask().execute("Start");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new RetrieveHtmlTask().execute("What");

                /*
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_server, menu);
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

    private class RetrieveHtmlTask extends AsyncTask<String, Integer, ArrayList<StatusItem>>
    {
        @Override
        protected void onPreExecute()
        {
            lt = new LoadToast(context);
            lt.setText("Checking Server");
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int height = size.y;
            lt.setTranslationY(height/2);
            lt.show();
        }

        @Override
        protected ArrayList<StatusItem> doInBackground(String... params)
        {
            toReturn.clear();
            int i = 0;
            publishProgress(i);

            try
            {
                Document doc = Jsoup.connect("http://www.mmoserverstatus.com/pokemon_go").get();
                Elements mTest = doc.getElementsByClass("white");

                if(!mTest.isEmpty())
                {
                    for(int x = 0; x < mTest.size(); x++)
                    {
                        String region = mTest.get(x).text();
                        String time = "";
                        //Parse Time Here

                        String[] arr = region.split("\\d+", 2);
                        String pt1 = arr[0].trim();
                        if(arr.length > 1)
                        {
                            String pt2 = region.substring(pt1.length()).trim();
                            time =  pt2;
                        }

                        //Feed into CountryList Find Func here
                        Country current = mCountries.FindCountry(region);
                        if(current == null)
                        {
                            current = new Country(region, R.drawable.other);
                        }

                        StatusItem toAdd = new StatusItem(current, 2, time);

                        Element owet2 = mTest.get(x).getAllElements().last().getAllElements().last().getAllElements().last().getAllElements().last();

                        boolean tester = owet2.toString().contains("green");

                        if(tester)
                        {
                            toAdd.Status = 0;
                        }
                        else
                        {
                            toAdd.Status = 1;
                        }

                        toReturn.add(toAdd);
                    }
                    return toReturn;
                }
            }
            catch(Exception e)
            {
                return toReturn;
            }
            return toReturn;
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {

        }

        @Override
        protected void onPostExecute(ArrayList<StatusItem> result)
        {
            super.onPostExecute(result);

            TextView lastUpdate = (TextView)findViewById(R.id.lastUpdate);
            Calendar c = Calendar.getInstance();
            lastUpdate.setText("Last Update: " + c.getTime().toString() );

            if(result.isEmpty())
            {
                lt.error();
                CoordinatorLayout mLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
                Snackbar.make(mLayout, "Update Failed: Server Status Source Unreachable", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else
            {
                ListRecyclerAdapter newAdapter = new ListRecyclerAdapter(result);
                recyclerView.swapAdapter(newAdapter, true);
                lt.success();
            }
        }
    }
}
