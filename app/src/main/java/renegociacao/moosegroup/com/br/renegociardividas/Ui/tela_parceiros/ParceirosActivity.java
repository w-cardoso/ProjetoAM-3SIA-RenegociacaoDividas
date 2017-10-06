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
import renegociacao.moosegroup.com.br.renegociardividas.Ui.lista_dividas.RecyclerItemClickListener;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_menu.MenuActivity;

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
        ListaParceiros parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Caixa", "111111111111", "99008877");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Caixa", "111111111111", "99008877");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Caixa", "111111111111", "99008877");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Caixa", "111111111111", "99008877");
        listaParceiros.add(parceiros);


        parceirosAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            Intent voltar = new Intent(this, MenuActivity.class);
            startActivity(voltar);
        }

        return super.onOptionsItemSelected(item);
    }


}
