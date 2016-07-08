package pogostatus.studios.redleef.pokemongoserverstatus;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ServerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

    private class RetrieveHtmlTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected void onPreExecute()
        {

        }

        @Override
        protected String doInBackground(String... params)
        {
            String myString = params[0];

            int i = 0;
            publishProgress(i);

            try
            {
                Document doc = Jsoup.connect("http://www.mmoserverstatus.com/pokemon_go").get();
                Elements newsHeadlines = doc.select("status");
                Elements mTest = doc.getElementsByClass("white");

                if(!mTest.isEmpty())
                {
                    String toReturn = "";
                    for(int x = 0; x < mTest.size(); x++)
                    {
                        toReturn = toReturn + " | " + mTest.get(x).toString();
                    }
                    return toReturn;
                }
                else
                {
                    /*
                    Snackbar.make(view, "Update Failed: Malformed HTML Tags", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                            */
                }
            }
            catch(Exception e)
            {
                /*
                Snackbar.make(view, "Update Failed: Server Status Source Unreachable", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                        */

                return e.getMessage();
            }
            return "Not sure what happened";
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {

        }

        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);

            TextView contentCount = (TextView)findViewById(R.id.contentCount);
            contentCount.setText(result);

        }
    }
}
