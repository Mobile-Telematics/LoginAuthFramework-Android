# LoginAuth Framework for Android

## Description

This library is created by DATA MOTION PTE. LTD. and allows you to integrate with our UserService API in a few steps.

LoginAuth Framework main functions:
1. Register `deviceToken` for each new Telematics SDK user.
2. Refresh the `accessToken (JWT)` when it is expired.
3. Authorize existing SDK user
4. Get authorized user profile info
5. Update authorized user profile info


## Credentials

Before you start using the framework, make sure you registered a company account in [DataHub](https://app.damoov.com/) and obtained `InstanceID` and`InstanceKEY`. If you are new, please refer to the [documentation](https://docs.telematicssdk.com/docs/setting-up-the-company-account) and register your company account in DataHub. [Sing Up](https://app.damoov.com/user/registration)


## Basic concepts

`deviceToken` - is the main individual SDK user identifier for your app. This identifier is used as a key across all our services.

`accessToken` - or JSON Web Token (JWT) is the main UserService API key, that allows you to get user individual statistics and user scoring by UserService APIs calls.

`refreshToken` - is a secret key that allows you to refresh the `accessToken` when it expires.


## Setup

Here you can find a short video guide, how to add LoginAuth Framework to your Android app:

[![Watch the video](https://github.com/Mobile-Telematics/LoginAuthFramework-Android/blob/master/Android%20LoginAuth%20Framework.png)](https://www.youtube.com/watch?v=jpdLDzMdCuw&ab)




1. Add our maven-repository to your `build.gradle` file:
``` groovy
maven {
    url "https://s3.us-east-2.amazonaws.com/android.telematics.sdk.production/"
}
```
2. Import library:
``` groovy
dependencies {
    implementation "com.telematicssdk:auth:1.0.1"
}
```

3. If you are using R8 or ProGuard add this line to options:
```
-keep public class com.telematicssdk.auth.** {*;}
```

## Methods
### Create DeviceToken

Each SDK user has to have a `Devicetoken` and be associated with the app users. To create `DeviceToken` please use the method below. To complete a call, you are required to provide `instanceId` & `instanceKey`. If you still have quiestions on how to obtain the credentails, please refer to the [documentation](https://docs.telematicssdk.com/docs/datahub#user-group-credentials)
    
``` kotlin
    TelematicsAuth.createDeviceToken(
        instanceId = "<your instanceId>",
        instanceKey = "<your instanceKey>"
     )
        .onSuccess {
            it.deviceToken
            it.accessToken
            it.refreshToken
        }
        .onError { e ->
            e.printStackTrace()
        }
```



Once user is registered, you will receive the user credentails. make sure you pass the `Devicetoken` to your server and store it against a user profile, then pass it to your App - this is the main user detials that you will use for our services.

Additionally, you can create a user's `deviceToken` and get the necessary keys (`accessToken`, `refreshToken`) with additional parameters. This is not a required method, it just allows you to store the user profile in a different way. You can specify the below given parameters when creating a user's deviceToken:

- email
- phone
- firstName
- lastName
- address
- birthday
- gender
- maritalStatus
- childrenCount
- clientId

``` kotlin
    TelematicsAuth.createDeviceToken(
            instanceId = "<your instanceId>",
            instanceKey = "<your instanceKey>"
            email = "mail@mail.mail",
            phone = "+10000000000",
            clientId = "idOptional",
            firstName = "TELEMATICS_USERNAME",
            lastName = "TELEMATICS_LASTNAME",
            birthDay = "1970-01-01",
            maritalStatus = MaritalStatus.Married,
            childrenCount = 0,
            address = "CITY",
            gender = Gender.Male
        )
            .onSuccess {
                it.deviceToken
                it.accessToken
                it.refreshToken
            }
            .onError { e ->
                e.printStackTrace()                
            }
```

### Refresh Access Token

Each `accessToken` has a limmited lifetime and in a certain period of time it is expired. As a result, when you call our API using invalid `accessToken` you will receive an Error `Unauthorized 401`.
**Error 401** indicates that the user's `accessToken` has been expired. If so, as the first step, you have to update the `accessToken`.

To update the `accessToken`, you are required to provide the latest `accessToken` & `refreshToken` to the method below.


``` kotlin
    TelematicsAuth.refreshToken(
        instanceId = "<your instanceId>",
        instanceKey = "<your instanceKey>",
        accessToken = "<your accessToken>",
        refreshToken = "<your refreshToken>"
    )
        .onSuccess {
            it.refreshToken
        }
        .onError {e ->
            e.printStackTrace()
        }
```

In response you will receive new `accessToken`.


### Get Access Token for existing SDK users

During the app usage, there may be several scenarios when the app loses `accessToken`, for example if the a user changes a smartphone or logs out. BTW, that is a reason why we strongly recommend you to store the `deviceToken` on your backend side. `deviceToken` cannot be restored if it is lost!

We provide you with a simple re-authorization, a method that you can use to get a valid `accessToken` for a particular user throught providing `DeviceToken`
To use this mehod, you need `deviceToken`, `instanceId`, and `instanceKey` of which group the user belongs. in this case, `Devicetoken` works as a login, `instancekey` as a password. Then you can re-login the user and get a valid `accessToken` & `refreshToken`.

``` kotlin
    TelematicsAuth.login(
       instanceId = "<your instanceId>",
       instanceKey = "<your instanceKey>",
       deviceToken = "<your deviceToken>" 
    )
        .onSuccess {
            it.accessToken
            it.refreshToken
        }
        .onError {e ->
            e.printStackTrace()           
        }
```

In response, you will receive a new `accessToken` and `refreshToken`.

## Additional methods

### Get User Profile
You can always request information about the user profile anytime:

``` kotlin
    TelematicsAuth.getUserProfile(
        instanceId = "<your instanceId>",
        instanceKey = "<your instanceKey>",
        accessToken = "<your accessToken>"
    )
        .onSuccess {user ->
            user
        }
        .onError {{e ->
            e.printStackTrace()    
        }
```
    
### Update User

``` kotlin 
    TelematicsAuth.updateUserProfile(
       instanceId = "<your instanceId>",
       instanceKey = "<your instanceKey>",
       deviceToken = "<your deviceToken>",
       accessToken = "<your accessToken>",
       email = "mail@mail.mail",
       phone = "+10000000000",
       clientId = "idOptional",
       firstName = "TELEMATICS_USERNAME",
       lastName = "TELEMATICS_LASTNAME",
       birthDay = "1970-01-01",
       maritalStatus = MaritalStatus.Married,
       childrenCount = 0,
       address = "CITY",
       gender = Gender.Male
    )
        .onSuccess {

        }
        .onError {

        }
```

That's it! Now you are ready to use our authorization library. 

Also you can check [references in our doc](https://mobile-telematics.github.io/LoginAuthFramework-Android/).

Happy coding!


## Links

[Official product Web-page](https://app.damoov.com/)

[Official API services web-page](https://www.damoov.com/telematics-api/)

[Official API references](https://docs.telematicssdk.com/reference)

[Official ZenRoad web-page](https://www.damoov.com/telematics-app/)

[Official ZenRoad app for iOS](https://apps.apple.com/jo/app/zenroad/id1563218393)

[Official ZenRoad app for Android](https://play.google.com/store/apps/details?id=com.telematicssdk.zenroad&hl=en&gl=US)

[Official ZenRoad app for Huawei](https://appgallery.huawei.com/#/app/C104163115)

###### Copyright © 2020-2021 DATA MOTION PTE. LTD. All rights reserved.


