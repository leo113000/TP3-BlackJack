# TP3-BlackJack

## Persistencia

- Se utilizó JDBC persistiendo con el Driver de MySQL. Solo se utilizó una única tabla para probar la correcta creación y persistencia de los datos.

## Desarrollo del juego

- Teniendo en cuenta los requisitos propuestos y los contenidos vistos para aplicar (concurrencia, patrón observador y persistencia) se optó por crear una implementación similar al juego de BlackJack.

 La partida estará divida en turnos. Cada jugador tendrá turnos individuales, en los cuales recibirá dos cartas. Si la suma de los valores de las cartas es inferior a 21, el jugador podrá retirarse o pedir más cartas (de a una) para llegar a 21, con el riesgo de pasarse. Si el jugador se retira, recupera el 50% de la apuesta, si gana se lleva el doble de la apuesta y si se pasa, no recupera lo apostado.
 El juego terminara de dos maneras posibles. Si los jugadores no tienen dinero para llegar a la apuesta requerida o si se acaba la baraja. En el caso que se acabe la baraja, el ganador es quién tiene más dinero en total, si hay empate, se respeta quien ingresó primero 

- El turno en la mesa para jugar es el recurso compartido, por lo que los jugadores serán los hilos que intenten acceder para jugar.
- La mesa también observa al Dealer quién es el que maneja la baraja, notifica las cartas que salen y cuando no quedan cartas.
- Los jugadores tienen un patrón Strategy aplicado para tener diferentes maneras de jugar.

**UML ADJUNTO**


