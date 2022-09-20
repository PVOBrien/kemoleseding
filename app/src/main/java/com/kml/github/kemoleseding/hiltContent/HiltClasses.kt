package com.kml.github.kemoleseding.hiltContent

import javax.inject.Inject

class HiltClasses
@Inject
constructor(
    private val HCTwo: HCTwo
) {
    fun doAThing(): String { // not included in a constructor. is field injection
        return "Look I did a thing!"
    }

    fun doThatOtherThing(): String {
        return HCTwo.forConstructorFOne();
    }

}

class HCTwo
@Inject
constructor() {
    fun forConstructorFOne(): String {
        return "is in the constructor"
    }
}





// https://stackoverflow.com/questions/52586940/what-is-the-use-case-for-binds-vs-provides-annotation-in-dagger2
// https://medium.com/android-news/practical-guide-to-dagger-76398948a2ea
// https://zhuinden.medium.com/that-missing-guide-how-to-use-dagger2-ef116fbea97