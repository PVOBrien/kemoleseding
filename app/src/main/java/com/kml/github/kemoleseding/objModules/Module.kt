package com.kml.github.kemoleseding.objModules

import com.kml.github.kemoleseding.R

class Module(
    val title: String,
    val summaryENG: String,
    val summarySET: String,
    val modPhoto: Int,
    val docList: List<DocDetails>,
    )

data class DocDetails(
    val docName: String,
    val docDescriptionENG: String,
    val picType: Int,
)

val modOne = Module(
    "HIV in Botswana Then and Now",
    "In the 1990s and early 2000s Botswana contended with epidemic levels of HIV and AIDS. It was a difficult time, with the prevalence rate hovering around 30% at the height of the epidemic. However, with the advent of ARVs, and improved understanding, Botswana worked to contain its spread. Key populations and risky activities were identified, and people were informed of these, and the government took strides to protect vulnerable groups. Batswana made it happen, and over the course of the years HIV and AIDS is on the down trend.",
    "Ka ngwaga wa 1990 le wa 2000 Botswana o ne lwantshana le segajaja sa mogare wa HIV le bolwetsi jwa AIDS.Go ne go le boima,dipalo di goroga kwa go masome a mararo mo lekgolong ka nako ya segajaja.Le fa go ntse jalo,go goroga ga di ARV le go tlhaloganya botoka, Botswana a bereka go laola kanamo ya one. Ba ba amegang le maitsholo a a diphatsa a ne a lemogiwa,batho ba itsisiwe le goromente a tsaya dikgato go sireletsa ba ba mo diphatseng. Mo tsamaong ya nako mogare wa HIV le bolwetse jwa AIDS  gone ga nna le kwelotlase ya dipalo.",
    R.drawable.mod_one_pic,
    listOf(
        DocDetails("modanswerone", "Answers", R.drawable.key),
        DocDetails("modonedisc", "Discussion\nQuestions", R.drawable.qpunc),
        DocDetails("modOneSubSETS.mp4", "Video", R.drawable.ic_baseline_play_circle_filled_24),
    )
)

val modTwo = Module(
    "HIV Prevention",
    "Stanley says, \"the best cure is prevention\", and it's true. Thankfully there are many methods of HIV prevention: male and female condoms, PrEP, PEP, abstinence, ARVs, VMMC and understanding HIV all form barriers between a body and HIV to reduce the chance of infection. It is important to use as many of these as possible to keep healthy and safe.",
    "Rre Stanley a re thibelo ke yone kalafi e tona ebile ke boammaruri. Go a lebosega gore go nale metlhale e mentsi ya thibelo mogare wa HIV; dikausu tsa bomme le borre, dipilisi tse di thibelang kanamo ya mogare pele o anama PrEP, dipilisi tse di thibelang kanamo ya mogare morago ga o amega PEP, go ikgapha mo go tsa tlhakanelo dikobo, dipilisi tsa ARV, go kgaola  letlalo la bonna VMMC ga mmogo  le go tlhaloganya mogare wa HIV. Tse tsotlhe di nna dikganedi gare ga mmele le mogare wa HIV go fokotsa go tsenwa ke mogare. Go botlhokwa go dirisa bontsi jwa tsone go nna le botsogo jo bo itekanetse le go sireletsega.",
    R.drawable.mod_two_pic,
    listOf(
        DocDetails("modanswertwo", "Answers", R.drawable.key),
        DocDetails("modqtwo", "Discussion\nQuestions", R.drawable.qpunc),
        DocDetails("modTwoSubSETS.mp4", "Video", R.drawable.ic_baseline_play_circle_filled_24)
    )
)

val modThree = Module(
    "Living with HIV & Coming Out Publicly",
    "HIV is not a death sentence. ARVs have progressed to the point that \"U equals U\" meaning \"undetectable equals untransmittable\". Adhering to ARVs can mean a full and normal life. Stanley is just such a testament, being able to walk hundreds of kilometers despite once having had AIDS. ARVs are key to living with HIV, and Botswana has made ARVs free and available to anyone with a positive test.",
    "Mogare wa HIV ga se katlholelo loso. Dipilisi tsa di ARV di tokafaditse seemo thata go supa fa mogare yo ritibetseng e le yo o sa anamisegeng mo ka sekgoa re reng ke “undetectable equals untransmittable”. Go sala morago melawana ya go tsaya dipilisi go raya gore o tshela botshelo fela jo bo siameng jaaka mongwe le mongwe. Rre Stanley ke mopaki wa se ka a kgonne go tsamaya dikhilomethara dile lekgolo ntle le gore o kile a tshela ka bolwetsi jwa AIDS. Di ARV ke konokono ya go tshela le mogare wa HIV, ke jaaka mo Botswana go rulagantswe gore pilisi ya ARV e nne teng go ka  fiwa motswana mongwe le mongwe yo o supileng fa ana le mogre ka nako ya  tlhatlhobo .Ditlamelo tsotlhe di abiwa ntle le tuelo epe.",
    R.drawable.mod_three_pic,
    listOf(
        DocDetails("daysfriends", "Script", R.drawable.script),
        DocDetails("modanswerthree", "Answers", R.drawable.key),
        DocDetails("modqthree", "Discussion\nQuestions", R.drawable.qpunc),
        DocDetails("modThreeSubSETS.mp4", "Video", R.drawable.ic_baseline_play_circle_filled_24)
    )
)

val modFour = Module(
    "The Future of HIV in Botswana",
    "Botswana has come so far. The prevalence of HIV has dropped year over year, stigma and discrimination is decreasing, and understanding and awareness of HIV treatment continues to rise. All these are signs of great hope for all Batswana. It is because of them all - and HIV activists leading the way - that such positive steps are being made.",
    "Botswana o tswa kgakala. Selekanyo sa go anama ga mogare wa HIV se fokotsega ngwaga le ngwaga, kgethololo ya ba ba nang le mogare le yone e a fokotsega,  dithuto le tsiboso ka go tsaya  dipilisi tsa HIV di ile magoletsa. Tse tsohle ke dikai tsa tsholofelo mo Batswaneng. Mme ke ka ntlha ya bone botlhe - bagaka ba rona ba ba re eteletseng pele - ke bone ba dirang pharologolo ee.",
    R.drawable.mod_four_pic,
    listOf(
        DocDetails("modanswerfour", "Answers", R.drawable.key),
        DocDetails("modqfour", "Discussion\nQuestions", R.drawable.qpunc),
        DocDetails("modFourSubSETS.mp4", "Video", R.drawable.ic_baseline_play_circle_filled_24)
    )
)