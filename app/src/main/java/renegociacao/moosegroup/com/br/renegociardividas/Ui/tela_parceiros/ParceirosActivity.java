package renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.ListaParceiros;
import renegociacao.moosegroup.com.br.renegociardividas.R;

public class ParceirosActivity extends AppCompatActivity {
    private List<ListaParceiros> listaParceiros = new ArrayList<>();
    private RecyclerView recyclerView;
    private ParceirosAdapter parceirosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parceiros);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        parceirosAdapter = new ParceirosAdapter(listaParceiros);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(parceirosAdapter);

        preparandoListaParceiros();
    }

    private void preparandoListaParceiros() {
        ListaParceiros parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Bradesco", "00000000000", "56229988");
        listaParceiros.add(parceiros);

        parceirosAdapter.notifyDataSetChanged();
    }
}
