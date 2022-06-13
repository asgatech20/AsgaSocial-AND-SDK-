# Social Auth Library

An Android Library created to facilitate the use of authentication with social media by [Kotlin](http://kotlinlang.org/).

## Supported Platforms

* [Google](https://developers.google.com/identity/sign-in/android/sign-in)
* [Facebook](https://developers.facebook.com/docs/facebook-login/android/)

## Import

Add the following code to `build.gradle` in the root folder.

```groovy
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

Add the following dependencies to the `build.gradle` of the module you want to use.

```groovy
dependencies {
    implementation 'com.github.User:Repo:Tag'
}
```
### Google
Add authentication code in your activity
Example:
```kotlin
class MainActivity : SocialAuthActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.loginGoogle.setOnClickListener { view ->
            startLoginWithGoogle(
                object :AuthCallBack {
                    override fun onSuccess(
                        socialUser: SocialUser,
                        granted: List<AuthPermissions>,
                        block: List<AuthPermissions>
                    ) {
                        // updateUi(socialUser)
                    }
                    override fun onCancel() {
                       
                    }
                    override fun onError(throwable: Throwable) {
                        // handel error
                    }
                }
            )
        }
    }
}
```
### Facebook
1. [create a new app on Facebook developer](https://developers.facebook.com/docs/facebook-login/android/#1--select-an-app-or-create-a-new-app)
2. [Associate Your Package Name and Default Class with Your App](https://developers.facebook.com/docs/facebook-login/android/#5--associate-your-package-name-and-default-class-with-your-app)
3. [Provide the Development and Release Key Hashes for Your App](https://developers.facebook.com/docs/facebook-login/android/#6--provide-the-development-and-release-key-hashes-for-your-app)
4. Edit Your Resources and Manifest.
</br >


1. Open your `/app/res/values/strings.xml` file.
2. Add string elements with the names facebook_app_id, fb_login_protocol_scheme and facebook_client_token, and set the values to your App ID and Client Token. For example, if your app ID is 1234 and your client token is 56789 your code looks like the following:
```xml
<string name="facebook_app_id">1234</string>
<string name="fb_login_protocol_scheme">fb1234</string>
<string name="facebook_client_token">56789</string>
```

Open the /app/manifest/AndroidManifest.xml file.
Add meta-data elements to the application element for your app ID and client token:
```xml
<application android:label="@string/app_name" ...>

<meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/facebook_app_id"/>
<meta-data android:name="com.facebook.sdk.ClientToken" android:value="@string/facebook_client_token"/>

...
</application>
```
Add an activity for Facebook, and an activity and intent filter for Chrome Custom Tabs inside your application element:

```xml
<activity 
    android:name="com.facebook.FacebookActivity"
    android:configChanges="keyboard|keyboardHidden|screenLayout|screenSize|orientation"
    android:label="@string/app_name" />
<activity
android:name="com.facebook.CustomTabActivity"
android:exported="true">
<intent-filter>
<action android:name="android.intent.action.VIEW" />
<category android:name="android.intent.category.DEFAULT" />
<category android:name="android.intent.category.BROWSABLE" />
<data android:scheme="@string/fb_login_protocol_scheme" />
</intent-filter>
</activity>
```
Add a uses-permission element to the `manifest` after the application element:
```xml
<uses-permission android:name="android.permission.INTERNET"/>
```
5. Add authentication code in your activity
   Example:
```kotlin
class MainActivity : SocialAuthActivity() {

    private lateinit var binding: ActivityMainBinding
    private val facePermissions = listOf(
        AuthPermissions.EMAIL,
        AuthPermissions.PUBLIC_PROFILE,
        AuthPermissions.BIRTH_DAY
    )
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginFacebookButton.setOnClickListener { view ->
            startLoginWithFaceBook(
                object :AuthCallBack {
                    override fun onSuccess(
                        socialUser: SocialUser,
                        granted: List<AuthPermissions>,
                        block: List<AuthPermissions>
                    ) {
                        // updateUi(socialUser)
                    }
                    override fun onCancel() {
                       
                    }
                    override fun onError(throwable: Throwable) {
                        // handel error
                    }
                },
                permissions = facePermissions
            )
        }
    }
}
```




