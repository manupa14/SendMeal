package com.example.sendmeal;

import android.view.KeyEvent;

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
import static androidx.test.espresso.action.ViewActions.pressKey;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTest {

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

        //Completo los campos con la informacion del nuevo plato (para simplificar sin imagen).
        onView(withId(R.id.txtTituloItem)).perform(typeText("Probando.."));
        onView(withId(R.id.txtDescripcion)).perform(typeText("Probando.."));
        onView(withId(R.id.txtPrecioItem)).perform(typeText("1"));
        onView(withId(R.id.txtCalorias)).perform(typeText("2"));

        //Presiono el boton de registro.
        onView(withId(R.id.btnRegistrarPlato)).perform(ViewActions.click());

        //Verifico que se muestre el mensaje toast correspondiente al exito del regitro.
        onView(withText(R.string.datosGuardados))
                .inRoot(withDecorView(not(is(activityTestRule
                        .getActivity()
                        .getWindow()
                        .getDecorView()))))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testBuscarPlatoExito(){
        //Despliego el menu y accedo a crear un pedido, donde primero debo buscar un plato.
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.menu1Opt4)).perform(ViewActions.click());

        //Verifico que la barra de busqueda aparezca en la pantalla.
        onView(withId(R.id.svBuscar)).check(matches(isDisplayed()));

        //Cargo nombre del plato y presiono tecla de busqueda.
        onView(withId(R.id.svBuscar)).perform(typeText("Probando.."), pressKey(
                KeyEvent.KEYCODE_ENTER));

        //Verifico que el primer item de la lista resultante contenga el texto "Pizza".
        onView(withRecyclerView(R.id.rvBuscarPlatos).atPosition(0))
                .check(matches(hasDescendant(withText("Probando.."))));
    }

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

}
