# EgyCinema — Static (HTML / CSS / JS)

Plain static files. No build step, no framework.

## Files
- `index.html`        — Welcome page (animated background, dynamic tagline)
- `login.html`        — Login page
- `signup.html`       — Sign up page
- `home.html`         — Home with featured hero + cinema rows
- `movie.html`        — Movie detail (uses `?id=...`)
- `css/styles.css`    — All styles (deep crimson & gold dark theme)
- `js/data.js`        — Cinemas + movies sample data
- `js/common.js`      — Shared header/footer, icons, auth, toast

## Run it
Just open `index.html` in a browser. For best results (so navigation between pages works smoothly), serve the folder with any static server, e.g.:

```bash
cd egycinema
python3 -m http.server 8000
# then visit http://localhost:8000
```

## Auth
Uses `localStorage` to simulate signup/login (no backend). Replace `Auth` in `js/common.js` later to plug in a real backend.
