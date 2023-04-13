Feature: Nombre del país
  Yo como administrador de un servicio soap de busquda de nombres de paises
  Quiero poder ver los paises por su codigos
  Para validar la funcionalidad busqueda su código de pais

  @busquedaPaisCorrecta
  Scenario Outline: busqueda exitosa de pais
    Given el administrador quiere buscar un pais por el codigo internacional corresondiente
    When el administrador realiza la peticion de busqueda del pais con su <codigo>
    Then el administrador deberia ver el nombre del pais corresponiente al codigo proporcionado y un status <code>
    Examples:
      | codigo | code |
      | "CO"   | 200    |
      | "AR"   | 200  |


