# FranekTimePvP

![banner](assets/banner.png)

Ochrona nowych graczy przed biciem przez weteranów na serwerze (i w drugą stronę). Plugin dzieli graczy na dwie grupy według przegranego czasu na serwerze - ci poniżej ustalonego progu mogą walczyć tylko między sobą, a ci powyżej progu tylko ze sobą.

Domyślnie próg jest ustawiony na 20 godzin (`72000` sekund). Dzięki temu świeży gracz po wejściu na serwer nie zostanie od razu zabity przez kogoś kto gra od tygodnia.

## Komendy

| Komenda | Co robi |
|---|---|
| `/timepvp reload` | przeładowuje `config.json` bez restartu serwera (wymaga `franek.timepvp.admin` lub `op`) |

## Config

`plugins/FranekTimePvP/config.json`, tworzy się sam przy pierwszym starcie:

```json
{
  "options": {
    "pvpTimeSeconds": 72000,
    "cantAttackMessage": "&cNie mozesz atakowac graczy ktorzy maja mniej/wiecej przegranego czasu od ciebie!"
  }
}
```

Wiadomość obsługuje zwykłe kolory `&` oraz kolory HEX (`#RRGGBB`). Czas liczony jest z wbudowanych statystyk Minecrafta (`PLAY_ONE_MINUTE`).

## Instalacja

Jar z folderu [`releases/`](releases/) wrzucasz do `plugins/` na serwerze (Spigot / Paper) i restart.

## Budowanie

```bash
mvn package
```

Wychodzi `target/FranekTimePvP-1.0.jar`. Wymaga JDK 16+ i Mavena.

## Licencja

Wszelkie prawa zastrzeżone. Autor: franekk ([GitHub](https://github.com/Blejziak)).
