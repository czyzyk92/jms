# Opis aplikacji

### Aplikacja udostępnia 3 endpointy:

    1. /savePage
    2. /produce
    3. /receive

Do każdego endpointu można podać parametr w postaci url stronu

Przykład:

    http://localhost:8080/savePage?url=https://bluemedia.pl/
    
Endpoint savePage zapisze do bazy stronę o podanym w parametrze adresie

    http://localhost:8080/produce?msg=https://bluemedia.pl/
    
Endpoint produce pobierze z bazy stronę o podanym adresie oraz doda ją do kolejki

    http://localhost:8080/receive?url=https://bluemedia.pl/#
    
Endpoint receive pobierze stronę z kolejki i wyświetli ją od razu w przeglądarce


### Uruchomienie aplikacji

Broker ActiveMq uruchamiamy za pomocą dockera 

    sudo docker run -p 61616:61616 -p 8161:8161 -t webcenter/activemq
    
Skrypt tworzący bazę danych znajduje się w:

    resources/static/sql/jms.sql
    
Dane do połączenia z brokerem i bazą danych znajdują się w application.properties