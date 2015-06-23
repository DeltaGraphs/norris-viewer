package deltagraphs.norrisviewer.presenter.mainPresenter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import java.util.Observable;
import java.util.Observer;

import deltagraphs.norrisviewer.model.pageModel.*;
import deltagraphs.norrisviewer.presenter.SocketManager;
import deltagraphs.norrisviewer.view.mainView.MainActivity;
import deltagraphs.norrisviewer.view.mainView.MainView;


/*
 * Name : MainPresenterImpl.java
 * Module : norrisviewer::presenter::mainPresenter
 * Location : norrisviewer\presenter\mainPresenter
 *
 * History :

 * Version Date Programmer Description
 * ===============================================================
 *
 * 0.3.2 2015-05-21 Davide Trivellato Added methods isConnected() and boolean isSocketNull()
 *
 * 0.3.1 2015-05-21 Davide Trivellato Fixes to connection methods
 *
 * 0.3.0 2015-05-20 Davide Trivellato Major changes to the whole class
 *
 * 0.2.0 2015-05-19 Davide Trivellato Add dialog to insert URl
 *
 * 0.1.0 2015-05-19 Davide Trivellato Coding of all methods and attributes
 *
 * 0.0.1 2015-05-19 Davide Trivellato Creation of the file
 *
 * ===============================================================
 *
 */

public class MainPresenterImpl implements MainPresenter, Observer {

    private SocketManager mainSocket;
    public MainView mainView;
    private PageModel pageModel = new PageModelImpl(this);

    public MainPresenterImpl(MainView view) {
        mainSocket = new SocketManager();
        mainView = view;
    }

    public void setUpSocket(String url) {
        mainSocket.setSocketUrl(url);
        mainSocket.startListening("configPageList", (MainActivity) mainView, pageModel);
        mainSocket.startListening("insertPage", (MainActivity) mainView, pageModel);
        mainSocket.startListening("updatePage", (MainActivity) mainView, pageModel);
        mainSocket.startListening("insertGraph", (MainActivity) mainView, pageModel);
        mainSocket.startListening("updateGraph", (MainActivity) mainView, pageModel);
        mainSocket.startConnection();
    }

    public void reinitializePageList() {
        pageModel = new PageModelImpl(this);
    }

    public AlertDialog showDialog(final Context context) {
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
                if (!(input.getText().toString().equals(mainSocket.getSocketUrl()))) {
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

        AlertDialog dialog = builder.show();
        return dialog;
    }

    @Override
    public void stopConnection() {
        mainSocket.stopConnection();
    }

    @Override
    public void destroyConnection() {
        mainSocket.destroyConnection();
    }

    @Override
    public void startConnection() {
        mainSocket.startConnection();
    }

    @Override
    public boolean isConnected() {
        return mainSocket.isConnected();
    }

    @Override
    public boolean isSocketNull() {
        return mainSocket.isNull();
    }

    @Override
    public void update(Observable observable, Object data) {
        mainView.updatePagesList(pageModel);
    }

}
