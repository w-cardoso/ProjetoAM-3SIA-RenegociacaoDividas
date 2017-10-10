package renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.ListaParceiros;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas.DividasActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas.RecyclerItemClickListener;

public class ParceirosActivity extends AppCompatActivity {
    private List<ListaParceiros> listaParceiros = new ArrayList<>();
    private RecyclerView recyclerView;
    private ParceirosAdapter parceirosAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceiros);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        parceirosAdapter = new ParceirosAdapter(listaParceiros);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(parceirosAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ListaParceiros parceiro = listaParceiros.get(position);
                Toast.makeText(getApplicationContext(), parceiro.getNomeFanstasia() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }




        }));

        preparandoListaParceiros();
    }

    private void preparandoListaParceiros() {
        ListaParceiros parceiros = new ListaParceiros("Bradesco", "86.232.801/0001-06", "(11)2595-3231");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Santander", "82.374.407/0001-43", "(11)3729-8387");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Caixa", "83.328.362/0001-33", "(11)2685-8358");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Crefisa", "71.454.197/0001-87", "(85)3629-6156");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("BMB", "35.129.746/0001-84", "(79)2691-5217");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Banco do Brasil", "37.469.722/0001-08", "(91)3898-1450");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Simplic", "01.787.653/0001-25", "(83)2991-3498");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Losango", "19.647.376/0001-10", "(81)2558-6638");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("BV Financeira", "38.383.579/0001-91", "(11)2907-2154");
        listaParceiros.add(parceiros);


        parceirosAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent voltar = new Intent(this, DividasActivity.class);
            startActivity(voltar);
        }

        return super.onOptionsItemSelected(item);
    }


}
