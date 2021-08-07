# kemoleseding

This will be an app for my documentary [_Ke mo Leseding_](https://www.kemoleseding.com). I intend for it to - at the beginning - have a main page that has:
- the 4 modules of the film
- a "meet the dev" option
- a link to the website

Clicking on each module will go to details for the module include:
- runtime
- topics that are discussed within the film (and therefore should likely come up in the discussion of the film)
- supporting documents (__stretch__)

Additional next steps/stretch goals include:
- the actual movie file(s) available for download or streaming
- the ability to upload surveys and then have them processed to results (__BIG STRETCH__)

## The Why?

I have a film. It will be screened in <a href="https://www.google.com/maps/place/Botswana/@-22.3223124,22.4436813,7z/data=!3m1!4b1!4m5!3m4!1s0x1ea44321d1452211:0xf1647c2a8715af7b!8m2!3d-22.328474!4d24.684866" target="_blank">Botswana, Africa</a> and used as tool for HIV/AIDS awareness and education. Part of that is quantitative data input, but by my experience, data input IS A PAIN. At least for my data, I want it simple. Hopefully, I'll be able to incorporate either OCR for field input from uploaded photos (processed in AWS Lambda? idk), or from some library that can suss out specific boxes (filled in or not) and then fill in an entry that way. TBD.

## The how?

Jetpack Compose baby. And kotlin. Learning ahead, but narrowly scoped enough, that when going down rabbit holes I won't be so lost.
