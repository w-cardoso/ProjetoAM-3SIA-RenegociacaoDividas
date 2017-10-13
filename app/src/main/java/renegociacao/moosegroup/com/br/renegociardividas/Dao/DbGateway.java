package renegociacao.moosegroup.com.br.renegociardividas.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by re034850 on 12/10/2017.
 */

public class DbGateway {

    private static DbGateway gw;
    private SQLiteDatabase db;

    private DbGateway(Context ctx){
        DbHelper divida = new DbHelper(ctx);
        db = divida.getWritableDatabase();
    }

    public static DbGateway getInstance(Context ctx){
        if(gw == null)
            gw = new DbGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}
