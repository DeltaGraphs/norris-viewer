package deltagraphs.norrisviewer.view.mainView;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.nkzawa.socketio.client.Socket;

import deltagraphs.norrisviewer.R;
import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenter;
import deltagraphs.norrisviewer.presenter.mainPresenter.MainPresenterImpl;
import deltagraphs.norrisviewer.view.graphsView.LineChartActivity;

public class HomeActivity extends ActionBarActivity {

    private SocketManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        EditText t = (EditText)findViewById(R.id.editURL);
        t.setGravity(Gravity.CENTER);
        t.setText("http://norris-nrti-dev.herokuapp.com/norris");
        final Button button = (Button) findViewById(R.id.setURLbutton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSetURLButtonClicked();
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    void onSetURLButtonClicked(){

        String url = ((EditText)findViewById(R.id.editURL)).getText().toString();
        sm = new SocketManager(url);
        sm.startConnection();
        if(sm.isConnected() == true){
            //MainPresenter mp = new MainPresenterImpl();
        }
        /*
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        intent.putExtra("EXTRA_SOURCE_URL", url);
        startActivity(intent);
        */
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
}
