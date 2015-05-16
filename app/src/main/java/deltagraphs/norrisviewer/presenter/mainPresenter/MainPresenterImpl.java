package deltagraphs.norrisviewer.presenter.mainPresenter;


    import android.app.AlertDialog;
    import android.content.Context;
    import android.content.DialogInterface;
    import android.text.InputType;
    import android.widget.EditText;

    import deltagraphs.norrisviewer.view.mainView.MainActivity;
    import deltagraphs.norrisviewer.view.mainView.MainView;
    import deltagraphs.norrisviewer.presenter.SocketManager;

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

public class MainPresenterImpl implements MainPresenter {

    private SocketManager mainSocket;
    private MainView mainView = new MainActivity();

    public MainPresenterImpl(){
        mainSocket = new SocketManager();
    }

    public void showDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Insert source URL");

        // Set up the input
        final EditText input = new EditText(context);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText("http://5.231.33.217:3000/page1");
        builder.setView(input);

// Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainSocket.setSocketUrl(input.getText().toString());
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

}
