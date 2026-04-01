const CACHE_NAME = 'v1';

// Install event
self.addEventListener('install', (e) => {
    console.log('Service Worker: Installed');
    self.skipWaiting();
});

// Activate event
self.addEventListener('activate', (e) => {
    console.log('Service Worker: Activated');
});

// Fetch event (Optional: Offline support ke liye)
self.addEventListener('fetch', (e) => {
    e.respondWith(fetch(e.request).catch(() => caches.match(e.request)));
});