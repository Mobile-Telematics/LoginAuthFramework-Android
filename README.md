# Telematics authorization library for Android

## Description

This library is created by DATA MOTION PTE. LTD. and allows you to integrate with our UserService API in a few steps.

LoginAuth Framework has three main functions:
1. Register `deviceToken` for each new Telematics SDK user.
2. Refresh the `accessToken` when it is expired.
3. Authorize existing SDK user
4. Get authorized user profile info
5. Update authorized user profile info


## Credentials

Before you start using the framework, make sure you registered a company account in the [Datahub](https://userdatahub.com/) and obtained `InstanceId` and`InstanceKey`. If you are new, please refer to the [documentation](doc.telematicssdk.com) and register your company account in Datahub. [Sing Up](https://userdatahub.com/user/registration)


## Basic concepts

`deviceToken` - is the main individual SDK user identifier for your app. this identifier is used as a key across all our services.

`accessToken` - or JSON Web Token (JWT) is the main UserService API key, that allows you to get user individual statistics and user scoring by UserService APIs calls.

`refreshToken` - is a secret key that allows you to refresh the `accessToken` when it expires.


## Setup

1. Add our maven-repository to your `build.gradle` file:
``` groovy
maven {
    url "https://s3.us-east-2.amazonaws.com/android.telematics.sdk.production/"
}
```
2. Import library:
``` groovy
dependencies {
    implementation "com.telematicssdk:auth:1.0.0"
}
```

## Example

Let's dive in and see library's API. The main entry point of the library is `TelematicsAuth` object.
It's an Kotlin `Object` (or static class for Java users) that contains inside all methods.

Every method returns a `Task` class. It's a task that should be processed and contains inside callbacks about success or error.

Let's see how it work. In this example we will register a new empty user (without additional fields):

Call `TelematicsAuth.createDeviceToken` method that takes an `instanceId`, `instanceKey` and creates a new empty user:

``` kotlin
    TelematicsAuth.createDeviceToken(
        instanceId = "<your instanceId>",
        instanceKey = "<your instanceKey>")
    .onSuccess { result ->
        println(result.deviceToken)
        println(result.accessToken)
        println(result.refreshToken)
    }
    .onError { e ->
        e.printStackTrace()
    }

```

That's it! Now you are ready to use our authorization library. 

Also you can check [references in our doc](https://mobile-telematics.github.io/LoginAuthFramework-Android/).

Happy coding!


## Links

[Official product Web-page](https://telematicssdk.com/)

[Official API services web-page](https://www.telematicssdk.com/api-services/)

[Official SDK and API references](https://www.telematicssdk.com/api-services/)

[Official ZenRoad web-page](https://www.telematicssdk.com/telematics-app/)

[Official ZenRoad app for iOS](https://apps.apple.com/jo/app/zenroad/id1563218393)

[Official ZenRoad app for Android](https://play.google.com/store/apps/details?id=com.telematicssdk.zenroad&hl=en&gl=US)

[Official ZenRoad app for Huawei](https://appgallery.huawei.com/#/app/C104163115)

###### Copyright © 2020-2021 DATA MOTION PTE. LTD. All rights reserved.


