package renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.DividaModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_dividas.CadastrarDividasActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros.ParceirosActivity;

public class DividasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayoutManager lLayout;
    private Context context;
    private TextView txtValorTotal;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        List<DividaModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(DividasActivity.this);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(DividasActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);

        txtValorTotal = (TextView) findViewById(R.id.telaInicial_txt_total);

        for (int i = 0; i < rowListItem.size(); i++) {
            total += rowListItem.get(i).getValor();
        }

        txtValorTotal.setText("R$ " + Double.toString(total));

        rView.addOnItemTouchListener(new RecyclerItemClickListener(context, rView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(DividasActivity.this);
                builder.setTitle("Descrição");
                builder.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam semper erat libero, eu rhoncus massa fringilla vel. Nunc elit mi, sodales et malesuada nec, sagittis id ipsum. Curabitur ut lacinia velit. Mauris pharetra sem vitae justo elementum, sed viverra ligula consectetur. Sed aliquet nibh a rhoncus tempor. Nunc euismod mauris non dolor malesuada maximus. Fusce mattis risus et lectus bibendum mollis.");
                builder.setPositiveButton("Negociar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(DividasActivity.this, ParceirosActivity.class);
                        startActivity(intent);

                    }
                });
                builder.setNeutralButton("Voltar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });


                AlertDialog dialog = builder.create();
                dialog.show();
            }


            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent listaDividas = new Intent(DividasActivity.this, DividasActivity.class);
            startActivity(listaDividas);

        } else if (id == R.id.nav_gallery) {
            Intent listaDividas = new Intent(DividasActivity.this, ParceirosActivity.class);
            startActivity(listaDividas);

        } else if (id == R.id.nav_slideshow) {
            Intent listaDividas = new Intent(DividasActivity.this, CadastrarDividasActivity.class);
            startActivity(listaDividas);

        } else if (id == R.id.nav_manage) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<DividaModel> getAllItemList() {
        List<DividaModel> allItems = new ArrayList<DividaModel>();
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 329.71, "www.bradesco.com.br"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 252.32, "www.caixa.com.br"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 122.98, "www.itau.com.br"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 134.41, "www.crefisa.com.br"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "www.santander.com.br"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 500.09, "www.net.com.br"));

        return allItems;
    }
}
