Miinaharava

Sovellus mahdollistaa miinharavan pelaamisen sekä aikaisempien tulosten kirjanpidon.

Dokumentaatio

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

[Vaativuusmäärittely](dokumentaatio/vaativuusmäärittely.md)

Komentorivitoiminnot

Testit suoritetaan komennolla

mvn test

Testikattavuusraportti luodaan komennolla

mvn jacoco:report

Suorittettava jari generoidaan komennolla

mvn package

jJari generoidaan hakemistoon target

Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset löytyvät tiedostosta target/site/checkstyle.html
