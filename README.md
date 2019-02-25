# Doordash-lite



As Extra credits I added 
1) a pull to refresh feature for the discovery page, as I thought would be a usefull feature and a low hanging frouth 
2) an architrecture that would preserve the data for screen rotation, through android architecture components. which also helped me make sure the implementation of the layout looks fine in different screens.


*Known issues:** 
there is an error coming up during build that is a [Known Android issue](https://issuetracker.google.com/issues/124274577)
Unfortunatly, I did not have time to dig more to it and see whats causing it as it was not introducing any issues with the app (as far as i could tell)

```E/TypefaceCompatApi21Impl: java.lang.NoSuchMethodException
    java.lang.NoSuchMethodException: addFontWeightStyle [class java.lang.String, int, boolean]
        at java.lang.Class.getMethod(Class.java:2068)
        ...
E/TypefaceCompatApi21Impl:     at android.view.View.layout(View.java:20672)
        at android.view.ViewGroup.layout(ViewGroup.java:6194)
        at android.widget.FrameLayout.layoutChildren(FrameLayout.java:323)
        ...
```
