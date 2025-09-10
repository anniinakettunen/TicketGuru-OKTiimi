# TicketGuru-OKTiimi
OK-Tiimi

# Johdanto
Kehitettävä ohjelmisto on lippujenmyyntijärjestelmä, johon voi lisätä tapahtumia, määritellä niihin lipputyyppejä ja myydä lippuja tapahtumiin. Järjestelmä rekisteröi myös myytyjen lippujen tiedot tapahtumakohtaisesti. Järjestelmä kehitetään niin, että siihen voidaan jatkokehityksessä lisätä myös verkkokauppa, josta lipputoimiston asiakkaat voivat ostaa itse lippuja.
Asiakas on lipputoimisto, joka on tilannut lipunmyyntijärjestelmän myyntipisteeseensä. 
Projekti toteutetaan Scrum-mallin mukaisesti.

## Toteutus- ja toimintaympäristö
Ohjelmisto toteutetaan Javalla käyttäen Spring Boot -kehystä. 
Päätelaitteena on pääasiassa desktop, koska aluksi ohjelmaa käytetään vain lipunmyyntipisteessä. Jatkokehitystä varten ohjelmiston tulee olla laajennettavissa myös mobiilikäyttöön.

# Järjestelmän määrittely

## Käyttäjäryhmät

**Tapahtumanjärjestäjä**
- omien tapahtumien tietojen muokkaaminen
- omien tapahtumien lippujen määrien ja tyyppien muokkaaminen
**Myyjä**
- lippujen myyminen ja tulostaminen
- lippujen määrien ja hintojen tarkastelu

# Käyttäjätarinat
> **1.**
> Lipunmyyntitoimistona tahdon ominaisuuden myydä lippuja eri asiakasryhmille (mm. aikuiset & lapset)

> **2.**
> Lipunmyyntitoimistona tahdon ominaisuuden tulostaa myydyn lipun

>**3.**
> Lipunmyyntitoimistona tahdon ominaisuuden muokata tapahtumien tietoja (aika, paikka, kuvaus, kaupunki, lippujen määrä)

>**4.** 
> Lipunmyyntitoimistona tahdon ominaisuuden tarkastella myyntiraportteja tapahtumakohtaisesti

## Käyttöliittymä

![Kayttoliittymakuva](ticketguru/public/kayttoliittymakuva.jpg)
