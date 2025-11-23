# Google Play Data Safety Form Checklist

**⚠️ REQUIRED FOR GOOGLE PLAY STORE SUBMISSION**

This checklist helps you complete the Data Safety form in Google Play Console. You must complete this form for your app to be published or updated.

---

## How to Access

1. Go to [Google Play Console](https://play.google.com/console)
2. Select your app
3. Navigate to **App Content** → **Data Safety**
4. Click **Start** or **Manage**

---

## Section 1: Data Collection and Security

### Does your app collect or share any of the required user data types?

**Answer: YES**

Your app collects analytics and crash data through Firebase.

### Is all of the user data collected by your app encrypted in transit?

**Answer: YES**

Data transmitted to Firebase and WhatsApp is encrypted using HTTPS/TLS.

### Do you provide a way for users to request that their data is deleted?

**Answer: RECOMMENDED - YES**

Provide an email address where users can request data deletion from Firebase Analytics/Crashlytics.

---

## Section 2: Data Types Collected

Go through each category and declare what your app collects:

### ✅ Location
**Answer: NO** - Your app does not collect location data

### ✅ Personal Info

**Phone number**
- ☑️ Collected: **NO** (phone numbers are NOT stored, only passed to WhatsApp)
- If you query device phone number: Select **YES** and specify usage

**Name, Email address, User IDs, Address, etc.**
- ☑️ Collected: **NO**

### ✅ Financial Info
**Answer: NO** - No financial data collected

### ✅ Health and Fitness
**Answer: NO** - No health data collected

### ✅ Messages
**Answer: NO** - App does not access messages

### ✅ Photos and Videos
**Answer: NO** - No media access

### ✅ Audio Files
**Answer: NO** - No audio access

### ✅ Files and Docs
**Answer: NO** - No file access

### ✅ Calendar
**Answer: NO** - No calendar access

### ✅ Contacts
**Answer: NO** - App does not access contacts
*(Note: You query WhatsApp package availability, not actual contacts)*

### ✅ App Activity

**App interactions**
- ☑️ Collected: **YES**
- **Data usage:** Analytics
- **Collection:** Optional
- **Purpose:**
  - ☑️ Analytics
  - ☑️ App functionality
- **Sharing:** Shared with Firebase (Google)
- **Encrypted:** YES
- **Deletion:** User can request deletion

**In-app search history**
- ☑️ Collected: **NO**

**Other user-generated content**
- ☑️ Collected: **NO**

**Other actions**
- ☑️ Collected: **NO**

### ✅ Web Browsing
**Answer: NO** - No browsing data collected

### ✅ App Info and Performance

**Crash logs**
- ☑️ Collected: **YES**
- **Data usage:** Analytics
- **Collection:** Optional (automatically collected)
- **Purpose:**
  - ☑️ Analytics
  - ☑️ App functionality
- **Sharing:** Shared with Firebase Crashlytics (Google)
- **Encrypted:** YES
- **Deletion:** User can request deletion

**Diagnostics**
- ☑️ Collected: **YES**
- **Data usage:** Analytics
- **Collection:** Optional
- **Purpose:**
  - ☑️ Analytics
  - ☑️ App functionality
- **Sharing:** Shared with Firebase Analytics (Google)
- **Encrypted:** YES
- **Deletion:** User can request deletion

**Other app performance data**
- ☑️ Collected: **NO**

### ✅ Device or Other IDs

**Device or other IDs**
- ☑️ Collected: **YES** (Firebase Analytics collects device identifiers)
- **Data usage:** Analytics
- **Collection:** Optional
- **Purpose:**
  - ☑️ Analytics
  - ☑️ App functionality
- **Sharing:** Shared with Firebase (Google)
- **Encrypted:** YES
- **Deletion:** User can request deletion

---

## Section 3: Third-Party SDKs Data Collection

You must account for data collected by third-party libraries:

### Firebase Analytics
- Collects: Device IDs, app interactions, diagnostics
- Purpose: Analytics, app functionality
- Shared with: Google

### Firebase Crashlytics
- Collects: Crash logs, device info
- Purpose: Analytics, crash reporting
- Shared with: Google

### Firebase Remote Config
- Collects: Minimal device/app state information
- Purpose: App configuration
- Shared with: Google

### Country Code Picker (ccp:2.7.0)
- Does NOT collect or transmit data

### MotionToast
- Does NOT collect or transmit data

---

## Section 4: Data Usage and Purpose

For each data type collected, specify:

1. **Why it's collected:**
   - App functionality
   - Analytics
   - Developer communications (if applicable)
   - Fraud prevention, security, and compliance (if applicable)

2. **Is it shared with third parties?**
   - YES - Firebase (Google) for analytics and crash reporting

3. **Is collection optional?**
   - Analytics: Optional (user can disable in device settings)
   - Crash logs: Optional (automatically collected but can be disabled)

---

## Section 5: Privacy Policy

**Privacy Policy URL:** [Your URL Here]

You MUST provide a valid, publicly accessible privacy policy URL.

---

## Important Notes

### ✅ DO
- Review all third-party SDKs/libraries for data collection
- Check Firebase Analytics data collection documentation
- Be transparent about all data collection
- Update the form if you add new features or SDKs
- Test that your privacy policy URL is accessible

### ❌ DON'T
- Skip any data types that third-party SDKs collect
- Declare data as "not collected" if any SDK collects it
- Forget to update when adding new libraries
- Use vague or incorrect descriptions

---

## Common Questions

**Q: The app doesn't store phone numbers. Why declare Firebase data?**
A: You must declare ALL data collected by your app and its third-party libraries, even if you don't directly collect it.

**Q: Can users opt out of analytics?**
A: Users can disable analytics through device settings or you can add an in-app setting.

**Q: How long until the form is approved?**
A: Usually within 24-48 hours after submission.

**Q: What if I'm not sure about a data type?**
A: Check the documentation for each library you use:
- [Firebase Analytics Data Collection](https://firebase.google.com/support/privacy)
- Review each library's privacy documentation

---

## After Submission

1. ✅ Submit the Data Safety form
2. ✅ Wait for Google's review (24-48 hours)
3. ✅ Address any feedback or rejections
4. ✅ Keep the form updated with app changes

---

## Resources

- [Google Play Data Safety Help](https://support.google.com/googleplay/android-developer/answer/10787469)
- [Firebase Privacy Documentation](https://firebase.google.com/support/privacy)
- [Data Safety Form Examples](https://support.google.com/googleplay/android-developer/answer/10787469#examples)
