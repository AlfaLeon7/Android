package inc.alfaleon.pruebarv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Informacion extends AppCompatActivity {
TextView image_name;
TextView description;
TextView descripcion_large;
ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        image_name = (TextView) findViewById(R.id.imageInfo);
        description = (TextView)  findViewById(R.id.descripcion);
        descripcion_large = (TextView) findViewById(R.id.descripcionExtensa);
        imagen = (ImageView) findViewById(R.id.imagenGaleria);



        Bundle bundle = getIntent().getExtras();


        if(bundle!=null){
            Monstruo monster = (Monstruo)bundle.getSerializable("Monstruo");
            image_name.setText(monster.getNombre().toString());
            description.setText(monster.getDescripcion().toString());
            descripcion_large.setText(monster.getDescripcionLarga().toString());
            imagen.setImageResource(monster.getFoto());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id= item.getItemId();
            //Buscar en internet
        if(id==R.id.buscar){
            Bundle wikipedia = getIntent().getExtras();
            Monstruo monstruos = (Monstruo)wikipedia.getSerializable("Monstruo");
            String url = "https://wikipedia.org/wiki/" + monstruos.getNombre();
            Intent buscar = new Intent(Intent.ACTION_VIEW);
            buscar.setData(Uri.parse(url));
            startActivity(buscar);
        }
        //Compartir
        if(id==R.id.compartir){
            Bundle share = getIntent().getExtras();
            Monstruo monstruos = (Monstruo)share.getSerializable("Monstruo");
            Intent compartir = new Intent(Intent.ACTION_SEND);
            compartir.setType("text/plain");
            compartir.putExtra(Intent.EXTRA_TEXT, monstruos.getNombre() + "\n" + monstruos.getDescripcion() + "\n"  + monstruos.getDescripcionLarga() + "\n\nAprende más sobre folklore japonés con la App de Christian!");
            startActivity(Intent.createChooser(compartir, "Compartir con"));


        }
        //Enviar por correo
        if(id==R.id.correo){
            Bundle mail = getIntent().getExtras();
            Monstruo monstruos = (Monstruo)mail.getSerializable("Monstruo");
            Intent email = new Intent(Intent.ACTION_SEND);
            email.setData(Uri.parse("mailto"));
            email.setType("text/plain");
            email.putExtra(Intent.EXTRA_EMAIL, "");
            email.putExtra(Intent.EXTRA_SUBJECT, monstruos.getNombre());
            email.putExtra(Intent.EXTRA_TEXT, monstruos.getDescripcionLarga());

            try{
                startActivity(Intent.createChooser(email, "Enviar mail"));
                finish();
            }catch(android.content.ActivityNotFoundException ex){
                Toast.makeText(Informacion.this, "No tienes aplicaciones de email instaladas", Toast.LENGTH_SHORT).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }



}
