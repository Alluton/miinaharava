# Rakenne
[Rakenne](https://github.com/Alluton/miinaharava/blob/master/dokumentaatio/arkkitehtuuri.png)

Pakkaus minesweeper.domain sisältää sovelluslogiikan ja pakkaus minesweeper.ui sisältää JavaFX:llä toteutetun graafisen käyttöliittymän.

# Käyttöliittymä

Käyttöliittymä sisältää kolme osaa: 

1.Aloitus ruutu jossa käyttäjä syöttää peliruudukon koon ja miinojen määrän.

2.Peliruutu jossa peliä pelataan. Peliruutu sisältää peliruudukon lisäksi ajastimen, jäljellä olevien miinojen määrän sekä huipputulokset kyseessä olevilla peliruudukon parametreilla.

3.Voitto/häviö ruutu joka kertoo pelin tuloksen.

Käyttöliittymä rakennetaan minesweeper.ui.GraphicalUi luokassa. Peliruutu on ohjelman scene olio. Muut ruudut avautuvat peliruudun päälle alertteina. Käyttöliittymä on eriytetty sovelluslogiikasta ja se kutsuu ainoastaan minesweeper.domain.grid luokan metodeja.

# Sovelluslogiikka

[Datamalli](https://github.com/Alluton/miinaharava/blob/master/dokumentaatio/datamalli.jpg)

Sovelluksen loogisen datamallin muodostavat luokat grid ja tile.

# Tietojentallennus

Pakkausen minesweeper.dao luokka MinesweeperFileDao huolehtii tiedon tallentamisesta ja lukemisesta. Tieto tallenetaan juuressa sijaitsevaan results.txt tiedostoon. Jos tiedostoa ei ole olemassa se luodaan ensin.

# Pelin pelaaminen

[pelin kaavio](https://github.com/Alluton/miinaharava/blob/master/dokumentaatio/sekvenssikaavio.png)

