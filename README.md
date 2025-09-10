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


# Käyttäjäroolit & Käyttäjätarinat

## Lipunmyyjä

## Asiakas
Roolissani asiakas, haluan tarkastella tulevia tapahtumia itsenäisesti internetistä sekä nähdä tapahtumien lipputyypit ja hinnat.

Roolissani asiakas, haluan ongelmatilanteissa helpon väylän saada yhteyden lipputoimiston asiakaspalveluun.

Roolissani asiakas, haluan tarkastella omia varauksiani sekä hallinnoida näitä.

Roolissani asiakas, haluan hakea tapahtumia eri hakusuodattimilla, jotta löydän oikean alueen sekä ajan tapahtumat omien kriteerieni mukaisesti.

Roolissani asiakas, tahdon ladata lippuni sähköisesti laitteelleni kätevästi nettisivulta.

Roolissani asiakas, tahdon saada sähköpostivahvistuksen maksutapahtumistani.

## Tapahtumanjärjestäjä
Roolissani tapahtumanjärjestäjä, tahdon reaaliaikaisesti seurata tapahtumani lipunmyyntiä.

Roolissani tapahtumanjärjestäjä, tahdon kasvattaa tai laskea lippukapasiteettia lipunmyyntipalvelussa mahdollisten muutoksien myötä tapahtumaa järjestäessä.

Roolissani tapahtumanjärjestäjä, tahdon lisätä eri lipputyyppejä sekä hinnoittelumalleja (mm. early bird- sekä VIP-liput)

+ Toimisto voi lisätä tapahtumat, joihin myydään lippuja
+ Lipunmyyjä myy lipun
+ Lipunmyyjä tulostaa lipun
+ Myymättä jääneet liput tulostetaan
+ Lipuissa on koodi, jolla lippu voidaan ovella merkitä käytetyksi
+ Verkkokauppa, jossa asiakkaat voivat ostaa lippuja

## Käyttöliittymä

![Kayttoliittymakuva](ticketguru/public/kayttoliittymakuva.jpg)
