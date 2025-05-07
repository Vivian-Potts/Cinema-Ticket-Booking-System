async function loadMovies(query = '') {
    try {
        const response = await fetch(`http://localhost:8080/get?title=${query}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        console.log('response: ', response);

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const movies = await response.json();
        console.log('movies:', movies);
        let movieContainer = document.getElementById('moviesList');
        movieContainer.innerHTML = ''; // Clear previous content

        if (movies.length === 0) {
            movieContainer.innerHTML = '<p>No movies found.</p>';
            return;
        }

        movies.forEach(movie => {
            let movieCard = document.createElement('div');
            movieCard.classList.add('movie-card');

            movieCard.innerHTML = `
        <img src="https://via.placeholder.com/150" alt="${movie.Title}">
        <h3>${movie.Title}</h3>
        <p>Released: ${movie.Released}</p>
        <button>Book Now</button>
    `;

            movieContainer.appendChild(movieCard);
        });


    } catch (error) {
        console.error("Error loading movies:", error);
        document.getElementById('moviesList').innerHTML = `<p style="color: red;">Failed to load movies. Check the console for details.</p>`;
    }
}

// Search function
function searchMovie() {
    let searchBox = document.getElementById('searchBox');
    loadMovies(searchBox.value);
}

// Load movies when the page opens
window.onload = () => loadMovies();
// Admin Login Modal Logic
const adminBtn = document.getElementById("adminLogin");
const modal = document.getElementById("adminModal");
const closeModal = document.getElementById("closeModal");
const loginBtn = document.getElementById("loginBtn");

adminBtn.addEventListener('click', () => {
    modal.classList.remove("hidden");
});

closeModal.addEventListener('click', () => {
    modal.classList.add("hidden");
});

loginBtn.addEventListener('click', () => {
    const user = document.getElementById("adminUser").value;
    const pass = document.getElementById("adminPass").value;

    if (user === "admin" && pass === "password") {
        alert("Login successful!");
        modal.classList.add("hidden");
        // Redirect or show admin dashboard
    } else {
        alert("Invalid credentials");
    }
});
// Top nav link functionality
document.querySelector("a[href='#']").addEventListener("click", (e) => {
    e.preventDefault();
    alert("Now Showing - Placeholder action");
});

document.querySelectorAll("nav ul li a")[1].addEventListener("click", (e) => {
    e.preventDefault();
    alert("Upcoming Movies - Placeholder action");
});

document.querySelectorAll("nav ul li a")[2].addEventListener("click", (e) => {
    e.preventDefault();
    alert("Book Tickets - Placeholder action");
});
