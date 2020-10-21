package com.example.noteapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.common.base.MoreObjects;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GetTokenResult;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {

    private static final String TAG = "MainActivity";
    RecyclerView recyclerView;
    String date, time;
    TextView txtDateTime;
    Button btnTime;
    EditText taskName;
    private BottomSheetDialog bottomSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView    = findViewById(R.id.recyclerView);

        //cria instância do bottomSheet
        bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
        View bottomSheetDialogView = getLayoutInflater().inflate(R.layout.bottom_sheet_persistent, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);

        //componentes da bottomSheet
        btnTime     = (Button) bottomSheetDialogView.findViewById(R.id.btnDatePicker);
        txtDateTime = (TextView) bottomSheetDialogView.findViewById(R.id.txtDateTime);
        taskName    = (EditText) bottomSheetDialogView.findViewById(R.id.taskName);


        //chama a bottomsheet pelo FAB
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog.show();
                taskName.requestFocus();

            }
        });

        //botão para iniciar DateTimeAcitivy
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent datetime = new Intent(MainActivity.this, DateTimeActivity.class);
                startActivity(datetime);
            }
        });

       /*if( FirebaseAuth.getInstance().getCurrentUser() == null) {
            startLoginActivity();
        } else {
            FirebaseAuth.getInstance().getCurrentUser().getIdToken(true)
                    .addOnSuccessListener(new OnSuccessListener<GetTokenResult>() {
                        @Override
                        public void onSuccess(GetTokenResult getTokenResult) {

                            Log.d(TAG, "onSucess: " + getTokenResult.getToken());

                        }
                    });
        }*/
    }

    private void addNote(String text){

    }

    private void startLoginActivity() {
        Intent intent = new Intent( this, LoginRegisterActivity.class);
        startActivity(intent);
        finish();
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

       switch (id) {

           case R.id.action_logout:
               Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
               AuthUI.getInstance().signOut(this);
                       /*.addOnCompleteListener(new OnCompleteListener<Void>() {
                           @Override
                           public void onComplete(@NonNull Task<Void> task) {
                               if (task.isSuccessful()) {
                                   startLoginActivity();
                               } else {
                                   Log.e(TAG, "onComplete: ", task.getException());
                               }
                           }
                       });*/

           case R.id.action_profile:
               Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
               return true;
       }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);
    }

    @Override
    protected void onStop() {

        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);
    }


    @Override
    protected void onResume() {

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String value = extras.getString("date");
            txtDateTime.setText(value);
            taskName.requestFocus();
            bottomSheetDialog.show();
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //Listener para verificar se o usuário é null
    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        if(firebaseAuth.getCurrentUser() == null) {
            startLoginActivity();
            return;
        }

        Log.d(TAG, "onAuthStateChanged: userUid " + firebaseAuth.getCurrentUser().getUid());

    }


}
