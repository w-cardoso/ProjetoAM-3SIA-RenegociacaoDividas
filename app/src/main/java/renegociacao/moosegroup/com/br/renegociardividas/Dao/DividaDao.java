package renegociacao.moosegroup.com.br.renegociardividas.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.DividaModel;

/**
 * Created by re034850 on 12/10/2017.
 */

public class DividaDao {
    public static final String TABELA_NOME = "dividas";
    private DbGateway gw;

    public DividaDao(Context ctx) {
        gw = DbGateway.getInstance(ctx);
    }


    public boolean salvar(int user_id, String titulo, String descricao, String valor, String empresa) {
        ContentValues cv = new ContentValues();
        cv.put("user_id", user_id);
        cv.put("titulo", titulo);
        cv.put("descricao", descricao);
        cv.put("valor", valor);
        cv.put("empresa", empresa);
        return gw.getDatabase().insert(TABELA_NOME, null, cv) > 0;
    }

    public List<DividaModel> retornarTodos() {
        List<DividaModel> dividas = new ArrayList<>();
        Cursor cursor = gw.getDatabase().rawQuery("SELECT * FROM dividas", null);
        while (cursor.moveToNext()) {
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));
            String titulo = cursor.getString(cursor.getColumnIndex("titulo"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            int valor = cursor.getInt(cursor.getColumnIndex("valor"));
            String empresa = cursor.getString(cursor.getColumnIndex("empresa"));
            dividas.add(new DividaModel(id, user_id, titulo, descricao, valor, empresa));

        }
        cursor.close();
        return dividas;
    }
}
