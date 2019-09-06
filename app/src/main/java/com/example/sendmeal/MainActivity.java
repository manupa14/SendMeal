package com.example.sendmeal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Switch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Character.isLetter;

public class MainActivity extends AppCompatActivity {

    private EditText Nombre;
    private EditText Clave;
    private EditText RepetirClave;
    private EditText Correo;
    private EditText NumeroTarjeta;
    private EditText CCV;
    private EditText Vencimiento;
    private RadioButton Base;
    private RadioButton Premium;
    private RadioButton Full;
    private SeekBar Credito;
    private Button Notificaciones;
    private Switch EsVendedor;
    private CheckBox Terminos;
    private EditText AliasCBU;
    private EditText CBU;
    private LinearLayout Vendedor;
    private Button Registrar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Nombre = (EditText)findViewById(R.id.txtNombreRegistrar);
        Clave = (EditText) findViewById(R.id.txtClave);
        RepetirClave = (EditText) findViewById(R.id.txtRepetirClave);
        Correo = (EditText) findViewById(R.id.txtCorreo);
        NumeroTarjeta = (EditText) findViewById(R.id.txtNumeroTarjeta);
        CCV = (EditText) findViewById(R.id.txtCCV);
        Vencimiento = (EditText) findViewById(R.id.txtVencimiento);
        Base = (RadioButton) findViewById(R.id.rbBase);
        Premium = (RadioButton) findViewById(R.id.rbPremium);
        Full = (RadioButton) findViewById(R.id.rbFull);
        Credito = (SeekBar) findViewById(R.id.seekBarCreditoInicial);
        Notificaciones = (Button) findViewById(R.id.BtnNotificaciones);
        EsVendedor = (Switch) findViewById(R.id.swVendedor);
        Terminos = (CheckBox) findViewById(R.id.chkTerminos);
        AliasCBU = (EditText) findViewById(R.id.txtAliasCbu);
        CBU = (EditText) findViewById(R.id.txtCBU);
        Vendedor = (LinearLayout) findViewById(R.id.hiddenLayout);
        Registrar = (Button) findViewById(R.id.btnRegistrar);


        EsVendedor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Vendedor.setVisibility(View.VISIBLE);
                }else{
                    Vendedor.setVisibility(View.GONE);
                }
            }
        });


        Registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //validar campos obligatorios vacios()
                //validar restricciones de campos()

            }
        });

       }

    private Boolean validarVacio(){

        Boolean resultado = Correo.getText().length()>0 && Clave.getText().length()>0 && RepetirClave.getText().length()>0 && NumeroTarjeta.getText().length()>0 && (Base.isChecked() || Premium.isChecked() || Full.isChecked()) && Terminos.isChecked();

        if(resultado && EsVendedor.isChecked()){
            resultado = AliasCBU.getText().length()>0 && CBU.getText().length()>0;
        }

        return resultado;
}

private Boolean coincidenClaves(){

        return (Clave.getText().equals(RepetirClave.getText()));

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
      //hola

}


















}







