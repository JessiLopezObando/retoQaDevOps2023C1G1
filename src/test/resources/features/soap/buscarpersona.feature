Feature: Datos de la persona
  Yo como administrador de un servicio soap de busquda de datos de personas por su codigo
  Quiero poder ver los datos de una persona
  Para validar la funcionalidad busqueda


  Scenario: busqueda exitosa de persona
    Given el administrador quiere buscar una persona
    When el administrador realiza la peticion de busqueda de la persona por su codigo
    Then el administrador deberia ver llos datos de la persona asociada al codigo

