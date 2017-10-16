package renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

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
                final ListaParceiros parceiro = listaParceiros.get(position);
                final AlertDialog.Builder builder = new AlertDialog.Builder(ParceirosActivity.this);
                builder.setTitle("Informação");
                builder.setMessage("Renegocie suas dividas conosco, condições especiais para você");
                builder.setPositiveButton("Ligar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        acessarTelefone(parceiro);
                    }
                });
                builder.setNegativeButton("WhatsApp", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        openWhatsApp(parceiro.getTelefone());
                    }
                });

                builder.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent voltar = new Intent(ParceirosActivity.this, DividasActivity.class);
                        startActivity(voltar);
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }


            @Override
            public void onLongItemClick(View view, int position) {

            }


        }));

        preparandoListaParceiros();
    }

    private void acessarTelefone(ListaParceiros parceiro) {
        if (ActivityCompat.checkSelfPermission(ParceirosActivity.this,
                android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ParceirosActivity.this, new String[]
                    {android.Manifest.permission.CALL_PHONE}, 123);
        } else {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + parceiro.getTelefone()));
            startActivity(callIntent);
        }
    }

    private void openWhatsApp(String telefone) {
        Uri uri = Uri.parse("smsto:" + telefone);
        Intent whats = new Intent(Intent.ACTION_SENDTO, uri);
        //whats.putExtra(Intent.EXTRA_TEXT, "Gostaria de negociar minhas dividas");
        whats.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(whats, null));
    }

    private void preparandoListaParceiros() {
        ListaParceiros parceiros = new ListaParceiros("Bradesco", "86.232.801/0001-06", "(11) 94536-4455");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Santander", "82.374.407/0001-43", "(11) 3729-8387");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Caixa", "83.328.362/0001-33", "(11) 2685-8358");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Crefisa", "71.454.197/0001-87", "(11) 3629-6156");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("BMB", "35.129.746/0001-84", "(11) 2691-5217");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Banco do Brasil", "37.469.722/0001-08", "(91) 3898-1450");
        listaParceiros.add(parceiros);

        parceiros = new ListaParceiros("Simplic", "01.787.653/0001-25", "(83) 2991-3498");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("Losango", "19.647.376/0001-10", "(81) 2558-6638");
        listaParceiros.add(parceiros);


        parceiros = new ListaParceiros("BV Financeira", "38.383.579/0001-91", "(11) 2907-2154");
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
