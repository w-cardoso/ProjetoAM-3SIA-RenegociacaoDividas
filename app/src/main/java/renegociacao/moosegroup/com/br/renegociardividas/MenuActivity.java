package renegociacao.moosegroup.com.br.renegociardividas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class MenuActivity extends AppCompatActivity {
    String arrayTelas[] = {"Lista de Dividas",
            "Cadastrar Dividas",
            "Parceiros",
            "Sair"};
    int selectedIdex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.menu_circleMenu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.add_song, R.drawable.cancel)
                .addSubMenu(Color.parseColor("#87CEFA"), R.drawable.lista_dividas)
                .addSubMenu(Color.parseColor("#0000FF"), R.drawable.cadastrar)
                .addSubMenu(Color.parseColor("#00FF7F"), R.drawable.parceiros)
                .addSubMenu(Color.parseColor("#FFA500"), R.drawable.sair)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                                               @Override
                                               public void onMenuSelected(int i) {
                                                   Toast.makeText(MenuActivity.this, "Você selecionou " + arrayTelas[i], Toast.LENGTH_SHORT).show();
                                                   selectedIdex = i;
                                               }
                                           }

                )
                .setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
                    @Override
                    public void onMenuOpened() {
                    }

                    @Override
                    public void onMenuClosed() {
                        switch (selectedIdex) {
                            case 0:
                                Intent listaDividas = new Intent(MenuActivity.this, TelaInicialActivity.class);
                                startActivity(listaDividas);
                                break;
                            case 1:
                                Intent cadastrarDividas = new Intent(MenuActivity.this, CadastrarDividasActivity.class);
                                startActivity(cadastrarDividas);
                                break;
                            case 2:
                                Intent parceiros = new Intent(MenuActivity.this, ParceirosActivity.class);
                                startActivity(parceiros);
                                break;
                            case 3:
                                System.exit(0);
                                break;

                        }
                    }
                });

    }


}

