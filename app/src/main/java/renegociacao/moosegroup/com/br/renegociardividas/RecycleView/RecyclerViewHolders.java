package renegociacao.moosegroup.com.br.renegociardividas.RecycleView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import renegociacao.moosegroup.com.br.renegociardividas.R;

/**
 * Created by re034850 on 11/09/2017.
 */

public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    public TextView txtTitulo;
    public TextView txtValor;
    public ImageView imgFotoEmpresa;
    private Context context;


    public RecyclerViewHolders(View itemView, Context context) {
        super(itemView);
        this.context = itemView.getContext();
        txtTitulo = (TextView) itemView.findViewById(R.id.cardView_txtTitulo);
        txtValor = (TextView) itemView.findViewById(R.id.cardView_txtValor);

    }
}
