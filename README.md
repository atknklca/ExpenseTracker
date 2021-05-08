# Expense Tracker - EX-KER

Bu proje Google Developer Groups'un organizatörlüğünü yaptığı, "[Android Bootcamp Turkey](https://events.withgoogle.com/android-bootcamp/)" eğitimi doğrultusunda hazırlanmıştır. Proje dökümantasyonuna [buradan](https://github.com/erkanercan/android-bootcamp-turkey-bitirme-projesi) ulaşabilirsiniz.


## Uygulamanın Görünümleri

### Uygulamanın Görünümü
<img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(1).png" width="250px" >
Mobil cihazınızda bu görünümde EX-KER uygulamanız yer almaktadır.

### Splash  Ekranı
<img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(2).png" width="250px" >
Uygulama açıldığında kısa süreli, animasyonlu bir açılış ekranı sizi karşılar.

### Ana Ekran(TL & Sterlin bazlı)
<img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(3).png" width="250px" > <img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(4).png" width="250px" >
Ana sayfada eklenmiş olan harcamaların kayıtları listelenir. En son hangi kurda bıraktıysanız, bırakılan kurda listelenir. Eğer internet bağlantınız varsa güncel kur değerleri ile çalışmaktadır. Merhaba yazan alan, etkileşimli bir alandır. Tıklama yaparsanız isim değiştirme sayfasına yönlendirilirsiniz. Ekle bölümünden yeni bir harcama ekleyebilir, dilerseniz de listelenen harcamalardan herhangi birine tıklayarak detaylı görünümünden silme işlemlerini yapabilirsiniz.

### İsim Değiştirme Ekranı
<img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(5).png" width="250px" >
Girilen bilgiler neticesinde uygulamanın ana sayfasında kullanıcıya hitap edilmektedir. Örn: Atakan Bey

### Harcama Ekleme Ekranı
<img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(6).png" width="250px" >
Harcamanızın ismini, tutarını, hangi kurda işlem yapıldığını ve hangi türde harcama yapıldığını girerek kaydet'e tıklamanız halinde internet bağlantısına gerek kalmaksızın, mobil cihazınızın içerisindeki veritabanında saklanır ve ana sayfada listelenir.

### Harcama Detay Ekranı
<img src="https://github.com/atknklca/ExpenseTracker/blob/master/ScreenShots/UygulamaG%C3%B6r%C3%BCn%C3%BCm%C3%BC%20(7).png" width="250px" >
Bu ekranda harcamalarınızın silme işlemlerini yapabilirsiniz.

## API
Uygulama güncel kur değerlerinin açık kaynaklı bir API'den sağlamaktadır. API'nin dökümantasyonuna [buradan](https://exchangeratesapi.io/) ulaşım sağlayabilirsiniz. 

## Projede Kullanılan Teknoloji ve Kütüphaneler
* [Lottie](https://lottiefiles.com/featured) (Animasyonlar lottie ile sağlanmıştır)
* [View Binding](https://developer.android.com/topic/libraries/view-binding) (Görünümler ile arka plan yapısı arasındaki bağlantıyı sağlamıştır)
* [Room](https://developer.android.com/training/data-storage/room?hl=en) (Room'dan yararlanılarak SQlite veritabanı kullanışmıştır)
* [Navigation](https://developer.android.com/guide/navigation?hl=en) (Sayfalar arasındaki geçiş aşamaları ve argümanlar için kullanılmıştır)
* [Retrofit](https://square.github.io/retrofit/) (API işlemleri için kullanılmıştır)
* [RecyclerView](https://developer.android.com/reference/androidx/recyclerview/widget/RecyclerView) (Harcamaların kullanıcıya gösterilmesinde kullanılmıştır)
* [ViewPager2](https://developer.android.com/jetpack/androidx/releases/viewpager2) (onBoarding ekranında kullanılmıştır)

## Projenin özellikleri
* Minimum SDK: 21 (Android 5.0 ve Sonrası)
* Kullanılan Dil: Kotlin
* Hazırlandığı Emulator: Android Studio Emulator(Pixel 3a, 1080x2220:440dpi, Android 11(SDK: 30))
* Offline & Online kullanabilme
* Harcamaları farklı kurlarda takip edebilme
