package com.example.sendmeal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Character.isLetter;

public class Registro extends AppCompatActivity {

    private EditText Nombre;
    private EditText Clave;
    private EditText RepetirClave;
    private EditText Correo;
    private EditText NumeroTarjeta;
    private EditText CCV;
    private EditText Vencimiento;
    private RadioGroup TipoCuenta;
    private SeekBar Credito;
    private Button Notificaciones;
    private Switch EsVendedor;
    private CheckBox Terminos;
    private EditText AliasCBU;
    private EditText CBU;
    private LinearLayout Vendedor;
    private Button Registrar;
    private TextView Progreso;
    private int creditoMinimo;
    private static final int creditoMinimoBase=100;
    private static final int creditoMinimoPremium=250;
    private static final int creditoMinimoFull=500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        creditoMinimo = creditoMinimoBase;
        Nombre = (EditText)findViewById(R.id.txtNombreRegistrar);
        Clave = (EditText) findViewById(R.id.txtClave);
        RepetirClave = (EditText) findViewById(R.id.txtRepetirClave);
        Correo = (EditText) findViewById(R.id.txtCorreo);
        NumeroTarjeta = (EditText) findViewById(R.id.txtNumeroTarjeta);
        CCV = (EditText) findViewById(R.id.txtCCV);
        Vencimiento = (EditText) findViewById(R.id.txtVencimiento);
        Credito = (SeekBar) findViewById(R.id.seekBarCreditoInicial);
        Notificaciones = (Button) findViewById(R.id.BtnNotificaciones);
        EsVendedor = (Switch) findViewById(R.id.swVendedor);
        Terminos = (CheckBox) findViewById(R.id.chkTerminos);
        AliasCBU = (EditText) findViewById(R.id.txtAliasCbu);
        CBU = (EditText) findViewById(R.id.txtCBU);
        Vendedor = (LinearLayout) findViewById(R.id.hiddenLayout);
        Registrar = (Button) findViewById(R.id.btnRegistrar);
        Progreso = (TextView) findViewById(R.id.lblCreditoInicial);
        Progreso.setText(((Integer) creditoMinimo).toString());
        TipoCuenta = (RadioGroup) findViewById(R.id.rgTipoCuenta);



        EsVendedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Vendedor.setVisibility(View.VISIBLE);
                }
                else {
                    Vendedor.setVisibility(View.GONE);
                }
            }
        });

        Terminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    Registrar.setEnabled(true);
                }
                else {
                    Registrar.setEnabled(false);
                }
            }
        });

        Credito.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                String aux = ((Integer)(progress + creditoMinimo)).toString();
                Progreso.setText(aux);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        TipoCuenta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id){
                    case R.id.rbBase:
                        creditoMinimo = creditoMinimoBase;
                        break;
                    case R.id.rbPremium:
                        creditoMinimo = creditoMinimoPremium;
                        break;
                    case R.id.rbFull:
                        creditoMinimo = creditoMinimoFull;
                        break;
                }
                Credito.setProgress(0);
                Progreso.setText(((Integer)creditoMinimo).toString());
            }
        });

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarVacio()) {//si faltan campos por completar
                    if(EsVendedor.isChecked()) { //si es vendedor informar que los campos de comprador + los de Alias CBU y CBU son obligatorios
                        String text = "¡Debe completar los datos de usuario y cuenta bancaria!";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    }
                    else { //si es comprador informar solo de la obligatoriedad de los campos de comprador
                        String text = "¡Debe completar los datos de usuario!";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    }
                }
                else { //si todos los campos obligatorios fueron completados satisfactoriamente se verifican las demás restricciones
                    if (!coincidenClaves()) { //si las claves no coinciden
                        String text = "¡Las claves ingresadas no coinciden!";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    }
                    else {//si las claves coinciden
                        if(!restriccionCorreo()) { // si las restricciones del correo electrónico no se cumplen satisfactoriamente
                            String text = "¡El correo electrónico ingresado no es válido!";
                            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                        }
                        else {//las restricciones del correo se cumplen
                            try {
                                if(!restriccionVencimiento()) { // si las restricciones de vencimiento de la tarjeta no se cumplen
                                    String text = "¡La tarjeta de crédito ingresada está próxima a vencerse!";
                                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                }
                                else { //todas las restricciones se cumplieron
                                    String text = "¡Datos guardados correctamente!";
                                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                }
                            }
                            catch(ParseException e) {
                              e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private Boolean validarVacio(){

        Boolean resultado = Correo.getText().length()>0 && Clave.getText().length()>0 && RepetirClave.getText().length()>0 && NumeroTarjeta.getText().length()>0;

        if(resultado && EsVendedor.isChecked()) {
            resultado = AliasCBU.getText().length()>0 && CBU.getText().length()>0;
        }

        return resultado;
    }

    private Boolean coincidenClaves(){
        return (Clave.getText().toString().equals(RepetirClave.getText().toString()));
    }

    private Boolean restriccionCorreo(){

        Boolean resultado = Correo.getText().toString().contains("@");

        if(resultado) {

            int indexArroba = Correo.getText().toString().indexOf("@");
            int diferencia = Correo.getText().length() - indexArroba - 1;

            if(diferencia>=3) {
                resultado = (isLetter(Correo.getText().toString().charAt(indexArroba + 1)) && isLetter(Correo.getText().toString().charAt(indexArroba + 2)) && isLetter(Correo.getText().toString().charAt(indexArroba + 3)));
            }
            else resultado = false;

        }

        return resultado;
    }

    private Boolean restriccionVencimiento() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/YY");
        Date v = sdf.parse(Vencimiento.getText().toString());
        String c = sdf.format(new Date());
        Date actual = sdf.parse(c);

        long diff = v.getTime() - actual.getTime();
        int difdias = (int) (diff / (24 * 60 * 60 * 1000));

        return (difdias>=92);
    }


















}







