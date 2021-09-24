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
Further down the rabbit hole, now I'm running into URI issues; namely, under the hood, the files in assets are just data, not files; to get that happening, you either have to do everything in app to view whatever the asset is (pdf, bitmap, what-have-you), OR it seems you have to copy the file over to a storage location, and then it can be treated as whatever file it is through intent. [This](https://inthecheesefactory.com/blog/how-to-share-access-to-file-with-fileprovider-on-android-nougat/en) has the best and most straightforward code and example yet.

### 2021-08-22 Update

Finally, I came across this [SO article](https://stackoverflow.com/questions/4447477/how-to-copy-files-from-assets-folder-to-sdcard) that detailed FileProvider specifically in relation to updates to surrounding details re:updating to Kotlin.

### 2021-09-09 Update

Well, it's been a minute. I finally cracked the FileProvider work and all that, and it works. Now: Navigation. Slowly making my way through it, this [article](https://proandroiddev.com/bottom-navigation-and-navigation-drawer-using-scaffold-from-jetpack-compose-e2167440e7a9) is the foundation, be sure to use the "NavDrawerBottomNavScaffold" branch if you're using navcontroller.

### 2021-09-21

Got the Scaffold working, plus a splash screen.
Next on the docket: Settings, specifically switching between English and Setswana via a toggle. I'll want to talk through this, as it requires swapping between the two for everything, so I'll need to figure a way to... abstract? the languages/strings so I only have to store that a/b option in one place and it'll propagate/cascade down simply. Shall see.

