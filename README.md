## Gestor de Productos con JUnit

Objetivo: Aplicar pruebas unitarias con Junit 5 utilizando aserciones, suposiciones, ciclo de vida de los test, pruebas parametrizadas y Hamcrest, en el desarrollo de un sistema Java sencillo.

### Instrucciones:

1. Crea un proyecto llamado ProductManager
    - Este proyecto será un sistema que permita administrar productos: agregar, actualizar y eliminar productos desde una lista. 
2. Implementa las pruebas utilizando el ciclo RED-GREEN-REFACTOR. 
3. Funciones a implementar: 
    - Agregar un producto: 
         * Cada producto debe tener un nombre, una descripción y un precio. 
         * Debe almacenarse correctamente en una lista de productos. 
    - Actualizar un producto: 
         * Permitir modificar el nombre, descripción o precio. 
         * Validar que el producto exista antes de actualizarlo. 
    - Eliminar un producto: 
         * Permitir eliminar un producto por su identificador único. 
         * Verificar que exista antes de eliminarlo. 
4. Implementa pruebas unitarias que incluyan:
    - Aserciones tradicionales de JUnit. 
    - Aserciones con Hamcrest. 
    - Uso de assumeTrue() o assumingThat(). 
    - Pruebas parametrizadas. 
    - Anotaciones @BeforeEach y @AfterEach.

### Grupo 5

- Soledad Barrera
- Loreto Galvez
- Leonardo Rojas
- Álvaro Lupa