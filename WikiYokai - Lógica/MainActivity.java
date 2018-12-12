package inc.alfaleon.pruebarv;

import android.app.Activity;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.Menu;

import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    AdapterDatos adapter;

      public  ArrayList<Monstruo> listaMonstruos;
        RecyclerView recyclerMonstrus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        listaMonstruos = new ArrayList<>();
        recyclerMonstrus = findViewById(R.id.recyclerId);
        recyclerMonstrus.setLayoutManager(new LinearLayoutManager(this));
        llenarPersonajes();
         adapter = new AdapterDatos(listaMonstruos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),  listaMonstruos.get(recyclerMonstrus.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();
                Monstruo monstruo = listaMonstruos.get(recyclerMonstrus.getChildAdapterPosition(v));

                Intent intent = new Intent(MainActivity.this, Informacion.class);

                Bundle bundle = new Bundle();

                bundle.putSerializable("Monstruo", monstruo);
                intent.putExtras(bundle);
                startActivity(intent);
                adapter.notifyDataSetChanged();

            }
        });
        recyclerMonstrus.setAdapter(adapter);





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();


        if(id==R.id.addmonstruo){

            Intent crear = new Intent(this, CrearMonstruo.class);
            startActivity(crear);

        }

        if(id==R.id.filtrar){

            SearchView searchview = (SearchView) item.getActionView();
            searchview.setOnQueryTextListener(this);
        }

        if(id==R.id.title3){
            Toast.makeText(this, "Item3", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                Bundle bundle = getIntent().getExtras();
               Monstruo monstruo = (Monstruo)bundle.getSerializable("resultado");
                listaMonstruos.add(new Monstruo(monstruo.getNombre(), monstruo.getDescripcion(), monstruo.getDescripcionLarga()));
                adapter.notifyDataSetChanged();
                recyclerMonstrus.setAdapter(adapter);



            }
        }
    }

    public void llenarPersonajes() {
        listaMonstruos.add(new Monstruo("Yamawarawa", "Significa: Niño de la montaña", "Monstruo que habita en las montañas que normalmente ayuda a las personas a cargar los troncos a sus casas a cambio de arroz y pescado. Se dice que canta muy bien", R.drawable.yamawarawa));
        listaMonstruos.add(new Monstruo("Yamauba", "Significa: Anciana de la montaña", "En varias regiones, muchas mujeres se quedaban aisladas cuando morían sus esposos. A estas mujeres se le acabaron atribuyendo características macabras", R.drawable.yamauba));
        listaMonstruos.add(new Monstruo("Inugami y Shirachigo", "Significa: Perro Dios y Niño Blanco", "Se le considera un tsukimono, demonio que posee a las personas. Está asociado con un hechicero que ha dominado a estas criaturas, y el Shirachigo es su sirviente.", R.drawable.inu));
        listaMonstruos.add(new Monstruo("Nekomata", "Significa: Gato de dos colas","En algunas regiones se cree que si una persona domestica durante mucho tiempo a un gato, a este le saldrá una cola nueva.", R.drawable.neko));
        listaMonstruos.add(new Monstruo("Kappa", "Conocida criatura acuática","Es uno de los monstruos actuáticos más populares de Japón. Se cree que tira de las piernas a las personas que están en las orillas de los ríos y las ahoga.", R.drawable.kappa));
        listaMonstruos.add(new Monstruo("Kawauso", "Significa: Nutria de río","En muchas regiones de Japón se considera que engaña a las personas, transformándose en diversas formas, como una bella mujer o un niño.", R.drawable.kawauso));
        listaMonstruos.add(new Monstruo("Tanuki", "Animal común que engaña a las personas","El perro-mapache o tanuki es un animal común de Japón y Asia Oriental. Engaña a las personas y convierte las hojas de los árboles en monedas.", R.drawable.tanuki));
        listaMonstruos.add(new Monstruo("Kamaitachi", "Significa: Comadreja con hoz", "Existen varias leyendas sobre este monstruo. Aparece cuando hay viente y se dice que corta la piel de las personas.",R.drawable.hoz));
        listaMonstruos.add(new Monstruo("Akaname", "Significa: Lame suciedad","Viene por las noches y lame la migre de las tinas de baño. No hace nada en realidad, es una criatura que resalta la suciedad de algunas casas.", R.drawable.akaname));



    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userinput = newText.toLowerCase();
        ArrayList<Monstruo> newList = new ArrayList<Monstruo>();
        for(Monstruo monstru : listaMonstruos){
            if(monstru.getNombre().toLowerCase().contains(userinput)||(monstru.getDescripcion().toLowerCase().contains(userinput))){
                newList.add(monstru);
            }
        }
        adapter.updateList(newList);

        return true;
    }
}
