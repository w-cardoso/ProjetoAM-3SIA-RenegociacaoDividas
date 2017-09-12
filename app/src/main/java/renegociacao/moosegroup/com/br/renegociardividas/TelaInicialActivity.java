package renegociacao.moosegroup.com.br.renegociardividas;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import renegociacao.moosegroup.com.br.renegociardividas.Model.DividaModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;
import renegociacao.moosegroup.com.br.renegociardividas.RecycleView.RecyclerViewAdapter;

public class TelaInicialActivity extends AppCompatActivity {

    private LinearLayoutManager lLayout;
    private Context context;
    private TextView txtValorTotal;
    double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        List<DividaModel> rowListItem = getAllItemList();
        lLayout = new LinearLayoutManager(TelaInicialActivity.this);

        RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(TelaInicialActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);

        txtValorTotal = (TextView) findViewById(R.id.telaInicial_txt_total);

        for (int i = 0; i < rowListItem.size(); i++){
            total += rowListItem.get(i).getValor();
        }

        txtValorTotal.setText(Double.toString(total));

    }

    private List<DividaModel> getAllItemList() {
        List<DividaModel> allItems = new ArrayList<DividaModel>();

        allItems.add(new DividaModel("Cartão de Crédito", "Conta não paga a 1 ano", "www.itau.com.br", 1222.35, "Itau", R.drawable.itau));
        allItems.add(new DividaModel("Apartamento", "Divida referente a chaves do apartamento", "www.caixa.com.br", 12332.99, "Caixa Economica", R.drawable.caixa));
        allItems.add(new DividaModel("Emprestimo Consignado", "Empréstimo realizado em 2012, não foi pago nenhuma parcela", "www.bradesco.com.br", 5600.00, "Bradesco", R.drawable.bradesco));

        return allItems;
    }

}
