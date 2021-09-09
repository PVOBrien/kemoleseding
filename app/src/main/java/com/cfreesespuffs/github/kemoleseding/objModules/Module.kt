package com.cfreesespuffs.github.kemoleseding.objModules

import com.cfreesespuffs.github.kemoleseding.R

class Module(
    val title: String,
    val summary: String,
    val modPhoto: Int,
    val docList: List<docDetails>
)

data class docDetails(val docName: String, val docDescription: String, val picType: Int)

val modOne = Module(
    "HIV in Botswana Then and Now",
    "In the 1990s and early 2000s Botswana contended with epidemic levels of HIV and AIDS. It was a difficult time, with the prevalence rate hovering around 30% at the height of the epidemic. However, with the advent of ARVs, and improved understanding, Botswana worked to contain its spread. Key populations and risky activities were identified and people were informed of these, and the government took strides to protect vulnerable groups. Batswana made it happen, and over the course of the years HIV and AIDS is on the down trend.",
    R.drawable.mod_one_pic,
    listOf(docDetails("modanswerone", "Module 1 Answers", R.drawable.key))
)

val modTwo = Module(
    "HIV Prevention",
    "Stanley says, \"the best cure is prevention\", and it's true. Thankfully there are many methods of HIV prevention: male and female condoms, PrEP, PEP, abstinence, ARVs, and understanding HIV all form barriers between a body and HIV to reduce the chance of infection. It is important to use as many of these as possible to keep healthy and safe.",
    R.drawable.mod_two_pic,
    listOf(docDetails("modanswertwo", "Module 2 Answers", R.drawable.key))
)

val modThree = Module(
    "Living with HIV & Coming Out Publicly",
    "HIV is not a death sentence. ARVs have progressed to the point that \"U equals U\" meaning \"undetectable equals untransmittable\". Adhering to ARVs can mean a full and normal life. Stanley is just such a testament, being able to walk hundreds of kilometers despite once having had AIDS. ARVs are key to living with HIV, and Botswana has made ARVs free and available to anyone with a positive test.",
    R.drawable.mod_three_pic,
    listOf(docDetails("daysfriends", "Script", R.drawable.script), docDetails("modanswerthree", "Module 3 Answers", R.drawable.key))
)

val modFour = Module(
    "The Future of HIV in Botswana",
    "Botswana has come so far. The prevalence of HIV has dropped year over year, stigma and discrimination is decreasing, and understanding and awareness of HIV treatment continues to rise. All these are signs of great hope for all Batswana. It is because of them all - and HIV activists leading the way - that such positive steps are being made.",
    R.drawable.mod_four_pic,
    listOf(docDetails("modanswerfour", "Module 4 Answers", R.drawable.key))
)