package com.example.croxas.wow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextPass;
    Button buttonLog;
    View mProgressView;
    View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        buttonLog = (Button) findViewById(R.id.botonLog);
        mProgressView = findViewById(R.id.login_progress);
        mLoginFormView = findViewById(R.id.login_form);

    }

    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private void showProgress(final boolean show) {
        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);

        int visibility = show ? View.GONE : View.VISIBLE;
        mLoginFormView.setVisibility(visibility);

    }


    public void attemptLogin(View view) {
        //Poner aqui todas las comprobaciones y progressbar de marta&ana
        showProgress(true);
        Call<Jugador> loginCall = ApiAdapter.getApiService().getLogin(editTextEmail.getText().toString(),new Login(editTextPass.getText().toString()));
        loginCall.enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(Call<Jugador> call, Response<Jugador> response) {
                switch (response.code()) {
                    case 200:
                        showLoginError("Login correcto");
                        Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                        Jugador jug = response.body();
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            String jsonResult = mapper.writeValueAsString(jug);
                            myIntent.putExtra("jugador", jsonResult);
                            showProgress(false);
                            LoginActivity.this.startActivity(myIntent);
                        }catch (Exception e){
                            showLoginError("No serializable");
                        }
                        break;
                    case 204:
                        showLoginError("Contraseña incorrecta");
                        break;
                    case 500:
                        showLoginError("El correo no existe");
                        break;

                }
            }
            @Override
            public void onFailure(Call<Jugador> call, Throwable t) {
                showLoginError("La conexion no esta disponible");
                showProgress(false);
                return;
            }
        });


    }
}
