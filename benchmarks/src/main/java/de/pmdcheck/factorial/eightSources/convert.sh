rm util/Comparator.java
rm util/Iterator.java
rm util/Spliterator.java
rm util/zip -rf
rm util/concurrent -rf
rm util/jar -rf
rm util/prefs -rf
rm util/logging -rf
rm util/Calendar.java
rm util/Date.java
rm util/Formatter.java
rm util/Formattable.java
rm util/TimeZone.java
rm util/SimpleTimeZone.java
rm util/ListResourceBundle.java
rm util/GregorianCalendar.java
rm util/Locale.java
rm util/Currency.java
rm util/JapaneseImperialCalendar.java
find . -name "*.java" | xargs sed -i "s/package java.util/package de.pmdcheck.factorial.eightSources.util/" $1
find . -name "*.java" | xargs sed -i "/package de.pmdcheck.factorial.eightSources/a\import java.util.Iterator;\nimport java.util.Spliterator;\nimport java.util.Comparator;" $1
