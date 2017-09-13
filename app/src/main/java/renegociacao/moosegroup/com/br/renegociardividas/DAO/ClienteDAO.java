package renegociacao.moosegroup.com.br.renegociardividas.DAO;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by re034850 on 13/09/2017.
 */

public class ClienteDAO extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "db.info";
    private static final int DATABASE_VERSION = 1;

    public static final String TABELA_NOME = "cliente";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_NOME = "nome";
    public static final String COLUNA_CPF = "cpf";
    public static final String COLUNA_EMAIL = "email";
    public static final String COLUNA_SENHA = "senha";
    public static final String COLUNA_TELEFONE = "telefone";

    private static final String CREATE_TABLE_QUERY =
            "CREATE TABLE " + TABELA_NOME + " (" + COLUNA_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUNA_NOME + " TEXT, " +
                    COLUNA_CPF + " TEXT, " +
                    COLUNA_EMAIL + " TEXT, " +
                    COLUNA_SENHA + " TEXT, " +
                    COLUNA_TELEFONE + " TEXT " + ")";

    public ClienteDAO(Context context) {
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
