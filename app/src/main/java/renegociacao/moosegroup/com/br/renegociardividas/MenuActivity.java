package renegociacao.moosegroup.com.br.renegociardividas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    private CardView listaDividas;
    private CardView cadastrarDividas;
    private CardView parceiros;
    private CardView sair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        listaDividas = (CardView) findViewById(R.id.menu_cardView_listaDeDividas);
        cadastrarDividas = (CardView) findViewById(R.id.menu_cardView_cadastrarDividas);
        parceiros = (CardView) findViewById(R.id.menu_cardView_parceiros);
        sair = (CardView) findViewById(R.id.menu_cardView_sair);

        listaDividas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irDividas = new Intent(MenuActivity.this, TelaInicialActivity.class);
                startActivity(irDividas);
            }
        });

        cadastrarDividas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telacadastrarD = new Intent(MenuActivity.this, CadastrarDividasActivity.class);
                startActivity(telacadastrarD);
            }
        });

        parceiros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent telaParceiro = new Intent(MenuActivity.this, ParceirosActivity.class);
                startActivity(telaParceiro);
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });


    }
}
