# Turnausohjelma

Turnausohjelma antaa käyttäjän asettaa joukkueita lohkoihin sekä vaikuttaa lohko-otteluiden tuloksiin. Siitä joukkueet etenevät pudotuspeleihin, joiden tuloksia käyttäjä voi asettaa kunnes parhaat 4 ovat selvillä.

## Dokumentaatio

[Käyttöohje](https://github.com/StarSovu/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/StarSovu/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/StarSovu/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuuri](https://github.com/StarSovu/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

## Releaset

[Viikko 5](https://github.com/StarSovu/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/StarSovu/ot-harjoitustyo/releases/tag/viikko6)

[Loppupalautus](https://github.com/StarSovu/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _TurnausOhjelma-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/StarSovu/ot-harjoitustyo/blob/master/TurnausOhjelma/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

