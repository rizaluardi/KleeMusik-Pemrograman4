package com.appsmor.kleemusik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

public class LoginAct extends AppCompatActivity {

    EditText editTextEmail,editTextMail;
    EditText editTextPassword;

    //Declaration TextInputLayout
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Declaration Button
    Button buttonLogin;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_EMAIL ="email";
    private static final String DROP="com.appsmor.kleemusik";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = this.getSharedPreferences(DROP, Context.MODE_PRIVATE);

        if (prefs.getBoolean("isLogin", false)) {
            Intent mainIntent = new Intent(LoginAct.this, MainActivity.class);
            startActivity(mainIntent);
        }
        else

        setContentView(R.layout.activity_login);

        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        editTextMail = findViewById(R.id.editEmail);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        String mail = sharedPreferences.getString(KEY_EMAIL,null);


        //set click event of login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Check user input is correct or not
                if (validate()) {

                    //Get values from EditText fields
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate user
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    //Check Authentication is successful or not
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Berhasil Login!", Snackbar.LENGTH_LONG).show();
                        SharedPreferences.Editor editor  = sharedPreferences.edit();
                        editor.putString(KEY_EMAIL,editTextMail.getText().toString());
                        prefs.edit().putBoolean("isLogin", true).apply();
                        editor.apply();

                        //User Logged in Successfully Launch You home screen activity
                        Intent intent=new Intent(LoginAct.this,MainActivity.class);
                        startActivity(intent);
                        finish();

                    } else {

                        //User Logged in Failed
                        Snackbar.make(buttonLogin, "Gagal Login, cobalah kembali", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    //this method used to set Create account TextView text and click event( maltipal colors
    // for TextView yet not supported in Xml so i have done it programmatically)
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textVBuatAkun);
        textViewCreateAccount.setText(fromHtml("<font color='#000000'>Tidak Punya akun ?. </font><font color='#0c0099'>Buat Disini</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginAct.this, RegisterAct.class);
                startActivity(intent);
            }
        });
    }

    //this method is used to connect XML views to its Objects
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editEmail);
        editTextPassword = (EditText) findViewById(R.id.editPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textLayoutMasukanEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.btnsLogin);

    }

    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //This method is used to validate input given by user
    public boolean validate() {
            boolean valid = false;

            //Get values from EditText fields
            String Email = editTextEmail.getText().toString();
            String Password = editTextPassword.getText().toString();

            //Handling validation for Email field
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                valid = false;
                textInputLayoutEmail.setError("Masukkan email yang valid!");
            } else {
                valid = true;
                textInputLayoutEmail.setError(null);
            }

            //Handling validation for Password field
            if (Password.isEmpty()) {
                valid = false;
                textInputLayoutPassword.setError("Masukkan password yang valid!");
            } else {
                if (Password.length() > 5) {
                    valid = true;
                    textInputLayoutPassword.setError(null);
                } else {
                    valid = false;
                    textInputLayoutPassword.setError("Password terlalu pendek!");
                }
            }

        return valid;
    }


}