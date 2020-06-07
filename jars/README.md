### Jak uruchomić projekt?

1. pobierz zawartość repozytorium.
2. aplikacja wykorzystuje technologię JavaFX, dlatego do uruchomienia aplikacji potrzebne 
będą odpowiednie moduły. Ze strony https://gluonhq.com/products/javafx/ w pierwszej sekcji LTS 
należy wybrać odpowienią paczkę JavaFX SDK i rozpakować. Do uruchomienia będziemy potrzebowali 
ścieżki do folderu lib z pobranej paczki.
3. w folderze z dostarczonym plikiem JAR aplikacji należy wykonać poniższe 
polecenie w celu uruchomienia aplikacji, podając ścieżkę do folderu lib
z paczki pobranej w poprzednim punkcie. 

```shell script
java --module-path /path/to/javafxsdk/lib --add-modules=javafx.controls -jar GameOfBugs.jar 
```