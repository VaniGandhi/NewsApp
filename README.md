# News App (MVVM Architecture)

##  Project Overview
This is an **Android News App** built using **MVVM architecture**, **Retrofit for API calls**, **Room Database for local storage**, and **Kotlin Coroutines for asynchronous tasks**.

---

##  Tech Stack
- **Language**: Kotlin
- **Architecture**: MVVM
- **Networking**: Retrofit
- **Local Storage**: Room Database
- **Concurrency**: Kotlin Coroutines
- **Testing**: JUnit, Mockito

---

##  Setup Instructions

### 1️ **Clone the Repository**
```sh
git clone https://github.com/VaniGandhi/NewsApp.git
cd news-app
```

###  **Open in Android Studio**
- Open **Android Studio**.
- Select **Open an Existing Project**.
- Choose the cloned project directory.

###  **Configure API Key**
This project uses [NewsAPI](https://newsapi.org/) for fetching news.
- Create a free account at **[NewsAPI](https://newsapi.org/)**.
- Get your **API key**.
- Open `NewsService.kt` and replace `YOUR_API_KEY` with your actual key:

```kotlin
@GET("everything")
suspend fun getNews(
    @Query("q") query: String = "tesla",
    @Query("from") from: String = "2025-01-01",
    @Query("sortBy") sortBy: String = "publishedAt",
    @Query("apiKey") apiKey: String = "YOUR_API_KEY"
): NewsResponse
```

###  **Sync Gradle & Build the Project**
- Click **File > Sync Project with Gradle Files**.
- Click **Build > Rebuild Project**.

### **Run the App**
- Connect an **Android device** or use an **emulator**.
- Click **Run ** in Android Studio.


## 📂 Project Structure
```
com.example.newsapp
│── network
│   ├── NewsService.kt
│   ├── RetrofitInstance.kt
│
│── data
│   ├── NewsDatabase.kt
│   ├── NewsDao.kt
│   ├── NewsArticle.kt
│
│── repository
│   ├── NewsRepository.kt
│
│── ui
│   ├── NewsViewModel.kt
│   ├── NewsAdapter.kt
│   ├── MainActivity.kt
│
│── NewsApp.kt
│── NewsRepositoryInstance.kt
```

---

##  Features
✅ Fetches **live news** from NewsAPI
✅ **Offline support** with Room Database
✅ Uses **MVVM architecture**
✅ **Unit tests** for Repository, ViewModel, and DAO


