# RPNCalc
starter for Android calculator

This includes the .jar file and my version of RPNCalcGUIHelper. You may replace it with yours if you want.

I recompiled the backend .jar with the java setting for Java 8 instead of 11 to avoid a compiler error. Android studio uses an internal copy of version 8 by default.

Like the default "Hello, world" app, this runs as is - displays 45 by calling addKey with: 2 ^ 3 + 4 ^ 5 + * and putting the returned string into a text view.
