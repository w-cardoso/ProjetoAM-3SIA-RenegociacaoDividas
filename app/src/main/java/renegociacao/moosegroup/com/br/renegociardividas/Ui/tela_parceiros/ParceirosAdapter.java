package renegociacao.moosegroup.com.br.renegociardividas.Ui.tela_parceiros;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.ListaParceiros;
import renegociacao.moosegroup.com.br.renegociardividas.R;

/**
 * Created by re034850 on 04/10/2017.
 */

public class ParceirosAdapter extends RecyclerView.Adapter<ParceirosAdapter.MyViewHolder> {

    private List<ListaParceiros> listaParceiros;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nomeFantasia, cnpj, telefone;

        public MyViewHolder(View view) {
            super(view);
            nomeFantasia = (TextView) view.findViewById(R.id.parceiros_nome);
            cnpj = (TextView) view.findViewById(R.id.parceiros_cnpj);
            telefone = (TextView) view.findViewById(R.id.parceiros_telefone);
        }
    }

    public ParceirosAdapter(List<ListaParceiros> listaParceiros) {
        this.listaParceiros = listaParceiros;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.parceiros_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ParceirosAdapter.MyViewHolder holder, int position) {
        ListaParceiros parceiros = listaParceiros.get(position);
        holder.nomeFantasia.setText(parceiros.getNomeFanstasia());
        holder.cnpj.setText(parceiros.getCnpj());
        holder.telefone.setText(parceiros.getTelefone());
    }

    @Override
    public int getItemCount() {
        return listaParceiros.size();
    }
}
