async function loadMovies(query = '') {
    try {
        const response = await fetch(`http://localhost:8080/get?title=${query}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json"
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const movies = await response.json();
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
                <img src="${movie.posterUrl}" alt="${movie.title}">
                <h3>${movie.title}</h3>
                <p>${movie.description}</p>
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
