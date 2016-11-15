# PreferencesProvider
[ ![Download](https://api.bintray.com/packages/mengdd/maven/preferences-provider/images/download.svg) ](https://bintray.com/mengdd/maven/preferences-provider/_latestVersion)

A replacement of Android SharedPreferences based on ContentProvider.

It solves the problem when we have to store/use preference values across process.

Inspired by the great work of [grandcentrix/tray](https://github.com/grandcentrix/tray), but PreferencesProvider has a much simpler implementation.

## Setup
```
repositories {
    jcenter() // If not already there
}

dependencies {
    compile 'com.mengdd.preferencesprovider:preferences-provider:0.2.0'
}

```

## Set Authority
Library users must define their own authority for the content provider.

This is done by defining the `preferences_provider_authority` string value:
```
resValue "string", "preferences_provider_authority", "${applicationId}.preferencesprovider"
```
For example:
In your app's `build.gradle`
```
defaultConfig {
    applicationId "com.ddmeng.preferencesprovider.sample"
    minSdkVersion 14
    targetSdkVersion 25
    versionCode 1
    versionName "1.0"
    resValue "string", "preferences_provider_authority", "${applicationId}.preferencesprovider"
}
```

This is because every content provider needs an unique authority.
If multiple apps use the library but they have same authority for a content provider, only one app can be installed on a device.

(See issue: https://github.com/mengdd/PreferencesProvider/issues/2).

So the library enforce the client users override the authority. If the authority is not defined, an `IllegalStateException` will be thrown.
An authority with **applicationId** is recommended.


## Usage
### 1. Create a module for your preferences.

A storage module is a group of preferences with a group name. You can create as many modules as you need.

```java
myModule = new PreferencesStorageModule(this, "HelloModule1");
```

### 2. Put values

The key is a String,
The value can be String, boolean, int, float or long.

```java
myModule.put("stringKey", "Hello");
myModule.put("booleanKey1", true);
myModule.put("booleanKey2", false);
myModule.put("intKey", 123);
myModule.put("floatKey", 1.5f);
myModule.put("longKey", 123L);
```

### 3. Get values

We know we can pass a default value as parameter when you get SharedPreferences values. We can also do that using PreferencesProvider.

**With Default Values:**

```java
String stringValue = myModule.getString("non-existing-key-2", "defaultValue");
boolean boolean1 = myModule.getBoolean("non-existing-booleanKey1", false);
boolean boolean2 = myModule.getBoolean("non-existing-booleanKey2", false);
int intValue = myModule.getInt("non-existing-intKey", 0);
float floatValue = myModule.getFloat("non-existing-floatKey", 0f);
long longValue = myModule.getLong("non-existing-longKey", 0L);
```

**Without Default Values**

You will have to add a try-catch block when you are trying to get value but not provide default value.

```java
try {
    String stringValue = myModule.getString("stringKey");
    boolean boolean1 = myModule.getBoolean("booleanKey1");
    boolean boolean2 = myModule.getBoolean("booleanKey2");
    int intValue = myModule.getInt("intKey");
    float floatValue = myModule.getFloat("floatKey");
    long longValue = myModule.getLong("longKey");

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