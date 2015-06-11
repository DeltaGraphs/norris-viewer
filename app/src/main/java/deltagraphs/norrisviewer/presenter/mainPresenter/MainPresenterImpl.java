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
 * 0.1.0 2015-05-13 Davide Trivellato Codifica di tutti gli attributi e i metodi
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

    public void showDialog(final Context context) {
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

        builder.show();
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
