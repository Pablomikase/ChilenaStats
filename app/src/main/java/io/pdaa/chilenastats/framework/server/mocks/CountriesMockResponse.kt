package io.pdaa.chilenastats.framework.server.mocks

object CountriesMockResponse {
    const val COUNTRIES_RESPONSE = """
        {
  "errors": [],
  "get": "api/v3/countries",
  "paging": {
    "current": 1,
    "total": 1
  },
  "parameters": [],
  "response": [
    {
      "code": "AL",
      "flag": "https://media.api-sports.io/flags/al.svg",
      "name": "Albania"
    },
    {
      "code": "DZ",
      "flag": "https://media.api-sports.io/flags/dz.svg",
      "name": "Algeria"
    },
    {
      "code": "AD",
      "flag": "https://media.api-sports.io/flags/ad.svg",
      "name": "Andorra"
    },
    {
      "code": "AO",
      "flag": "https://media.api-sports.io/flags/ao.svg",
      "name": "Angola"
    },
    {
      "code": "AR",
      "flag": "https://media.api-sports.io/flags/ar.svg",
      "name": "Argentina"
    },
    {
      "code": "AM",
      "flag": "https://media.api-sports.io/flags/am.svg",
      "name": "Armenia"
    },
    {
      "code": "AW",
      "flag": "https://media.api-sports.io/flags/aw.svg",
      "name": "Aruba"
    },
    {
      "code": "AU",
      "flag": "https://media.api-sports.io/flags/au.svg",
      "name": "Australia"
    },
    {
      "code": "AT",
      "flag": "https://media.api-sports.io/flags/at.svg",
      "name": "Austria"
    },
    {
      "code": "AZ",
      "flag": "https://media.api-sports.io/flags/az.svg",
      "name": "Azerbaidjan"
    },
    {
      "code": "BH",
      "flag": "https://media.api-sports.io/flags/bh.svg",
      "name": "Bahrain"
    },
    {
      "code": "BD",
      "flag": "https://media.api-sports.io/flags/bd.svg",
      "name": "Bangladesh"
    },
    {
      "code": "BB",
      "flag": "https://media.api-sports.io/flags/bb.svg",
      "name": "Barbados"
    },
    {
      "code": "BY",
      "flag": "https://media.api-sports.io/flags/by.svg",
      "name": "Belarus"
    },
    {
      "code": "BE",
      "flag": "https://media.api-sports.io/flags/be.svg",
      "name": "Belgium"
    },
    {
      "code": "BZ",
      "flag": "https://media.api-sports.io/flags/bz.svg",
      "name": "Belize"
    },
    {
      "code": "BJ",
      "flag": "https://media.api-sports.io/flags/bj.svg",
      "name": "Benin"
    },
    {
      "code": "BM",
      "flag": "https://media.api-sports.io/flags/bm.svg",
      "name": "Bermuda"
    },
    {
      "code": "BT",
      "flag": "https://media.api-sports.io/flags/bt.svg",
      "name": "Bhutan"
    },
    {
      "code": "BO",
      "flag": "https://media.api-sports.io/flags/bo.svg",
      "name": "Bolivia"
    },
    {
      "code": "BA",
      "flag": "https://media.api-sports.io/flags/ba.svg",
      "name": "Bosnia"
    },
    {
      "code": "BW",
      "flag": "https://media.api-sports.io/flags/bw.svg",
      "name": "Botswana"
    },
    {
      "code": "BR",
      "flag": "https://media.api-sports.io/flags/br.svg",
      "name": "Brazil"
    },
    {
      "code": "BG",
      "flag": "https://media.api-sports.io/flags/bg.svg",
      "name": "Bulgaria"
    },
    {
      "code": "BF",
      "flag": "https://media.api-sports.io/flags/bf.svg",
      "name": "Burkina-Faso"
    },
    {
      "code": "BI",
      "flag": "https://media.api-sports.io/flags/bi.svg",
      "name": "Burundi"
    },
    {
      "code": "KH",
      "flag": "https://media.api-sports.io/flags/kh.svg",
      "name": "Cambodia"
    },
    {
      "code": "CM",
      "flag": "https://media.api-sports.io/flags/cm.svg",
      "name": "Cameroon"
    },
    {
      "code": "CA",
      "flag": "https://media.api-sports.io/flags/ca.svg",
      "name": "Canada"
    },
    {
      "code": "CL",
      "flag": "https://media.api-sports.io/flags/cl.svg",
      "name": "Chile"
    },
    {
      "code": "CN",
      "flag": "https://media.api-sports.io/flags/cn.svg",
      "name": "China"
    },
    {
      "code": "TW",
      "flag": "https://media.api-sports.io/flags/tw.svg",
      "name": "Chinese-Taipei"
    },
    {
      "code": "CO",
      "flag": "https://media.api-sports.io/flags/co.svg",
      "name": "Colombia"
    },
    {
      "code": "CG",
      "flag": "https://media.api-sports.io/flags/cg.svg",
      "name": "Congo-DR"
    },
    {
      "code": "CR",
      "flag": "https://media.api-sports.io/flags/cr.svg",
      "name": "Costa-Rica"
    },
    {
      "code": "HR",
      "flag": "https://media.api-sports.io/flags/hr.svg",
      "name": "Croatia"
    },
    {
      "code": "CW",
      "flag": "https://media.api-sports.io/flags/cw.svg",
      "name": "Curacao"
    },
    {
      "code": "CY",
      "flag": "https://media.api-sports.io/flags/cy.svg",
      "name": "Cyprus"
    },
    {
      "code": "CZ",
      "flag": "https://media.api-sports.io/flags/cz.svg",
      "name": "Czech-Republic"
    },
    {
      "code": "DK",
      "flag": "https://media.api-sports.io/flags/dk.svg",
      "name": "Denmark"
    },
    {
      "code": "DO",
      "flag": "https://media.api-sports.io/flags/do.svg",
      "name": "Dominican-Republic"
    },
    {
      "code": "EC",
      "flag": "https://media.api-sports.io/flags/ec.svg",
      "name": "Ecuador"
    },
    {
      "code": "EG",
      "flag": "https://media.api-sports.io/flags/eg.svg",
      "name": "Egypt"
    },
    {
      "code": "SV",
      "flag": "https://media.api-sports.io/flags/sv.svg",
      "name": "El-Salvador"
    },
    {
      "code": "GB",
      "flag": "https://media.api-sports.io/flags/gb.svg",
      "name": "England"
    },
    {
      "code": "EE",
      "flag": "https://media.api-sports.io/flags/ee.svg",
      "name": "Estonia"
    },
    {
      "code": "ET",
      "flag": "https://media.api-sports.io/flags/et.svg",
      "name": "Ethiopia"
    },
    {
      "code": "FO",
      "flag": "https://media.api-sports.io/flags/fo.svg",
      "name": "Faroe-Islands"
    },
    {
      "code": "FJ",
      "flag": "https://media.api-sports.io/flags/fj.svg",
      "name": "Fiji"
    },
    {
      "code": "FI",
      "flag": "https://media.api-sports.io/flags/fi.svg",
      "name": "Finland"
    },
    {
      "code": "FR",
      "flag": "https://media.api-sports.io/flags/fr.svg",
      "name": "France"
    },
    {
      "code": "GE",
      "flag": "https://media.api-sports.io/flags/ge.svg",
      "name": "Georgia"
    },
    {
      "code": "DE",
      "flag": "https://media.api-sports.io/flags/de.svg",
      "name": "Germany"
    },
    {
      "code": "GH",
      "flag": "https://media.api-sports.io/flags/gh.svg",
      "name": "Ghana"
    },
    {
      "code": "GI",
      "flag": "https://media.api-sports.io/flags/gi.svg",
      "name": "Gibraltar"
    },
    {
      "code": "GR",
      "flag": "https://media.api-sports.io/flags/gr.svg",
      "name": "Greece"
    },
    {
      "code": "GP",
      "flag": "https://media.api-sports.io/flags/gp.svg",
      "name": "Guadeloupe"
    },
    {
      "code": "GT",
      "flag": "https://media.api-sports.io/flags/gt.svg",
      "name": "Guatemala"
    },
    {
      "code": "GN",
      "flag": "https://media.api-sports.io/flags/gn.svg",
      "name": "Guinea"
    },
    {
      "code": "HT",
      "flag": "https://media.api-sports.io/flags/ht.svg",
      "name": "Haiti"
    },
    {
      "code": "HN",
      "flag": "https://media.api-sports.io/flags/hn.svg",
      "name": "Honduras"
    },
    {
      "code": "HK",
      "flag": "https://media.api-sports.io/flags/hk.svg",
      "name": "Hong-Kong"
    },
    {
      "code": "HU",
      "flag": "https://media.api-sports.io/flags/hu.svg",
      "name": "Hungary"
    },
    {
      "code": "IS",
      "flag": "https://media.api-sports.io/flags/is.svg",
      "name": "Iceland"
    },
    {
      "code": "IN",
      "flag": "https://media.api-sports.io/flags/in.svg",
      "name": "India"
    },
    {
      "code": "ID",
      "flag": "https://media.api-sports.io/flags/id.svg",
      "name": "Indonesia"
    },
    {
      "code": "IR",
      "flag": "https://media.api-sports.io/flags/ir.svg",
      "name": "Iran"
    },
    {
      "code": "IQ",
      "flag": "https://media.api-sports.io/flags/iq.svg",
      "name": "Iraq"
    },
    {
      "code": "IE",
      "flag": "https://media.api-sports.io/flags/ie.svg",
      "name": "Ireland"
    },
    {
      "code": "IL",
      "flag": "https://media.api-sports.io/flags/il.svg",
      "name": "Israel"
    },
    {
      "code": "IT",
      "flag": "https://media.api-sports.io/flags/it.svg",
      "name": "Italy"
    },
    {
      "code": "CI",
      "flag": "https://media.api-sports.io/flags/ci.svg",
      "name": "Ivory-Coast"
    },
    {
      "code": "JM",
      "flag": "https://media.api-sports.io/flags/jm.svg",
      "name": "Jamaica"
    },
    {
      "code": "JP",
      "flag": "https://media.api-sports.io/flags/jp.svg",
      "name": "Japan"
    },
    {
      "code": "JO",
      "flag": "https://media.api-sports.io/flags/jo.svg",
      "name": "Jordan"
    },
    {
      "code": "KZ",
      "flag": "https://media.api-sports.io/flags/kz.svg",
      "name": "Kazakhstan"
    },
    {
      "code": "KE",
      "flag": "https://media.api-sports.io/flags/ke.svg",
      "name": "Kenya"
    },
    {
      "code": "XK",
      "flag": "https://media.api-sports.io/flags/xk.svg",
      "name": "Kosovo"
    },
    {
      "code": "KW",
      "flag": "https://media.api-sports.io/flags/kw.svg",
      "name": "Kuwait"
    },
    {
      "code": "KG",
      "flag": "https://media.api-sports.io/flags/kg.svg",
      "name": "Kyrgyzstan"
    },
    {
      "code": "LA",
      "flag": "https://media.api-sports.io/flags/la.svg",
      "name": "Laos"
    },
    {
      "code": "LV",
      "flag": "https://media.api-sports.io/flags/lv.svg",
      "name": "Latvia"
    },
    {
      "code": "LB",
      "flag": "https://media.api-sports.io/flags/lb.svg",
      "name": "Lebanon"
    },
    {
      "code": "LR",
      "flag": "https://media.api-sports.io/flags/lr.svg",
      "name": "Liberia"
    },
    {
      "code": "LY",
      "flag": "https://media.api-sports.io/flags/ly.svg",
      "name": "Libya"
    },
    {
      "code": "LI",
      "flag": "https://media.api-sports.io/flags/li.svg",
      "name": "Liechtenstein"
    },
    {
      "code": "LT",
      "flag": "https://media.api-sports.io/flags/lt.svg",
      "name": "Lithuania"
    },
    {
      "code": "LU",
      "flag": "https://media.api-sports.io/flags/lu.svg",
      "name": "Luxembourg"
    },
    {
      "code": "MK",
      "flag": "https://media.api-sports.io/flags/mk.svg",
      "name": "Macedonia"
    },
    {
      "code": "MW",
      "flag": "https://media.api-sports.io/flags/mw.svg",
      "name": "Malawi"
    },
    {
      "code": "MY",
      "flag": "https://media.api-sports.io/flags/my.svg",
      "name": "Malaysia"
    },
    {
      "code": "ML",
      "flag": "https://media.api-sports.io/flags/ml.svg",
      "name": "Mali"
    },
    {
      "code": "MT",
      "flag": "https://media.api-sports.io/flags/mt.svg",
      "name": "Malta"
    },
    {
      "code": "MR",
      "flag": "https://media.api-sports.io/flags/mr.svg",
      "name": "Mauritania"
    },
    {
      "code": "MU",
      "flag": "https://media.api-sports.io/flags/mu.svg",
      "name": "Mauritius"
    },
    {
      "code": "MX",
      "flag": "https://media.api-sports.io/flags/mx.svg",
      "name": "Mexico"
    },
    {
      "code": "MD",
      "flag": "https://media.api-sports.io/flags/md.svg",
      "name": "Moldova"
    },
    {
      "code": "MN",
      "flag": "https://media.api-sports.io/flags/mn.svg",
      "name": "Mongolia"
    },
    {
      "code": "ME",
      "flag": "https://media.api-sports.io/flags/me.svg",
      "name": "Montenegro"
    },
    {
      "code": "MA",
      "flag": "https://media.api-sports.io/flags/ma.svg",
      "name": "Morocco"
    },
    {
      "code": "MM",
      "flag": "https://media.api-sports.io/flags/mm.svg",
      "name": "Myanmar"
    },
    {
      "code": "NA",
      "flag": "https://media.api-sports.io/flags/na.svg",
      "name": "Namibia"
    },
    {
      "code": "NP",
      "flag": "https://media.api-sports.io/flags/np.svg",
      "name": "Nepal"
    },
    {
      "code": "NL",
      "flag": "https://media.api-sports.io/flags/nl.svg",
      "name": "Netherlands"
    },
    {
      "code": "NZ",
      "flag": "https://media.api-sports.io/flags/nz.svg",
      "name": "New-Zealand"
    },
    {
      "code": "NI",
      "flag": "https://media.api-sports.io/flags/ni.svg",
      "name": "Nicaragua"
    },
    {
      "code": "NG",
      "flag": "https://media.api-sports.io/flags/ng.svg",
      "name": "Nigeria"
    },
    {
      "code": "GB",
      "flag": "https://media.api-sports.io/flags/gb.svg",
      "name": "Northern-Ireland"
    },
    {
      "code": "NO",
      "flag": "https://media.api-sports.io/flags/no.svg",
      "name": "Norway"
    },
    {
      "code": "OM",
      "flag": "https://media.api-sports.io/flags/om.svg",
      "name": "Oman"
    },
    {
      "code": "PS",
      "flag": "https://media.api-sports.io/flags/ps.svg",
      "name": "Palestine"
    },
    {
      "code": "PA",
      "flag": "https://media.api-sports.io/flags/pa.svg",
      "name": "Panama"
    },
    {
      "code": "PY",
      "flag": "https://media.api-sports.io/flags/py.svg",
      "name": "Paraguay"
    },
    {
      "code": "PE",
      "flag": "https://media.api-sports.io/flags/pe.svg",
      "name": "Peru"
    },
    {
      "code": "PH",
      "flag": "https://media.api-sports.io/flags/ph.svg",
      "name": "Philippines"
    },
    {
      "code": "PL",
      "flag": "https://media.api-sports.io/flags/pl.svg",
      "name": "Poland"
    },
    {
      "code": "PT",
      "flag": "https://media.api-sports.io/flags/pt.svg",
      "name": "Portugal"
    },
    {
      "code": "QA",
      "flag": "https://media.api-sports.io/flags/qa.svg",
      "name": "Qatar"
    },
    {
      "code": "RO",
      "flag": "https://media.api-sports.io/flags/ro.svg",
      "name": "Romania"
    },
    {
      "code": "RU",
      "flag": "https://media.api-sports.io/flags/ru.svg",
      "name": "Russia"
    },
    {
      "code": "RW",
      "flag": "https://media.api-sports.io/flags/rw.svg",
      "name": "Rwanda"
    },
    {
      "code": "SM",
      "flag": "https://media.api-sports.io/flags/sm.svg",
      "name": "San-Marino"
    },
    {
      "code": "SA",
      "flag": "https://media.api-sports.io/flags/sa.svg",
      "name": "Saudi-Arabia"
    },
    {
      "code": "GB",
      "flag": "https://media.api-sports.io/flags/gb.svg",
      "name": "Scotland"
    },
    {
      "code": "SN",
      "flag": "https://media.api-sports.io/flags/sn.svg",
      "name": "Senegal"
    },
    {
      "code": "RS",
      "flag": "https://media.api-sports.io/flags/rs.svg",
      "name": "Serbia"
    },
    {
      "code": "SG",
      "flag": "https://media.api-sports.io/flags/sg.svg",
      "name": "Singapore"
    },
    {
      "code": "SK",
      "flag": "https://media.api-sports.io/flags/sk.svg",
      "name": "Slovakia"
    },
    {
      "code": "SI",
      "flag": "https://media.api-sports.io/flags/si.svg",
      "name": "Slovenia"
    },
    {
      "code": "ZA",
      "flag": "https://media.api-sports.io/flags/za.svg",
      "name": "South-Africa"
    },
    {
      "code": "KR",
      "flag": "https://media.api-sports.io/flags/kr.svg",
      "name": "South-Korea"
    },
    {
      "code": "ES",
      "flag": "https://media.api-sports.io/flags/es.svg",
      "name": "Spain"
    },
    {
      "code": "SD",
      "flag": "https://media.api-sports.io/flags/sd.svg",
      "name": "Sudan"
    },
    {
      "code": "SR",
      "flag": "https://media.api-sports.io/flags/sr.svg",
      "name": "Surinam"
    },
    {
      "code": "SE",
      "flag": "https://media.api-sports.io/flags/se.svg",
      "name": "Sweden"
    },
    {
      "code": "CH",
      "flag": "https://media.api-sports.io/flags/ch.svg",
      "name": "Switzerland"
    },
    {
      "code": "SY",
      "flag": "https://media.api-sports.io/flags/sy.svg",
      "name": "Syria"
    },
    {
      "code": "TJ",
      "flag": "https://media.api-sports.io/flags/tj.svg",
      "name": "Tajikistan"
    },
    {
      "code": "TZ",
      "flag": "https://media.api-sports.io/flags/tz.svg",
      "name": "Tanzania"
    },
    {
      "code": "TH",
      "flag": "https://media.api-sports.io/flags/th.svg",
      "name": "Thailand"
    },
    {
      "code": "TT",
      "flag": "https://media.api-sports.io/flags/tt.svg",
      "name": "Trinidad-And-Tobago"
    },
    {
      "code": "TN",
      "flag": "https://media.api-sports.io/flags/tn.svg",
      "name": "Tunisia"
    },
    {
      "code": "TR",
      "flag": "https://media.api-sports.io/flags/tr.svg",
      "name": "Turkey"
    },
    {
      "code": "TM",
      "flag": "https://media.api-sports.io/flags/tm.svg",
      "name": "Turkmenistan"
    },
    {
      "code": "UG",
      "flag": "https://media.api-sports.io/flags/ug.svg",
      "name": "Uganda"
    },
    {
      "code": "UA",
      "flag": "https://media.api-sports.io/flags/ua.svg",
      "name": "Ukraine"
    },
    {
      "code": "AE",
      "flag": "https://media.api-sports.io/flags/ae.svg",
      "name": "United-Arab-Emirates"
    },
    {
      "code": "UY",
      "flag": "https://media.api-sports.io/flags/uy.svg",
      "name": "Uruguay"
    },
    {
      "code": "US",
      "flag": "https://media.api-sports.io/flags/us.svg",
      "name": "USA"
    },
    {
      "code": "UZ",
      "flag": "https://media.api-sports.io/flags/uz.svg",
      "name": "Uzbekistan"
    },
    {
      "code": "VE",
      "flag": "https://media.api-sports.io/flags/ve.svg",
      "name": "Venezuela"
    },
    {
      "code": "VN",
      "flag": "https://media.api-sports.io/flags/vn.svg",
      "name": "Vietnam"
    },
    {
      "code": "GB",
      "flag": "https://media.api-sports.io/flags/gb.svg",
      "name": "Wales"
    },
    {
      "code": null,
      "flag": null,
      "name": "World"
    },
    {
      "code": "ZM",
      "flag": "https://media.api-sports.io/flags/zm.svg",
      "name": "Zambia"
    },
    {
      "code": "ZW",
      "flag": "https://media.api-sports.io/flags/zw.svg",
      "name": "Zimbabwe"
    }
  ],
  "results": 156
}
    """
}