package renegociacao.moosegroup.com.br.renegociardividas.Ui.lista_dividas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.DividaModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.RecycleView.RecyclerItemClickListener;
import renegociacao.moosegroup.com.br.renegociardividas.RecycleView.RecyclerViewAdapter;
import renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros.ParceirosActivity;

public class ListaDividasActivity extends AppCompatActivity {

    private LinearLayoutManager lLayout;
    private Context context;
    private TextView txtValorTotal;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_lista_dividas);

        ActionBar ab = getSupportActionBar();
        ab.hide();

        List<DividaModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(ListaDividasActivity.this);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(ListaDividasActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);

        txtValorTotal = (TextView) findViewById(R.id.telaInicial_txt_total);

        for (int i = 0; i < rowListItem.size(); i++) {
            total += rowListItem.get(i).getValor();
        }

        txtValorTotal.setText("R$ " + Double.toString(total));

        rView.addOnItemTouchListener(new RecyclerItemClickListener(context, rView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(ListaDividasActivity.this);
                builder.setTitle("Descrição");
                builder.setMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam semper erat libero, eu rhoncus massa fringilla vel. Nunc elit mi, sodales et malesuada nec, sagittis id ipsum. Curabitur ut lacinia velit. Mauris pharetra sem vitae justo elementum, sed viverra ligula consectetur. Sed aliquet nibh a rhoncus tempor. Nunc euismod mauris non dolor malesuada maximus. Fusce mattis risus et lectus bibendum mollis.");
                builder.setPositiveButton("Negociar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ListaDividasActivity.this, ParceirosActivity.class);
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

    private List<DividaModel> getAllItemList() {
        List<DividaModel> allItems = new ArrayList<DividaModel>();

        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "vishhh"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "vishhh"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "vishhh"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "vishhh"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "vishhh"));
        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", 1222.44, "vishhh"));

        return allItems;
    }

}
