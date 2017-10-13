package renegociacao.moosegroup.com.br.renegociardividas.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import renegociacao.moosegroup.com.br.renegociardividas.Model.DividaModel;

/**
 * Created by re034850 on 12/10/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.info";
    private static final int DATABASE_VERSION = 1;

    public static final String TABELA_NOME = "dividas";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_USER_ID = "user_id";
    public static final String COLUNA_TITULO = "titulo";
    public static final String COLUNA_DESCRICAO = "descricao";
    public static final String COLUNA_VALOR = "valor";
    public static final String COLUNA_EMPRESA = "empresa";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_USER_ID + " INTEGER, " +
                    COLUNA_TITULO + " TEXT, " +
                    COLUNA_DESCRICAO + " TEXT, " +
                    COLUNA_VALOR + " TEXT, " +
                    COLUNA_EMPRESA + " TEXT " + ")";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA_NOME);
        onCreate(sqLiteDatabase);
    }


}
