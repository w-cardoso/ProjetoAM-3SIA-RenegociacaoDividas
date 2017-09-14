package renegociacao.moosegroup.com.br.renegociardividas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.ClienteModel;

/**
 * Created by re034850 on 13/09/2017.
 */

public class ClienteDAO extends SQLiteOpenHelper {


    public ClienteDAO(Context context) {
        super(context, "dividaZero", null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Cliente (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "cpf TEXT, " +
                "email TEXT, " +
                "senha TEXT, " +
                "telefone TEXT );";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion) {
            case 1:
                sql = "DROP TABLE IF EXISTS Cliente";
                db.execSQL(sql);
        }
    }

    public void inserir(ClienteModel cliente) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosDoCliente(cliente);
        db.insert("Clientes", null, dados);
    }

    public List<ClienteModel> buscaClientes() {
        String sql = "SELECT * FROM Cliente;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<ClienteModel> clientes = new ArrayList<>();
        while (c.moveToNext()) {
            ClienteModel cliente = new ClienteModel();
            cliente.setId(c.getLong(c.getColumnIndex("id")));
            cliente.setNome(c.getString(c.getColumnIndex("nome")));
            cliente.setCpf(c.getString(c.getColumnIndex("cpf")));
            cliente.setEmail(c.getString(c.getColumnIndex("email")));
            cliente.setSenha(c.getString(c.getColumnIndex("senha")));
            cliente.setTelefone(c.getString(c.getColumnIndex("telefone")));
            clientes.add(cliente);
        }
        c.close();
        return clientes;
    }


    @NonNull
    private ContentValues pegaDadosDoCliente(ClienteModel cliente) {
        ContentValues dados = new ContentValues();
        dados.put("nome", cliente.getNome());
        dados.put("cpf", cliente.getCpf());
        dados.put("email", cliente.getEmail());
        dados.put("senha", cliente.getSenha());
        dados.put("telefone", cliente.getTelefone());
        return dados;
    }
}