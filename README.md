# Miinaharava
Miinaharava

Sovellus mahdollistaa miinaharavan pelaamisen eri asetuksilla sekä aikaisempien tulosten ylläpidon.

# Dokumentaatio

[Arkkitehtuurikuvaus](dokumentaatio/arkkitehtuuri.md)

[Vaatimusmäärittely](dokumentaatio/Vaatimusmäärittely.md)

[Käyttöohje](dokumentaatio/Käyttöohje.md)

[Tuntikirjanpito](https://github.com/Alluton/miinaharava/blob/master/dokumentaatio/miinaharava%20tuntikirjanpito%20-%20Sheet1.pdf)

[Release linkki](https://github.com/Alluton/miinaharava/releases/latest)

[Testaus](dokumentaatio/Testaus.md)

# Komentorivitoiminnot

Testit suoritetaan komennolla

mvn test

Testikattavuusraportti luodaan komennolla

mvn jacoco:report

Suorittettava jari generoidaan komennolla

mvn package

Jari generoidaan hakemistoon target

Tiedostoon checkstyle.xml määritellyt tarkistukset suoritetaan komennolla mvn jxr:jxr checkstyle:checkstyle

Mahdolliset virheilmoitukset löytyvät tiedostosta target/site/checkstyle.html

Javadoc generoidaan komennolla

mvn javadoc:javadoc
