package renegociacao.moosegroup.com.br.renegociardividas.Ui.dividas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

import renegociacao.moosegroup.com.br.renegociardividas.Dao.DividaDao;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.cadastrar_dividas.CadastrarDividasActivity;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros.ParceirosActivity;

public class DividasActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private LinearLayoutManager lLayout;
    private Context context;
    private TextView txtValorTotal;
    double total;
    private String titulo, descricao, empresa;
    private double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sp = getSharedPreferences("sp", MODE_PRIVATE);
        int userId = sp.getInt("user_id", 0);

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

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        lLayout = new LinearLayoutManager(DividasActivity.this);
        rView.setLayoutManager(lLayout);

        DividaDao dao = new DividaDao(this);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(dao.listarPorUsuario(userId));
        rView.setAdapter(rcAdapter);


        txtValorTotal = (TextView) findViewById(R.id.telaInicial_txt_total);


        String resultado = String.format("%.2f", dao.somarDividas());
        txtValorTotal.setText("R$ " + resultado);

        rView.addOnItemTouchListener(new RecyclerItemClickListener(context, rView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                DividaDao dao = new DividaDao(getApplicationContext());

                TextView txtId = (TextView) view.findViewById(R.id.cardView_id);
                long id = Long.parseLong(txtId.getText().toString());
                dao.pegarDescricao(id);


                final AlertDialog.Builder builder = new AlertDialog.Builder(DividasActivity.this);
                builder.setTitle("Descrição");
                builder.setMessage(dao.pegarDescricao(id));
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


}
