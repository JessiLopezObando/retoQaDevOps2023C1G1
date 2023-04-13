Feature: Crear productos
  Yo como administrador del sistema de gestion de productos
  Quiero poder crear productos
  Para poder agregar nuevos productos al sistema

  Scenario Outline: Crear un nuevo producto
    Given el administrador esta en la pagina FakerProducts seccion crear producto
    When el administrador crea un nuevo producto con <title>, <precio>, <descripcion>, <imagen>, <categoria>
    Then el administrador debe ver un mensaje con informacion del nuevo producto con un estatus <code>

    Examples:
      | title             | precio | descripcion                           | imagen                              | categoria     | code |
      | "Auriculares"     | 29.99  | "Auriculares inalámbricos"            | "https://i.pravatar.cc/300?img=104" | "Electrónica" | 200  |
      | "Camiseta básica" | 12.99  | "Camiseta unisex de algodón 100%"     | "https://i.pravatar.cc/300?img=20"  | "Moda"        | 200  |
      | "Libro de cocina" | 24.99  | "Libro de recetas de cocina italiana" | "https://i.pravatar.cc/300?img=332" | "Libros"      | 200  |