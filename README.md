# miinaharava
Miinaharava

Sovellus mahdollistaa miinharavan pelaamisen eri asetuksilla sekä aikaisempien tulosten kirjanpidon.

Dokumentaatio

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](dokumentaatio/Vaativuusmäärittely.md)

[tuntikirjanpito](dokumentaatio/tuntikirjanpito.odt)

[release linkki](https://github.com/Alluton/miinaharava/releases/tag/viikko5)

Komentorivitoiminnot

Testit suoritetaan komennolla

mvn test

Testikattavuusraportti luodaan komennolla

mvn jacoco:report

Suorittettava jari generoidaan komennolla

mvn package

Jari generoidaan hakemistoon target

Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset löytyvät tiedostosta target/site/checkstyle.html
