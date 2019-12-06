package com.example.sendmeal.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.ToggleButton;

import com.example.sendmeal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class Registro extends AppCompatActivity {

    private EditText txtNombre;
    private EditText txtClave;
    private EditText txtRepetirClave;
    private EditText txtCorreo;
    private EditText txtNumeroTarjeta;
    private EditText txtCcv;
    private EditText txtVencimiento;
    private RadioGroup rgTipoCuenta;
    private SeekBar sbCredito;
    private ToggleButton btnNotificaciones;
    private Switch swEsVendedor;
    private CheckBox chkTerminos;
    private EditText txtAliasCbu;
    private EditText txtCbu;
    private LinearLayout llVendedor;
    private Button btnRegistrar;
    private TextView lblProgreso;
    private int creditoMinimo;
    private static final int creditoMinimoBase=100;
    private static final int creditoMinimoPremium=250;
    private static final int creditoMinimoFull=500;
    private Toolbar tbRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializarComponentes();
        setSupportActionBar(tbRegistro);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        configurarEventos();
    }

    private void inicializarComponentes(){
        txtNombre = findViewById(R.id.txtNombreRegistrar);
        txtClave = findViewById(R.id.txtClave);
        txtRepetirClave = findViewById(R.id.txtRepetirClave);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtNumeroTarjeta = findViewById(R.id.txtNumeroTarjeta);
        txtCcv = findViewById(R.id.txtCCV);
        txtVencimiento = findViewById(R.id.txtVencimiento);
        sbCredito = findViewById(R.id.seekBarCreditoInicial);
        btnNotificaciones = findViewById(R.id.BtnNotificaciones);
        swEsVendedor = findViewById(R.id.swVendedor);
        chkTerminos = findViewById(R.id.chkTerminos);
        txtAliasCbu = findViewById(R.id.txtAliasCbu);
        txtCbu = findViewById(R.id.txtCBU);
        llVendedor = findViewById(R.id.hiddenLayout);
        btnRegistrar = findViewById(R.id.btnRegistrarUsuario);
        lblProgreso = findViewById(R.id.lblProgresoCredito);
        lblProgreso.setText("0");
        rgTipoCuenta = findViewById(R.id.rgTipoCuenta);
        tbRegistro = findViewById(R.id.tbRegistro);
        creditoMinimo = creditoMinimoBase;
    }

    private void configurarEventos(){

        swEsVendedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    llVendedor.setVisibility(View.VISIBLE);
                }
                else {
                    llVendedor.setVisibility(View.GONE);
                }
            }
        });

        chkTerminos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    btnRegistrar.setEnabled(true);
                }
                else {
                    btnRegistrar.setEnabled(false);
                }
            }
        });

        sbCredito.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                lblProgreso.setText(((Integer) progress).toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        rgTipoCuenta.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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
                sbCredito.setProgress(0);
                lblProgreso.setText("0");
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validarVacio()) {//si faltan campos por completar
                    if(swEsVendedor.isChecked()) { //si es vendedor informar que los campos de comprador + los de Alias CBU y CBU son obligatorios
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
                            if(!restriccionVencimiento()) { // si las restricciones de vencimiento de la tarjeta no se cumplen
                                String text = "¡La tarjeta de crédito ingresada está próxima a vencerse!";
                                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                            }
                            else { //todas las restricciones se cumplieron
                                String text = "¡Datos guardados correctamente!";
                                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                //limpiarDatos();
                            }
                        }
                    }
                }
            }
        });


    }

    private Boolean validarVacio(){

        Boolean resultado = txtCorreo.getText().length()>0 && txtClave.getText().length()>0 && txtRepetirClave.getText().length()>0 && txtNumeroTarjeta.getText().length()>0;

        if(resultado && swEsVendedor.isChecked()) {
            resultado = txtAliasCbu.getText().length()>0 && txtCbu.getText().length()>0;
        }

        return resultado;
    }

    private Boolean coincidenClaves(){
        return (txtClave.getText().toString().equals(txtRepetirClave.getText().toString()));
    }

    private Boolean restriccionCorreo(){

        Boolean resultado = txtCorreo.getText().toString().contains("@");

        if(resultado) {

            int indexArroba = txtCorreo.getText().toString().indexOf("@");
            int diferencia = txtCorreo.getText().length() - indexArroba - 1;

            if(diferencia>=3) {
                resultado = (isLetter(txtCorreo.getText().toString().charAt(indexArroba + 1)) && isLetter(txtCorreo.getText().toString().charAt(indexArroba + 2)) && isLetter(txtCorreo.getText().toString().charAt(indexArroba + 3)));
            }
            else resultado = false;

        }

        return resultado;
    }

    private Boolean restriccionVencimiento(){

        String fechaIngresada = txtVencimiento.getText().toString();

        Boolean esValida = false;

        if (validarFormatoFecha(fechaIngresada)) {
            Integer mes = Integer.parseInt(fechaIngresada.substring(0, 2));
            Integer año = Integer.parseInt(fechaIngresada.substring(3, 7));

            Calendar fechaActual = Calendar.getInstance();
            Integer añoActual = fechaActual.get(Calendar.YEAR);
            Integer mesActual = fechaActual.get(Calendar.MONTH) + 1;

            if (mes >= 1 && mes <= 12) {
                if (añoActual.equals(año)) {
                    if ((mes - mesActual) > 3) {
                        esValida = true;
                    }
                }
                else {
                    if ((año - añoActual) > 1) {
                        esValida = true;
                    }
                    else {
                        if ((año - añoActual) == 1) {
                            if ((mesActual - mes) < 9) {
                                esValida = true;
                            }
                        }
                    }
                }
            }
        }

        return esValida;
    }

    private boolean validarFormatoFecha(String fecha){
        boolean esValido = false;

        if (fecha.length() == 7) {
            if (isDigit(fecha.charAt(0)) && isDigit(fecha.charAt(1)) && fecha.charAt(2) == '/'
                    && isDigit(fecha.charAt(3)) && isDigit(fecha.charAt(4))
                    && isDigit(fecha.charAt(5)) && isDigit(fecha.charAt(6))) {
                esValido = true;
            }
        }

        return esValido;
    }

    private void limpiarDatos(){
        txtNombre.setText("");
        txtClave.setText("");
        txtRepetirClave.setText("");
        txtCorreo.setText("");
        txtNumeroTarjeta.setText("");
        txtCcv.setText("");
        txtVencimiento.setText("");
        sbCredito.setProgress(0);
        btnNotificaciones.setChecked(false);
        swEsVendedor.setChecked(false);
        chkTerminos.setChecked(false);
        txtAliasCbu.setText("");
        txtCbu.setText("");
        lblProgreso.setText("0");
        rgTipoCuenta.check(R.id.rbBase);
        creditoMinimo = creditoMinimoBase;
    }

    public Button getBtnRegistrar() {
        return btnRegistrar;
    }

    public int getCreditoMinimo(){
        return creditoMinimo;
    }

    public int getCreditoMinimoBase(){
        return creditoMinimoBase;
    }

}







