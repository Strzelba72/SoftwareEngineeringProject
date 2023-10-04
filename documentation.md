<h1><b>Gwont - specyfikacja wymagań</b></h1>

| Wersja      | Data utwprzenia | Data ost. modyfikacji | Autorzy                                                          |
| ----------- | --------------- | --------------------- | ---------------------------------------------------------------- |
| 2.0         | 20.04.2023      | 28.09.2023            | Kacper Ciążyński <br/> Mikołaj Strzelczyk <br/> Jordan Szymański |

<h1><b>1. Ogólne informacje</b></h1>

<h2><b>1.1 Opis systemu</b></h2>
<i>Gra polegająca na starciach dwóch graczy, używających skompletowanych talii kart. Każdy z graczy losuje karty ze swojej talii na początku rozgrywki, a jego zadaniem jest wygranie 2 rund z 3, poprzez uzyskanie w danej rundzie większej liczby punktów niż przeciwnik. Punkty uzyskuje się z</i>

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

<b>Talia</b>
<i>Obiekt reprezentujący zbiór kart w ręce gracza:</i>\
* Wielkośćć

<h1><b>4. Diagram kontekstu</b></h1>


<h1><b>5. Wymagania funkcjonalne</b></h1>
<b>WF01: Logowanie</b><br/>
<p>Aktorzy główni: Gracz <br/>
Poziom: Użytkownika <br/>
Wyzwalacze: Użytkownik po uruchomieniu aplikacji klika przycisk odpowiedzialny za logowanie. <br/>
Opis: Użytkownik po uruchomieniu aplikacji, musi zalogować się na swoje konto w celu korzystania ze wszystkich funkcjonalności aplikacji. <br/>
Warunki początkowe: Użytkownik wyraził chęć zalogowania się. <br/>
Warunki końcowe: Użytkownikowi udało pomyślnie się zalogować. <br/>
Scenariusz główny:
1. Użytkownik wybiera opcję zalogowania się.
2.  Wprowadza swoje dane logowania.
3. System sprawdza poprawność wprowadzonych danych.
4. Gracze zostaje zalogowany i przekierowany do menu gracza.
</p>
Wyjątki:<br/>
	1. Operacja logowania użytkownika się nie powiodła w wyniku wprowadzenia złych danych.<br/>
		1.1.  System informuje użytkownika /y o nie wprowadzeniu błędnych danych.<br/>
		1.2.  Powrót do kroku 2.<br/>
2. Operacja logowania użytkownika nie powiodła się w wyniku braku połączenia z serwerem.<br/>
	2.1.  System informuje użytkownika /y o nie niemożności nawiązania połączenia z serwerem.<br/>
	2.2.  Wyłączenie aplikacji.<br/>

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
Wyjątki:<br/>
	1. Operacja rejestracji konta użytkownika się nie powiodła w wyniku wprowadzenia złych danych.<br/>
		1.1.  System informuje użytkownika /y o nie wprowadzeniu błędnych danych.<br/>
		1.2.  Powrót do kroku 2.<br/>
2. Operacja logowania użytkownika nie powiodła się w wyniku braku połączenia z serwerem.<br/>
	2.1.  System informuje użytkownika /y o nie niemożności nawiązania połączenia z serwerem.<br/>
	2.2.  Wyłączenie aplikacji<br/>
WF03: Rozpoczynanie rozgrywki
Aktorzy główni :  Gracz
Poziom: Użytkownika
Wyzwalacze: Dwóch graczy chce dołączyć do rozgrywki.
Opis: Użytkownik próbuje dołączyć do rozgrywki z drugim graczem.
Warunki początkowe: W tym samym czasie co najmniej dwóch użytkowników musi szukać rozgrywki.
Warunki końcowe: Gracze zostali sparowani i przekierowani do wspólnej sesji rozgrywki.
Scenariusz główny: 
Gracz sygnalizuje chęć rozpoczęcia rozgrywki.
System sprawdza ilość dostępnych graczy o takim samym statusie.
System dobiera drugiego gracza, priorytetyzując graczy oczekujących najdłużej.
Gracze zostają przekierowani do wspólnej sesji rozgrywki.
Wyjątki:
	1.Operacja dobierania gracza się nie powiodła.
		1.1. System informuje gracza/y o nie możliwości zestawienia połączenia.
		1.2. Powrót gracza/y do menu.

WF04: Zakończenie sesji rozgrywki
Aktorzy główni :  Gracz
Poziom: Użytkownika
Wyzwalacze: Rozgrywka w sesji zostaje zakończona.
Opis: Rozgrywka pomiędzy graczami została zakończona.
Warunki początkowe: Jeden z graczy przegrał
Warunki końcowe: Gracze zostali przekierowani do menu gracza, a wynik rozgrywki został zapisany na serwerze..
Scenariusz główny: 
Sesja sygnalizuje koniec rozgrywki.
System informuje graczy o zakończeniu rozgrywki.
Gracze zostają przekierowani do menu gracza.
Wynik rozgrywki zostaje zapisany na serwerze.


6. Wymagania pozafunkcjonalne
 
   NFR1: Zabezpieczenia - hasła
System wymusza na użytkownikach stosowanie haseł o długości min. 6 znaków i wykorzystujących min. jedną wielką literę, jedną cyfrę i jeden znak specjalny. Hasła w bazie danych są przechowywane w sposób szyfrowany.
 
   NFR2: Charakterystyka czasowa
Średni czas odpowiedzi systemu na wykonaną akcję użytkownika (wyświetlenie listy z ogłoszeniami lub dodanie nowego ogłoszenia) ma być nie większy niż 10 sekundy, czas odpowiedzi systemu przy maksymalnym obciążeniu nie przekracza 20 sekund.
Ponadto dla 100 wykonanych równocześnie zapytań w systemie dotyczących wyświetlania strony z listą ogłoszeń, średni czas obsługi takiego zapytania powinien być nie dłuższy niż
15 sekund, natomiast maksymalny czas obsługi takiego zapytania to 30 sekund.
Powyższa charakterystyka czasowa powinna być spełniona przy następujących założeniach:
•      	Serwer dedykowany o następujących parametrach: procesor serwerowy Amd 1,8 GHz 16 Cores , pamięć 64GB DDR4 (4x16GB), dysk serwerowy NVME lub SAS o pojemności minimum 1 TB
•      	Połączenie internetowe użytkownika o przepustowości min. 512 Kb/s
•      	Dane czasowe zakładają użycie zasobów serwera tylko na potrzeby platformy, uruchomione inne usługi na serwerze mogą spowodować ograniczenie zasobów

Poniżej zaprezentowano standardowe wymagania pozafunkcjonalne, które mogą się przydać.
NFR01: System musi być wygodny w użyciu na ekranach urządzeń mobilnych o minimalnej szerokości 800 pikseli.
NFR02: System musi być zgodny z rozporządzeniem RODO w zakresie pseudonimizacji danych osobowych użytkowników systemu.
NFR03: Architektura systemu musi być gotowa na obsługę wielu rodzajów klientów
(np. aplikację mobilną) poprzez zastosowanie usług sieciowych według protokołu REST.
NFR04: System musi wspierać wyświetlanie oraz uzupełnianie tekstów w różnych językach (internacjonalizacja).
NFR05: Jednoznacznym, unikalnym identyfikatorem użytkowników w systemie jest adres e-mail.
NFR06: System musi być kompatybilny z następującymi systemem:
Android 7.0 lub wyższym

NFR07: System wymusza na użytkownikach stosowanie haseł o długości min. 6 znaków
i  wykorzystujących co najmniej jedną wielką literę, jedną cyfrę i jeden znak specjalny.

<h1><b>Harmonogram prac</b></h1>
<p>Praca nad projektem odbywała się przy wykorzystaniu zwinmnych metod zarządzania projektem. Pracowaliśmy w kilku sprintach, gdzie w każdym planowaliśmy, dodawaliśmy i testowaliśmy nowe funkcjonalności</p>

<p><b>Sprint 1:</b> <br/>
Data rozpoczęcia: 30.05.2023<br/>
Data zakończenia: 04.06.2023<br/>
Cel sprintu: Uruchomienie środowiska, stworzenie podstawowych layotów projektu oraz grafik<br/>
<br/>
<br/>
<br/>
</p>

<p><b>Sprint 2:</b> <br/>
Data rozpoczęcia: 30.05.2023<br/>
Data zakończenia: 04.06.2023<br/>
Cel sprintu: Dodanie funkcjonalności logowania i rejestracji, ustawienie nawikacji pomiędzy fragmentami<br/>
<br/>
<br/>
<br/>
</p>

<p><b>Sprint 3:</b> <br/>
Data rozpoczęcia: 30.05.2023<br/>
Data zakończenia: 04.06.2023<br/>
Cel sprintu: Zarządzanie przejściami i eventami poprzez wykorzystanie bindingów <br/>
<br/>
<br/>
<br/>
</p>

<p><b>Sprint 4:</b> <br/>
Data rozpoczęcia: 30.05.2023<br/>
Data zakończenia: 04.06.2023<br/>
Cel sprintu: Stworzenie rozgrywnki, poprawa błędów, optymalizacja rozgrywki<br/>
<br/>
<br/>
<br/>
</p>



