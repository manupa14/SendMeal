package com.example.sendmeal;

import android.text.Editable;
import android.widget.EditText;

import com.example.sendmeal.Activities.AltaPlato;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AltaPlatoTest {

    private AltaPlato activityAltaPlato;

    @Mock private EditText txtIdPlatoMock;
    @Mock private EditText txtTituloMock;
    @Mock private EditText txtDescripcionMock;
    @Mock private EditText txtPrecioMock;
    @Mock private EditText txtCaloriasMock;
    @Mock private Editable edtMock;

    @Test
    public void testValidarVacio(){

        activityAltaPlato = new AltaPlato();

        doReturn(1).when(edtMock).length();

        doReturn(edtMock).when(txtIdPlatoMock).getText();
        doReturn(edtMock).when(txtTituloMock).getText();
        doReturn(edtMock).when(txtDescripcionMock).getText();
        doReturn(edtMock).when(txtPrecioMock).getText();
        doReturn(edtMock).when(txtCaloriasMock).getText();

        activityAltaPlato.setTxtIdPlato(txtIdPlatoMock);
        activityAltaPlato.setTxtTitulo(txtTituloMock);
        activityAltaPlato.setTxtDescripcion(txtDescripcionMock);
        activityAltaPlato.setTxtPrecio(txtPrecioMock);
        activityAltaPlato.setTxtCalorias(txtCaloriasMock);

        assertTrue(activityAltaPlato.validarVacio());
    }









}
