package com.example.ejemplosqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;
    EditText et6;

    AdminSQLiteOpenHelper admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        et4=findViewById(R.id.et4);
        et5=findViewById(R.id.et5);
        et6=findViewById(R.id.et6);

        admin=new AdminSQLiteOpenHelper(this,"ventas",null,2);


    }

    public void insertar(View view) {
        SQLiteDatabase bd = admin.getWritableDatabase();

        int id=Integer.parseInt(et1.getText().toString());
        String nombre=et2.getText().toString();
        String apellido1=et3.getText().toString();
        String apellido2=et4.getText().toString();
        String ciudad=et5.getText().toString();
        int categoria=Integer.parseInt(et6.getText().toString());

        ContentValues registro=new ContentValues();
        registro.put("id",id);
        registro.put("nombre",nombre);
        registro.put("apellido1",apellido1);
        registro.put("apellido2",apellido2);
        registro.put("ciudad",ciudad);
        registro.put("categoria",categoria);

        long result=bd.insert("cliente",null,registro);

        if(result>0){
            Toast.makeText(this,"Se ha realizado inserción correctamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"ERROR en la inserción",Toast.LENGTH_LONG).show();
        }


        bd.close();

        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        et5.setText("");
        et6.setText("");

    }

    public void consultaPorId(View view) {

        SQLiteDatabase bd = admin.getWritableDatabase();

        int id=Integer.parseInt(et1.getText().toString());

        String consulta="select * from cliente where id="+id;

        Cursor registro=bd.rawQuery(consulta,null);

        if(registro.moveToFirst()){
            id=registro.getInt(0);
            String nombre=registro.getString(1);
            String apellido1=registro.getString(2);
            String apellido2=registro.getString(3);
            String ciudad=registro.getString(4);
            int categoria=registro.getInt(5);

            //et1.setText(id);
            et2.setText(nombre);
            et3.setText(apellido1);
            et4.setText(apellido2);
            et5.setText(ciudad);
            et6.setText(""+categoria);


        }else{
            Toast.makeText(this,"No hay resultados",Toast.LENGTH_LONG).show();

        }

        bd.close();
    }

    public void actualizarPorId(View view) {
        SQLiteDatabase bd = admin.getWritableDatabase();

        int id=Integer.parseInt(et1.getText().toString());
        String where="id="+id;

        String nombre=et2.getText().toString();
        String apellido1=et3.getText().toString();
        String apellido2=et4.getText().toString();
        String ciudad=et5.getText().toString();
        int categoria=Integer.parseInt(et6.getText().toString());

        ContentValues registro=new ContentValues();

        registro.put("id",id);
        registro.put("nombre",nombre);
        registro.put("apellido1",apellido1);
        registro.put("apellido2",apellido2);
        registro.put("ciudad",ciudad);
        registro.put("categoria",categoria);


        long result=bd.update("cliente",registro,where,null);

        if(result>0){
            Toast.makeText(this,"Se ha realizado actualización correctamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"ERROR en la actualización",Toast.LENGTH_LONG).show();
        }

        bd.close();

    }

    public void borrarPorId(View view) {
        SQLiteDatabase bd = admin.getWritableDatabase();

        int id=Integer.parseInt(et1.getText().toString());
        String where="id="+id;
        long result=bd.delete("cliente",where,null);

        if(result>0){
            Toast.makeText(this,"Se ha realizado borrado correctamente",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"ERROR en la eliminación",Toast.LENGTH_LONG).show();
        }

        bd.close();

    }
}