// Shared icons, header, footer, auth, toast, favorites, showtimes
const ICONS = {
  film:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="2" y="2" width="20" height="20" rx="2.18" ry="2.18"/><line x1="7" y1="2" x2="7" y2="22"/><line x1="17" y1="2" x2="17" y2="22"/><line x1="2" y1="12" x2="22" y2="12"/><line x1="2" y1="7" x2="7" y2="7"/><line x1="2" y1="17" x2="7" y2="17"/><line x1="17" y1="17" x2="22" y2="17"/><line x1="17" y1="7" x2="22" y2="7"/></svg>',
  sparkles:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M12 3v3M12 18v3M3 12h3M18 12h3M5.6 5.6l2.1 2.1M16.3 16.3l2.1 2.1M5.6 18.4l2.1-2.1M16.3 7.7l2.1-2.1"/></svg>',
  arrow:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="5" y1="12" x2="19" y2="12"/><polyline points="12 5 19 12 12 19"/></svg>',
  back:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><line x1="19" y1="12" x2="5" y2="12"/><polyline points="12 19 5 12 12 5"/></svg>',
  user:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>',
  logout:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16 17 21 12 16 7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>',
  pin:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"/><circle cx="12" cy="10" r="3"/></svg>',
  clock:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><circle cx="12" cy="12" r="10"/><polyline points="12 6 12 12 16 14"/></svg>',
  cal:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>',
  star:'<svg viewBox="0 0 24 24" fill="currentColor" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/></svg>',
  starOutline:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"/></svg>',
  ticket:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M2 9a3 3 0 0 1 0 6v2a2 2 0 0 0 2 2h16a2 2 0 0 0 2-2v-2a3 3 0 0 1 0-6V7a2 2 0 0 0-2-2H4a2 2 0 0 0-2 2z"/><line x1="13" y1="5" x2="13" y2="19"/></svg>',
  play:'<svg viewBox="0 0 24 24" fill="currentColor" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><polygon points="5 3 19 12 5 21 5 3"/></svg>',
  ig:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><rect x="2" y="2" width="20" height="20" rx="5"/><path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"/><line x1="17.5" y1="6.5" x2="17.51" y2="6.5"/></svg>',
  fb:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"/></svg>',
  tw:'<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"><path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"/></svg>',
};

// --- Auth (localStorage) ---
const AUTH_KEY = 'egycinema:user';
const Auth = {
  current(){ try { return JSON.parse(localStorage.getItem(AUTH_KEY)); } catch { return null; } },
  signup(email, password){
    const users = JSON.parse(localStorage.getItem('egycinema:users') || '{}');
    if(users[email]) throw new Error('That email is already registered. Try logging in.');
    users[email] = password;
    localStorage.setItem('egycinema:users', JSON.stringify(users));
    localStorage.setItem(AUTH_KEY, JSON.stringify({ email }));
  },
  login(email, password){
    const users = JSON.parse(localStorage.getItem('egycinema:users') || '{}');
    if(!users[email] || users[email] !== password) throw new Error('Wrong email or password.');
    localStorage.setItem(AUTH_KEY, JSON.stringify({ email }));
  },
  logout(){ localStorage.removeItem(AUTH_KEY); },
};

// --- Favorites (per user, fallback to guest) ---
function favKey(){
  const u = Auth.current();
  return 'egycinema:favs:' + (u ? u.email : 'guest');
}
const Favs = {
  all(){ try { return JSON.parse(localStorage.getItem(favKey())) || []; } catch { return []; } },
  has(id){ return Favs.all().includes(id); },
  toggle(id){
    const list = Favs.all();
    const i = list.indexOf(id);
    if(i >= 0) list.splice(i,1); else list.push(id);
    localStorage.setItem(favKey(), JSON.stringify(list));
    return i < 0; // true = now favorited
  },
};

// --- Showtimes (deterministic per movie+cinema) ---
function _hash(str){ let h = 0; for(let i=0;i<str.length;i++){ h = ((h<<5)-h) + str.charCodeAt(i); h |= 0; } return Math.abs(h); }
function getShowtimes(movieId, cinemaId){
  const seed = _hash(movieId + '|' + cinemaId);
  const today = new Date(); today.setHours(0,0,0,0);
  const days = [0,1,2].map(d => { const dt = new Date(today); dt.setDate(today.getDate()+d); return dt; });
  const baseTimes = ['12:30','15:45','18:30','21:15','23:45'];
  return days.map((dt, i) => {
    const count = 2 + ((seed >> i) % 3); // 2..4 times
    const times = [];
    for(let k=0;k<count;k++) times.push(baseTimes[(seed + i*7 + k*3) % baseTimes.length]);
    const uniq = [...new Set(times)].sort();
    return {
      date: dt,
      label: i===0 ? 'Today' : i===1 ? 'Tomorrow' : dt.toLocaleDateString(undefined,{weekday:'short', month:'short', day:'numeric'}),
      iso: dt.toISOString().slice(0,10),
      times: uniq,
    };
  });
}

  // Book ticket
  function bookTicket(movieId, cinemaId, dateLabel, time) {
    if (!Auth.current()) {
        toast('Please log in to book', 'error');
        setTimeout(() => {
            location.href = 'login.html?next=' + encodeURIComponent(location.pathname + location.search);
        }, 700);
        return;
    }

    const url = `book.html?movie=${movieId}&cinema=${cinemaId}&date=${dateLabel}&time=${time}`;
    window.location.href = url;
}




// --- Toast ---
function toast(message, type='success'){
  const el = document.createElement('div');
  el.className = `toast ${type}`;
  el.textContent = message;
  document.body.appendChild(el);
  requestAnimationFrame(() => el.classList.add('show'));
  setTimeout(() => { el.classList.remove('show'); setTimeout(() => el.remove(), 300); }, 2400);
}

// --- Favorite button helper ---
function favButtonHTML(movieId, opts={}){
  const active = Favs.has(movieId);
  const cls = opts.size === 'lg' ? 'fav-btn fav-btn-lg' : 'fav-btn';
  return `<button type="button" class="${cls} ${active?'is-active':''}" data-fav="${movieId}" aria-label="Toggle favorite" aria-pressed="${active}">${ICONS.star}</button>`;
}
// Delegate clicks
document.addEventListener('click', (e) => {
  const btn = e.target.closest('[data-fav]');
  if(!btn) return;
  e.preventDefault(); e.stopPropagation();
  const id = btn.getAttribute('data-fav');
  const nowFav = Favs.toggle(id);
  document.querySelectorAll(`[data-fav="${id}"]`).forEach(b => {
    b.classList.toggle('is-active', nowFav);
    b.setAttribute('aria-pressed', String(nowFav));
  });
  const m = (typeof getMovie === 'function') ? getMovie(id) : null;
  toast(nowFav ? `Added ${m?m.title:'movie'} to favorites` : `Removed ${m?m.title:'movie'} from favorites`);
});

// Delegate Bookings
document.addEventListener('click', (e) => {
  const bookBtn = e.target.closest('[data-book]');
  if (!bookBtn) return;
  
  e.preventDefault();
  
  try {
    const data = JSON.parse(bookBtn.getAttribute('data-book'));
    if (!Auth.current()) {
      toast('Please log in to book', 'error');
      setTimeout(() => location.href = 'login.html', 700);
      return;
    }
    
    location.href = `book.html?movie=${data.m}&cinema=${data.c}&date=${data.d}&time=${data.t}`;
  } catch (err) {
  }
});

// --- Header ---
function renderHeader(){
  const u = Auth.current();
  return `
  <header class="site-header">
    <div class="container">
      <a href="home.html" class="brand">
        <span class="brand-mark">${ICONS.film}</span>
        <span class="brand-name">Egy<span class="text-gradient-gold">Cinema</span></span>
      </a>
      <nav class="nav">
        <a href="home.html">Now Showing</a>
        <a href="home.html#cinemas">Cinemas</a>
        <a href="favorites.html">Favorites</a>
        <a href="home.html#footer">About</a>
      </nav>
      <div class="header-actions">
        ${u ? `
          <div class="user-email"><span class="text-gold icon">${ICONS.user}</span><span>${u.email}</span></div>
          <button class="btn btn-ghost btn-sm" id="logoutBtn" aria-label="Sign out"><span class="icon">${ICONS.logout}</span></button>
        ` : `
          <a class="btn btn-ghost btn-sm" href="login.html">Login</a>
          <a class="btn btn-hero btn-sm" href="signup.html">Sign up</a>
        `}
      </div>
    </div>
  </header>`;
}

function renderFooter(){
  return `
  <footer id="footer" class="site-footer">
    <div class="container footer-grid">
      <div>
        <div class="brand" style="margin-bottom:.75rem">
          <span class="brand-mark" style="height:2rem;width:2rem">${ICONS.film}</span>
          <span class="brand-name" style="font-size:1.1rem">Egy<span class="text-gradient-gold">Cinema</span></span>
        </div>
        <p class="text-muted" style="font-size:.9rem;max-width:28rem">
          Egypt's premier destination to discover the latest movies and book tickets across the country's top cinemas.
        </p>
      </div>
      <div>
        <div class="footer-h">Explore</div>
        <ul class="footer-list">
          <li><a href="home.html#cinemas">Cinemas</a></li>
          <li><a href="home.html">Now Showing</a></li>
          <li><a href="favorites.html">Favorites</a></li>
        </ul>
      </div>
      <div>
        <div class="footer-h">Follow</div>
        <div class="socials">
          <a class="social" href="#" aria-label="Instagram">${ICONS.ig}</a>
          <a class="social" href="#" aria-label="Facebook">${ICONS.fb}</a>
          <a class="social" href="#" aria-label="Twitter">${ICONS.tw}</a>
        </div>
      </div>
    </div>
    <div class="copyright">© ${new Date().getFullYear()} EgyCinema. Crafted for movie lovers in Egypt.</div>
  </footer>`;
}

function mountChrome(){
  const h = document.getElementById('header-slot');
  const f = document.getElementById('footer-slot');
  if(h) h.innerHTML = renderHeader();
  if(f) f.innerHTML = renderFooter();
  const lo = document.getElementById('logoutBtn');
  if(lo) lo.addEventListener('click', () => { Auth.logout(); toast('Signed out'); setTimeout(()=>location.href='index.html', 400); });
}
/**
 * Cinema System - Admin Controller Logic
 * Handles UI interactions, safety checks, and navigation
 */

document.addEventListener('DOMContentLoaded', () => {
    console.log("Admin System Initialized...");

    // 1. AUTOMATIC SIDEBAR ACTIVE STATE
    // Checks the current URL and highlights the correct sidebar link
    const currentPath = window.location.pathname.split("/").pop();
    const navItems = document.querySelectorAll('.nav-item');
    
    navItems.forEach(item => {
        if (item.getAttribute('href') === currentPath) {
            item.classList.add('active');
        } else {
            item.classList.remove('active');
        }
    });

    // 2. DELETE SAFETY PROTOCOL (DELETE /anything)
    // Prevents accidental clicks on destructive admin actions
    const deleteButtons = document.querySelectorAll('.btn-ghost[style*="color:var(--primary)"]');
    
    deleteButtons.forEach(btn => {
        btn.addEventListener('click', (e) => {
            const confirmed = confirm("⚠️ WARNING: This action is permanent. Are you sure you want to delete this item?");
            if (!confirmed) {
                e.preventDefault();
            } else {
                console.log("Proceeding with DELETE request...");
                // Here is where you would call your DELETE /anything API
            }
        });
    });

    // 3. FORM SUBMISSION (POST /employees, /cinemas, /movies)
    const adminForms = document.querySelectorAll('form');
    adminForms.forEach(form => {
        form.addEventListener('submit', (e) => {
            // Optional: Add a loading state to the button
            const submitBtn = form.querySelector('.btn-hero');
            if (submitBtn) {
                submitBtn.innerHTML = "Processing...";
                submitBtn.style.opacity = "0.7";
                submitBtn.disabled = true;
            }
        });
    });

    // 4. TOAST NOTIFICATIONS (Reusing your .toast CSS)
    window.showToast = (message, type = 'success') => {
        const toast = document.createElement('div');
        toast.className = `toast show ${type}`;
        toast.innerText = message;
        document.body.appendChild(toast);

        setTimeout(() => {
            toast.classList.remove('show');
            setTimeout(() => toast.remove(), 300);
        }, 3000);
    };
});
// Replace this URL with your actual AWS API Gateway or Backend URL
// Change this to your Spring Boot server's address
// Example: 'http://123.45.67.89:8080/api/users' 
const API_URL = 'http://localhost:8080/api/users'; 

async function fetchUsers() {
    const tableBody = document.getElementById('user-table-body');
    
    try {
        const response = await fetch(API_URL);
        
        if (!response.ok) throw new Error('Network response was not ok');
        
        const users = await response.json(); 

        tableBody.innerHTML = '';

        users.forEach(user => {
            const row = document.createElement('tr');
            row.style.borderBottom = '1px solid var(--border)';

            // Requirement: if no cinema assigned, show "-"
            const cinemaDisplay = user.assignedCinema ? user.assignedCinema : '-';

            row.innerHTML = `
                <td style="padding: 1.5rem 1.2rem;">
                    <div style="font-weight: 600;">${user.name}</div>
                    <div class="text-muted" style="font-size: 0.8rem;">${user.email}</div>
                </td>
                <td style="padding: 1.2rem;">
                    <span style="color: var(--muted); font-size: 0.8rem; letter-spacing: 1px;">${user.role}</span>
                </td>
                <td style="padding: 1.2rem;" class="text-muted">
                    ${cinemaDisplay}
                </td>
                <td style="padding: 1.2rem; text-align: right;">
                    <button class="btn btn-outline btn-sm">Manage</button>
                </td>
            `;
            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error('Fetch error:', error);
        tableBody.innerHTML = `<tr><td colspan="4" style="padding:2rem; text-align:center; color:var(--primary);">Cannot connect to Spring Boot Server.</td></tr>`;
    }
}