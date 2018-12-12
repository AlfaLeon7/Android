package inc.alfaleon.pruebarv;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class CrearMonstruo extends AppCompatActivity {
    EditText nombre;
    EditText significado;
    EditText descripcion;
    TextView vernombre;
    TextView versignificado;
    TextView verdescripcion;
    Button añadirimagen;
    Button añadirObjeto;
    ImageView cargarimagen;
    private static final int PHOTO_SELECTED =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_monstruo);

        nombre = (EditText) findViewById(R.id.crearMonstruoNombre);
        significado = (EditText) findViewById(R.id.crearMonstruoDescripcion);
        descripcion = (EditText) findViewById(R.id.crearMonstruoDescripcionLarga);
        vernombre = (TextView) findViewById(R.id.campoNombre);
        versignificado = (TextView) findViewById(R.id.campoSignificado);
        verdescripcion = (TextView) findViewById(R.id.campoDescripcion);
        añadirimagen = (Button) findViewById(R.id.botonImagen);
        añadirObjeto = (Button) findViewById(R.id.botonCrear);





        añadirimagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivity(intent);

            }
        });

        añadirObjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Monstruo monster = new Monstruo();
                monster.setNombre((nombre.getText().toString()));
                monster.setDescripcion(significado.getText().toString());
                monster.setDescripcionLarga(descripcion.getText().toString());
                Intent devolver = new Intent();

                devolver.putExtra("resultado", monster);
                setResult(MainActivity.RESULT_OK, devolver);
                finish();



            }
        });



    }

    @Override
    protected void onActivityResult(int requeatCode, int resultCode, Intent imageReturnedIntent){

        Uri selectedImageUri = imageReturnedIntent.getData();
        InputStream is;

        try{
            is=getContentResolver().openInputStream(selectedImageUri);
            BufferedInputStream bis = new BufferedInputStream(is);
            Bitmap bitmap = BitmapFactory.decodeStream(bis);
            cargarimagen = (ImageView) findViewById(R.id.crearMonstruoImagen);
            cargarimagen.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }





    }






}
