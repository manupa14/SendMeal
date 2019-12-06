package com.example.sendmeal;

import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.sendmeal.Activities.HOME;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AltaPlatoEspressoTest {

    @Rule
    public ActivityTestRule<HOME> activityTestRule = new ActivityTestRule<>(HOME.class);

    @Test
    public void testAltaPlatoExito(){
        //Despliego el menu y accedo a crear el plato.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu1Opt2)).perform(ViewActions.click());

        //Verifico que los campos para cargar informacion y botones aparezcan en la pantalla.
        onView(withId(R.id.txtIdPlato)).check(matches(isDisplayed()));
        onView(withId(R.id.txtTituloItem)).check(matches(isDisplayed()));
        onView(withId(R.id.txtDescripcion)).check(matches(isDisplayed()));
        onView(withId(R.id.txtPrecioItem)).check(matches(isDisplayed()));
        onView(withId(R.id.txtCalorias)).check(matches(isDisplayed()));
        onView(withId(R.id.btnCamara)).check(matches(isDisplayed()));
        onView(withId(R.id.btnRegistrarPlato)).check(matches(isDisplayed()));

        //Completo los campos con la informacion del nuevo plato.
        onView(withId(R.id.txtIdPlato)).perform(ViewActions.typeText("1"));
        onView(withId(R.id.txtTituloItem)).perform(ViewActions.typeText("Pizza"));
        onView(withId(R.id.txtDescripcion)).perform(ViewActions.typeText("Mozzarella"));
        onView(withId(R.id.txtPrecioItem)).perform(ViewActions.typeText("250"));
        onView(withId(R.id.txtCalorias)).perform(ViewActions.typeText("500"));
        //ver como cargar la imagen.

        //Presiono el boton de registro.
        onView(withId(R.id.btnRegistrarPlato)).perform(ViewActions.click());

        //Ver que utilizar para verificar que el plato se guardo correctamente.
    }

    @Test
    public void testBuscarPlatoExito(){

        //Despliego el menu y accedo a crear un pedido, donde primero debo buscar un plato.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu1Opt4)).perform(ViewActions.click());

        //Verifico que la barra de busqueda aparezca en la pantalla.
        onView(withId(R.id.svBuscar)).check(matches(isDisplayed()));

        //Cargo nombre del plato a buscar.
        onView(withId(R.id.svBuscar)).perform(ViewActions.typeText("Pizza"));

        //Presiono el boton de busqueda.
        onView(withId(R.id.svBuscar)).perform(ViewActions.click());

        //Ver como validar que la busqueda tuvo exito.
    }

}
