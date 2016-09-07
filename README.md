# PreferencesProvider
A replacement of Android SharedPreferences based on ContentProvider.

It solves the problem when we have to store/use preference values across process.

Inspired by the great work of [grandcentrix/tray](https://github.com/grandcentrix/tray), but PreferencesProvider has a much simpler implementation.

## Setup
```
repositories {
    jcenter() // If not already there
}

dependencies {
    compile 'com.mengdd.preferencesprovider:preferences-provider:0.1.0'
}

```

## Usage
### 1. Create a module for your preferences.

A storage module is a group of preferences with a group name. You can create as many modules as you need.

```java
preferenceStorageModule = new PreferencesStorageModule(this, "HelloModule1");
```

### 2. Put values

The key is a String,
The value can be String, boolean, int, float or long.

```java
preferenceStorageModule.put("stringKey", "Hello");
preferenceStorageModule.put("booleanKey1", true);
preferenceStorageModule.put("booleanKey2", false);
preferenceStorageModule.put("intKey", 123);
preferenceStorageModule.put("floatKey", 1.5f);
preferenceStorageModule.put("longKey", 123L);
```

### 3. Get values

We know we can pass a default value as parameter when you get SharedPreferences values. We can also do that using PreferencesProvider.

**With Default Values:**

```java
String stringValue = preferenceStorageModule.getString("non-existing-key-2", "defaultValue");
boolean boolean1 = preferenceStorageModule.getBoolean("non-existing-booleanKey1", false);
boolean boolean2 = preferenceStorageModule.getBoolean("non-existing-booleanKey2", false);
int intValue = preferenceStorageModule.getInt("non-existing-intKey", 0);
float floatValue = preferenceStorageModule.getFloat("non-existing-floatKey", 0f);
long longValue = preferenceStorageModule.getLong("non-existing-longKey", 0L);
```

**Without Default Values**

You will have to add a try-catch block when you are trying to get value but not provide default value.

```java
try {
    String stringValue = preferenceStorageModule.getString("stringKey");
    boolean boolean1 = preferenceStorageModule.getBoolean("booleanKey1");
    boolean boolean2 = preferenceStorageModule.getBoolean("booleanKey2");
    int intValue = preferenceStorageModule.getInt("intKey");
    float floatValue = preferenceStorageModule.getFloat("floatKey");
    long longValue = preferenceStorageModule.getLong("longKey");

    ...

} catch (ItemNotFoundException e) {
    e.printStackTrace();
}
```

### Other operations

remove(), clear() and getAll() are also supported.

See: [Sample Activity](https://github.com/mengdd/PreferencesProvider/blob/master/sample/src/main/java/com/ddmeng/preferencesprovider/sample/MainActivity.java)


## Used Tools
[BoD/android-contentprovider-generator](https://github.com/BoD/android-contentprovider-generator) is used to generated all the database and content provider codes.


[Stetho](http://facebook.github.io/stetho/) is used to watch the database.