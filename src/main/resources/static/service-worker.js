const CACHE_NAME = 'secure-stream-v1';

// Install Event
self.addEventListener('install', event => {
    self.skipWaiting();
});

// Activate Event
self.addEventListener('activate', event => {
    event.waitUntil(caches.keys().then(names => Promise.all(names.map(n => caches.delete(n)))));
    return self.clients.claim();
});

// Fetch Event (Main Fix)
self.addEventListener('fetch', event => {
    const url = event.request.url;

    // Render aur Streaming requests ko bilkul mat chhedo
    if (url.includes('onrender.com') || url.includes('video-stream') || event.request.headers.get('Upgrade') === 'websocket') {
        return; // Direct network par bhej do
    }

    event.respondWith(
        fetch(event.request).catch(() => caches.match(event.request))
    );
});