<h1><b>Gwont - specyfikacja wymagań</b></h1>

| Wersja      | Data utwprzenia | Data ost. modyfikacji | Autorzy                                                          |
| ----------- | --------------- | --------------------- | ---------------------------------------------------------------- |
| 2.0         | 20.04.2023      | 28.09.2023            | Kacper Ciążyński <br/> Mikołaj Strzelczyk <br/> Jordan Szymański |

<h1><b>1. Ogólne informacje</b></h1>

<h2><b>1.1 Opis systemu</b></h2>
<i>Gra polegająca na starciach dwóch graczy w grze, w wojnę, używających rozlosowanych talii kart. Gra odbywa się turowo, na początku każdej tury graczy wybiera karte do wyłożenia, po czym drugi gracz robi to samo. Obaj gracze nie widzą kart w swoich taliach, anie tych wyłożonych na środek, do czasu zakończenia swoich ruchów</i>

<h2><b>1.2 Odbiorcy docelowi</b></h2>
<i>Użytkownicy smartphonów opartych na systemie Android.</i>

<h2><b>1.3 Oczekiwane korzyści biznesowe</b></h2>
<i>Atrakcyjny dodatek do portfolio oraz poznanie nowych technologii i metodyk.</i>

<h1><b>2. Aktorzy</b></h1>

<b>Gracz (AKT_PLAYER)</b><i> - użytkownik korzystający z aplikacji. Może jedynie zarządzać swoim kontem i brać udział w rozgrywce.</i>
<b>Administrator (AKT_ADMIN)</b><i> - zarządza zawartością aplikacji. Może wprowadzać dowolne zmiany we wszystkich modułach.</i>

<h1><b>3. Obiekty biznesowe</b></h1>
<i>Opis i cechy obiektów, które funkcjonują w ramach systemu, na przykład: </i>

<b>Karta</b><br/>
<i>Obiekt reprezentujący jednostkę :</i></br>
* Punkty

<b>Talia</b><br/>
<i>Obiekt reprezentujący zbiór kart w ręce gracza:</i>\
* Wielkośćć

<h1><b>4. Diagram kontekstu</b></h1>
![image](https://github.com/Strzelba72/SoftwareEngineeringProject/assets/115944998/d8eecd5c-323d-4a1e-8f1a-c842414799fa)



<h1><b>5. Wymagania funkcjonalne</b></h1>
<b>WF01: Logowanie</b><br/>
<p>Aktorzy główni: Gracz <br/>
Poziom: Użytkownika <br/>
Wyzwalacze: Użytkownik po uruchomieniu aplikacji klika przycisk odpowiedzialny za logowanie. <br/>
Opis: Użytkownik po uruchomieniu aplikacji, musi zalogować się na swoje konto w celu korzystania ze wszystkich funkcjonalności aplikacji. <br/>
Warunki początkowe: Użytkownik wyraził chęć zalogowania się. <br/>
Warunki końcowe: Użytkownikowi udało pomyślnie się zalogować. <br/>
Scenariusz główny:<pre>
 1.Użytkownik wybiera opcję zalogowania się.
 2.Wprowadza swoje dane logowania.
 3.System sprawdza poprawność wprowadzonych danych.
 4.Gracze zostaje zalogowany i przekierowany do menu gracza.</pre>
</p>
Wyjątki:<br/>
<pre>
 1. Operacja logowania użytkownika się nie powiodła w wyniku wprowadzenia złych danych.
  1.1.  System informuje użytkownika /y o nie wprowadzeniu błędnych danych.
  1.2.  Powrót do kroku 2.
 2. Operacja logowania użytkownika nie powiodła się w wyniku braku połączenia z serwerem.
  2.1.  System informuje użytkownika /y o nie niemożności nawiązania połączenia z serwerem.
  2.2.  Wyłączenie aplikacji.</pre><br/>

<p><b>WF02: Rejestrowanie konta</b><br/>
Aktorzy główni: Gracz<br/>
Poziom: Użytkownika<br/>
Wyzwalacze: Użytkownik wyraża chęć zarejestrowania konta.<br/>
Opis: Użytkownik po uruchomieniu aplikacji, wyraża chęć zarejestrowania swojego konta w celu korzystania ze wszystkich funkcjonalności aplikacji.<br/>
Warunki początkowe: Użytkownik wyraził chęć zarejestrowania się.<br/>
Warunki końcowe: Użytkownikowi udało się pomyślnie zarejestrować konto.<br/>
Scenariusz główny: <br/>
Użytkownik wybiera opcję zarejestrowania się.<br/>
Wprowadza swoje dane logowania - email, login, hasło.<br/>
System sprawdza poprawność i unikalność wprowadzonych danych.<br/>
Konto zostaje utworzone.<br/>
Użytkownik zostaje poinformowany o utworzeniu konta i przekierowany do panelu logowania.</p>
Wyjątki:<br/><pre>
1. Operacja rejestracji konta użytkownika się nie powiodła w wyniku wprowadzenia złych danych.
 1.1.  System informuje użytkownika /y o nie wprowadzeniu błędnych danych.
 1.2.  Powrót do kroku 2.
2. Operacja logowania użytkownika nie powiodła się w wyniku braku połączenia z serwerem.
 2.1.  System informuje użytkownika /y o nie niemożności nawiązania połączenia z serwerem.
 2.2.  Wyłączenie aplikacji</pre><br/>
<p><b>WF03: Rozpoczynanie rozgrywki</b><br/>
Aktorzy główni: Gracz<br/>
Poziom: Użytkownika<br/>
Wyzwalacze: Dwóch graczy chce dołączyć do rozgrywki.<br/>
Opis: Użytkownik próbuje dołączyć do rozgrywki z drugim graczem.<br/>
Warunki początkowe: W tym samym czasie co najmniej dwóch użytkowników musi szukać rozgrywki.<br/>
Warunki końcowe: Gracze zostali sparowani i przekierowani do wspólnej sesji rozgrywki.<br/>
Scenariusz główny: </p><pre>
1.Gracz sygnalizuje chęć rozpoczęcia rozgrywki.
2.System sprawdza ilość dostępnych graczy o takim samym statusie.
3.System dobiera drugiego gracza, priorytetyzując graczy oczekujących najdłużej.
4.Gracze zostają przekierowani do wspólnej sesji rozgrywki.</pre><br/>
Wyjątki:<pre>
1.Operacja dobierania gracza się nie powiodła.
 1.1. System informuje gracza/y o nie możliwości zestawienia połączenia.
 1.2. Powrót gracza/y do menu.</pre><br/>

<p><b>WF04: Zakończenie sesji rozgrywki</b><br/>
Aktorzy główni: Gracz<br/>
Poziom: Użytkownika<br/>
Wyzwalacze: Rozgrywka w sesji zostaje zakończona.<br/>
Opis: Rozgrywka pomiędzy graczami została zakończona.<br/>
Warunki początkowe: Jeden z graczy przegrał<br/>
Warunki końcowe: Gracze zostali przekierowani do menu gracza, a wynik rozgrywki został zapisany na serwerze.<br/>
Scenariusz główny: <br/><pre>
1.Sesja sygnalizuje koniec rozgrywki.
2.System informuje graczy o zakończeniu rozgrywki.
3.Gracze zostają przekierowani do menu gracza.
4.Wynik rozgrywki zostaje zapisany na serwerze.</pre>

<h1><b>6. Wymagania pozafunkcjonalne</b></h1>
 
<p><b>NFR1: Zabezpieczenia - hasła</b></p>
System wymusza na użytkownikach stosowanie haseł o długości min. 6 znaków i wykorzystujących min. jedną wielką literę, jedną cyfrę i jeden znak specjalny. Hasła w bazie danych są przechowywane w sposób szyfrowany.
 
<p><b>NFR2: Charakterystyka czasowa</b></p>
Średni czas odpowiedzi systemu na wykonaną akcję użytkownika (wyświetlenie listy z ogłoszeniami lub dodanie nowego ogłoszenia) ma być nie większy niż 10 sekundy, czas odpowiedzi systemu przy maksymalnym obciążeniu nie przekracza 20 sekund.
Ponadto dla 100 wykonanych równocześnie zapytań w systemie dotyczących wyświetlania strony z listą ogłoszeń, średni czas obsługi takiego zapytania powinien być nie dłuższy niż
15 sekund, natomiast maksymalny czas obsługi takiego zapytania to 30 sekund.
Powyższa charakterystyka czasowa powinna być spełniona przy następujących założeniach:
* Serwer dedykowany o następujących parametrach: procesor serwerowy Amd 1,8 GHz 16 Cores , pamięć 64GB DDR4 (4x16GB), dysk serwerowy NVME lub SAS o pojemności minimum 1 TB
* Połączenie internetowe użytkownika o przepustowości min. 512 Kb/s
* Dane czasowe zakładają użycie zasobów serwera tylko na potrzeby platformy, uruchomione inne usługi na serwerze mogą spowodować ograniczenie zasobów

Poniżej zaprezentowano standardowe wymagania pozafunkcjonalne, które mogą się przydać.
<p><b>NFR01: System musi być wygodny w użyciu na ekranach urządzeń mobilnych o minimalnej szerokości 800 pikseli.</b></p>
<p><b>NFR02: System musi być zgodny z rozporządzeniem RODO w zakresie pseudonimizacji danych osobowych użytkowników systemu.</b></p>
<p><b>NFR03: Architektura systemu musi być gotowa na obsługę wielu rodzajów klientów
(np. aplikację mobilną) poprzez zastosowanie usług sieciowych według protokołu REST.</b></p>
<p><b>NFR04: System musi wspierać wyświetlanie oraz uzupełnianie tekstów w różnych językach (internacjonalizacja).</b></p>
<p><b>NFR05: Jednoznacznym, unikalnym identyfikatorem użytkowników w systemie jest adres e-mail.</b></p>
<p><b>NFR06: System musi być kompatybilny z następującymi systemem: Android 7.0 lub wyższym</b></p>

<p><b>NFR07: System wymusza na użytkownikach stosowanie haseł o długości min. 6 znaków
i  wykorzystujących co najmniej jedną wielką literę, jedną cyfrę i jeden znak specjalny. </b></p>

<h1><b>Harmonogram prac</b></h1>
<p>Praca nad projektem odbywała się przy wykorzystaniu zwinnych metod zarządzania projektem. Pracowaliśmy w kilku sprintach, gdzie w każdym planowaliśmy, dodawaliśmy i testowaliśmy nowe funkcjonalności. Przed każdym spotkaniem odbywało się krótkie daily, na którym omawialiśmy co zrobiliśmy, co robimy i co będziemy robić</p>

<p><b>Sprint 1:</b> <br/>
Data rozpoczęcia: 29.05.2023<br/>
Data zakończenia: 02.06.2023<br/>
Cel sprintu: Uruchomienie środowiska, stworzenie podstawowych layotów projektu oraz grafik <br/>
<b>Uruchomienie środowiska</b><br/>
Osoba odpowiedzialna: Mikołaj Strzelczyk, Jordan Szymański<br/>
Szacowany czas wykonania: 10h<br/>
Priorytet: wysoki <br/>

<b>Utworzenie podstawowych layoutów projektu</b><br/>
Osoba odpowiedzialna: Kacper Ciążyński, Mikołaj Strzelczyk, Jordan Szymański<br/>
Szacowany czas wykonania: 12h<br/>
Priorytet: wysoki <br/>

<b>Stworzenie grafik</b><br/>
Osoba odpowiedzialna: Kacper Ciążyński<br/>
Szacowany czas wykonania: 10h<br/>
Priorytet: średni <br/>
</p>

<p><b>Sprint 2:</b> <br/>
Data rozpoczęcia: 12.06.2023<br/>
Data zakończenia: 16.06.2023<br/>
Cel sprintu: Dodanie funkcjonalności logowania i rejestracji, ustawienie nawigacji pomiędzy fragmentami<br/>
<b>Dodanie funkcjonalności logowania i rejestracji</b><br/>
Osoba odpowiedzialna: Jordan Szymański, Kacper Ciążyński<br/>
Szacowany czas wykonania: 14h<br/>
Priorytet: średni <br/>

<b>Ustawienie nawigacji pomiędzy fragmentami</b><br/>
Osoba odpowiedzialna: Mikołaj Strzelczyk<br/>
Szacowany czas wykonania: 8h<br/>
Priorytet: wysoki <br/>
</p>

<p><b>Sprint 3:</b> <br/>
Data rozpoczęcia: 18.09.2023<br/>
Data zakończenia: 22.09.2023<br/>
Cel sprintu: Zarządzanie przejściami i eventami poprzez wykorzystanie bindingów.<br/>

<b>Zarządzanie przejściami i eventami poprzez wykorzystanie bindingów</b><br/>
Osoba odpowiedzialna: Mikołaj Strzelczyk, Jordan Szymański<br/>
Szacowany czas wykonania: 18h<br/>
Priorytet: wysoki <br/>
</p>

<p><b>Sprint 4:</b> <br/>
Data rozpoczęcia: 25.09.2023<br/>
Data zakończenia: 29.09.2023<br/>
Cel sprintu: Stworzenie mechanik rozgrywnki, poprawa błędów, optymalizacja rozgrywki, uzupełnienie dokumentacji<br/>

<b>Stworzenie mechanik rozgrywnki</b><br/>
Osoba odpowiedzialna: Jordan Szymański<br/>
Szacowany czas wykonania: 8h<br/>
Priorytet: wysoki <br/>

<b>Poprawa błędów</b><br/>
Osoba odpowiedzialna: Mikołaj Strzelczyk, Jordan Szymański, Kacper Ciążyński<br/>
Szacowany czas wykonania: 12h<br/>
Priorytet: wysoki <br/>

<b>Optymalizacja rozgrywki</b><br/>
Osoba odpowiedzialna: Mikołaj Strzelczyk<br/>
Szacowany czas wykonania: 8h<br/>
Priorytet: średni <br/>

<b>Uzupełnienie dokumentacji</b><br/>
Osoba odpowiedzialna: Kacper Ciążyński<br/>
Szacowany czas wykonania: 8h<br/>
Priorytet: niski <br/>
</p>



