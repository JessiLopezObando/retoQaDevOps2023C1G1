Feature: Lista de Juegos
  Como usuario de la pagina Freetogame
  quiero conocer la lista de todos los juegos
  para poder elegir un juego

  @ListaJuegos
  Scenario Outline: Obtener todos los juegos
    Given El jugador se encuentra dentro de la pagina adecuada para realizar la consulta
    When El jugador realiza la consulta consulta del  juego por <id>
    Then El jugador recibe un estadtus <code> con el juego encontrado
    Examples:
      | id  | code |
      | 453 | 200    |
      | 453 | 200  |