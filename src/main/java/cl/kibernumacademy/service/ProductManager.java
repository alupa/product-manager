package cl.kibernumacademy.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import cl.kibernumacademy.model.Product;

public class ProductManager {
    private List<Product> productos = new ArrayList<>();

    public void agregarProducto(Product producto) {
        productos.add(producto);
    }

    // GREEN
    // public List<Product> obtenerProductos() {
    //     return productos;
    // }

    // REFACTOR
    public List<Product> obtenerProductos() {
        return List.copyOf(productos); // Devuelve una copia inmutable de la lista de productos
    }

    // GREEN
    // public boolean actualizarProducto(int id, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio) {
    //     for (Product producto : productos) {
    //         if (producto.getId() == id) {
    //             producto.setNombre(nuevoNombre);
    //             producto.setDescripcion(nuevaDescripcion);
    //             producto.setPrecio(nuevoPrecio);
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    // REFACTOR
    public boolean actualizarProducto(int id, String nuevoNombre, String nuevaDescripcion, double nuevoPrecio) {
        return findProductById(id)
            .map(producto -> {
                producto.setNombre(nuevoNombre);
                producto.setDescripcion(nuevaDescripcion);
                producto.setPrecio(nuevoPrecio);
                return true;
            })
            .orElse(false);
    }

    private Optional<Product> findProductById(int id) {
        return productos.stream()
            .filter(producto -> producto.getId() == id)
            .findFirst();
    }

    // GREEN
    // public void eliminarProductoPorId(int id) {
    //     Iterator<Product> iterador = productos.iterator();
    //     while (iterador.hasNext()) {
    //         Product producto = iterador.next();
    //         if (producto.getId() == id) {
    //             iterador.remove();
    //             break;
    //         }
    //     }
    // }

    // REFACTOR
    public void eliminarProductoPorId(int id) {
        productos.removeIf(producto -> producto.getId() == id);
    }

    // GREEN
    // public List<Product> buscarProductoPorNombre(String filtro) {
    //     List<Product> resultadoBusqueda = new ArrayList<>();

    //     for (Product producto : productos) {
    //         if (producto.getNombre().contains(filtro)) {
    //             resultadoBusqueda.add(producto);
    //         }
    //     }
    //     return resultadoBusqueda;
    // }

    // REFACTOR
    public List<Product> buscarProductoPorNombre(String filtro) {
        return productos.stream()
                .filter(producto -> producto.getNombre().toLowerCase().contains(filtro.toLowerCase()))
                .collect(Collectors.toList());
    }
} 