Aji Nur Fahrurrahman / 122203004

Teknik Informatika

Mata kuliah : 
Pemograman Web dan Mobile

Dosen pengajar : DONNY MAULANA, S.Kom., MMSI 

--------------------------------------------

Aplikasi Resep Makanan (Recipe App)
Aplikasi Android sederhana yang menampilkan daftar resep makanan dari API publik. Aplikasi ini dibangun sebagai contoh implementasi arsitektur Android modern menggunakan Kotlin, Retrofit untuk networking, dan RecyclerView untuk menampilkan data dalam format grid yang menarik.

✨ Fitur
1. Tampilan Grid: Menampilkan daftar resep dalam format grid dua kolom yang responsif.
2. Ambil Data dari API: Mengambil data resep secara asynchronous dari endpoint https://dummyjson.com/recipes menggunakan Retrofit.
3. Detail Pop-up: Menampilkan detail resep (bahan dan instruksi) dalam dialog pop-up (AlertDialog) yang interaktif tanpa perlu berpindah layar.
4. Loading Indicator: Menampilkan ProgressBar saat data sedang dimuat dari jaringan.
5. UI Modern: Desain antarmuka yang bersih dan menarik menggunakan Material Design Components dan CardView.
6. Gambar Online: Memuat dan menampilkan gambar resep dari URL secara efisien menggunakan library Glide.

🛠️ Teknologi yang Digunakan
1. Bahasa: Kotlin dan Java
2. Arsitektur: Model-View-ViewModel (MVVM) - (Meskipun sederhana, strukturnya mengikuti prinsip pemisahan tugas)
3. Networking:
    1. Retrofit - Untuk melakukan panggilan HTTP ke API.
    2. Gson - Untuk parsing data JSON menjadi objek Kotlin.
    3. OkHttp Logging Interceptor - Untuk debugging panggilan jaringan.
4. Tampilan (UI):
    1. RecyclerView - Untuk menampilkan daftar data yang efisien.
    2. CardView - Untuk membuat setiap item resep dalam bentuk kartu.
    3. ConstraintLayout - Untuk membangun layout yang kompleks dan fleksibel.

Gambar:
Glide - Untuk memuat dan caching gambar dari URL.

API
Aplikasi ini menggunakan API publik dari https://dummyjson.com/recipes menggunakan Retrofit. Terima kasih kepada tim dummyjson.com yang telah menyediakan data API yang bermanfaat untuk keperluan pengembangan.
