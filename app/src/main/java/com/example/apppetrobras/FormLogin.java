package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;
import com.example.apppetrobras.models.LoginResponse;
import com.example.apppetrobras.models.UserAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormLogin extends AppCompatActivity {

    EditText edit_user, edit_senha;
    Button button_login, esqueceu_senha;
    String user, pass, userbd, passbd,nomebd, emailbd, telbd;
    Dialog mDialog;
    ProgressBar progressbar;
    boolean passwordVisible;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

        edit_user = findViewById(R.id.edit_user);
        edit_senha = findViewById(R.id.edit_senha);
        button_login = findViewById(R.id.button_login);
        esqueceu_senha = findViewById(R.id.esqueceu_senha);

        mDialog = new Dialog(this);

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressbar=findViewById(R.id.progressbar);
                guardate();
                //new Task().execute();
                progressbar.setVisibility(View.VISIBLE);

            }
        });

        esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDialog.setContentView(R.layout.popup);
                mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                mDialog.show();

            }
        });

        edit_senha.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                final int Right=2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP) {

                    if(motionEvent.getRawX()>=edit_senha.getRight()-edit_senha.getCompoundDrawables() [Right].getBounds().width()) {

                        int selection=edit_senha.getSelectionEnd();
                        if(passwordVisible){

                            // set drawable image here
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_eye, 0);

                            //for hide password
                            edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;


                        }else {

                            //set drawable image here
                            edit_senha.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_eye_off,0);


                            //for show password
                            edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;


                        }

                        edit_senha.setSelection(selection);
                        return true;

                    }
                }




                return false;
            }
        });

    }

    private void guardate(){

        String chave = edit_user.getText().toString().trim();
        String senha = edit_senha.getText().toString().trim();

        Call<LoginResponse> call = RetroFitClient
                .getInstance()
                .getAPI()
                .userLogin(chave, senha);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()){
                    String boradescobrir = loginResponse.getMessage();
//                    if(boradescobrir.equals(chave)) {
//                        Intent intent = new Intent(FormLogin.this, TabActivity.class);
//                        startActivity(intent);
//                    }



//                    public List<Message> readMessagesArray(JsonReader reader) throws IOException {
//                        List<Message> messages = new ArrayList<Message>();
//
//                        reader.beginArray();
//                        while (reader.hasNext()) {
//                            messages.add(readMessage(reader));
//                        }
//                        reader.endArray();
//                        return messages;
//                    }





                    Toast.makeText(FormLogin.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();


//                    Intent intent = new Intent(FormLogin.this, RelatorioProcesso.class);
//                    startActivity(intent);
                } else {
                    Toast.makeText(FormLogin.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                //String happened
                Toast.makeText(FormLogin.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


    public userLogged guardaInfo() {
        String guardanome;
        String guardatel;
        String guardaemail;
        String guardachave;
        String guardasenha;

        guardanome = nomebd;
        guardatel = telbd;
        guardaemail = emailbd;
        guardachave = userbd;
        guardasenha = passbd;

        userLogged usuario = new userLogged(guardanome, guardatel, guardaemail, guardachave, guardasenha);
        usuario.setNome(guardanome);
        usuario.setTel(guardatel);
        usuario.setEmail(guardaemail);
        usuario.setChave(guardachave);
        usuario.setSenha(guardasenha);

        //Intent intent = new Intent(this, RelatorioProcesso.class);
        //intent.putExtra("userlogged", usuario);

        return usuario;

    }

//    class Task extends AsyncTask<Void, Void, Void> {
//        String records = "", error="";
//        String user = edit_user.getText().toString();
//        String pass = edit_senha.getText().toString();
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            try {
//
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection connection = DriverManager.getConnection("jdbc:mysql://139.177.199.178/test","backend","agathusia");
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery("SELECT * FROM funcionarios");
//
//                while(resultSet.next()) {
//                    nomebd = resultSet.getString("nome");
//                    telbd = resultSet.getString("tel");
//                    emailbd = resultSet.getString("email");
//                    userbd = resultSet.getString("chave");
//                    passbd = resultSet.getString("senha");
//
//                    if(user.equals(userbd) && pass.equals(passbd)) {
//                        break;
//                    }
//                }
//
//            } catch(Exception e) {
//                error = e.toString();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void unused) {
//            if (error != "") {
//            }
//
//            if(user.equals(userbd) && pass.equals(passbd)) {
//                Toast.makeText(FormLogin.this, "Bem vindo "+nomebd , Toast.LENGTH_SHORT).show();
//
//                Intent intent = new Intent(FormLogin.this, TabActivity.class);
//                intent.putExtra("userlogged", guardaInfo());
//                startActivity(intent);
//                finish();
//
//            } else {
//                Toast.makeText(FormLogin.this, "n foi dnv", Toast.LENGTH_SHORT).show();
//            }
//
//            super.onPostExecute(unused);
//        }
//    }
}
