package io.pdaa.chilenastats.framework.server.mocks

object LeaguesMockResponse {

    const val LEAGUES_RESPONSE = """
        {
  "get": "leagues",
  "parameters": {
    "code": "it"
  },
  "errors": [],
  "results": 19,
  "paging": {
    "current": 1,
    "total": 1
  },
  "response": [
    {
      "league": {
        "id": 135,
        "name": "Serie A",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/135.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2010,
          "start": "2010-08-28",
          "end": "2011-05-22",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2011,
          "start": "2011-09-09",
          "end": "2012-05-13",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2012,
          "start": "2012-08-25",
          "end": "2013-05-19",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2013,
          "start": "2013-08-24",
          "end": "2014-05-18",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2014,
          "start": "2014-08-30",
          "end": "2015-05-31",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2015,
          "start": "2015-08-22",
          "end": "2016-05-15",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2016,
          "start": "2016-08-20",
          "end": "2017-05-28",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2017,
          "start": "2017-08-19",
          "end": "2018-05-20",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2018,
          "start": "2018-08-18",
          "end": "2019-05-26",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2019,
          "start": "2019-08-24",
          "end": "2020-08-02",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2020,
          "start": "2020-09-19",
          "end": "2021-05-23",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": true,
            "predictions": true,
            "odds": true
          }
        }
      ]
    },
    {
      "league": {
        "id": 547,
        "name": "Super Cup",
        "type": "Cup",
        "logo": "https://media.api-sports.io/football/leagues/547.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2014,
          "start": "2014-12-22",
          "end": "2014-12-22",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2015,
          "start": "2015-08-08",
          "end": "2015-08-08",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2016,
          "start": "2016-12-23",
          "end": "2016-12-23",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2018,
          "start": "2019-01-16",
          "end": "2019-01-16",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2019,
          "start": "2019-12-22",
          "end": "2019-12-22",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2020,
          "start": "2021-01-20",
          "end": "2021-01-20",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 136,
        "name": "Serie B",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/136.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2016,
          "start": "2016-08-26",
          "end": "2017-06-08",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2017,
          "start": "2017-08-25",
          "end": "2018-06-16",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2018,
          "start": "2018-08-24",
          "end": "2019-05-11",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": false
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2019,
          "start": "2019-08-23",
          "end": "2020-08-20",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2020,
          "start": "2020-09-25",
          "end": "2021-05-10",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": true,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": true
          }
        }
      ]
    },
    {
      "league": {
        "id": 137,
        "name": "Coppa Italia",
        "type": "Cup",
        "logo": "https://media.api-sports.io/football/leagues/137.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2016,
          "start": "2016-08-05",
          "end": "2017-05-17",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2017,
          "start": "2017-07-29",
          "end": "2018-05-09",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2018,
          "start": "2018-07-28",
          "end": "2019-05-15",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2019,
          "start": "2019-08-03",
          "end": "2020-06-17",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2020,
          "start": "2020-09-22",
          "end": "2021-05-19",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": true,
              "statistics_players": true
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 138,
        "name": "Serie C",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/138.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2016,
          "start": "2016-08-27",
          "end": "2017-05-07",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2017,
          "start": "2017-08-26",
          "end": "2018-05-06",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2018,
          "start": "2018-09-16",
          "end": "2019-06-15",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2019,
          "start": "2019-08-24",
          "end": "2020-07-22",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2020,
          "start": "2020-09-26",
          "end": "2021-05-02",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": true
          }
        }
      ]
    },
    {
      "league": {
        "id": 139,
        "name": "Serie A Women",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/139.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2016,
          "start": "2016-10-01",
          "end": "2017-05-20",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": false,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2017,
          "start": "2017-09-30",
          "end": "2018-06-16",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": false,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2018,
          "start": "2018-09-21",
          "end": "2019-04-20",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2019,
          "start": "2019-09-14",
          "end": "2020-05-16",
          "current": false,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        },
        {
          "year": 2020,
          "start": "2020-08-22",
          "end": "2021-05-23",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": true,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": true,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": true
          }
        }
      ]
    },
    {
      "league": {
        "id": 434,
        "name": "Serie D - Girone I",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/434.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 433,
        "name": "Serie D - Girone H",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/433.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 432,
        "name": "Serie D - Girone G",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/432.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 431,
        "name": "Serie D - Girone F",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/431.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 429,
        "name": "Serie D - Girone D",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/429.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 428,
        "name": "Serie D - Girone C",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/428.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 427,
        "name": "Serie D - Girone B",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/427.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 426,
        "name": "Serie D - Girone A",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/426.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 430,
        "name": "Serie D - Girone E",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/430.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2019,
          "start": "2019-09-01",
          "end": "2020-05-03",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": true,
            "top_scorers": true,
            "top_assists": true,
            "top_cards": true,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 817,
        "name": "Super Cup Primavera",
        "type": "Cup",
        "logo": "https://media.api-sports.io/football/leagues/817.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2020,
          "start": "2021-01-21",
          "end": "2021-01-21",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": false,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": false
          }
        }
      ]
    },
    {
      "league": {
        "id": 706,
        "name": "Campionato Primavera - 2",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/706.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2020,
          "start": "2020-10-03",
          "end": "2021-05-29",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": false,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": true
          }
        }
      ]
    },
    {
      "league": {
        "id": 705,
        "name": "Campionato Primavera - 1",
        "type": "League",
        "logo": "https://media.api-sports.io/football/leagues/705.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2020,
          "start": "2020-09-18",
          "end": "2021-06-16",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": true
          }
        }
      ]
    },
    {
      "league": {
        "id": 704,
        "name": "Coppa Italia Primavera",
        "type": "Cup",
        "logo": "https://media.api-sports.io/football/leagues/704.png"
      },
      "country": {
        "name": "Italy",
        "code": "IT",
        "flag": "https://media.api-sports.io/flags/it.svg"
      },
      "seasons": [
        {
          "year": 2020,
          "start": "2020-09-23",
          "end": "2021-04-28",
          "current": true,
          "coverage": {
            "fixtures": {
              "events": true,
              "lineups": false,
              "statistics_fixtures": false,
              "statistics_players": false
            },
            "standings": false,
            "players": false,
            "top_scorers": false,
            "top_assists": false,
            "top_cards": false,
            "injuries": false,
            "predictions": true,
            "odds": true
          }
        }
      ]
    }
  ]
}
    """

}