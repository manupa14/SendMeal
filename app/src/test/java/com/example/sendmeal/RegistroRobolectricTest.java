package com.example.sendmeal;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.sendmeal.Activities.Registro;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(RobolectricTestRunner.class)
public class RegistroRobolectricTest {

    private Registro activityRegistro;

    @Before
    public void cargarActivity(){
        activityRegistro = Robolectric.buildActivity(Registro.class).create().resume().get();
    }

    @Test
    public void testRegistroVendedorExitoso() throws Exception {
        assertNotNull(activityRegistro.getBtnRegistrar());

        EditText txtNombre = activityRegistro.findViewById(R.id.txtNombreRegistrar);
        EditText txtClave = activityRegistro.findViewById(R.id.txtClave);
        EditText txtRepetirClave = activityRegistro.findViewById(R.id.txtRepetirClave);
        EditText txtCorreo = activityRegistro.findViewById(R.id.txtCorreo);
        EditText txtNumeroTarjeta = activityRegistro.findViewById(R.id.txtNumeroTarjeta);
        EditText txtCcv = activityRegistro.findViewById(R.id.txtCCV);
        EditText txtVencimiento = activityRegistro.findViewById(R.id.txtVencimiento);
        SeekBar sbCredito = activityRegistro.findViewById(R.id.seekBarCreditoInicial);
        ToggleButton btnNotificaciones = activityRegistro.findViewById(R.id.BtnNotificaciones);
        Switch swEsVendedor = activityRegistro.findViewById(R.id.swVendedor);
        CheckBox chkTerminos = activityRegistro.findViewById(R.id.chkTerminos);
        EditText txtAliasCbu = activityRegistro.findViewById(R.id.txtAliasCbu);
        EditText txtCbu = activityRegistro.findViewById(R.id.txtCBU);
        Button btnRegistrar = activityRegistro.findViewById(R.id.btnRegistrarUsuario);
        TextView lblProgreso = activityRegistro.findViewById(R.id.lblProgresoCredito);
        RadioGroup rgTipoCuenta = activityRegistro.findViewById(R.id.rgTipoCuenta);
        int creditoMinimo = activityRegistro.getCreditoMinimo();
        int creditoMinimoBase = activityRegistro.getCreditoMinimoBase();

        //Cargo los datos para un registro exitoso.
        txtNombre.setText("Matias");
        txtClave.setText("admin");
        txtRepetirClave.setText("admin");
        txtCorreo.setText("asd@gmail");
        txtNumeroTarjeta.setText("123456789");
        txtCcv.setText("123");
        txtVencimiento.setText("12/2020");
        rgTipoCuenta.check(R.id.rbPremium);
        btnNotificaciones.setChecked(true);
        swEsVendedor.setChecked(true);
        txtAliasCbu.setText("ABCD1234");
        txtCbu.setText("1111111111111111111111");
        chkTerminos.setChecked(true);

        btnRegistrar.performClick();
        Thread.sleep(1500);
        Robolectric.flushForegroundThreadScheduler();

        //Verifico que los datos y los estados de los views se reinicien.
        assertEquals("", txtNombre.getText().toString());
        assertEquals("", txtClave.getText().toString());
        assertEquals("", txtRepetirClave.getText().toString());
        assertEquals("", txtCorreo.getText().toString());
        assertEquals("", txtNumeroTarjeta.getText().toString());
        assertEquals("", txtCcv.getText().toString());
        assertEquals("", txtVencimiento.getText().toString());

        assertEquals(R.id.rbBase, rgTipoCuenta.getCheckedRadioButtonId());

        assertEquals("0", lblProgreso.getText().toString());
        assertEquals(0, sbCredito.getProgress());

        assertFalse(btnNotificaciones.isChecked());

        assertFalse(swEsVendedor.isChecked());

        assertEquals("", txtAliasCbu.getText().toString());
        assertEquals("", txtCbu.getText().toString());

        assertFalse(chkTerminos.isChecked());

        assertEquals(creditoMinimoBase, creditoMinimo);

    }


}
