package cl.kibernumacademy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import cl.kibernumacademy.model.Product;
import cl.kibernumacademy.service.ProductManager;

public class ProductManagerTest {
    private ProductManager productManager;

    @BeforeEach
    void setUp() {
        // Se espera que exista una clase ContactManager
        productManager = new ProductManager();
    }

    @AfterEach
    public void tearDown() {
        productManager = null;
    }

    @Test
    void agregarProducto_debeAgregarUnProductoALaLista() {
        // Se espera que exista una clase Producto con un constructor que reciba nombre,
        // una descripción y un precio.
        Product producto = new Product(1, "Caja", "Caja dimensiones 50x50x20", 2000.0);

        // Se espera que la instancia pueda acceder al metodo agregarProducto y que
        // añada el producto en una lista interna
        productManager.agregarProducto(producto);

        // Aqui validamos que la lista tenga solo un elemento y que el primer elemento
        // tenga el nombre "Caja"
        // Assert
        assertNotNull(producto, "Product should not be null");
        assertThat(productManager.obtenerProductos(), hasSize(1));
        assertThat(productManager.obtenerProductos().get(0).getNombre(), is("Caja"));
    }

    @Test
    void actualizarProducto_debeActualizarNombreDescripcionOPrecioSiExiste() {
        // Arrange: agregamos un producto
        Product producto = new Product(1, "Caja", "Caja dimensiones 50x50x20", 2000.0);
        productManager.agregarProducto(producto);

        // Validamos que el producto existe antes de actualizar
        assertNotNull(producto, "Product should not be null");
        assertThat(productManager.obtenerProductos(), hasSize(1));
        assertThat(productManager.obtenerProductos().get(0).getNombre(), is("Caja"));

        // Act: actualizamos el producto
        boolean actualizado = productManager.actualizarProducto(1, "Caja grande", "Caja dimensiones 100x100x40",
                4000.0);
        assertThat(actualizado, is(true));

        // Validamos que los datos fueron actualizados correctamente
        assertThat(productManager.obtenerProductos().get(0).getNombre(), is("Caja grande"));
    }

    @Test
    void eliminarProducto_debeEliminarElProductoConIdDado() {
        // Se espera que exista una clase Producto con un constructor que reciba nombre,
        // una descripción y un precio.
        Product producto = new Product(1, "Caja", "Caja dimensiones 50x50x20", 2000.0);

        // Se espera que la instancia pueda acceder al metodo agregarProducto y que
        // añada el producto en una lista interna
        productManager.agregarProducto(producto);

        // Se espera que exista un método llamado eliminarProductoPorId, que elimine
        // producto
        productManager.eliminarProductoPorId(1);

        // Aqui validamos que la lista esté vacío
        assertThat(productManager.obtenerProductos(), hasSize(0));
    }

    @ParameterizedTest
    @ValueSource(strings = { "Ca", "Caja", "Laptop", "Tablet" })
    void buscarProductoPorNombre_debeRetornarProductosQueContienenLaCadena(String filtro) {
        // Arrange: agregamos productos a una lista
        productManager.agregarProducto(new Product(1, "Caja", "Caja dimensiones 50x50x20", 2000.0));
        productManager.agregarProducto(new Product(2, "Laptop", "asus x14", 500000.0));
        productManager.agregarProducto(new Product(3, "Tablet", "ipad 9", 350000.0));

        // Act: buscamos el producto por el nombre
        var resultado = productManager.buscarProductoPorNombre(filtro);

        // Validamos que el producto fue encontrado
        assertThat(resultado, notNullValue());
        assertTrue(resultado.stream().anyMatch(c -> c.getNombre().toLowerCase().contains(filtro.toLowerCase())));
    }

    @Test
    void actualizarProducto_debeActualizarProductoSoloSiHayProductos() {
        // Arrange
        Product producto = new Product(1, "Caja", "Caja dimensiones 50x50x20", 2000.0);
        // productManager.agregarProducto(producto);

        // Solo ejecuta el test si hay productos en la lista
        assumeTrue(productManager.obtenerProductos().size() > 0, "No hay productos, se omite el test");

        // Act
        boolean actualizado = productManager.actualizarProducto(1, "Caja grande", "Caja dimensiones 100x100x40",
                4000.0);

        // Assert
        assertThat(actualizado, is(true));
    }
}
