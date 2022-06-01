[![build workflow](https://github.com/haggbart/dat153-oblig1/actions/workflows/build.yml/badge.svg)](https://github.com/haggbart/dat153-oblig1/actions/workflows/build.yml)


# dat153-oblig1
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152841033-3ef27bb2-4ed9-4826-bee6-449f74a36acc.png">

## The quiz
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152842732-8fe6bdf1-74ba-4217-b789-10e35ac32be2.png">

### Picking the correct answer
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152842817-d9c5fb0d-d1ec-4ad2-9b85-befb5d547f30.png">

### vs picking the wrong answer (updates the score accordingly)
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152842907-c6819d8a-c726-4a89-b30f-a82d68fc260a.png">

## Database, toolbar menu for adding entries and sorting
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152841140-3ceaa13b-45eb-4eae-9273-6b5922084535.png">

## Remove items by swiping
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152842213-0eb2eacc-ba9b-4c3e-a23f-abb3ca38a187.png">

## Add entry using a picker
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152842488-c4edd1ef-8ee6-4f3f-9103-3018ddec09ad.png">

## Toast confirming entry added to the database
<img width="550" alt="image" src="https://user-images.githubusercontent.com/4412029/152841632-01f94ac2-9de7-4f74-b294-b4289100217a.png">


# Answers
Which APKs are used during testing?
app apk and debug apk

Which adb commands does Gradle use to install & run the tests on the phone/emulator?
adb install example.apk
adb install testexample.apk
adb shell am instrument -w <test_package_name>/<runner_class>
