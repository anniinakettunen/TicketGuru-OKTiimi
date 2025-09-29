# TicketGuru
OK-Tiimi: Anniina, Erkka, Maria, Teppo, Thu, Ville

## Johdanto
Kehitettävä ohjelmisto on lippujenmyyntijärjestelmä, johon voi lisätä tapahtumia, määritellä niihin lipputyyppejä ja myydä lippuja tapahtumiin. Järjestelmä rekisteröi myös myytyjen lippujen tiedot tapahtumakohtaisesti. Järjestelmä kehitetään niin, että siihen voidaan jatkokehityksessä lisätä myös verkkokauppa, josta lipputoimiston asiakkaat voivat ostaa itse lippuja.
Asiakas on lipputoimisto, joka on tilannut lipunmyyntijärjestelmän myyntipisteeseensä. 
Projekti toteutetaan Scrum-mallin mukaisesti.

### Toteutus- ja toimintaympäristö
Ohjelmisto toteutetaan Javalla käyttäen Spring Boot -kehystä. 
Päätelaitteena on pääasiassa desktop, koska aluksi ohjelmaa käytetään vain lipunmyyntipisteessä. Jatkokehitystä varten ohjelmiston tulee olla laajennettavissa myös mobiilikäyttöön.

## Järjestelmän määrittely

### Käyttäjäryhmät

**Admin**
- tapahtumien tietojen muokkaaminen
- tapahtumien lippujen määrien muokkaaminen
- tapahtumien lipputyyppien muokkaaminen

**Lipunmyyjä**
- lippujen myyminen ja tulostaminen
- lippujen määrien ja hintojen tarkastelu

**Lipunostaja**

## Käyttäjätarinat
> **1.**
> Lipunmyyntitoimistona haluan myydä lippuja eri asiakasryhmille (esim. aikuiset ja lapset), jotta voin tarjota asiakkaille sopivat lippuvaihtoehdot ja hinnoittelun.

> **2.**
> Lipunmyyntitoimistona haluan luoda uuden tapahtuman järjestelmään (nimi, kuvaus, aika, paikka, kaupunki, lippujen määrä), jotta voin aloittaa lipunmyynnin ajoissa.

> **3.**
> Lipunmyyntitoimistona haluan tulostaa myydyn lipun, jotta voin antaa asiakkaalle fyysisen todisteen ostoksesta ja varmistaa pääsyn tapahtumaan.

>**4.**
> Lipunmyyntitoimistona haluan muokata tapahtumien tietoja (aika, paikka, kuvaus, kaupunki, lippujen määrä), jotta voin pitää tapahtumatiedot ajan tasalla ja varmistaa oikean tiedon asiakkaille.

>**5.** 
> Lipunmyyntitoimistona haluan tarkastella myyntiraportteja tapahtumakohtaisesti, jotta voin seurata lipunmyynnin kehitystä, kirjata myydyt lipputyypit ja niiden tuottamat tulot sekä tehdä parempia liiketoimintapäätöksiä.


>**6.** 
> Tapahtumanjärjestäjänä haluan luoda uuden tapahtuman järjestelmään (nimi, kuvaus, aika, paikka, kaupunki), jotta voin aloittaa lipunmyynnin ajoissa.



### Käyttöliittymä

![Kayttoliittymakuva](ticketguru/public/kayttoliittymakuva.jpg)
![Kayttoliittymakuva2](ticketguru/public/kayttoliityma2.jpg)

## Tietokantamalli

![Tietokanta](ticketguru/public/Tietokanta.jpg)

## Tietohakemistokuvaukset

![tk_kayttaja](ticketguru/public/tk_kayttaja.jpg)
![tk_rooli](ticketguru/public/tk_rooli.jpg)
![tk_lippu](ticketguru/public/tk_lippu.jpg)
![tk_lipputyypit](ticketguru/public/tk_lipputyypit.jpg)
![tk_lipunmyynti](ticketguru/public/tk_lipunmyynti.jpg)
![tk_tapahtumat](ticketguru/public/tk_tapahtumat.jpg)


## API 

### TAPAHTUMAT API

#### Base-URL:
http://localhost:8080/events/api

#### Huomio päivämärästä:

**Päivämäärä**: YYYY-MM-DD (esim. "2025-10-05")

**Päivämäärä ja aika**: YYYY-MM-DDTHH:MM:SS (esim. "2025-10-10T18:00:00")

#### Endpointit

1. Hae kaikki tapahtumat

    **Metodi**: GET

    **URL**: http://localhost:8080/events/api

    **Vastaus**: 200 OK

```json
[
  {
    "eventId": 1,
    "eventName": "Rock Concert",
    "eventLocation": "Arena A",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-05",
    "eventDescription": "Live concert",
    "maxNumberOfTickets": 500
  },
   {
        "eventId": 2,
        "eventName": "Jazz Sunday",
        "eventLocation": "Jazz Club",
        "eventCity": "Tampere",
        "eventDate": "2025-11-02",
        "eventDescription": "Smooth jazz evening",
        "maxNumberOfTickets": 150
    },
]
```
2. Hae tapahtuma ID:llä
    
    **Metodi**: GET

    **URL**: http://localhost:8080/events/api/{id}

    **Parametrit**: id = tapahtuman Id (Long)

    **Vastaus**: 200 OK

```json
{
  "eventId": 1,
  "eventName": "Rock Night",
  "eventLocation": "Arena",
  "eventCity": "Helsinki",
  "eventDate": "2025-10-05",
  "eventDescription": "Live rock music",
  "maxNumberOfTickets": 500
}
```
  **Virhe**: 404 Not Found 

3. Luo uusi tapahtuma
    
    **Metodi**: POST

    **URL**: http://localhost:8080/events/api

    **Request Body (JSON)**: 
```json
  {
    "eventName": "Lapsimessu",
    "eventLocation": "Messukeskus",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-30",
    "eventDescription": "Child fair",
    "maxNumberOfTickets": 1000
}

```
  **Vastaus**: 200 OK

```json
   {
    "eventId": 4,
    "eventName": "Lapsimessu",
    "eventLocation": "messukeskus",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-30",
    "eventDescription": "Child fair",
    "maxNumberOfTickets": 1000
  }   

  ```
4. Päivitä tapahtuma ID:llä 
    
    **Metodi**: PUT

    **URL**: http://localhost:8080/events/api/{id}

    **Parametrit**: id = tapahtuman Id (Long)

    **Request Body (JSON)**: 
```json
  {
    "eventId": 1,
    "eventName": "Rock Night",
    "eventLocation": "Päivitetty uusi arena",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-05",
    "eventDescription": "Live rock music",
    "maxNumberOfTickets": 500
}

```
  **Vastaus**: 200 OK

```json
   {
    "eventId": 1,
    "eventName": "Rock Night",
    "eventLocation": "Päivitetty uusi arena",
    "eventCity": "Helsinki",
    "eventDate": "2025-10-05",
    "eventDescription": "Live rock music",
    "maxNumberOfTickets": 500
}

  ```
**Virhe**: 404 Not Found 

5. Poista tapahtuma ID:llä
**Metodi**: DELETE

**URL**: http://localhost:8080/events/api/{id}

**Parametrit**: id = tapahtuman Id (Long)

**Vastaus**: 204 No Content

**Virhe**: 404 Not Found 

### LIPUNMYYNTI API

#### Base-URL:
http://localhost:8080/ticketsales/api

#### Huomio päivämärästä:

**Päivämäärä**: YYYY-MM-DD (esim. "2025-10-05")

**Päivämäärä ja aika**: YYYY-MM-DDTHH:MM:SS (esim. "2025-10-10T18:00:00")

#### Endpointit

1. Hae kaikki tapahtumat

    **Metodi**: GET

    **URL**: http://localhost:8080/ticketsales/api

    **Vastaus**: 200 OK

```json
[
    {
        "saleId": 1,
        "dateTime": "2025-10-10T18:00:00",
        "price": 60.00,
        "user": {
            "id": 1,
            "firstname": "Demo1",
            "lastname": "User1",
            "email": "demo1@example.com",
            "phone": "12345678",
            "role": {
                "roleId": 1,
                "roleName": "myyjä",
                "notes": null
            }
        }
    },
    {
        "saleId": 2,
        "dateTime": "2025-10-01T09:00:00",
        "price": 100.00,
        "user": {
            "id": 2,
            "firstname": "Demo2",
            "lastname": "User2",
            "email": "demo2@example.com",
            "phone": "87654321",
            "role": {
                "roleId": 1,
                "roleName": "myyjä",
                "notes": null
            }
        }
    }
]
```
2. Hae lipunmyynti ID:llä
    
    **Metodi**: GET

    **URL**: http://localhost:8080/ticketsales/api/{id}

    **Parametrit**: id = tapahtuman Id (Long)

    **Vastaus**: 200 OK

```json
{
    "saleId": 1,
    "dateTime": "2025-10-10T18:00:00",
    "price": 60.00,
    "user": {
        "id": 1,
        "firstname": "Demo1",
        "lastname": "User1",
        "email": "demo1@example.com",
        "phone": "12345678",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    }
}

```
  **Virhe**: 404 Not Found 

3. Luo uusi lipunmyynti
    
    **Metodi**: POST

    **URL**: http://localhost:8080/ticketsales/api

    **Request Body (JSON)**: 
```json
  {
  "dateTime": "2025-10-10T18:00:00",
  "price": 60.0,
  "user": {
    "id": 1
  }
}

```
  **Vastaus**: 200 OK

```json
   {
    "saleId": 1,
    "dateTime": "2025-10-10T18:00:00",
    "price": 60.0,
    "user": {
        "id": 1,
        "firstname": "Demo1",
        "lastname": "User1",
        "email": "demo1@example.com",
        "phone": "12345678",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    }
}
  ```
4. Päivitä lipunmyynti ID:llä 
    
    **Metodi**: PUT

    **URL**: http://localhost:8080/ticketsales/api/{id}

    **Parametrit**: id = tapahtuman Id (Long)

    **Request Body (JSON)**: 
```json
  {
    "saleId": 1,
    "dateTime": "2025-10-10T18:00:00",
    "price": 360.00,
    "user": {
        "id": 1,
        "firstname": "Demo1",
        "lastname": "User1",
        "email": "demo1@example.com",
        "phone": "12345678",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    }
}

```
  **Vastaus**: 200 OK

```json
   {
    "saleId": 1,
    "dateTime": "2025-10-10T18:00:00",
    "price": 360.00,
    "user": {
        "id": 1,
        "firstname": "Demo1",
        "lastname": "User1",
        "email": "demo1@example.com",
        "phone": "12345678",
        "role": {
            "roleId": 1,
            "roleName": "myyjä",
            "notes": null
        }
    }
}

  ```
**Virhe**: 404 Not Found 

5. Poista tapahtuma ID:llä
**Metodi**: DELETE

**URL**: http://localhost:8080/ticketsales/api/{id}

**Parametrit**: id = tapahtuman Id (Long)

**Vastaus**: 204 No Content

**Virhe**: 404 Not Found 



