package com.example.apppetrobras;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.OnClickAction;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apppetrobras.api.RetroFitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SoluctionActivity extends AppCompatActivity {

    // Declaração das variáveis
    int idTitulo, idSolucao, tipoProblema, idPasso;

    Context context;

    TextView numeroPasso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soluction);

        tipoProblema = getIntent().getIntExtra("TIPO", 1);
        idTitulo = getIntent().getIntExtra("ID_TITULO",1);
        idSolucao = getIntent().getIntExtra("ID_SOLUCAO",1);
        idPasso = getIntent().getIntExtra("PASSO",1);

        Toast.makeText(this, tipoProblema+"-"+idTitulo+"-"+idSolucao+"-"+idPasso, Toast.LENGTH_SHORT).show();

        numeroPasso = findViewById(R.id.numeroPasso);
        numeroPasso.setText("Passo: "+idPasso);

        context = this;

        Call<List<Problems>> call;

        // pega o item no BD da tabela TextoTipo que tenha: tipo-idTitulo-idSolucao-idPasso(1-1-1-1)
//        call = RetroFitClient
//                .getInstance()
//                .getAPI()
//                .getLentidao(idTitulo);


    }

    public void btnCancel(View view){
        if (idPasso > 1) { restartPage(idPasso-1); }
    }

    public void btnCheck(View view){
        // verificar se há mais um passo e executar a seguinte linha:
        restartPage(idPasso+1);
    }

    public void restartPage(int step){
        // Reinicia a activity passando um novo passo

        Intent intent = getIntent();
        finish();

        // Definição de valores que serão redirecionados
        intent.putExtra("TIPO",tipoProblema);
        intent.putExtra("ID_TITULO", idTitulo);
        // position começa em 0, por isso é necessário adicionar 1 a ele
        intent.putExtra("ID_SOLUCAO", idSolucao);
        // A atualização do passo:
        intent.putExtra("PASSO", step);
        startActivity(intent);
    }
}