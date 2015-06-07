package deltagraphs.norrisviewer.presenter.mainPresenter;


    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.text.InputType;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.EditText;
    import android.support.v4.app.Fragment;
    import android.support.v4.app.FragmentManager;
    import android.widget.FrameLayout;
    import android.widget.ListView;
    import android.widget.TextView;

    import deltagraphs.norrisviewer.R;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Observable;
    import java.util.Observer;

    import deltagraphs.norrisviewer.model.pageModel.*;
    import deltagraphs.norrisviewer.presenter.SocketManager;
    import deltagraphs.norrisviewer.view.graphsView.*;
    import deltagraphs.norrisviewer.view.mainView.MainActivity;
    import deltagraphs.norrisviewer.view.mainView.MainView;

    import lecho.lib.hellocharts.view.AbstractChartView;
    import lecho.lib.hellocharts.view.ColumnChartView;
    import lecho.lib.hellocharts.view.PreviewLineChartView;


/*
 * Name : MainPresenterImpl.java
 * Module : norrisviewer::presenter::mainPresenter
 * Location : norrisviewer\presenter\mainPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.0.1 2015-05-13 Davide Trivellato Creazione file
 *
 * ===============================================================
 *
 */

public class MainPresenterImpl implements MainPresenter, Observer {

    private SocketManager mainSocket;
    public MainView mainView;
    private PageModel pageModel = new PageModelImpl(this);

    public MainPresenterImpl(MainView view){
        mainSocket = new SocketManager();
        mainView = view;
    }

    public void setUpSocket(String url){
        mainSocket.setSocketUrl(url);
        mainSocket.startListening("configPageList", (MainActivity) mainView, pageModel);
        mainSocket.startListening("insertPage", (MainActivity) mainView, pageModel);
        mainSocket.startListening("updatePage", (MainActivity) mainView, pageModel);
        mainSocket.startListening("insertGraph", (MainActivity) mainView, pageModel);
        mainSocket.startListening("updateGraph", (MainActivity) mainView, pageModel);
        mainSocket.startConnection();
        Log.d("", mainSocket.getSocketUrl());
    }

    public void reinitializePageList(){
        pageModel = new PageModelImpl(this);
    }

    public void showDialog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Insert source URL");

        // Set up the input
        final EditText input = new EditText(context);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        //input.setText("http://norris-nrti-dev.herokuapp.com/page1/map1");
        input.setText("http://norris-nrti-dev.herokuapp.com/norris");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!(input.getText().toString().equals(mainSocket.getSocketUrl()))) {
                    if ((!mainSocket.isNull()) && (mainSocket.isConnected())) {
                        pageModel.removeObservers();
                        mainSocket.stopConnection();
                        mainSocket = new SocketManager();
                        reinitializePageList();
                    }
                    setUpSocket(input.getText().toString());
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.d("", "update");
        mainView.updatePagesList(pageModel);
    }

}
