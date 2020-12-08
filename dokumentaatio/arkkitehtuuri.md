[Rakenne](https://github.com/Alluton/miinaharava/blob/master/dokumentaatio/arkkitehtuuri.png)

Huom minesweeper.dao ei vielä toteutettu.

Pakkaus minesweeper.domain sisältää sovelluslogiikan ja pakkaus minesweeper.ui sisältää JavaFX:llä toteutetun graafisen käyttöliittymän.

Käyttöliittymä

Käyttöliittymä sisältää kolme osaa: 

1.Aloitus ruutu jossa käyttäjä syöttää peliruudukon koon ja miinojen määrän.

2.Peliruutu jossa peliä pelataan.

3.Voitto/häviö ruutu joka kertoo pelin tuloksen sekä tilastoja ja sisältää mahdollisuuden aloittaa uusi peli.

Käyttöliittymä rakennetaan minesweeper.ui.GraphicalUi luokassa. Peliruutu on ohjelman scene olio. Muut ruudut avautuvat peliruudun päälle alertteina.

