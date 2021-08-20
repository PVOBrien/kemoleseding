# kemoleseding

This will be an app for my documentary [_Ke mo Leseding_](https://www.kemoleseding.com). I intend for it to - at the beginning - have a main page that has:
- the 4 modules of the film
- a "meet the dev" option
- a link to the website

Clicking on each module will go to details for the module include:
- runtime
- topics that are discussed within the film (and therefore should likely come up in the discussion of the film)
- supporting documents (__stretch__)

Next steps/stretch goals include:
- the actual movie file(s) available for download or streaming
- the ability to upload surveys and then have them processed to results (__BIG STRETCH__)

## The Why?

I have a film. It will be screened in <a href= "https://www.google.com/maps/place/Botswana/@-22.3223124,22.4436813,7z/data=!3m1!4b1!4m5!3m4!1s0x1ea44321d1452211:0xf1647c2a8715af7b!8m2!3d-22.328474!4d24.684866"  target= "_blank" >Botswana, Africa</a> and used as tool for HIV/AIDS awareness and education. Part of that is quantitative data input, but by my experience, data input IS A PAIN. At least for my data, I want it simple. Hopefully, I'll be able to incorporate either OCR for field input from uploaded photos (processed in AWS Lambda? idk), or from some library that can suss out specific boxes (filled in or not) and then fill in an entry that way. TBD.

## The how?

Jetpack Compose baby. And kotlin. Learning ahead, but narrowly scoped enough, that when going down rabbit holes I won't be so lost.

### 2021-08-19 Update

With help from [David Dicken](https://github.com/daviddicken) I was able to get a clickable picture (Modifier.clickable()). Next I wanted to use that icon to open a pdf via the user's choice of their PDF reader. Which has so far proven difficult. After bumbling around the web, the answers have slowly gravitated into the need to use and configure ContentProvider, and specifically its subclass FileProvider. Usually SO is __the place__ for these, but those results were passable at best, with very few questions, fewer answers, and with many of those using deprecated methods.
But they did keep talking about having to use URIs in concert with a ContentProvider, so they did connect me to blogs that seem to have a full walk through and explanation for _Providers. [This](https://getaround.tech/android-fileprovider/) and [this](https://infinum.com/the-capsized-eight/share-files-using-fileprovider), and [this](https://techenum.com/learn-how-to-use-fileprovider-in-android-with-example/) are the ones I finally settled on to follow, so far.