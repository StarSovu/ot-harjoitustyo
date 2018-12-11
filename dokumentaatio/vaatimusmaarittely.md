# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen tarkoituksena on kirjata tuloksia turnauksisia lajeissa, joissa tehdään maaleja. Turnaukset ovat sitä muotoa, että aloitetaan lohkovaiheesta, ja lohkon parhaat etenevät pudotuspeleihin.

## Perusversion tarjoamat toiminnallisuudet

- Alussa valitaan joukkueita, jotka asetetaan samankokoisiin lohkoihin. Lohkojen määrän on oltava kahden potenssi.
- Käyttäjä asettaa tulokset lohko-otteluihin. Lohkossa kukin joukkue pelaa jokaista muuta lohkon joukkuetta vastaan kerran. Sovelluksen toiminnan kannalta ei ole merkitystä, missä järjestyksessä otteluiden tulokset kirjataan.
- Sovelluksesta pystyy katsomaan joukkuiden sijoitukset lohkossa (myös silloin, kun kaikkia tuloksia ei ole vielä ilmoitettu). Käyttäjä näkee joukkueen voitot, häviöt, tasapelit, tehdyt maalit, päästetyt maalit, maalieron ja pisteet. Pisteet määritetään siten, että voitosta saa kolme, tasapelistä yhden ja häviöstä nolla.
- Kun kaikkien lohko-otteluiden tulokset on kirjattu, sovellus asettaa joukkueen pudotuspelikaavioon.
- Pudotuspeleissä käyttäjä asettaa tulokset otteluihin, jolloin ottelun voittaja etenee seuraavalle kierrokselle, kunnes yksi voittaja ratkeaa. Myös välierissä hävinneet maat asetetaan pelaamaan pronssiottelussa.
- Pudotuspeliottelu voidaan saada jatkoajalle asettamalla ottelun tulokseksi tasapeli. Jos myös jatkoajan jälkeinen tilanne on tasan, siirrytään rangaistuspotkuihin.
  - Jatkoajan jälkeisessä tilanteessa kummallakin joukkueella on oltava vähintään yhtä monta maalia kuin varsinaisen peliajan jälkeen.

## Jatkokehitysideoita

- Otteluiden tulokset voidaan määrittää satunnaisesti. Kummankin joukkueen maalit voidaan määrittää käyttämällä geometrista jakaumaa, eli todennäköisyys tehdä vähintään yksi maali on sama kuin todennäköisyys tehdä vähintään kaksi maalia ehdolla, että tekee vähintään yhden maalin jne. Tämä todennäköisyys on oletuksena 0,5, mutta käyttäjä voi määrittää sen kummallekin joukkueelle.
- Alkuvaiheessa voidaan asettaa joukkueet koreihin, jossa korien määrä on lohkossa olevien joukkueiden määrä ja korissa olevien joukkueiden määrä on lohkojen määrä. Sovellus arpoo joukkueet lohkoihin siten, että kaikki samassa korissa olevat joukkueet päätyvät eri lohkoihin.
  - Käyttäjä pystyy valitsemaan tietyn korin 1 joukkueen, joka päätyy automaattisesti lohkoon 1 (kuten isäntämaa jalkapallon MM-kisoissa vuodesta 2006 lähtien).
- Joukkueet voidaan jakaa satunnaisesti lohkoihin myös ilman koreja.
- Käyttäjä pystyy itse määrittämään lohkojen määrän ja lohkossa olevien maiden määrän.
- Myöhemmässä versiossa käyttäjä pystyy valitsemaan myös muun verran lohkoja ja asettaa säännöt, joiden perusteella esim. lohkokolmoset voivat edetä jatkoon. Käyttäjä pystyy myös valitsemaan, että esimerkiksi lohkon neljä parasta tai vain yksi paras (kahden sijasta) etenee pudotuspeleihin.
- Käyttäjä pystyy itse määrittämään, millä perusteella joukkueet asetetaan pudotuspeleihin (tämä vaadittu, jos lohkosta päätyy joku muu määrä kuin 2 maata pudotuspeleihin).
- Käyttäjä pystyy määrittämään, missä järjestyksessä pisteet, maaliero, tehdyt/päästetyt maalit ja keskinäiset ottelut vaikuttavat joukkueiden sijoitukseen lohkossa.
- Tasapelit voidaan kieltää lohkovaiheessa, jolloin jo niissä on käytössä jatkoaikasääntö.
  - Oletuksena lohkoissa jatkoajan voitto tuo 2 pistettä ja häviö yhden.
- Käyttäjä pystyy asettamaan jatkoajat siten, että ne loppuvat heti, kun yksi maali tehdään. Tämä muuttaa satunnaisuuden toimintaa. Tällöin käyttäjä ei myöskään aseta tulosta jatkoajalle, vaan valitsee voittavan joukkueen tai sen, että siirrtään rangaistuskilpailuun.
- Käyttäjä voi vaihtaa voitoista, häviöistä ja tasapeleistä saatavat pisteet lohkovaiheessa.
- Käyttäjä voi halutessaan jättää lohkot kokonaan pois, jolloin siirrytään suoraan pudotuspeleihin ja joukkueita on oltava kahden potenssin verran.
- Jos pudotuspeleissä on neljä tai enemmän joukkuetta, käyttäjä voi päättää, pelataanko pronssiottelua.
- Pelaaja voi asettaa pudotuspelit siten, että jatkoon pääsy ei ratkea yhdestä ottelusta, vaan pelaamalla paras
- Pudotuspelit voidaan jättää kokonaan pois valitsemalla yksi lohko, josta vain yksi joukkue pääsee jatkoon.
- Jos lohkot on jätetty pois, voidaan käyttää erilaista korisysteemiä, jossa korissa olevien joukkueiden määrän on oltava kahden potenssi. Samassa korissa olevat joukkueet ovat mahdollisimman kaukana toisistaan, eli esimerkiksi jos yhdessä korissa on 8 joukkuetta, joukkue voi päätyä saman korin joukkuetta vastaan aikaisintaan puolivälierissä.
  - Tässä voidaan tehdä siten, että on vain yksi kori, joka huomioidaan joukkueiden arpomisessa ensin, ja tämän jälkeen arvotaan kaikki loput joukkueet.
- Käyttäjä pystyy tallentamaan näillä vaatimuksilla tehtyjä kilpailupohjia tietyillä nimillä. Jalkapallon MM-kisoissa 1998-2022 käytettävä on valmiiksi saatavana.
- Käyttäjä voi tallentaa keskeneräisiä ja valmiita turnauksia ja muokata niitä millä hetkellä tahansa.
