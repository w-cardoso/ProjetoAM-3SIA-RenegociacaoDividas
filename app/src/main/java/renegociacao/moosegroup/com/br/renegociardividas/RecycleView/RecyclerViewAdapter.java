package renegociacao.moosegroup.com.br.renegociardividas.RecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.DividaModel;
import renegociacao.moosegroup.com.br.renegociardividas.R;

/**
 * Created by re034850 on 11/09/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {
    private List<DividaModel> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<DividaModel> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_adapter, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView, context);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.txtTitulo.setText(itemList.get(position).getTitulo());

        holder.txtValor.setText("R$ " + Double.toString(itemList.get(position).getValor()));

        holder.imgFotoEmpresa.setImageResource(itemList.get(position).getFotoEmpresa());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
