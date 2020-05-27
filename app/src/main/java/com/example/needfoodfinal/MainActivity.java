package com.example.needfoodfinal;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ViewFlipper vf;
    EditText dateText;

    Calendar c;
    DatePickerDialog dpd;

    SimpleDateFormat sdf;

    int mYear,mMonth,mdayOfMonth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vf = findViewById(R.id.viewf);
        dateText = findViewById(R.id.fechaRegis);
        sdf = new SimpleDateFormat("dd/MM/yyyy");
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            super.onCreateOptionsMenu(menu);
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            Toast.makeText(this, "Menú creado", Toast.LENGTH_LONG).show();
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            switch(id) {
                case R.id.perfil:
                    vf.setDisplayedChild(0);
                    Toast.makeText(this, "hi",
                            Toast.LENGTH_LONG).show();break;
                case R.id.opciones:
                    vf.setDisplayedChild(1);
                    Toast.makeText(this, "hi",
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.cerrarSesion:
                    vf.setDisplayedChild(2);
                    Toast.makeText(this, "Bye",
                            Toast.LENGTH_LONG).show();
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    break;
                case android.R.id.home: //es el widget de la flecha
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    vf.setDisplayedChild(0);
                    break;
            }
            return false;
        }




    public void onClickBtnRegisUsuario(View view){
        vf.setDisplayedChild(1);
    }



    public void onClickEntrar(View view) {
    }

    public void onClickFechanacregistro(View view) {
        c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int moth = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);

        dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int mYear, int mMonth, int mdayOfMonth) {
                dateText.setText(mdayOfMonth + "-"+ (mMonth+1) + "-" + mYear);
            }
        },year,moth,day);
        dpd.show();
    }

    public void onClickSignInUsuario(View view) throws ParseException {

        /*
        String ildate = dateText.getText().toString();
        try{
            Date userdate = (Date) sdf.parse(ildate);
            System.out.println(userdate);
            Date today = (Date) Calendar.getInstance().getTime();
            today.setYear(Calendar.YEAR - 18);

            if(userdate.before(today)){
                vf.setDisplayedChild(0);
                Toast.makeText(this, this.getText(R.string.registradouser), Toast.LENGTH_LONG).show();

            }
            else{
                Toast.makeText(this, this.getText(R.string.demasiadojoven), Toast.LENGTH_LONG).show();
            }
        }catch (ParseException e){
            e.printStackTrace();
        }


         */


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String sdfClientDate = dateText.getText().toString();
        Date firstDate = sdf.parse(sdfClientDate);

        Date mayorFecha = Calendar.getInstance().getTime();
        mayorFecha.setYear(mayorFecha.getDate() - 18);


        long diffInMillies = Math.abs(mayorFecha.getTime() - firstDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        System.out.println("");

        if (diffInMillies > 0) {
            vf.setDisplayedChild(0);
            Toast.makeText(this, this.getText(R.string.registradouser), Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, this.getText(R.string.demasiadojoven), Toast.LENGTH_LONG).show();
        }




    }
}

