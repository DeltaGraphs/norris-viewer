package deltagraphs.norrisviewer.presenter.mainPresenter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
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

    protected SocketManager mainSocket;
    public MainView mainView;

    /* MainPresenterImpl will observe the changes on PageModel, according to the observer design pattern.*/
    protected PageModel pageModel = new PageModelImpl(this);

    /* constructor of MainPresenterImpl. It requires an activity to be instatiated properly.
    The object will be associated to the activity. A new socket is created. */
    public MainPresenterImpl(MainView view) {
        mainSocket = new SocketManager();
        mainView = view;
    }

    /* This method is called when a new connection must be established.
    The socket is put to listen for some events that will arrive when the connection is started.
    For each type of event a different path of instructions will be executed, in order to set,
    update or delete some informations. */
    public void setUpSocket(String url) {
        mainSocket.setSocketUrl(url);
        startListening();
        mainSocket.startConnection();
    }

    // the pageModel will be reinitialized.
    public void reinitializePageList() {
        pageModel = new PageModelImpl(this);
    }

    /* the following method is used to create a new dialog. An URL is set as default.
    when the user will press the "ok" button of this dialog on the app activity, a new socket
    connection will be established. */
    public AlertDialog showDialog(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Insert source URL");

        // Set up the input
        final EditText input = new EditText(context);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        //input.setText("http://norris-nrti-dev.herokuapp.com/page1/map1");
        input.setText(mainSocket.getSocketUrl());
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newUrl = input.getText().toString();
                String oldUrl = mainSocket.getSocketUrl();
                if ((!mainSocket.isConnected()) ||
                        ((mainSocket.isConnected()) && (!(newUrl.equals(oldUrl))))) {
                    pageModel.removeObservers();
                    mainSocket.stopConnection();
                    mainSocket = new SocketManager();
                    reinitializePageList();
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

    public void stopListening(){
        mainSocket.stopListening("configPageList");
        mainSocket.stopListening("insertPage");
        mainSocket.stopListening("updatePage");
        mainSocket.stopListening("insertGraph");
        mainSocket.stopListening("updateGraph");
    }

    public void startListening(){
        mainSocket.startListening("configPageList", (MainActivity) mainView, pageModel);
        mainSocket.startListening("insertPage", (MainActivity) mainView, pageModel);
        mainSocket.startListening("updatePage", (MainActivity) mainView, pageModel);
        mainSocket.startListening("insertGraph", (MainActivity) mainView, pageModel);
        mainSocket.startListening("updateGraph", (MainActivity) mainView, pageModel);
    }

    //when called, the socket connection is stopped
    @Override
    public void stopConnection() {
        mainSocket.stopConnection();
    }

    //when called, the socket connection is started
    @Override
    public void startConnection() {
        mainSocket.startConnection();
    }

    // it returns true if the socket is connected.
    @Override
    public boolean isConnected() {
        return mainSocket.isConnected();
    }

    // it returns true if the socket hasn't been instatiated.
    @Override
    public boolean isSocketNull() {
        return mainSocket.isNull();
    }

    /* This object is an observer of the page model. When there are some changes on it, a signal is sent
    to this object and the following method is called. Its aim is to set or update informations that are
    shown on the activity. All of these informations are extracted from the model.*/
    @Override
    public void update(Observable observable, Object data) {
        mainView.updatePagesList(pageModel); Log.d("", "updatePage");
    }

}
